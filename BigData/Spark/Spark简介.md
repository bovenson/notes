# Spark

## Spark是什么

-   Spark是基于内存计算的大数据并行计算框架
-   Spark是MapReduce的替代方案

## Spark与Hadoop

Spark是一个计算框架,而Hadoop中包含计算框架MapReduce和分布式文件系统HDFS,Hadoop更广泛地说还包括在其生态系统上的其他系统.

## Spark的优势

### 中间结果输出

-   基于MapReduce的计算引擎通常会将中间结果输出到磁盘上,进行储存和容错.当一些查询翻译到MapReduce任务是,往往会产生多个Stage,而这些串联的Stage又依赖于底层的文件系统来存储每一个Stage的输出结果.
-   Spark将执行模型抽象为通用的有向无环图执行计划(Directed Acyclic Graph,DAG).这可以将多个Stage的任务串联或者并行执行,而无须将Stage中间结果输出到HDFS中.类似的引擎包括Dryad,Tez

### 数据格式和内存布局

Spark抽象出分布式内存储存结构弹性分布式数据集RDD,进行数据存储.RDD能支持粗粒度写操作,但对于读取操作,RDD可以精确到每条记录,这使得RDD可以用来作为分布式索引.

### 执行策略

MapReduce在数据Shuffle之前花费了大量的时间来排序,Spark任务在Shuffle中不是所有情景都需要排序.所以支持Hash的分布式聚合,调度中采用更为通用的任务执行计划图(DAG),每一轮次的输出结果在内存中缓存.

### 任务调度的开销

-   传统的MapReduce系统,如Hadoop,是为了运行常达数小时的批量作业而设计的
-   Spark采用了事件驱动的类库AKKA来启动任务,通过线程池复用线程来避免进程或线程启动和切换开销

## Spark能带来什么

### 打造全栈多计算范式的高效数据流水线

Spark支持复杂查询(SQL查询,流式计算,机器学习和图算法)

### 轻量级快速处理

-   这是由于Scala语言的简洁和丰富的表达力
-   Spark充分利用和集成Hadoop等其他第三方组件
-   Spark通过将中间结果缓存在内存减少磁盘I/O来达到性能的提升

### 易于使用,Spark支持多语言

-   Spark支持通过Scala,Java,Python编写程序
-   同事允许在Shell中进行交互计算
-   用户可以利用Spark像书写单机程序一样编写分布式程序

### 与HDFS等存储层兼容

-   Spark可以独立运行,除了可以运行在当下的YARN等集群管系统之外,它还可以读取已有的任何Hadoop数据
-   它可以运行在任何Hadoop数据源上,比如Hive,HBase,HDFS等

## Spark的局限性

-   RDD模型适合的是粗粒度的全局数据并行计算,不适合细粒度的,需要异步更新的计算
-   如果针对特定工作负载达到最优性能,还是需要使用一些其他的大数据系统

## Spark生态系统BDAS

### Spark

-   Spark是整个BDAS的核心组件,是一个大数据分布式编程框架,不仅实现了MapReduce的算子map函数和reduce函数的计算模型,还提供了更为丰富的算子,如filter,join,groupByKey等.
-   Spark将分布式数据集(RDD),实现了应用任务调度,RPC,序列化和压缩,并为运行在其上的上层组件提供API.
-   底层采用Scala这种函数式语言书写成
-   所提供的API深度借鉴Scala函数式的编程思想,提供与Scala类似的编程接口
-   Spark将数据在分布式环境下分区,然后将作业转化为有向无环图(DAG),并分阶段进行DAG的调度和任务的分布式并行处理

#### Shark

-   Shark是构建在Spark和Hive基础上的数据仓库
-   Shark已经完成学术使命,终止开发,但其架构和原理仍具有借鉴意义
-   提供了能够查询Hive中所储存数据的一套SQL接口,兼容现有的Hive QL语法
-   熟悉Hive QL和SQL的用户可以基于Shark进行快速的Ad-Hoc,Reporting等类型的SQL查询
-   Shark底层复用Hive的解析器,优化器以及元数据存储和序列化接口
-   Shark将Hive QL编译转化为一组Spark任务,进行分布式运算

#### Spark SQL

-   Spark SQL提供在大数据上的SQL查询功能
-   Spark SQL使用Catalyst做查询解析和优化器,并在底层使用Spark作为执行引擎实现的SQL的Operator
-   用户可以在Spark上直接书写SQL

#### Spark Streaming

-   Spark Streaming通过将流数据按指定时间片累积为RDD,然后将RDD进行批处理,进而实现大规模的流数据处理

#### GraphX

-   GraphX基于BSP模型,在Spark之上,封装类似Pregel的接口,进而大规模同步全局的图计算
-   当用户进行多轮迭代时,基于Spark内存计算的优势尤为明显

#### Tachyon

-   Tachyon是一个分布式内存文件系统,可以理解为内存中的HDFS
-   为了提供更高的性能,将数据存储剥离Java Heap
-   用户可以通过Tachyon实现RDD或者文件的跨应用共享,并提高容错机制,保证数据的可靠性

#### Mesos

-   Mesos是一个资源管理框架
-   提供类似于YARN的功能
-   用户可以在其中插件式地运行Spark,MapReduce,Tez等计算框架任务
-   Mesos对资源和任务进行隔离,并实现高效的资源任务调度

