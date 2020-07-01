# Linux下使用javac编译Hadoop程序

## 首先要配置好Hadoop, 给出两个教程

- [Hadoop安装教程_单机/伪分布式配置_Hadoop2.6.0/Ubuntu14.04](http://dblab.xmu.edu.cn/blog/install-hadoop/)
- [Hadoop集群安装配置教程_Hadoop2.6.0_Ubuntu/CentOS](http://dblab.xmu.edu.cn/blog/install-hadoop-cluster/)

## 然后修改CLASSPATH

可以在/etc/profile中添加这一行

`export CLASSPATH=$($HADOOP_HOME/bin/hadoop classpath):$CLASSPATH`

**放在HADOOP_HOME之后**