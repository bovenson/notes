# Hadoop权威指南:MapReduce应用开发

[TOC]

# 一般流程

1.  编写map函数和reduce函数
2.  编写驱动程序运行作业

# 用于配置的API

-   Hadoop中的组件是通过Hadoop自己的配置API来配置的

-   一个`Configuration`类的实例代表配置属性及其取值的一个集合

-   `Configuration`从资源(XML文件)中读取属性值,一个简单的配置文件(configuration-1.xml)如下

    ```xml
    <?xml version="1.0"?>
    <configuration>
    	<property>
    		<name>color</name>
    		<value>yellow</value>
    	</property>
    	<property>
    		<name>size</name>
    		<value>10</value>
    	</property>
    	<property>
    		<name>weight</name>
    		<value>heavy</value>
    		<final>true</final>
    	</property>
    	<property>
    		<name>size-weight</name>
    		<value>${size},${weight}</value>
    	</property>
    </configuration>
    ```

    使用上述配置文件:

    ```java
    Configuration conf = new Configuration();
    conf.addReasource("configuration-1.xml");
    assertThat(conf.get("color"), is("yellow"));
    assertThat(conf.getInt("size", 0), is(10));
    assertThat(conf.get("breadth", "wide"), is("wide")); // get()方法允许为XML文件中没有定义的属性指定默认值
    ```

## 资源合并

使用多个资源来定义一个配置时，后添加的配置文件的属性会覆盖之前定义的属性，但是被标记为**final**的属性不能被后面的定义所覆盖

### 使用多个资源定义配置

```
Configuration conf = new Configuration();conf.addResource("configuration-1.xml");conf.addResource("configuration-2.xml");
```

### 可变的扩展

配置属性可以用其他属性或系统属性进行定义,例如文件`configuration-1.xml`中`size-weight`属性可以定义为`${size}`和`${weight}`,而且这些属性是用配置文件中的值来扩展的:

`assertThat(conf.get("size-weight"), is("12,heavy"));`

**系统属性的优先级高于资源文件中定义的属性:**

```java
System.setProperty("size", "14");
assertThat(conf.get("size-weight"), is("14,heavy"));
```

虽然配置属性可以通过系统属性来定义,但除非系统属性使用配置属性重新定义,否则他们是无法通过配置API进行访问的:

```java
System.setProperty("length", "2");
assertThat(conf.get("length"), is(String)null);
```

## 配置开发环境

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.bovenson</groupId>
    <artifactId>mapreduce</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>

    <dependencies>
        <!-- Hadoop main artifact -->
        <dependency>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-core</artifactId>
            <version>1.0.0</version>
        </dependency>
        <!-- Unit test artifacts -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.10</version>
            <!--<scope>test</scope>-->
        </dependency>
        <dependency>
            <groupId>org.hamcrest</groupId>
            <artifactId>hamcrest-all</artifactId>
            <version>1.1</version>
            <!--<scope>test</scope>-->
        </dependency>
        <!-- Hadoop test artifacts for running mini clusters -->
        <dependency>
            <groupId>org.apache.mrunit</groupId>
            <artifactId>mrunit</artifactId>
            <version>0.8.0-incubating</version>
            <!--<classifier>hadoop1</classifier>-->
            <!--<scope>test</scope>-->
        </dependency>
        <dependency>
            <groupId>org.apache.hadoop</groupId>
            <artifactId>hadoop-test</artifactId>
            <version>1.0.0</version>
            <!--<scope>test</scope>-->
        </dependency>
        <!-- Missing dependency for running mini clusters -->
        <dependency>
            <groupId>com.sun.jersey</groupId>
            <artifactId>jersey-core</artifactId>
            <version>1.8</version>
            <!--<scope>test</scope>-->
        </dependency>
    </dependencies>

    <build>
        <finalName>hadoop-max-temperature-text</finalName>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <!--<version>2.3.2</version>-->
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <!--<version>2.4</version>-->
                <configuration>
                    <outputDirectory>${basedir}</outputDirectory>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

-   构建MapReduce作业只需要有hadoop-core依赖
-   使用junit及两个辅助库来运行单元测试
-   hamcrest-all帮助撰写测试断言的匹配符
-   mrunit用于写MapReduce测试

