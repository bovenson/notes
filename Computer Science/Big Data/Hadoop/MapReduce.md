# MR 作业

## 配置

作业提交 / 环境变量配置集群属性等。

```shell
# 代码中获取配置
Configuration conf = getConf();

# 添加配置
conf.set(key, value)
```

## Job

```shell
# setJarByClass
通过指定类名，获取需要分发到其他节点的Jar包，来执行map、reduce操作。

# setMapperClass
mapper 操作类

# setMapOutputKeyClass
mapper 输出的key类型

# setMapOutputValueClass
mapper 输出的value类型

# setReducerClass
reduce 操作类

# setOutputKeyClass
reduce 输出key类型

# setOutputValueClass
reduce 输出value类型

# setInputFormatClass
输入文件格式

# setOutputFormatClass
输出文件格式

# setNumReduceTasks
设置 reduce 任务数量

# setJobSetupCleanupNeeded
每个task, 是否执行 setup / cleanup 操作

# setSortComparatorClass
设置排序阶段比较器

# setReduceSpeculativeExecution
设置是否开启reduce阶段的推测执行

# setCombinerClass
设置map阶段combine的类；combine运行在reduce之前，也被称为semi-reducer；输入来自map class，输出发送给实际的reduce作为输入。
# 参考: https://www.tutorialspoint.com/map_reduce/map_reduce_combiners.htm

# setCombinerKeyGroupingComparatorClass
设置combine阶段比较器

# setGroupingComparatorClass	
对发往 reduce 的键值对进行分组操作


```

## 文件操作

```shell
# 获取 FileSystem
FileSystem fs = FileSystem.get(conf);

# 判断是否存在
fs.exists(path)

# 删除
fs.delete(path, true);	# (path, recursive)

# 
```

