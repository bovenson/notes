# Hadoop权威指南:HDFS-目录,查询文件系统,删除文件

[TOC]

## 目录

`FileSystem`实例提供了创建目录的方法

`public boolean mkdirs(Path f) throws IOException`

**这个方法一次性创建所有必要但还没有的父目录**

通常不需要显式创建一个目录,因为**调用`create()`方法写入文件时会自动创建所有父目录**

## 查询文件系统

### 文件元数据:FileStatus

- `FileStatus`类封装了文件系统中文件和目录的元数据包括**文件长度,块大小,副本,修改时间,所有者,权限信息**
- `FileSystem`的`getFileStatus`方法用于获取文件或目录的`FileStatus`对象
- 使用`exists()`方法检查**文件或者目录**是否存在

### 列出文件

使用`FileSystem`的`listStatus()`方法

```java
public FileStatus[] listStatus(Path f) throws IOException
public FileStatus[] listStatus(Path f, PathFilter filter) throws IOException
public FileStatus[] listStatus(Path[] files) throws IOException
public FileStatus[] listStatus(Path[] files, PathFilter filter) throws IOException
```

- 传入的Path参数可以是一个文件,也可以是一个目录
- 允许使用PathFilter来限制匹配的文件和目录

#### 显示Hadoop文件系统中一组路径的文件信息

##### 代码

```java
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

public class ListStatus {
    public static void main(String[] args) throws IOException {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);

        Path[] paths = new Path[args.length];
        for (int i=0; i < paths.length; ++i) {
            paths[i] = new Path(args[i]);
        }
        FileStatus[] status = fs.listStatus(paths);
        // stat2Path2方法将一个FileStatus对象数组转换为一个Path对象数组
        Path[] listedPaths = FileUtil.stat2Paths(status);
        for (Path p : listedPaths) {
            System.out.println(p);
        }
    }
}
```

##### 编译

`javac ListStatus.java`

##### 运行

`hadoop ListStatus hdfs://localhost:9000/user/hadoop/input hdfs://localhost:9000/user/hadoop/output `

### 文件模式

Hadoop为执行通配[^1]提供了两个`FileSystem`方法

```java
public FileStatus[] globStatus(Path pathPattern) throws IOException
public FileStatus[] globStatus(Path pathPattern, PathFilter filter) throws IOException
```

- `globStatus()`方法返回与其路径匹配于指定模式的所有文件的`FileStatus`对象数组,并按路径排序
- `PathFilter`命令作为可选项可以进一步对匹配结果进行限制

**Hadoop支持的通配符与Unix bash的相同**

| 通配符     | 名称    | 匹配                                    |
| ------- | ----- | ------------------------------------- |
| *       | 星号    | 匹配0或多个字符                              |
| ?       | 问号    | 匹配单一字符                                |
| [ab]    | 字符类   | 匹配{a,b}集合中的一个字符                       |
| [\^ab]  | 非字符类  | 匹配非{a,b}集合中的一个字符                      |
| [a-b]   | 字符范围  | 匹配一个在a-b范围内的字符(包括a,b),a在字典顺序上要小于或等于b  |
| [\^a-b] | 非字符范围 | 匹配一个不在a-b范围内的字符(包括a,b),a在字典顺序上要小于或等于b |
| {a,b}   | 或选择   | 匹配包含a或b中的一个的表达式                       |
| \c      | 转义字符  | 匹配元字符c                                |

### PathFilter对象

- 通配符模式并不总能描述我们想要访问的文件集

- `FileSystem`中的`listStatus()` 和 `globStatus()` 方法提供了可选的 `PathFilter` 对象, 以编程方式控制通配符

  ```java
  package org.apache.hadoop.fs;
  public interface PathFilter {
    boolean accept(Path path);
  }
  ```

- `pathFilter` 和 `java.io.FileFilter` 一样,是 `Path` 对象, 而不是 `File` 对象

#### PathFilter用于排除匹配正则表达式的路径

##### 代码

```java
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

public class RegexExcludePathFilter implements PathFilter {

    private final String regex;

    public RegexExcludePathFilter(String regex) {
        this.regex = regex;
    }


    @Override
    public boolean accept(Path path) {
        return !path.toString().matches(regex);
    }
}
```



## 删除数据

使用 `FileSystem` 的 `delete()` 方法可以永久性删除文件或目录

`public boolean delete(Path f, boolean recursive) throws IOException`

- 如果f是一个文件或空目录, 那么 `recursive` 的值会被忽略
- 只有在 `recursive` 值为 `true` 时,非空目录及其内容才会被删除, 否则会抛出IOException异常

[^1]: 在一个表达式中使用通配符来匹配多个文件是比较方便的,无需列举每个文件和目录来指定输入,该操作称为"通配"