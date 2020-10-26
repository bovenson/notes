# Maven是什么

**Apache Maven**是一个软件项目管理和综合工具,基于项目对象模型(POM)的概念,Maven可以从一个中心资料片管理项目构建,报告和文件.

-   Maven提供给开发人员构建一个完整生命周期框架.开发团队可以自动完成项目的基础工具建设,Maven使用标准的目录结构和默认构建生命周期.
-   在多个开发团队环境时,Maven可以设置按标准在非常短的时间里完成配置工作.由于大部分项目的设置都很简单,并且可重复使用,Maven让开发人员的工作更轻松,同时创建报表,检查,构建和测试自动化设置.
-   Maven提供了开发人员的方式来管理:
    -   Builds
    -   Documentation
    -   Reporting
    -   Dependencies
    -   SCMs
    -   Releases
    -   Distribution
    -   mailing list
-   概括地说,Maven简化和标准化项目建设过程.处理编译,分配,文档,团队协作和其他任务的无缝连接.Maven增加可重用性并负责建立相关的任务.

# Maven目标

Maven主要目标是提供给开发人员:

-   项目是可重复使用,易维护，更容易理解的一个综合模型
-   插件或交互的工具,这种声明性的模式

Maven项目的结构和内容在一个XML文件中声明,pom.xml 项目对象模型(POM),这是整个Maven系统的基本单元.有关详细信息,请参阅[Maven POM](http://www.yiibai.com/maven/maven_pom.htmll)的部分.

[Apache Maven](http://maven.apache.org/) 是一种创新的软件项目管理工具,提供了一个项目对象模型(POM)文件的新概念来管理项目的构建,相关性和文档.最强大的功能就是能够自动下载项目依赖库.

# Maven启用代理访问

-   找到文件`{M2_HOME}/conf/settings.xml`

-   找到下列代码:

    ```xml
    <!-- proxies
       | This is a list of proxies which can be used on this machine to connect to the network.
       | Unless otherwise specified (by system property or command-line switch), the first proxy
       | specification in this list marked as active will be used.
       |-->
      <proxies>
        <!-- proxy
         | Specification for one proxy, to be used in connecting to the network.
         |
        <proxy>
          <id>optional</id>
          <active>true</active>
          <protocol>http</protocol>
          <username>proxyuser</</username>
          <password>proxypass</password>
          <host>proxy.host.net</host>
          <port>80</port>
          <nonProxyHosts>local.net|some.host.com</nonProxyHosts>
        </proxy>
        -->
      </proxies>
    ```

-   修改将上述代码,设置代理服务器

# Maven本地资源库

Maven的本地资源库是用来存储所有项目的依赖关系(插件jar和其他文件，这些文件被Maven下载)到本地文件夹.

## 更新Maven的本地库

-   找到文件``{M2_HOME}/conf/settings.xml``

-   修改如下代码:

    ```xml
    <settings><!-- localRepository
       | The path to the local repository maven will use to store artifacts.
       |
       | Default: ~/.m2/repository
      <localRepository>/path/to/local/repo</localRepository>
      --><localRepository>D:\software\apache-maven\repository</localRepository>
    ```

#  Maven中央储存库

当你建立一个 Maven 的项目,Maven 会检查你的 pom.xml 文件,以确定哪些依赖下载.首先,Maven 将从本地资源库获得 Maven 的本地资源库依赖资源,如果没有找到,然后把它会从默认的 Maven [中央存储库](https://repo.maven.apache.org/maven2/) 查找下载.

# 从Maven远程存储库下载

**Maven的依赖库查询顺序:**

1.  在 Maven 本地资源库中搜索,如果没有找到,进入第 2 步,否则退出
2.  在 Maven 中央存储库搜索,如果没有找到,进入第 3 步,否则退出

3.  在给定url的远程存储库搜索,如果没有找到,提示错误信息,否则退出

### 给定URL的远程库

```xml
<repositories>
	<repository>
	    <id>java.net</id>
	    <url>https://maven.java.net/content/repositories/public/</url>
	</repository>
</repositories>
```

**如果不注明version, maven将保持包是最新版本**

# 定制库到Maven本地资源库

将本地的jar包导入到Maven本地资源库(以kaptcha为例):

1.  找到kaptcha jar包所在位置

2.  使用mvn命令安装到本地资源库:

    ```powershell
    mvn install:install-file -Dfile=c:\kaptcha-{version}.jar -DgroupId=com.google.code -DartifactId=kaptcha -Dversion={version} -Dpackaging=jar
    ```

    示例:

    ```shell
    D:\>mvn install:install-file -Dfile=c:\kaptcha-2.3.jar -DgroupId=com.google.code 
    -DartifactId=kaptcha -Dversion=2.3 -Dpackaging=jar
    [INFO] Scanning for projects...
    [INFO] Searching repository for plugin with prefix: 'install'.
    [INFO] ------------------------------------------------------------------------
    [INFO] Building Maven Default Project
    [INFO]    task-segment: [install:install-file] (aggregator-style)
    [INFO] ------------------------------------------------------------------------
    [INFO] [install:install-file]
    [INFO] Installing c:\kaptcha-2.3.jar to 
    D:\maven_repo\com\google\code\kaptcha\2.3\kaptcha-2.3.jar
    [INFO] ------------------------------------------------------------------------
    [INFO] BUILD SUCCESSFUL
    [INFO] ------------------------------------------------------------------------
    [INFO] Total time: < 1 second
    [INFO] Finished at: Tue May 12 13:41:42 SGT 2014
    [INFO] Final Memory: 3M/6M
    [INFO] ------------------------------------------------------------------------
    ```

3.  安装完毕后,就在pom.xml中声明kaptcha的坐标:

    ```xml
    <dependency>
          <groupId>com.google.code</groupId>
          <artifactId>kaptcha</artifactId>
          <version>2.3</version>
    </dependency>
    ```

    

# Maven依赖关系中Scope的作用

主要管理依赖的部署,目前scope可以有五个值:

-   **compile:**缺省值,适用于所有阶段,会随项目一期发布
-   **provided:**类似compile,期望JDK,容器或使用者会提供这个依赖
-   **runtime:**只在运行时使用,如JDBC驱动,适用于运行和测试阶段
-   **test:**只在测试时使用,用于编译和运行测试代码,不会随项目发布
-   **system:**类似provided,需要显式提供包含依赖的jar,Maven不会在Repository中查找它

# 使用

- `Run as -> Maven install`
- `Run as -> Maven build... -> clean tomcat7:run`