#### BlinkDB

-   BlinkDB是一个用于在海量数据上进行交互式SQL的近似查询引擎
-   允许用户通过查询准确性和查询时间之间做出权衡,完成近似查询
-   核心思想:通过一个自适应优化框架,随着时间的推移,从原始数据建立并维护一组多维样本,通过一个动态样本选择策略,选择一个适当大小的示例,然后基于查询的准确性和响应时间满足用户查询需求

## Spark架构

-   Spark是整个BDAS的核心,各个组件通过Spark来实现对分布式并行任务处理的程序支持

### Spark代码结构

| Spark Core |                                          |
| ---------- | ---------------------------------------- |
| 名称         | 说明                                       |
| scheduler  | 文件夹中含有负责整体的Spark应用,任务调度的代码               |
| broadcast  | 含有Broadcast(广播变量)的实现代码,API中是Java和Python API的实现 |
| deploy     | 含有Spark部署与启动运行的代码                        |
| common     | 不是一个文件夹,而是代表Spark通用的类和逻辑实现               |
| metrics    | 是运行时状态监控逻辑代码,Executor中含有Worker节点负责计算的逻辑代码 |
| partial    | 含有近似评估代码                                 |
| network    | 含有集群通信模块代码                               |
| serializer | 含有存储模块的代码                                |
| storage    | 含有存储模块的代码                                |
| ui         | 含有监控界面的代码逻辑,其他的代码模块分别是对Spark生态系统中其他组件的实现 |

| 其他          |                      |
| ----------- | -------------------- |
| 名称          | 说明                   |
| streaming   | Spark Streaming的实现代码 |
| YARN        | Spark on YARN的部分实现代码 |
| praphx      | 含有GraphX实现代码         |
| interpreter | 交互式Shell代码           |
| mllib       | 代表MLlib算法实现的代码       |
| sql         | 代表Sqark SQL的代码       |

### Spark的架构

-   Spark架构采用了分布式计算中的Master-Slave模型.Master是对应集群中的含有Master进程的节点,Slave是集群中含有Worker进程的节点
    -   Master作为整个集群的控制器,负责整个集群的正常运行
    -   Worker相当于是计算节点,接受主节点命令与进行状态汇报
    -   Executer负责任务的执行
    -   Client作为用户的客户端,负责提交应用
    -   Driver负责控制一个应用的执行
-   Spark集群部署后,需要在主节点和从节点分别启动Master进程和Worker进程,对整个集群进行控制
-   在一个Spark应用的执行过程中,Driver和Worker是两个重要角色
    -   Driver程序是应用逻辑执行的起点,负责作业的调度,即执行Task任务的分发
    -   多个Worker用来管理计算节点和创建Executor并行处理任务
    -   在执行阶段,Driver会将Task和Task所依赖的file和jar序列化后传递给对应的Worker机器,同时Executor对相应数据分区的任务进行处理

####  Spark架构中的基本组件

| 组件名            | 介绍                                       |
| -------------- | ---------------------------------------- |
| ClusterManager | 在Standalone模式中即为Master(主节点),控制整个集群,监控Master.在YARN中为资源管理器 |
| Worker         | 从节点,负责控制计算节点.启动Executor或Driver.在YARN模式中为NodeManager,负责计算节点的控制 |
| Driver         | 运行Application的main()函数并创建SparkContext    |
| Executor       | 执行器,在worker node上执行任务的组件,用于启动线程池运行任务.每个Application拥有独立的一组Executors |
| SparkContext   | 整个应用的上下文,控制应用的生命周期                       |
| RDD            | Spark的基本计算单元,一组RDD可形成执行的有向无环图RDD Graph   |
| DAG Scheduler  | 根据作业构建基于Stage的DAG,并提交Stage给TaskScheduler |
| TaskScheduler  | 将任务分发给Executor执行                         |
| SparkEnv       | 线程级别的上下文,存储运行时的重要组件的引用                   |

**SparkEnv内创建并包含如下一些重要组件的引用**

| SparkEnv内创建并包含的重要组件的引用 |                  |
| ---------------------- | ---------------- |
| 组件                     | 介绍               |
| MapOutPutTracker       | 负责Shuffle元信息的存储  |
| BroadcastManager       | 负责广播变量的控制与云信息的存储 |
| BlockManager           | 负责存储管理,创建和查找块    |
| MetricsSystem          | 监控运行时性能指标信息      |
| SparkConf              | 负责存储配置信息         |

#### Spark的整体流程

1.  Client提交应用
2.  Master找到一个Worker启动Driver
3.  Driver向Master或者资源管理器申请资源,之后将应用转化为RDD Grath
4.  DAGScheduler将RDD Graph转化为Stage的有向无环图提交给TaskScheduler
5.  TaskScheduler提交任务给Executor执行
6.  任务执行过程中,其他组件协同工作

### Spark运行逻辑

-   在Spark应用中,整个执行流程在逻辑上会形成有向无环图.Action算子触发之后,将所有累积的算子形成一个有向无环图.
-   Action算子触发之后,将所有累积的算子形成一个有向无环图,然后由调度器调度该图上的任务进行运算
-   Spark根据RDD之间不同的依赖关系切分成不同的阶段,一个阶段包含一系列函数执行流水线

