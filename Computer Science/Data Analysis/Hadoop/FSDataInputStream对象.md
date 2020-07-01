# Hadoop权威指南:FSDataInputStream对象

**FileSystem对象中的open()方法返回的是FSDataInputStream对象, 而不是标准的java.io类对象,这个类是继承了java.io.DataInputStream接口的一个特殊类,并支持随机访问,可以从流中的任意位置读取数据**

```
package org.apache.hadoop.fs;
public class FSDataInputStream extends DataInputStream implements Seekable, PositionedReadable { 
	// implementation elided
}
```

Seekable接口支持在文件中找到指定位置,并提供一个查询当前位置相对于文件起始位置偏移量的查询方法`(getPos())`

``` java
public interface Seekable {
  void seek(long pos) throws IOExcption;
  long getPos() throws IOException;
  boolean seekToNewSource(long targetPos) throws IOException;
}
```

**与`java.io.InputStream`的`skip()`不同,`seek()`可以移动到文件中任意一个绝对位置,`skip()`则只能相对于当前位置定位到另一个新位置.

## 使用`seek()`方法,将Hadoop文件系统中的一个文件写入标准输出两次

### 代码

```java
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URI;


public class FileSystemDoubleCat {
    public static void main(String[] args) throws IOException {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        FSDataInputStream in = null;
        try {
            in = fs.open(new Path(uri));
            IOUtils.copyBytes(in, System.out, 4096, false);
            in.seek(0);
            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }
}
```

### 编译

`javac FileSystemDoubleCat`

### 运行

`hadoop FileSystemDoubleCat hdfs://localhost/user/hadoop/in`



## PositionedReadable接口

**FSDataInputStream类也实现了PositionedReadable接口,从一个指定偏移量处读取文件的一部分**

```java
public interfacen PositionedReadable {
  public int read(long position, byte[] buffer, int offset, int length) throws IOExcption;
  public void readFully(long position, byte[] buffer, int offset, int length) throws IOExcption;
  public void readFully(long position, byte[] buffer) throws IOExcption;
}
```

### read()

`read()`方法从文件的指定`position`处读取之多为`length`字节的数据并存入缓冲区`buffer`的指定偏移量`offset`处.

返回值是**实际读到的数据的字节数** 可能小于指定的`length`长度

### readFully()

`readFully()`方法将指定`length`长度的字节数据读取到`buffer`中(或在只接受`buffer`字节数组的版本种)

**读取到文件末尾会抛出`EOFException`异常**