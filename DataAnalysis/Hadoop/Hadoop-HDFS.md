# HDFS

## HDFS的设计

- HDFS 以流式数据访问模式来储存超大文件
- **HDFS是为高数据吞吐量应用优化的,这可能会以提高时间延迟为代价**  对于低延迟的访问需求,HBase是更好的选择
- **namenode将文件系统的元数据储存在内存中,因此该文件系统所能储存的文件总数受限于namenode的内存容量**

## HDFS的概念

### 数据块

- **每个磁盘都有默认的数据块大小,这是磁盘进行数据读写的最小单位**

- HDFS也有块的概念,默认为**64MB** (HDFS的块比磁盘的块大的目的是为了最小化寻址开销)

- 使用如下命令查看块的信息: 

  `hadoop fsck / =files -blocks`

### namenode和datanode

**HDFS用两类节点以管理者-工作者模式运行,即一类为namenode(管理者), 一类为datanode(工作者)**

#### namenode

- namenode管理文件系统的命名空间,维护着文件系统树及整棵树内所有的文件和目录
- namenode也记录着每个文件中各个块所在的数据节点信息(这些信息会在系统启动时由数据节点重建)

#### datanode

- datanode是文件系统的工作节点
- 根据需要储存并检索数据块
- 定期向namenode发送它们所储存的块的列表


#### namenode容错机制

1. 备份那些组成文件系统元数据持久状态的文件.Hadoop可以通过配置使namenode在多个文件系统上保存元数据的持久状态,这些写操作是是是同步的,是原子操作.
2. 另一种可行的方法是运行一个辅助namenode.


### HDFS中的文件访问权限

**与POSIX非常相似,有三种权限**

- 只读权限(r)
- 写入权限(w)
- 可执行权限(x)

**每个文件和目录都有所属用户(owner),组别(group)和模式(mode)**

## Hadoop文件系统

**Hadoop有一个抽象的文件系统概念,HDFS是指其中的一个实现**

Java抽象类org.apache.hadoop.fs.FileSystem定义了Hadoop中的一个文件系统接口



