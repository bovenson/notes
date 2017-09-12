# Hadoop权威指南:HDFS-Hadoop存档

[TOC]

- 每个文件按块方式存储, 每个块的元数据存储在namenode的内存中
- Hadoop存档文件或HAR文件是一个更高效的文件存档工具,它将文件存入HDFS块,在减少内存使用的同时,允许对文件进行透明地访问
- Hadoop存档文件可以用作MapReduce的输入

## 使用Hadoop存档工具

- Hadoop存档是通过archive工具根据一组文件创建而来的,该存档工具运行一个MapReduce作业来并行处理所有的输入文件

- 使用archive

  `hadoop archive -archiveName files.har /my/files /my`

  - 第一个选项是存档文件的名称,这里是第一个参数 *file.har*
  - 第二个参数是需要存档的文件
  - 第三个参数是HAR文件的输出目录

- 列出HAR文件中的文件

  `hadoop fs -ls /my/files.har`

- 递归列出HAR文件中的文件

  `hadoop fs -lsr /my/files.har`

- 其他文件系统中引用HAR文件,则需要使用不同的URI路径格式,示例如下

  ```shell
  hadoop fs -lsr har:///my/files.har/my/files/dir
  hadoop fs -lsr har://hdfs-localhost:8020/my/files.har/my/files/dir
  ```

  第二种格式仍以har方案标示一个HAR文件系统,但是用hdfs指定基础文件系统方案的权限

- 要删除HAR文件,需要使用递归格式进行删除,因为对于基础文件系统来说,HAR文件是一个目录

  `hadoop fs -rmr /my/files.har`

## 不足

- 新建一个存档文件会创建原始文件的一个副本
- 一旦创建,不能修改
- InputFormat不知道文件已经存档