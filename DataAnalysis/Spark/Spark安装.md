# 安装Hadoop及Spark(Ubuntu 16.04)

## 安装JDK

-   下载jdk(以jdk-8u91-linux-x64.tar.gz为例)

-   新建文件夹

    `sudo mkdir /usr/lib/jvm`

-   解压下载的jdk文件并移动到新建的文件夹下

    `sudo tar -xzvf jdk-8u91-linux-x64.tar.gz -C /usr/lib/jvm`

-   进入jvm文件夹并重命名解压出来的文件夹

    ```shell
    cd /usr/lib/jvm
    sudo mv jdk1.8.0_91 jdk
    ```

-   添加环境变量

    ```shell
    sudo vim /etc/profile
    # 添加如下配置
    export JAVA_HOME=/usr/lib/jvm/jdk
    export CLASSPATH=.:$JAVA_HOME/lib:$JAVA_HOME/jre/lib:$CLASSPATH
    export PATH=$JAVA_HOME/bin:$JAVA_HOME/jre/bin:$PATH
    ```

-   使配置生效

    `source /etc/profile`

-   测试

    `java -version`

## 安装Scala

-   类似于jdk的安装

-   下载scala(以scala-2.11.8.tgz为例)

-   解压下载的scala文件

    `sudo tar -xzvf scala-2.11.8.tgz -C /usr/local`

-   重命名

    ```shell
    cd /usr/local
    sudo mv scala-2.11.8 scala
    ```

-   添加环境变量

    ```shell
    sudo vim /etc/profile
    # 在最后添加下面内容
    export SCALA_HOME=/usr/local/scala
    export PATH=$SCALA_HOME/bin:$PATH
    ```

-   使配置生效

    `source /etc/profile`

-   测试

    `scala -version`

## 安装Hadoop

**Spark默认使用HDFS充当持久化层,所以需要安装Hadoop,当然也可以不安装**

### 参考

