[TOC]

# 简单的java Hadoop MapReduce程序(计算平均成绩)从打包到提交及运行

## 程序源码

```java
import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
public class Score {
    public static class Map extends
            Mapper<LongWritable, Text, Text, IntWritable> {
        // 实现map函数
        public void map(LongWritable key, Text value, Context context)
                throws IOException, InterruptedException {
            // 将输入的纯文本文件的数据转化成String
            String line = value.toString();
            // 将输入的数据首先按行进行分割
            StringTokenizer tokenizerArticle = new StringTokenizer(line, "\n");
            // 分别对每一行进行处理
            while (tokenizerArticle.hasMoreElements()) {
                // 每行按空格划分
                StringTokenizer tokenizerLine = new StringTokenizer(tokenizerArticle.nextToken());
                String strName = tokenizerLine.nextToken();// 学生姓名部分
                String strScore = tokenizerLine.nextToken();// 成绩部分
                Text name = new Text(strName);
                int scoreInt = Integer.parseInt(strScore);
                // 输出姓名和成绩
                context.write(name, new IntWritable(scoreInt));
            }
        }
    }

 

    public static class Reduce extends
            Reducer<Text, IntWritable, Text, IntWritable> {
        // 实现reduce函数
        public void reduce(Text key, Iterable<IntWritable> values,
                Context context) throws IOException, InterruptedException {
            int sum = 0;
            int count = 0;
            Iterator<IntWritable> iterator = values.iterator();
            while (iterator.hasNext()) {
                sum += iterator.next().get();// 计算总分
                count++;// 统计总的科目数
            }
            int average = (int) sum / count;// 计算平均成绩
            context.write(key, new IntWritable(average));
        }
    }
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        // "localhost:9000" 需要根据实际情况设置一下
        conf.set("mapred.job.tracker", "localhost:9000");
      	// 一个hdfs文件系统中的 输入目录 及 输出目录
        String[] ioArgs = new String[] { "input/score", "output" };
        String[] otherArgs = new GenericOptionsParser(conf, ioArgs).getRemainingArgs();
        if (otherArgs.length != 2) {
            System.err.println("Usage: Score Average <in> <out>");
            System.exit(2);
        }

        Job job = new Job(conf, "Score Average");
        job.setJarByClass(Score.class);
        // 设置Map、Combine和Reduce处理类
        job.setMapperClass(Map.class);
        job.setCombinerClass(Reduce.class);
        job.setReducerClass(Reduce.class);
        // 设置输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        // 将输入的数据集分割成小数据块splites，提供一个RecordReder的实现
        job.setInputFormatClass(TextInputFormat.class);
        // 提供一个RecordWriter的实现，负责数据输出
        job.setOutputFormatClass(TextOutputFormat.class);
        // 设置输入和输出目录
        FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
        FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
```

## 编译

### 命令

`javac Score.java`

### 依赖错误

如果出现如下错误:

```shell
mint@lenovo ~/Desktop/hadoop $ javac Score.java 
Score.java:4: error: package org.apache.hadoop.conf does not exist
import org.apache.hadoop.conf.Configuration;
                             ^
Score.java:5: error: package org.apache.hadoop.fs does not exist
import org.apache.hadoop.fs.Path;
                           ^
Score.java:6: error: package org.apache.hadoop.io does not exist
import org.apache.hadoop.io.IntWritable;
                           ^
Score.java:7: error: package org.apache.hadoop.io does not exist
import org.apache.hadoop.io.LongWritable;
                           ^
Score.java:8: error: package org.apache.hadoop.io does not exist
import org.apache.hadoop.io.Text;
```

尝试修改环境变量`CLASSPATH`

```shell
sudo vim /etc/profile
# 添加如下内容
export HADOOP_HOME=/usr/local/hadoop	# 如果没设置的话, 路径是hadoop安装目录
export PATH=$HADOOP_HOME/bin:$HADOOP_HOME/sbin:$PATH	# 如果没设置的话
export CLASSPATH=$($HADOOP_HOME/bin/hadoop classpath):$CLASSPATH
```

`source /etc/profile`

然后重复上述编译命令.

## 打包

编译之后会生成三个class文件:

```shell
mint@lenovo ~/Desktop/hadoop $ ls | grep class
Score.class
Score$Map.class
Score$Reduce.class
```

使用`tar`程序打包class文件.

`tar -cvf Score.jar ./Score*.class `

会生成`Score.jar`文件.

## 提交运行

### 样例输入

```shell
mint@lenovo ~/Desktop/hadoop $ ls | grep txt
chinese.txt
english.txt
math.txt
mint@lenovo ~/Desktop/hadoop $ cat chinese.txt 
Zhao 98
Qian 9
Sun 67
Li 23
mint@lenovo ~/Desktop/hadoop $ cat english.txt 
Zhao 93
Qian 42
Sun 87
Li 54
mint@lenovo ~/Desktop/hadoop $ cat math.txt 
Zhao 38
Qian 45
Sun 23
Li 43
```

