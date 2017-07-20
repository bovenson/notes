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

-   配置默认JDK

    ```
    sudo update-alternatives --install /usr/lib/java java /usr/lib/jvm/jdk/bin/java 300  
    sudo update-alternatives --install /usr/lib/javac javac /usr/lib/jvm/jdk/bin/javac 300
    # 列出jdk版本，其中被“*”标注的为默认JDK, 如果要维持当前默认JDK，直接按回车键即可；否则输入相应JDK的编号将其设置为默认版本。
    sudo update-alternatives --config java  
    ```

-   测试

    `java -version`