-   [安装单机/伪分布式](http://www.powerxing.com/install-hadoop/)
-   [安装集群](http://www.powerxing.com/install-hadoop-cluster/)

### 安装

-   安装ssh

    `sudo apt install openssh-server`

-   配置ssh无密登陆

    ```shell
    ssh-keygen -t rsa	# 一直回车
    cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
    ```

-   测试ssh无密登陆

    `ssh localhost	# 如果不提示输入密码则配置成功`

-   下载Hadoop(以hadoop-2.7.2.tar.gz为例)

-   解压

    `sudo tar -xzvf hadoop-2.7.2.tar.gz -C /usr/local`

-   重命名

    ```shell
    cd /usr/local
    sudo mv hadoop-2.7.2 hadoop
    ```

-   修改权限

    ```shell
    cd /usr/local
    sudo chown -R yourusername:yourusername hadoop
    ```

-   配置环境变量

    ```shell
    sudo vim /etc/profile
    # 在最后添加下面代码
    export HADOOP_HOME=/usr/local/hadoop
    export PATH=$HADOOP_HOME/bin:$HADOOP_HOME/sbin:$PATH
    ```

-   测试

    `hadoop version`

### Hadoop伪分布式配置

-   修改配置文件`core-site.xml`

    ```shell
    cd /usr/local/hadoop
    vim ./etc/hadoop/core-site.xml
    # 修改为如下
    <configuration>
            <property>
                 <name>hadoop.tmp.dir</name>
                 <value>file:/usr/local/hadoop/tmp</value>
                 <description>Abase for other temporary directories.</description>
            </property>
            <property>
                 <name>fs.defaultFS</name>
                 <value>hdfs://localhost:9000</value>
            </property>
    </configuration>
    ```

-   修改配置文件`hdfs-site.xml`

    ```shell
    cd /usr/local/hadoop
    vim ./etc/hadoop/hdfs-site/xml
    # 修改为如下
    <configuration>
            <property>
                 <name>dfs.replication</name>
                 <value>1</value>
            </property>
            <property>
                 <name>dfs.namenode.name.dir</name>
                 <value>file:/usr/local/hadoop/tmp/dfs/name</value>
            </property>
            <property>
                 <name>dfs.datanode.data.dir</name>
                 <value>file:/usr/local/hadoop/tmp/dfs/data</value>
            </property>
    </configuration>
    ```

-   修改配置文件`hadoop-env.sh`

    ```shell
    cd /usr/local/hadoop
    vim ./etc/hadoop/hadoop-env.sh
    # 将 export JAVA_HOME=${JAVA_HOME} 更改为:
    export JAVA_HOME=/usr/lib/jvm/jdk
    ```

-   执行NameNode格式化

    `hdfs namenode -format`

-   运行

    `start-dfs.sh`

-   测试

    `jps`

    有如下几个进程

    ```shell
    5939 Jps
    5636 DataNode
    5493 NameNode
    5814 SecondaryNameNode
    ```

-   通过浏览器查看

    在浏览器中输入一下地址:`localhost:50070`

### 配置YARN

-   修改配置文件`mapred-site.xml`

    ```shell
    cd /usr/local/hadoop
    cp ./etc/hadoop/mapred-site.xml.template ./etc/hadoop/mapred-site.xml
    vim ./etc/hadoop/mapred-site.xml
    # 修改为如下配置
    <configuration>
            <property>
                 <name>mapreduce.framework.name</name>
                 <value>yarn</value>
            </property>
    </configuration>
    ```

-   修改配置文件`yarn-site.xml`

    ```shell
    cd /usr/local/hadoop
    vim ./etc/hadoop/yarn-site.xml
    # 修改为以下配置
    <configuration>
            <property>
                 <name>yarn.nodemanager.aux-services</name>
                 <value>mapreduce_shuffle</value>
                </property>
    </configuration>
    ```

-   编写启动脚本

    ```shell
    #!/bin/bash
    # 启动hadoop
    start-dfs.sh
    # 启动yarn
    start-yarn.sh
    # 启动历史服务器,以便在Web中查看任务运行情况
    mr-jobhistory-daemon.sh start historyserver
    ```

-   编写停止脚本

    ```shell
    #!/bin/bash
    # 停止历史服务器
    mr-jobhistory-daemon.sh stop historyserver
    # 停止yarn
    stop-yarn.sh
    # 停止hadoop
    stop-dfs.sh
    ```

-   通过 Web 界面查看任务的运行情况

    浏览器中输入地址:`localhost:8088`

## 安装Spark

-   下载spark(以spark-2.0.0-bin-hadoop2.7.tgz为例)

-   解压下载的spark文件

    `sudo tar -zxf spark-2.0.0-bin-hadoop2.7.tgz -C /usr/local`

-   重命名

    ```shell
    cd /usr/local
    sudo mv spark-2.0.0-bin-hadoop2.7 spark
    ```

-   添加环境变量

    ```shell
    sudo vim /etc/profile
    # 在最后添加下面内容
    export SPARK_HOME=/usr/local/spark
    export PATH=$SPARK_HOME/bin:$SPARK_HOME/sbin:$PATH
    ```

-   修改一下权限

    ```shell
    cd /usr/local
    sudo chown -R yourusername:yourusername ./spark
    ```

-   拷贝配置文件

    ```shell
    cd /usr/local/spark
    cp ./conf/spark-env.sh.template ./conf/spark-env.sh
    ```

-   修改配置文件

    ```shell
    cd /usr/loca/spark
    vim ./conf/spark-env.sh
    # 添加下面一行
    export SPARK_DIST_CLASSPATH=$(/usr/local/hadoop/bin/hadoop classpath)
    export JAVA_HOME=/usr/lib/jvm/jdk
    ```

-   运行简单示例

    `/usr/local/spark/bin/run-example SparkPi 2>&1 | grep "Pi is roughly"`

-   启动Spark

    `/usr/local/spark/sbin/start-all.sh`

-   编写脚本

    启动Hadoop以及Spark

    ```shell
    #!/bin/bash
    # 启动Hadoop以及yarn
    start-dfs.sh
    start-yarn.sh
    # 启动历史服务器
    mr-jobhistory-daemon.sh start historyserver
    # 启动Spark
    /usr/local/spark/sbin/start-all.sh
    ```

    停止Hadoop以及Spark

    ```shell
    #!/bin/bash
    # 停止Spark
    stop-dfs.sh
    stop-yarn.sh
    # 停止历史服务器
    mr-jobhistory-daemon.sh stop historyserver
    # 停止Hadoop以及yarn
    /usr/local/hadoop/sbin/stop-all.sh
    ```

-   通过WEB页面查看

    浏览器中输入地址:`localhost:8080`