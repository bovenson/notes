# Hadoop权威指南:HDFS-写入数据

FileSystem类有一系列的新建文件的方法.最简单的方法是给准备建的文件指定一个Path对象,然后返回一个用于写入数据的输出流:

`public FSDataOutputStream create(Path f) throws IOException`

此方法有多个重载版本, 指定是否需要覆盖现有文件,文件备份数量,写入文件时所用缓冲区大小,文件块大小及文件权限.

- **`creata()`方法能够为需要写入且当前不存在的文件创建父目录.**

- **调用`exists()`方法检查目录是否存在**

- 还有一个重载方法`Progressable`用于传递回调接口,可以把数据写入进度通知给应用

  ```java
  package org.apache.hadoop.util;
  public interface Progressable {
    public void progress();
  }
  ```

- `append()`方法在一个已有文件末尾追加数据

  ```java
  public FSDataOutputStream append(Path f) throws IOException
  ```



## 将本地文件复制到Hadoop文件系统

### 代码

```java
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.*;
import java.net.URI;

public class FileCopyWithProgress {
    public static void main(String[] args) throws IOException {
        String localSrc = args[0];
        String dst = args[1];
        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        OutputStream out = fs.create(new Path(dst), new Progressable() {
            @Override
            public void progress() {
                System.out.print(".");
            }
        });

        IOUtils.copyBytes(in, out, 4096, true);
    }
}
```

### 编译

`javac FileCopyWithProgress`

### 运行

`hadoop FileCopyWithProgress localfile.tar hdfs://localhost:9000/user/hadoop/hdfsfile.tar`



## FSDataOutputStream对象

`FileSystem`实例的`create()`方法返回`FSDataOutputStream`对象,与`FSDataInputStream`类相似,也有一个查询文件当前位置的方法

```java
package org.apache.hadoop.fs
public class FSDataOutputStream extends DataOutputStream implements Syncable {
  public long getPos() throws IOExcepiton {
  	// implementation elided
  }
  // implementation elided
}
```

**FSDataOutputStream类不允许在文件中定位** 不支持在除文件末尾之外的其他位置进行写入



