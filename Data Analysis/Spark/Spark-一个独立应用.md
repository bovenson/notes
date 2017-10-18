[TOC]
# Spark:一个独立应用

## 关于构建
### Java和Scala

在Java和Scala中,只需要给你的应用添加一个对于spark-core的Maven依赖.
### Python
在Python中,可以把应用写成脚本,然后使用Spark自带的bin/spark-submit脚本来运行.spark-submit会引入Python程序的Spark依赖.使用方式如下所示.
`/PATH_TO_SPARK/bin/spark-submit my_python_script.py`

## 初始化SparkContext
- 先创建一个`SparkConf`对象来配置应用
- 基于`SparkConf`创建一个`SparkContext`对象

### Python示例
#### 代码
```python
from pyspark import SparkConf, SparkContext


conf = SparkConf().setMaster("local").setAppName("My App")
sc = SparkContext(conf = conf)
```

#### 运行
```shell
spark-submit spark-app.py
```
### Scala示例
```scala
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._

val conf = new SparkConf().setMaster("local").setAppName("My App")
val sc = new SparkContext(conf)
```
### Java示例
```java
import org.apache.spark.SparkConf
import org.apache.spark.api.java.JavaSparkContext

SparkConf conf = new SparkConf().setMaster("local").setAppName("My App");
JavaSparkContext sc = new JavaSparkContext(conf);
```
### 说明
上述例子是创建SparkContext的最基本的方法,你只需传递两个参数:
- 集群URL(上述是local),告诉Spark如何运行连接到集群上
- 应用名可以用来在集群管理器的用户界面找到该应用

# 独立应用示例
创建空白目录,在新建目录下,新建文件`simpleApp.Scala`,添加如下代码.
## Scala代码
```scala
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf


object SimpleApp {
    def main(args: Array[String]) {
        val logFile = "README.md"
        val conf = new SparkConf().setAppName("Simple Application")
        val sc = new SparkContext(conf)
        val logData = sc.textFile(logFile, 2).cache()
        val numAs = logData.filter(line => line.contains("a")).count()
        val numBs = logData.filter(line => line.contains("b")).count()
        println("Lines with a: %s, lines with b: %s".format(numAs, numBs))
    }
}
```
## 构建文件
在新建目录下,新建文件`simple.sbt`,复制如下代码.
```sbt
name := "Simple Application"
version := "1.0"
scalaVersion := "2.11.8"
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.0.0"
```
- 使用`scala -version`命令查看scala版本,使用`spark-shell`可以查看spark版本及scala版本,使用`:quit`命令退出spark-shell


## 说明
- 程序构建需要安装sbt
- 程序用来统计`README.md`文件中包含`a`和`b`的行数
- 需要将`README.md`放到Spark使用的文件系统的相应位置.比如,如果使用的是HDFS,`README.md`应该放在`/user/YOUR_USER_NAME/`目录下, 或者将`val logFile = "README.md"`中的文件路径改为绝对路径,例如:`val logFile = "/user/mint/README.md"`.

## 构建
### 新建文件夹下包含的文件
```bash
$ ls
simpleApp.scala  simple.sbt
```
### 执行构建
```bash
$ sbt package
[info] Set current project to Simple Project (in build file:/home/public/program/scala/self-cont-app/)
[info] Updating {file:/home/public/program/scala/self-cont-app/}self-cont-app...
[info] Resolving jline#jline;2.12.1 ...
[info] Done updating.
[info] Compiling 1 Scala source to /home/public/program/scala/self-cont-app/target/scala-2.11/classes...
[info] Packaging /home/public/program/scala/self-cont-app/target/scala-2.11/simple-project_2.11-1.0.jar ...
[info] Done packaging.
[success] Total time: 11 s, completed Sep 8, 2016 3:12:31 PM
```
# 运行构建的程序
```bash
$ spark-submit --class "SimpleApp" --master local[4] ./target/scala-2.11/simple-project_2.11-1.0.jar 
SLF4J: Class path contains multiple SLF4J bindings.
...
Lines with a: 61, lines with b: 27
```