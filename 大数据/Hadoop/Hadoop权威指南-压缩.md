# Hadoop权威指南:压缩

[TOC]

文件压缩的两个好处:

- 减少储存文件所需要的磁盘空间
- 加速数据在网络和磁盘上的传输

压缩格式总结:

| 压缩格式    | 工具    | 算法      | 文件扩展名    | 是否可切分 |
| ------- | ----- | ------- | -------- | ----- |
| DEFLATE | 无     | DEFLATE | .deflate | 否     |
| Gzip    | gzip  | DEFLATE | .gz      | 否     |
| bzip2   | bzip2 | bzip2   | .bz2     | 是     |
| LZO     | lzop  | LZO     | .lzo     | 否     |
| LZ4     | 无     | LZ4     | .lz4     | 否     |
| Snappy  | 无     | Snappy  | .snapp   | 否     |

上述表中的所有压缩工具都提供9个不同的选项来控制压缩时必须考虑的权衡:

- -1位优化压缩速度
- -9为优化压缩空间

## codec

`codec` 实现了一种压缩-解压算法

Hadoop中一个对`CompressionCodec`接口的实现代表一个`codec`

Hadoop的压缩`codec`

| 压缩格式    | HadoopCompressionCodec                   |
| ------- | ---------------------------------------- |
| DEFLATE | org.apache.hadoop.io.compress.DefaultCodec |
| gzip    | org.apache.hadoop.io.compress.GzipCodec  |
| bzip2   | org.apache.hadoop.io.compress.BZip2Codec |
| LZO     | com.hadoop.compression.lzo.LzopCodec     |
| LZ4     | org.apache.hadoop.io.compress.Lz4Codec   |
| Snappy  | org.apache.hadoop.io.compress.SnappyCodec |

## 通过CompressionCodec对数据流进行压缩和解压缩

`CompressionCodec包含两个函数` 可以轻松用于压缩和解压缩数据

- `createOutputStream(OutputStream out)`方法在底层数据流中对需要以压缩格式写入在此之前尚未压缩的数据新建一个`CompressionOutputStream`对象
- 对输入数据流中读取的数据进行解压缩的时候,则调用`createInputStream(InputStream in)`获取`CompressionInputStream`,通过该方法从底层数据里读取解压缩后的数据

### 压缩从标准输入读取的数据,并写到标准输出

#### 代码

```java
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;

public class StreamCompressor {
    public static void main(String  args[]) throws ClassNotFoundException, IOException {
        // 符合ReflectionUtils的实现
        String codecClassname = args[0];
        Class<?> codecClass = Class.forName(codecClassname);
        Configuration conf = new Configuration();
        // 新建codec实例
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass, conf);

        // 获得在System.out上支持压缩的一个包裹方法
        CompressionOutputStream out = codec.createOutputStream(System.out);
        // 对IOUtils对象调用copyBytes()方法将输入数据复制到输出
        // 输出由CompressionOutputStream对象压缩
        IOUtils.copyBytes(System.in, out, 4096, false);
        // 要求压缩方法完成到压缩数据流的写操作
        out.finish();
    }
}

// echo "Test" | hadoop StreamCompressor org.apache.hadoop.io.compress.GzipCodec | gunzip
```

#### 编译

```shell
javac StreamCompressor.java
```

#### 测试

压缩从标准输入读取的数据,并写到标准输出,通过管道传递给gunzip, 显示压缩内容

```shell
echo "Test" | hadoop StreamCompressor org.apache.hadoop.io.compress.GzipCodec | gunzip
```



## 通过CompressionCodecFactory推断CompressionCodec

### 根据文件扩展名选取codec解压缩文件

#### 代码

```java
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

public class FileDecompressor {
    public static void main(String args[]) throws IOException {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);

        Path inputPath = new Path(uri);
        CompressionCodecFactory factory = new CompressionCodecFactory(conf);
        CompressionCodec codec = factory.getCodec(inputPath);
        if (codec == null) {
            System.err.println("No codec found for " + uri);
            System.exit(1);
        }

        String outputUri = CompressionCodecFactory.removeSuffix(uri, codec.getDefaultExtension());

        InputStream in = null;
        OutputStream out = null;
        try {
            in = codec.createInputStream(fs.open(inputPath));
            out = fs.create(new Path(outputUri));
            IOUtils.copyBytes(in, out, conf);
        } finally {
            IOUtils.closeStream(in);
            IOUtils.closeStream(out);
        }
    }
}
```

- 通过使用`CompressionCodecFactory`的`getCodec()`方法,  得到相应的`CompressionCodec`或者`null` 即`CompressionCodecFactory`可通过搜索注册的`codec` 找到匹配指定文件扩展名的 `codec`
- 如果压缩的是单个文本文件, 可以直接使用`cat`名查看解压缩后生成的文件

#### 编译

`javac FileDecompressor.java`

#### 运行

`hadoop FileDecompressor /usr/hadoop/file.gz`

- `file.gz` 要在hdfs中存在
- 会被解压在hdfs中名为`file`的文件

### 压缩代码库的实现

- **使用原生类库来实现解压缩, 会节约解压缩的时间.**
- 可以通过Java系统的`java.library.path`属性指定原生代码库

| 压缩格式    | 是否有Java实现 | 是否有原生(native)实现 |
| ------- | --------- | --------------- |
| DEFLATE | 是         | 是               |
| gzip    | 是         | 是               |
| bzip2   | 是         | 否               |
| LZO     | 否         | 是               |
| LZ4     | 否         | 是               |
| Snappy  | 否         | 是               |

## CodecPool

- `CodecPool`对象支持**反复**使用压缩和解压缩操作, 以减少创建对象的开销

