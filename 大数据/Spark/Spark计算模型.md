[TOC]

# Spark计算模型

## Spark程序模型

一个经典的示例模型

-   `SparkContext`中的`textFile`函数从HDFS读取日志文件,输出变量`file`

    ```scala
    var file = sc.textFile("hdfs://***")
    ```

-   RDD中的`filter`函数过滤带有'ERROR'的行,输出`errors`(一个RDD)

    ```scala
    var errors = file.filter(line=>line.contains("ERROR"))
    ```

-   RDD中的`count`函数返回"ERROR"的行数:`errors.count()`

### 小结

-   用户程序对RDD通过多个函数进行操作,将RDD进行转换
-   BlockManager管理RDD的物理分区,每个Block节点上对应一个数据块,可以存储在内存中或者磁盘
-   RDD中的`partition`是一个逻辑数据块,对应相应的物理块Block
-   一个RDD在代码中相当于是数据的一个元数据结构,存储着数据分区及其逻辑结构映射关系,存储着RDD之前的依赖转换关系

## 弹性分布式数据集(resilient distributed dataset,RDD)

### RDD简介

-   它是一个分布式数据架构,是逻辑集中的实体,在集群中的多台机器上进行了数据分区
-   RDD是Spark的核心数据结构,通过RDD的依赖关系形成了Spark的调度顺序
-   通过对RDD的操作形成整个Spark程序

#### RDD的两种创建方式

-   从Hadoop文件系统(或其他与Hadoop兼容的持久化存储系统,如Hive,Cassandra,Hbase...)输入创建
-   从父RDD转换得到新的RDD

#### RDD的两种操作算子

RDD有两种计算操作算子:**Transformation(转换)**和**Action(行动)** 

##### Transformation(转换)

Transformation是延迟计算的,也就是说从一个RDD转换成另一个RDD的转换操作不是马上执行的,需要等到有Actions操作时,才真正触发

##### Action(行动)

Action算子会触发Spark提交作业,并将数据输出到Spark系统

#### RDD的重要内部属性

-   分区列表
-   计算每个分片的函数
-   对父RDD的依赖列表
-   对Key-Value对数据类型RDD的分区器,控制分区策略和分区数
-   每个数据分区的地址列表(如HDFS上的数据块的地址)

### RDD与DSM的异同

#### DSM

-   DSM(distributed shared memory, 分布式共享内存).
-   在DSM系统中,应用可以向全局地址空间的任意位置进行读写操作
-   DSM是一种通用的内存数据抽象

#### RDD与DSM的区别

-   RDD不仅可以通过批量转换创建RDD,还可以对任意内存位置读写
-   RDD限制应用执行批量写操作,这样有利于实现有效的容错

### Spark的数据存储

-   核心是RDD
-   RDD可被抽象为一个大的数组,但是这个数组是分布在集群上的
-   逻辑上RDD的每个分区叫一个Partition
-   RDD对象实质上是一个元数据结构,存储着Block,Node等的映射关系
-   一个RDD就是一组分区,在物理数据存储上,RDD的每个分区对应的就是一个Block,Block可以存储在内存,也可以缓存在磁盘上
-   每个Block中存储着RDD所有数据项的一个子集,暴露给用户的可以是一个Block的迭代器也可以是一个数据项
-   HDFS中的一个分区对应Spark的一个分区
-   支持Hash分区和Range分区

## 算子的分类及功能

### 算子的分类

-   Value数据类型的Transformation算子

    这种变换并不触发提交作业,只对处理的数据项是Value型的数据

-   Key-Value性Transformation算子

    这种变换不触发提交作业,针对处理的数据项是Key-Value型的数据对

-   Action算子,这类算子触发SparkContext提交Job作业

### Value型Transformation算子

根据RDD变换算子的输入分区和输出分区关系分为以下几种类型.

#### 输入分区和输出分区一对一型

##### map

将原来RDD的每个数据项通过map中的用户自定义函数f映射转变为一个新的元素.源码中的map算子相当于初始化一个RDD,新RDD叫做`MappedRDD(this, sc.clean(f))`

##### flatMap

将原来RDD中的每个元素通过函数f转换为新的元素,并将生成的RDD的每个集合中的元素合并为一个集合.内部创建`FlatMappedRDD(this, sc.clean(f))`

##### mapPartitions

`mapPartitions`函数获取到每个分区的迭代器,在函数中通过这个分区整体的迭代器对整个分区的元素进行操作.内部实现是生成`MapPartitionsRDD`.

##### glom

`glom`函数将每个分区形成一个数组,内部实现是返回的`GlommeRDD`.

#### 输入分区和输出分区多对一型

##### union

使用`union`函数时需要保证两个RDD元素的数据类型相同,返回的RDD数据类型和被合并的RDD元素数据类型相同,并不进行去重操作,保存所有元素.

如果想去重可以适用`distinct()`.`++`符号相当于`union`函数操作 

##### cartesian

