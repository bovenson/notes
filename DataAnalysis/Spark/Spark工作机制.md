# Spark工作机制

## 主要模块

-   调度与任务分配
-   I/O模块
-   通信控制模块
-   容错模块
-   Shuffle模块

## 调度层次

-   应用
-   作业
-   Stage
-   Task

## 调度算法

-   FIFO
-   FAIR(公平调度)

## Spark应用执行机制

### 总览

**Spark应用提交后经历了一系列的转换,最后成为Task在每个节点上执行.**

-   RDD的Action算子触发Job的提交,提交到Spark中的Job生成RDD DAG
-   由DAGScheduler转化为Stage Dage
-   每个Stage中产生相应的Task集合
-   TaskScheduler将任务分发到Executor执行

每个任务对应相应的一个数据块,使用用户定义的函数处理数据块.

Spark实现了分布式计算和任务处理,并实现了任务的分发,跟踪,执行等工作.最终聚合结果,完成Spark应用的计算.

对RDD的块管理通过BlockManger完成.BlockManager将数据抽象为数据块,在内存或者磁盘进行存储,如果数据不在本节点,则还可以通过远端节点复制到本机进行计算.

### Spark应用

#### 执行模式

-   Local
-   Standalone
-   YARN
-   Mesos
-   Cluster模式
-   Client模式

#### 应用的基本组件

-   **Application:**用户自定义的Spark程序,用户提交后,Spark为App分配资源,将程序转换并执行
-   **Driver Program:**运行Application的main()创建并创建SparkContext
-   **RDD Graph:**RDD是Spark的核心结构,当RDD遇到Action算子时,将之前的所有算子形成一个DAG,也就是RDD Graph.再在Spark中转化为Job,提交到集群执行.一个App中可以包含多个Job.
-   **Job**:一个RDD Graph触发的作业,往往由Spark Action算子触发.在SparkContext中通过runJob方法向Saprk提交Job.
-   **Stage:**每个Job会根据RDD的宽依赖关系呗切分很多Stage,每个Stage中包含一组相同的Task,这一组Task也叫TaskSet.
-   **Task:**一个分区对应一个Task,Task执行RDD中对应Stage中包含的算子.Task被封装好后放入Executor的线程池中执行.