### 使用压缩池对读取自标准输入的数据进行压缩,然后将其写到标准输出

#### 代码

```java
/*
* Hadoop权威指南:第四章
* */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CodecPool;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.io.compress.Compressor;
import org.apache.hadoop.oncrpc.security.SysSecurityHandler;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.IOException;

public class PooledStreamCompressor {
    public static void main(String args[]) throws ClassNotFoundException, IOException {
        String codecClassName = args[0];
        // 找到对应CompressionCodec类
        Class<?> codeClass = Class.forName(codecClassName);
        Configuration conf = new Configuration();
        // 通过类名创建实例对象
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codeClass, conf);
        Compressor compressor = null;

        try {
            // 得到compressor
            compressor = CodecPool.getCompressor(codec);
            CompressionOutputStream out = codec.createOutputStream(System.out, compressor);
            IOUtils.copyBytes(System.in, out, 4096, false);
            out.flush();
        } finally {
            // 将compressor返回CodecPool中
            CodecPool.returnCompressor(compressor);
        }
    }
}
```

- 在`codec`的重载方法`createOutputStream`中,对于制定的`CompressionCodec`,我们从池中获取一个`Compressor`实例

## 压缩和输入分片

- 了解这些压缩格式是否支持切分非常重要,例如, 文件压缩为某种个格式后,为1G, 它将被储存成16块(每块是64M的话), 如果该压缩格式不支持切分, 那么就每个块就不能单独作为输入文件,无法实现从压缩数据流任意位置读取数据

### 应该使用哪种压缩格式?

大致按照效率从高到低排列:

- 适应容器文件格式,例如顺序文件,REFile或者Avro数据问津, 这些格式同时支持压缩和切分.通常最好与一个快速压缩工具联合使用,例如:LZO,LZ4或者Snappy
- 使用支持切分的压缩格式, 例如bzip2, 或者使用通过索引实现切分的压缩格式, 例如:LZO
- 在应用中将文件切分成块,并使用任意一种压缩格式为每个数据块建立压缩文件(不论它是否支持切分).这时, 需要合理选择数据块大小, 以确保压缩后数据块的大小近似于HDFS块的大小
- 储存未经压缩的文件

**对于大文件来说,不要使用不支持切分整个文件的压缩格式,因为会失去数据的本地特性,进而造成MapReduce应用效率低下**

## 在MapReduce中使用压缩

### 解压缩输入

- 如果输入文件是压缩的,那么在根据文件扩展名推断出相应的`codec`后,MapReduce会在读取文件是自动解压缩文件

### 解压缩输出

- 要想压缩MapReduce作业的输出,应在作业配置过程中将`mapred.output.compress`设为`true`和`mapred.output.compression.codec`属性设置为打算使用的压缩codec的类名
- 要想压缩MapReduce作业的输出,还可以在`FileOutputFormat`中使用更便捷的方法设置这些属性

#### 对查找最高温作业所产生输出进行压缩

#### 代码

```java
/*
* Hadoop权威指南:第四章
* */

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class MaxTemperatureWithCompression {
    public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {
        if (args.length != 2) {
            System.err.println("Usage: MaxTemperatureWithCompression <input path> <output path>");
            System.exit(-1);
        }

        Job job = new Job();
        job.setJarByClass(MaxTempreture.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileOutputFormat.setCompressOutput(job, true);
        FileOutputFormat.setOutputCompressorClass(job, GzipCodec.class);

        job.setMapperClass(MaxTemperatureMapper.class);
        job.setCombinerClass(MaxTemperatureReducer.class);
        job.setReducerClass(MaxTemperatureReducer.class);

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
```

#### 运行

`hadoop MaxTemperatureWithCompression input/sample.txt.gz output`

最终输出的每个部分都是经过压缩的

#### 查看输出

`gunzip -c output/part-c-00000.gz`

#### 为输出生成顺序文件(sequence file)

- 可以设置`mapred.output.compression.type`属性来控制限制使用压缩格式,默认值为`RECORD`, 即对每一条记录进行压缩, 如果将其改为`BLOCK`,将针对一组记录进行压缩
- 在`SequenceFileOutputFormat`类中还有一个静态方法`putCompressionType()`可用来便捷地设置该属性

##### MapReduce压缩格式

下表归纳概述了用于设置MapReduce作业输出的压缩格式的配置属性

| 属性名称                            | 类型      | 默认值                                      | 描述                                       |
| ------------------------------- | ------- | ---------------------------------------- | ---------------------------------------- |
| mapred.output.compress          | boolean | false                                    | 压缩输出                                     |
| mapred.output.compression.codec | 类名称     | org.apache.hadoop.io.compress.DefaultCodec | map输出所用的压缩codec                          |
| mapred.output.compression.type  | String  | RECORD                                   | SqeuenceFile的输出可以使用的压缩类型:NONE,RECORD,BLOCK |

### 对map任务输出进行压缩

map任务的输出需要写到磁盘并通过网络传输到reducer节点,所以如果使用LZO,LZ4或者Snappy这样的快速压缩方式,是可以获取性能提升的

#### map任务输出的压缩属性

| 属性名称                                | 类型      | 默认值                                      | 描述              |
| ----------------------------------- | ------- | ---------------------------------------- | --------------- |
| mapred.compress.map.output          | boolean | false                                    | 对map任务输出进行压缩    |
| mapred.map.output.compression.codec | Class   | org.apache.hadoop.io.compress.DefaultCodec | map输出所有的压缩codec |

#### 示例代码

```java
Configuration conf = new Configuration();
conf.setBoolean("mapred.compress.map.output", true);
conf.setClass("mapred.map.output.compression.codec", GzipCodec.class, CompressionCodec.class);
Job job = new Job(conf);
```