### 上传到HDFS

`hdfs dfs -put ./*/txt input/score`

```shell
mint@lenovo ~/Desktop/hadoop $ hdfs dfs -ls input/score
Found 3 items
-rw-r--r--   1 mint supergroup         28 2017-01-11 23:25 input/score/chinese.txt
-rw-r--r--   1 mint supergroup         29 2017-01-11 23:25 input/score/english.txt
-rw-r--r--   1 mint supergroup         29 2017-01-11 23:25 input/score/math.txt
```

### 运行

```shell
mint@lenovo ~/Desktop/hadoop $ hadoop jar Score.jar Score input/score output
17/01/11 23:26:26 INFO client.RMProxy: Connecting to ResourceManager at /0.0.0.0:8032
17/01/11 23:26:27 INFO input.FileInputFormat: Total input paths to process : 3
17/01/11 23:26:27 INFO mapreduce.JobSubmitter: number of splits:3
17/01/11 23:26:27 INFO Configuration.deprecation: mapred.job.tracker is deprecated. Instead, use mapreduce.jobtracker.address
17/01/11 23:26:27 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_1484147224423_0006
17/01/11 23:26:27 INFO impl.YarnClientImpl: Submitted application application_1484147224423_0006
17/01/11 23:26:27 INFO mapreduce.Job: The url to track the job: http://lenovo:8088/proxy/application_1484147224423_0006/
17/01/11 23:26:27 INFO mapreduce.Job: Running job: job_1484147224423_0006
17/01/11 23:26:33 INFO mapreduce.Job: Job job_1484147224423_0006 running in uber mode : false
17/01/11 23:26:33 INFO mapreduce.Job:  map 0% reduce 0%
17/01/11 23:26:40 INFO mapreduce.Job:  map 67% reduce 0%
17/01/11 23:26:41 INFO mapreduce.Job:  map 100% reduce 0%
17/01/11 23:26:46 INFO mapreduce.Job:  map 100% reduce 100%
17/01/11 23:26:46 INFO mapreduce.Job: Job job_1484147224423_0006 completed successfully
17/01/11 23:26:47 INFO mapreduce.Job: Counters: 49
	File System Counters
		FILE: Number of bytes read=129
		FILE: Number of bytes written=471147
		FILE: Number of read operations=0
		FILE: Number of large read operations=0
		FILE: Number of write operations=0
		HDFS: Number of bytes read=443
		HDFS: Number of bytes written=29
		HDFS: Number of read operations=12
		HDFS: Number of large read operations=0
		HDFS: Number of write operations=2
	Job Counters 
		Launched map tasks=3
		Launched reduce tasks=1
		Data-local map tasks=3
		Total time spent by all maps in occupied slots (ms)=15538
		Total time spent by all reduces in occupied slots (ms)=2551
		Total time spent by all map tasks (ms)=15538
		Total time spent by all reduce tasks (ms)=2551
		Total vcore-milliseconds taken by all map tasks=15538
		Total vcore-milliseconds taken by all reduce tasks=2551
		Total megabyte-milliseconds taken by all map tasks=15910912
		Total megabyte-milliseconds taken by all reduce tasks=2612224
	Map-Reduce Framework
		Map input records=12
		Map output records=12
		Map output bytes=99
		Map output materialized bytes=141
		Input split bytes=357
		Combine input records=12
		Combine output records=12
		Reduce input groups=4
		Reduce shuffle bytes=141
		Reduce input records=12
		Reduce output records=4
		Spilled Records=24
		Shuffled Maps =3
		Failed Shuffles=0
		Merged Map outputs=3
		GC time elapsed (ms)=462
		CPU time spent (ms)=2940
		Physical memory (bytes) snapshot=992215040
		Virtual memory (bytes) snapshot=7659905024
		Total committed heap usage (bytes)=732430336
	Shuffle Errors
		BAD_ID=0
		CONNECTION=0
		IO_ERROR=0
		WRONG_LENGTH=0
		WRONG_MAP=0
		WRONG_REDUCE=0
	File Input Format Counters 
		Bytes Read=86
	File Output Format Counters 
		Bytes Written=29
```

### 输出

```shell
mint@lenovo ~/Desktop/hadoop $ hdfs dfs -ls output
Found 2 items
-rw-r--r--   1 mint supergroup          0 2017-01-11 23:26 output/_SUCCESS
-rw-r--r--   1 mint supergroup         29 2017-01-11 23:26 output/part-r-00000
mint@lenovo ~/Desktop/hadoop $ hdfs dfs -cat output/part-r-00000
Li	40
Qian	32
Sun	59
Zhao	76
```