对两个RDD内的所有元素进行笛卡尔积操作.操作后,内部实现返回`CartesianRDD.

#### 输入分区与输出分区多对多型

##### groupBy

将元素通过函数生成相应的Key,数据就转化为Key-Value格式,之后将Key相同的元素分为一组.

函数实现如下:

-   `sc.clean()`函数将用户函数预处理:

    `var cleanF = sc.clean(f)`

-   对数据map进行函数操作,最后再对groupByKey进行分组操作

    `this.map(t=>(cleanF(t), t)).groupByKey(p)`

    其中,p中确定了分区个数和分区函数,也就决定了并行化的程度.

#### 输出分区为输入分区子集型

##### filter

`filter`的功能是对元素进行过滤,对每个元素应用f函数,返回值为true的元素在RDD中保留,返回false的将过滤掉.内部实现相当于生成`FilteredRDD(this, sc.clean(f))`.

下面代码是函数的本质实现:

`def filter(f:T=>Boolean):RDD[T]=new FilteredRDD(this,sc.clean(f))`

##### distinct

`distinct`将RDD中的元素进行去重操作.

##### subtract

`subtract`相当于进行集合的差操作.RDD1去除RDD1和RDD2交集中的所有元素.

##### sample

`sample`将RDD这个集合内的元素进行采样,获取所有元素的子集.用户可以设定是否有放回的抽样,百分比,随机种子,进而决定采样方式.

内部实现是生成`SampledRDD(withReplacement, fraction, seed)`

-   withReplacement:是否有放回的抽样.=true为有放回抽样
-   fraction:采样百分比,0-1
-   seed:随机种子

#####　takeSample

`takeSample()`函数和上面的`sample`函数是一个原理,但是不使用相对比例采样,而是按设定的采样个数进行采样,同时返回结果不再是RDD,而是相当于对采样后的数据进行Collect(),返回结果的集合为单机的数组.

#### Cache型

##### cache

`cache`将RDD元素从磁盘缓存到内存,相当于`persist(MEMORY_ONLY`函数的功能.

#####　persist

`persist`函数对RDD进行缓存操作,数据缓存在哪里由`StorageLevel`枚举类型确定.有以下几种类型的组合,DISK代表磁盘,MEMORY代表内存,SER代表数据是否进行序列化存储.

下面为函数定义,StorageLevel是枚举类型,代表存储模式:

`persist(newLevel: StorageLevel)`

### Key-Value型Transformation算子

Transformation处理的数据为Key-Value形式的算子,大致分为一下3中类型.

#### 输入分区与输出分区一对一

##### mapValues

针对(Key, Value)型数据中的Value进行Map操作,而不对Key进行处理

#### 聚集

##### 对单个RDD聚集

###### combineByKey

定义combineByKey算子的代码如下:

```scala
combineByKey[C](createCombiner:(V)=>C,
mergeValue:(C, V) => C,
mergeCombiners: (C, C) => C,
partitioner: Partitioner
mapSideCombine: boolean = true,
serializer: Serializer = null): RDD[(K, C)]
```

**说明**

-   createCombiner:V => C, 在C不存在的情况下,如通过V创建seq C.
-   mergeValue: (C, V) => C, 在C已经存在的情况下,需要merge,如把item V加到seq C中,或者叠加
-   mergeCombiners: (C, C) => C, 合并两个C
-   partitioner: Partitioner(分区器),Shuffle时需要通过Partitioner的分区策略进行分区
-   mapSideCombine: Boolean=true, 为了减小传输量,很多combine可以在map端先做.
-   serializerClass: String=null, 传输需要序列化,用户可以自定义序列化类

例如相当于把(Int, Int)的RDD变成了(Int, Seq[Int])类型元素的RDD

###### reduceByKey

多个值合并成一个值(将相同Key的数据的value相加).

函数实现如下.

```scala
def reduceByKey(partitioner: Partitioner, func: (V, V)=>V): RDD[(K, V)] = {
  combineByKey[V]((v: V)=>v, func, func, partitioner)
}
```

###### partitionBy

`partitionBy`函数对RDD进行分区操作.

函数定义如下.

`partitionBy(partitioner: Partitioner)`

 如果原有RDD的分区器和现有分区器一致,则不重新分区,否则相当于根据分区器生成一个新的ShuffledRDD.

##### 对两个RDD进行聚集

###### cogroup

`cogroup`函数将两个RDD进行协同划分,`cogroup`函数的定义如下.

`cogroup[W](other: RDD[(K, W)], numPartitions: Int): RDD[(K, (Iterable[V], Iterable[W]))]`

对于两个RDD中的Key-Value类型的元素,每个RDD相同Key的元素分别聚合为一个集合,并且返回两个RDD中对应Key的元素集合的迭代器`(K, (Itetable[V], Iterable[W])`.其中Key和Value,Value是两个RDD想相同Key的两个数据集合的迭代器所构成的元组.

#### 连接

##### join

`join`对两个需要连接的RDD进行`cogroup`函数操作,`cogroup`操作之后形成的新RDD,对每个Key下的元素进行笛卡尔积操作,返回的结果再展平,对应Key下的所有元组形成一个集合,最后返回`RDD[(K, (V, W))]`

下面是`join`的函数实现,本质是通过`cogroup`算子先进行协同划分,再通过`flatMapValues`将合并的数据打散.

```scala
this.cogroup(other, partitioner).flatMapValues { case (vs, ws) =>
  for (v <- vs; w <- ws) yield (v, w) }
```

##### leftOutJoin和rightOutJoin

`leftOutJoin`和`rightOutJoin`相当于在`join`基础上先判断一侧的RDD元素是否为空,如果为空,则填充为空.如果不为空,则将数据进行连接运算.

下面代码是`leftOutJoin`的实现.

```scala
if (ws.isEmpty) {
  vs.map(v => (v, None))
} else {
  for (v <- vs; w <- ws) yield (v, Some(w))
}
```

### Actions算子

本质上在Actions算子中通过SparkContext执行提交作业的runJob操作,出发了RDD DAG的执行.

下面根据Action算子的输出空间将Action算子进行分类.

#### 无输出

##### foreach

对RDD中的每个元素对应用f函数操作,不返回RDD和Array,而是返回Uint

#### HDFS

##### saveAsTextFile

函数将数据输出,存储到HDFS的指定目录.

下面为函数的内部实现.

```scala
this.map(x => (NullWritable.get(), new Text(x.toString)))
  .saveAsHadoopFile[TextOutputFormat[NullWritable, Text]](path)
```

将RDD中的每个元素映射变为(Null, x.toString),然后再将其写入HDFS.

##### saveAsObjectFile

`saveAsObjectFile`函数将分区中的每10个元素组成一个Array,然后将这个Array序列化映射为`(Null, BytesWritable(Y))`的元素,写入HDFS为SequenceFile的格式.

下面是代码实现.

`map(x=>(NullWirtable.get(), new BytesWritable(Utils.serialize(x))))`

#### Scala集合和数据类型

##### collect

`collect`相当于`toArray`,将分布式的RDD返回为一个单机的scala Array数组.在这个数组上运用scala的函数式操作.

##### collectAsMap

`collectAsMap`函数对(K, V)型的RDD数据返回一个单机HashMap.对于重复K的RDD元素,后面的元素覆盖前面的元素 .

##### reduceByKeyLocally

实现的是先reduce再collectAsMap的功能,相对RDD的整体进行reduce操作,然后再收集所有结果返回为一个HashMap.

##### lookup

下面代码是`lookup`的声明.

`lookup(key: K): Seq[V]`

Lookup函数对(Key, Value)型的RDD操作,返回指定Key对应的元素形成的Seq.这个函数处理优化的部分在于,如果这个RDD包含分区器,则只会对应处理K所在的分区,然后返回由(K, V)形成的Seq.如果RDD不包含分区器,则需要对全RDD元素进行暴力扫描处理,搜索指定K对应的元素.

##### count

`count`返回真个RDD的元素个数.内部函数实现如下.

`def count(): Long=sc.runJob(this, Utils.getIteratorSize_).sum`

##### top

`top`可返回最大的K个元素.函数定义如下.

`top(num: Int)(implicit ord: Ordering[T]): Array[T]`

**相近函数说明**

| 函数          | 说明                                       |
| ----------- | ---------------------------------------- |
| top         | 返回最大的k个元素                                |
| take        | 返回最小的K个元素                                |
| takeOrdered | 返回最小的K个元素,并且在返回的数组中保持元素的顺序               |
| first       | 相当于top(1)返回整个RDD中的前k个元素,可以定义排序的方式Ordering[T].返回的是一个数组 |

##### reduce

`reduce`函数相当于对RDD中的元素进行`reduceLeft`函数的操作.函数定义如下.

`Some(iter.reduceLeft(cleanF))`

`reduceLeft`先对两个元素<K,V>进行`reduce`函数操作,然后将结果和迭代器取出的下一个元素<K, V>进行`reduce`函数操作,直到迭代器遍历完所有元素,得到最后结果.

在RDD中先对每个分区中的所有元素<K, V>的集合分别进行`reduceLeft`.每个分区形成的结果相当于一个元素<K, V>,再对这个结果集合进行`reduceLeft`操作.

##### fold

`fold`和`reduce`的原理相同,但是于`reduce`不同,相当于每个`reduce`时,迭代器取的第一个元素是`zeroValue`

##### aggregate

`aggregate`先对每个分区的所有元素进行`aggregate`操作,再对分区的结果进行`fold`操作.

`aggreagate`与`fold`和`reduce`的不同之处在于,`aggreagate`相当于采用归并的方式进行数据聚集,这种聚集是并行化的.而在`fold`和`reduce`函数的运算过程中,每个分区中需要进行串行处理,每个分区串行计算完结果,结果再按之前的方式进行聚集,并返回最终聚集结果.

函数定义如下.

```scala
aggregate[B](z: B)(seqop: (B, A)=>B, combop: (B, B) => B): B
```

