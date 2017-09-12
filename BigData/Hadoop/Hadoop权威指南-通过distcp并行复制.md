# Hadoop权威指南:通过distcp并行复制

- distcp是一个分布式复制程序,改程序可以从Hadoop文件系统间复制大量数据,也可以将大量的数据复制到Hadoop中

- distcp的典型应用是在两个HDFS集群间传输数据

  `hadoop distcp hdfs://namenode1/foo hdfs://namemode2/bar`

- 默认情况下, distcp会跳过目标路径下已经存在的文件,可以通过overwirte选项覆盖现有文件,也可以通过update选项选择有改动的文件

- distcp是作为一个MapReduce作业来实现的,通过集群中并行运行的map来完成,这里没有reducer.

- 如果试图在两个运行着不同HDFS版本的集群上使用distcp复制数据并使用hdfs协议,会导致复制作业失败,因为两个系统版本的RPC是不兼容的

  - 使用HTTP协议复制

  ​     `hadoop distcp hftp://namenode1:50070/foo hdfs://namenode2/bar`

     **namenode的web端口是由dfs.http.address属性决定的**

  - 使用webhdfs协议

    `hadoop distcp webhdfs://namenode1:50070/foo webhdfs://namenode2:50070/bar`

  ​

