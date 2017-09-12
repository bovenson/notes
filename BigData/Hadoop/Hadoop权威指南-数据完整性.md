# Hadoop权威指南:数据完整性

[TOC]

**常用的错误检测码是CRC-32(循环冗余校验)**

## HDFS的数据完整性

- HDFS会对写入的所有数据计算校验和,并在读取数据时验证校验和
- datanode负责在收到数据后储存该数据及其验证校验和
- 客户端从datanode读取数据时也会验证校验和,与datanode中存储的校验和进行比较
- 每个datanode均持久保存一个用于验证的校验和日志
- 每个datanode也会在一个后台线程中运行一个DataBlockScanner,定期验证存储在这个datanode上的所有数据块
- 在使用`open()`方法前, 将`false`值传递给FileSystem对象的setVerifyChecksum()方法,即可以禁用校验和验证

## LoaclFileSystem

- Hadoop的LocalFileSystem执行客户端的校验和验证

- 禁用校验和计算

  使用`RawLoaclFileSystem`替代`LocalFileSystem`

- 在应用中实现全局校验和验证

  1. `fs.file.impl`属性设置为`org.apache.hadoop.fs.RawLocalFileSystem`,进而实现对文件URI的重新映射

  2. 新建一个RawLocalFileSystem实例,如果想针对一些读操作禁用校验和,这个方案非常有用,实例如下

     ```
     Configuration conf = ...
     FileSystem fs = new RawLocalFileSystem();
     fs.initialize(null, conf);
     ```

## ChecksumFileSystem

`LocalFileSystem`通过`CheckFileSystem`来完成自己的任务,该类继承自`FileSystem`,一般用法如下

```java
FileSystem rawFS = ...
FileSystem checksummedFS = new ChecksumFileSystem(rawFs);
```

底层文件系统成为"源"(raw)文件系统,可以适用`ChecksumFileSystem`实例的`getRawFileSystem()`方法获取它



