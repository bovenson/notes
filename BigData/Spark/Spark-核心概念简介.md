# Spark:核心概念简介
## 驱动程序
- 每个Spark应用都有一个 *驱动程序* 来发起集群上的并行操作
- **驱动程序**包含应用的`main`函数,并且定义了集群上的分布式数据集,还对这些分布式数据集应用了相关操作.
- 对于Spark Shell,驱动程序是其本身
- **驱动程序**通过一个`SparkContext`对象来访问Spark,这个对象代表对计算机集群的一个连接


## 传递函数
Spark有很多传递函数的API,可以将对应操作运行在集群上.
### 程序示例
```
# pyspark
>>> lines = sc.textFile("README.md")
>>> pythonlines = lines.filter(lambda line: "Python" in line)
>>> pythonlines.first()
u'high-level APIs in Scala, Java, Python, and R, and an optimized engine that'
```
**Spark会自动将函数(比如lambda line: "Python" in line)发送到各个执行节点.这样就可以在单一的驱动程序中编程,让代码自动运行在多个节点上.**


