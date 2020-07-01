# Hadoop权威指南:HDFS-数据流

[TOC]

## 剖析文件读取

1. 客户端通过调用`FileSystem`对象的`open()`方法来打开希望读取的文件,对于HDFS来说, 这个对象是分布式文件系统的一个实例

2. `DistributedFiltSystem`通过使用RPC来调用`namenode`,以确定文件起始块的位置. 

   - 对于每一个块,`namenode`返回存有该块副本的`datanode` 的地址,**这些datanode根据它们与客户端的距离来排序(根据集群的网络拓扑)**
   - `DistributedFileSystem`类返回一个`FSDataInputStream`对象(一个支持文件定位的输入流)给客户端并读取数据
   - `FSDataInputStream` 类转而封装 `DFSInputStream` 对象, 该对象管理着 `datanode` 和 `namenode` 的 I/O操作.

3. 接着, 客户端对这个输入流调用read()方法

4. 存储着文件起始几个块的`datanode`地址的`DFSInputStream`随即连接距离最近的`datanode`, 通过对数据流反复调用`read()`方法, 可以将数据从`datanode`传输到客户端

5. 到达块的末端时,`DFSInputStream`关闭与该`datanode`的连接, 然后寻找下一个块的最佳`datanode`

   **客户端只需要读取连续的流,并且对于客户端都是透明的**

6. 客户端从流中读取数据时,块是按照打开`DFSInputStream`与`datanode`新建连接的顺序读取的.一旦客户端完成读取,就对`FSDataInputStream`调用`cloase()`方法

### 几点说明

- 在读取数据的时候,如果`DFSInputStream`与`datanode`通讯时遇到错误,会尝试从这个块的另外一个最邻近`datanode`读取数据,也会记住那个故障`datanode`
- `namenode`告知客户端每个块中最佳的`datanode`, 并让客户端直接连接到该`datanode`检索数据
- `namenode`只需要相应块位置的请求(这些信息储存在内存中)



## 剖析文件写入

1. 客户端通过对`DistributedFileSystem`对象调用`create()`函数来新建文件

2. `DistributedFileSystem`对`namenode`创建一个RPC调用, 在文件系统的命名空间中新建一个文件, 此时该文件中还没有相应的数据块

3. `namenode`检查文件是否存在及客户端是否有该文件的权限

   - 检查通过,`namenode`就会为创建新文件记录一条记录
   - 否则,文件创建失败,并向客户端抛出一个`IOException`异常

4. `DistributedFileSystem`向客户端返回一个`FSDataOutputStream`对象,由此客户端可以开始写入数据

   **FSDataOutputStream封装一个DFSOutputStream对象, 该对象负责处理`datanode`和`namenode`之间的通讯**

5. 在客户端写入数据时,`DFSOutputStream`将它分成一个个的数据包, 并写入内部队列, 称为 "数据队列". `DataStreamer`处理数据队列,它的责任是根据`datanode`列表来要求`namenode`分配适合的新块来储存数据副本

6. 客户端完成数据的写入后, 对数据流调用`close()`方法,该操作将剩余的所有数据包写入`datanode`管线,并在联系到`namenode`且发送文件写入完成信号之前,等待确认

7. `namenode`已经知道文件由哪些块组成(通过`DataStreamer`请求分配数据块),所以它在返回成功前只需等待数据块进行最小量复制



## 一致模型

文件系统的一致模型(coherency model)描述了文件读写的数据可见性,HDFS为性能牺牲了一些POSIX要求

- 新建一个文件之后,能在文件系统的命名空间中立即可见, 但是文件的内容不保证能立即可见,及时数据流已经刷新(`out.flush()`)并存储

- 当前正在写入的块对其他reader不可见,HDFS提供一个方法来使所有缓存与数据节点强行同步,即对`FSDataOutputStream`调用`sync()`方法.当`sync()`方法返回成功后,对所有新的reader而言,HDFS能保证文件中到目前为止写入的数据均到达所有datanode的写入管道并且对所有新的reader均可见

  ```java
  Path p = new Path("p");
  FSDataOutputStream out = fs.create(p);
  out.write("content".getBytes("UTF-8"));
  out.flush();
  out.sync();
  ```

- 在HDFS中关闭文件其实还隐含执行`sync()`方法

**如果不调用`sync()`方法,在客户端或系统发生故障时可能还丢失数据块**