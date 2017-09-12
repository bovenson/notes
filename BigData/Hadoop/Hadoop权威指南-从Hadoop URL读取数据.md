[TOC]

# Hadoop权威指南:从Hadoop URL读取数据

**使用java.net.URL对象从Hadoop文件系统读取文件**

## 实现类似linux中cat命令的程序

### 文件名

`HDFSCat.java`

### 程序代码

```
import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class HDFSCat {
    public static void main(String[] args) {
        InputStream in = null;
        try {
            in = new URL(args[0]).openStream();
            IOUtils.copyBytes(in, System.out, 4096, false);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(in);
        }
    }

    static {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }
}
```

### 编译

`javac HDFSCat `

### 运行

`hadoop HDFSCat hdfs://localhost:9000/user/hadoop/in.txt`

### 说明

- 需要运行在配置hadoop的linux系统上
- 编译前,需要设置CLASSPATH [点击查看](http://www.cnblogs.com/bovenson/p/5715659.html)
- 运行命令中的 `hdfs://localhost:9000`是在`hadoop/etc/hadoop/core-site.xml`文件中`fs.defaultFS`的`value`值