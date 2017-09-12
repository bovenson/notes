[TOC]

# RDD编程

**RDD** 弹性分布式数据集

## RDD基础

Spark中的RDD就是一个不可变的分布式对象集合. 每个RDD都被分为多个分区, 这些分区运行在集群中的不同节点上. RDD可以包含Python, Java, Scala中任意类型的对象, 甚至用户自定义对象.

### 创建RDD的途径

- 读取一个外部数据集
- 在驱动器程序里分发驱动器程序中的对象集合(比如 list和set)

### Spark程序工作步骤

- 从外部数据创建输入RDD
- 使用诸如`filter()`这样的转化操作对RDD进行转化,以定义新的RDD
- 告诉Spark对需要被重用的中间结果RDD执行`persist()`操作
- 使用行动操作(例如, `count()` 或 `first()`)来触发一次并行计算,Spark会对计算进行优化后在执行

### 一个简单的Spark程序解析

#### 创建RDD

使用`SparkContext.textFile()`来读取文本文件作为一个字符串RDD的示例

```
>>> pyspark
>>> lines = sc.textFile("README.md")	# "README.md文件应该放到Spark使用的持久化文件系统中, 例如hdfs"
```

创建出来后,RDD支持两种类型的操作: 转化操作(transformation)和行动操作(action). 转化操作会由一个RDD生成一个新的RDD.

#### 执行转化操作

调用转化操作`filter()`

```bash
>>> pythonLines = lines.filter(lambda line: "Python" in line)
```

#### 执行行动操作

行动操作(action)会对RDD计算出一个结果, 并把结果返回到驱动器程序中, 或把结果存储到外部存储系统(如HDFS)中.

*调用`first()`行动操作*

```bash
>>> pythonLines.first()
u'high-level APIs in Scala, Java, Python, and R, and an optimized engine that'
```

### 小结

- 转化操作和行动操作的区别在于Spark计算RDD的方式不同. 虽然可以随时定义新的RDD, 但Spark只会**惰性**计算这些RDD. 他们只有第一次在一个行动操作中用到时才会真正计算.
- 默认情况下, Spark的RDD会在每次对它们进行行动操作时重新计算. 如果想在多个行动操作中重用一个RDD, 可以使用`RDD.persist()`把这个RDD缓存下来.