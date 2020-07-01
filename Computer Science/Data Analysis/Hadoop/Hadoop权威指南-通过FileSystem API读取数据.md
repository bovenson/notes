# Hadoop权威指南:通过FileSystem API读取数据

[TOC]

**在Hadoop中,FileSystem是一个通用的文件系统API**

## 获取FileSystem实例的几个静态方法

- `public static FileSystem get(Configureation conf) throws IOException`
- `public static FileSystem get(URI uri, Configureation conf) throws IOException`
- `public static FileSystem get(URI uri, Configuration conf, String user) throws IOException`

**Configuration对象封装了客户端或服务器的配置,通过设置配置文件读取类路径来实现**

- 第一种方法返回的是默认文件系统(在core-site.xml中指定, 如果没有指定则为默认)
- 第二种方法通过给定的URI方案和权限来确定要使用的文件系统,如果给定URI中没有指定方案,则返回默认文件系统
- 第三种方法,给定用户来访问文件系统,对安全来说至关重要

### 获取本地文件系统的运行实例

**使用getLocal()方法获取本地文件系统的运行实例**

`public static LocalFileSystem getLocal(Configuration conf) throws IOException`

### 获取文件输入流

有了FileSystem实例之后,调用open()函数来获取文件的输入流

- `public FSDataInputStream open(Path f) throws IOException`
- `public abstract FSDataInputStream open(Path f, int bufferSize) throws IOException`

**第一种方法默认缓冲区大小为4kb**

## 实现

### 源文件名

`FileSystemCat.java`

### 代码

```
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class FileSystemCat {
    public static void main(String[] args) throws IOException {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        InputStream in = null;
        try {
            in = fs.open(new Path(uri));
            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }
}
```

### 编译

`javac FileSystemCat.java`

### 运行

`hadoop FileSystemCat hdfs://localhost:9000/user/hadoop/in.txt`