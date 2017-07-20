# Spark:使用Spark Shell的两个示例
## Python 行数统计
** 注意： **使用的是Hadoop的HDFS作为持久层，需要先配置Hadoop
### 命令行代码
```
# pyspark
>>> lines = sc.textFile("/user/mint/README.md") # 创建一个名为lines的RDD.首先要确保README.md在HDFS文件系统相应的路径中.这里的文档是Spark在安装目录下,选择其他文档.
>>> lines.count() # 行数
>>> lines.first() # 显示第一个元素，这里就是第一行
```
如果运行出错,可以排查如下情况：
- Spark没有运行
- `README.md`没有放在对应的文件


### 结果示例
```
>>> lines = sc.textFile("README.md")
>>> lines.count()
99
>>> lines.first()
u'# Apache Spark'
>>> lines = sc.textFile("/user/mint/README.md")
>>> lines.first()
u'# Apache Spark'
```


## Scala 行数统计
### 命令及结果示例
```
# spark-shell
scala> val lines  = sc.textFile("README.md")
lines: org.apache.spark.rdd.RDD[String] = README.md MapPartitionsRDD[1] at textFile at <console>:24

scala> lines.count()
res0: Long = 99

scala> lines.first()
res1: String = # Apache Spark
```