## 用MRUnit来写单元测试

### 关于Mapper

#### MaxTemperatureMapper的单元测试

```java
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.junit.Test;

import java.io.IOException;

public class MaxTemperatureMapperTest {
    @Test
    public void processesValidRecord() throws IOException {
        Text value = new Text("0043011990999991950051518004+68750+023550FM-12+0382" +
        "99999V0203201N00261220001CN9999999N9-0011+99999999999");
        new MapDriver<LongWritable, Text, Text, IntWritable>()
                .withMapper(new MaxTemperatureMapper())
                .withInput(new LongWritable(0), value)
                .withOutput(new Text("1950"), new IntWritable(-11))
                .runTest();
    }
}
```

相应的`MaxTemperatureMapper`类:

```java
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
public class MaxTemperatureMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String year = line.substring(15, 19);
        int airTemperature = Integer.parseInt(line.substring(87, 92));
        context.write(new Text(year), new IntWritable(airTemperature));
    }
}
```

#### 运行

使用Eclipse或者Idea直接运行即可

### 关于Reducer

#### MaxTemperatureReducer的单元测试

```java
public class MaxTemperatureReducerTest {
    @Test
    public void returnsMaximumIntegerInValues() throws IOException {
        new ReduceDriver<Text, IntWritable, Text, IntWritable>()
                .withReducer(new MaxTemperatureReducer())
                .withInputKey(new Text("1950"))
                .withInputValues(Arrays.asList(new IntWritable(10), new IntWritable(5)))
                .withOutput(new Text("1950"), new IntWritable(10))
                .runTest();
    }
}
```

## 在集群上运行

-   本地作业运行器使用单JVM运行一个作业,只要作业需要的所有类都在类路径(classpath)上,那么作业就可以正常运行.

-   分布式环境中,开始的时候作业的类必须打包进作业的JAR文件中并发送给集群.Hadoop通过搜索驱动程序的类路径自动找到作业的JAR文件,该类路径包含了JobConf或Job上的`setJarByClass()`方法中设置的类.如果你想通过文件路径设置一个指定的JAR文件,可以使用`setJar()`方法.

-   使用Ant或Maven等这样的工具可以方便地创建作业的JAR文件(以Maven为例):

    `mvn package -DskipTests`

-   如果每个JAR文件都只有一个作业,可以在JAR文件的manifest中指定要运行的main类.如果没有在manifest中设置main类,则必须在命令行指定.

### 客户端的类路径

由`hadoop jar <jar>设置的用户客户端类路径包括以下几个组成部分`:

-   作业的JAR文件
-   作业JAR文件的lib目录中的所有JAR文件以及类目录(如果定义)
-   HADOOP_CLASSPATH定义的类路径

### 任务的类路径

咋集群上(包括伪分布式模式),map和reduce任务在各自的JVM上运行,他们的类路径不受`HADOOP_CLASSPATH`控制.`HADOOP_CLASSPATH`是一项客户端设置,并只针对驱动程序的JVM的类路径进行设置.

#### 用户任务的类路径有以下几个部分组成

-   作业的JAR文件
-   作业JAR文件的lib目录中包含的所有JAR文件以及类目录
-   使用 `-libjars`选项或`DistributedCache`的`addFileToClassPath()`方法或`Job`添加到分布式缓存的所有文件.

### 打包依赖

给定这些不同的方法来控制客户端和类路径上的内容,也有相应的操作处理作业的库依赖:

-   将库解包和重新打包到作业的JAR
-   对作业的JAR的目录中的库打包
-   保持库与作业的JAR分开,并且通过`HADOOP_CLASSPATH`将他们添加到客户端的类路径,通过`-libjars`将他们添加到任务的类路径

**最后使用分布式缓存的选项是最简单的,因为依赖不需要在作业的JAR中重新创建.同时,分布式缓存意味着在集群上更少的JAR文件转移**

### 任务类路径的优先权

用户的JAR文件被添加到客户端类路径和任务类路径的最后,如果Hadoop使用的库版本和你的代码使用的不同或不相容,在某些情况下可能会引发和Hadoop内置库的依赖冲突.

有时需要控制任务类路径优先放到搜索顺序中.对于任务的类路径,你可以将`mapreduce.task.classpath.first`设为`true`.

