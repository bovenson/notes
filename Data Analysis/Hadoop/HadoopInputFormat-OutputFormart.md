# InputFormat和RecordReader
**Hadoop提出了InputFormat的概念**
**org.apache.hadoop.mapreduce包里的InputFormat抽象类提供了如下列代码所示的两个方法**
```
public abstract class InputFormat<K, V> {
	public abstract List<InputSplit> getSplits(JobContext context);
    RecordReader<K, V> createRecordReader(InputSplit split, TaskAttemptContext context);
}
```
这些方法展示了InputFormat类的两个功能:
* 将输入文件切分为map处理所需的split
* 创建RecordReader类, 它将从一个split生成键值对序列

**RecordReader类同样也是org.apache.hadoop.mapreduce包里的抽象类**
```
public abstract class RecordReader<Key, Value> implements Closeable {
	public abstract void initialize(InputSplit split, TaskAttemptContext context);
    public abstract boolean nextKeyValue() throws IOException, InterruptedException;
    public abstract Key getCurrentKey() throws IOException, InterruptedException;
    public abstract Value getCurrentValue() throws IOException, InterruptedException;
    public abstract float getProgress() throws IOException, InterruptedException;;
    public abstract close() throws IOException;
}
```
**为每个split创建一个RecordReader实例,该实例调用getNextKeyValue并返回一个布尔值**
**组合使用InputFormat和RecordReader可以将任何类型的输入数据转换为MapReduce所需的键值对**

## InputFormat
Hadoop在org.apache.hadoop.mapreduce.lib.input包里提供了一些InputFormat的实现,有如下.
* FileInputFormat: 这是一个抽象基类,可以作为任何基于文本输入的父类
* SequenceFileInputFormat: 这是一个高效的二进制文件格式
* TextInputFormat: 它用于普通文本文件

**InputFormat并不局限于从文件读取数据**

## RecordReader
Hadoop在org.apache.hadoop.mapreduce.lib.input包里也提供了一些常见的RecordReader实现
* **LineRecordReader**: 这是RecordReader类对文本文件的默认实现,它将行号时为键并将该行内容视为值
* **SequenceFileRecordReader**: 该类从二进制文件SequenceFile读取键值

# OutputFormat和RecordWriter
**org.apache.hadoop.mapreduce包里的OutputFormat和RecordWriter的子类负责共同写入作业输出**
如果指定的输出路径已经存在,则会导致作业失败,如果想改变这种情况,需要一个重写该方法的OutputFormat子类.

## OutputFormat
org.apache.hadoop.mapreduce.output包提供了下列OutputFormat类.
* **FileOutputFormat**: 这是所有基于文件的OutputFormat的基类
* **NullOutputFormat**: 这是一个虚拟类,它丢弃所有输出并对文件不做任何写入
* **SequenceFileOutputFormat**: 它将输出写入二进制SequenceFile
* **TextOutputFormat**: 它吧输出写入到普通文本文件

**上述类把他们所需的RecordWriter定义为内部类,因此不存在单独实现的RecordWriter类**



## Sequence files

org.apache.hadoop.io包里的SequenceFile类提供了高效的二进制文件格式,他经常用于MapReduce作业的输出,尤其是当作业的输出被当做另一个作业的输入时.Sequence文件有如下优点.

* 作为二进制文件,它们本质上比文本文件更为紧凑
* 他们支持不同层面的可选压缩,也就是说,可以对每条记录或整个split进行压缩
* 该文件可被并行切分处理

**大多数二进制文件是无法被切分的,必须以单独的线性数据流形式读取,使用这种无法切分的文件作为MapReduce作业的输入,意味着需要使用一个单独的mapper处理整个文件,造成潜在的巨大性能损失**