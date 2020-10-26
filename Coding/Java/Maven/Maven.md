[toc]

# 简介

在Maven工程中，POM（Project Object Model）是基本的工作单元，它是一个包含工程信息和配置详细信息的XML文件，Maven根据该文件构建工程。当Maven执行一个task或者goal，会查看当前文件夹下的POM文件，从中获取需要的配置信息。

# 配置

## 说明

### **结构**

所有的编写的POM文件都继承自Super POM，不用maven版本的Super POM可能会有差异，附录是2.1.x版本Maven的Super POM。

### **Minimal POM**

以下字段是编写POM必须的：

- project root
  - modelVersion
  - groupId
  - artifactId
  - version

比如：

```xml
<project>
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.mycompany.app</groupId>
  <artifactId>my-app</artifactId>
  <version>1</version>
</project>
```

groupId, artifactId 和 version 三个值组成了项目的完整名称，组合格式是`<groupId>:<artifactId>:<version>`，以上面示例配置为例，artifact名称为`com.mycompany.app:my-app:1`。

### 项目继承

在继承（Project Inheritance）时，以下元素会被合并：

- dependencies
- developers and contributors
- plugin lists (including reports)
- plugin executions with matching ids
- plugin configuration
- resources

通过添加parent元素，配置parent POMs，实现POM文件的继承。比如：

- 目录

  ```xml
  .
   |-- my-module
   |   `-- pom.xml
   `-- pom.xml
  ```

- POM配置

  ```xml
  <project>
    <parent>
      <groupId>com.mycompany.app</groupId>
      <artifactId>my-app</artifactId>
      <version>1</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.mycompany.app</groupId>
    <artifactId>my-module</artifactId>
    <version>1</version>
  </project>
  ```

可选地，如果希望groupid、version、/ 和parent相同，可以在子POM中去掉相应字段，上例POM则可改为：

```xml
<project>
  <parent>
    <groupId>com.mycompany.app</groupId>
    <artifactId>my-app</artifactId>
    <version>1</version>
  </parent>
  <modelVersion>4.0.0</modelVersion>
  <artifactId>my-module</artifactId>
</project>
```

**相对路径继承**

如果目录结构如下：

```shell
.
 |-- my-module
 |   `-- pom.xml
 `-- parent
     `-- pom.xml
```

需要添加`<relativePath>` 元素到`parent`字段：

```xml
<project>
  <parent>
    <groupId>com.mycompany.app</groupId>
    <artifactId>my-app</artifactId>
    <version>1</version>
    <relativePath>../parent/pom.xml</relativePath>
  </parent>
  <modelVersion>4.0.0</modelVersion>
  <artifactId>my-module</artifactId>
</project>
```

### 项目集成

项目集成（Project Aggregation）类似于项目继承，相对于项目继承在子模块中配置parent POM信息，项目集成则是在parent POM中配置子模块信息，这样，parent POM就了解了子模块的信息。通过项目继承，parent POM调用的命令，也会应用在子模块中。

通过以下步骤实现Project Aggregation：

- 把 parent POMs 的 packageing值改为 "pom"
- 在 parent POM 中指定模块的路径

示例见附录。

### Inheritance OR Aggregation

- 继承：如果多个Maven工程，使用相似的配置，可以通过抽取相似的配置为parent对项目进行重构。这样通过继承parent POM，来应用parent POM中的项目。
- 集成：如果多个工程一起被构建或处理，可以创建parent工程，在parent中声明他的modules。这样，只需要构建parent即可，其余的也会被构建。
- 继承 && 集成：可以同时使用继承和集成。

### Variables

模型定义中属于单个值元素的任何字段都可以作为变量引用，比如`${project.groupId}`, `${project.version}`, `${project.build.sourceDirectory}`。

**使用定义的变量**

```xml
<!-- 定义 -->
<project>
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.mycompany.app</groupId>
  <artifactId>my-app</artifactId>
  <version>1</version>
</project>

<!-- 使用变量 project.version -->
<version>${project.version}</version>
```

**特殊变量**

- `project.basedir`
  - 当前工程所在的文件夹
- `project.baseUri`
  - 当前工程所在的文件夹，uri表示
- `maven.build.timestamp`
  - 表示生成开始的时间戳

### Properties

Maven中，属性（Properties）是值的占位符，他们的值在该POM内任意范围内可见，通过标记`${X}`使用。

属性设置有以下五种方式：

- `env.X`
  - `env.`开头
  - shell的环境变量，比如`${env.PATH}`
- `elementName.X`
  - 对应element的值，比如：`<project><version>1.0</version></project>` 通过 `${project.version}` 访问
- `settings.x`
  - 相应元素的值包含在`settings.xml`，比如：`<settings><offline>false</offline></settings>`通过`${settings.offline}`访问
- Java系统属性
  - 所有通过`java.lang.System.getProperties()`可以获取的值都是POM文件的属性，可以使用`${java.home}` 语法获取
- x
  - 设置在`<properties />` 内的属性，比如：`<properties><someVar>value</someVar></properties>` 可以通过`${someVar}`使用

示例如下：

```xml
<project>
  ...
  <properties>
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
  </properties>
  ...
</project>
```

### Build

根据POM 4.0.0 XSD规定，Build元素被分为两部分：

- `BaseBuild`
  - 两个Build（project build 和 profile build，即分别为 project下的top-level build 和 profiles 下的 build）的通用元素集（上层配置会覆盖下层）
- `Build`
  - 包含`BaseBuild`集合以及更多的 top-level 定义

比如：

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                      https://maven.apache.org/xsd/maven-4.0.0.xsd">
  ...
  <!-- "Project Build" contains more elements than just the BaseBuild set -->
  <build>...</build>
 
  <profiles>
    <profile>
      <!-- "Profile Build" contains a subset of "Project Build"s elements -->
      <build>...</build>
    </profile>
  </profiles>
</project>
```

#### **BaseBuild 元素集合**

```xml
<build>
  <defaultGoal>install</defaultGoal>
  <directory>${basedir}/target</directory>
  <finalName>${artifactId}-${version}</finalName>
  <filters>
    <filter>filters/filter1.properties</filter>
  </filters>
  ...
</build>
```

- `defaultGoal`
  - 如果没有给定，则执行的默认目标或阶段
- `directory`
  - 存储构建过程中产生的文件的目录，在Maven的说法中叫构建目标
  - 默认为`${basedir}/target`
- `finalName`
  - 构建时绑定的项目名称，默认是`${artifactId}-${version}`
- `filter`
  - 在`filters`文件夹下定义`*.properties` 文件，其包含一系列属性，作为resources的设置
  - maven默认filters文件夹是`${basedir}/src/main/filters/`
  - 示例：`name=value` 定义在 filter文件内， 在resources使用`${name}`访问

#### **Resources**

`resources`元素用于指定项目中包含的资源，资源通常不是代码，不会被编译，用于和工程绑定或其他原因。

比如，Plexus项目需要把配置文件`configuration.xml`放在`META-INF/plexus`内，尽管可以简单地放在`src/main/resources/META-INF/plexus`，但是有时会希望为Plexus建立它自己的文件夹`src/main/plexus`，并把配置文件放在该文件夹内。为了使jar文件可以正确地绑定该资源，可以使用如下的配置：

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                      https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <build>
    ...
    <resources>
      <resource>
        <targetPath>META-INF/plexus</targetPath>
        <filtering>false</filtering>
        <directory>${basedir}/src/main/plexus</directory>
        <includes>
          <include>configuration.xml</include>
        </includes>
        <excludes>
          <exclude>**/*.properties</exclude>
        </excludes>
      </resource>
    </resources>
    <testResources>
      ...
    </testResources>
    ...
  </build>
</project>
```

- `resources`
  - 一个资源元素列表，每个元素描述包含与此项目关联的文件的内容和位置
- `targetPaht`
  - 指定构建时放置资源集的目录结构，默认是base directory
  - 打包在JAR中的资源的路径，通常是META-INF
- `filtering`
  - `true` OR `false`
  - 表示是否要为此资源启用过滤
  - 不必单独定义properties文件，resources文件默认可以使用POM中定义的properties 以及 通过命令行参数（`-D`标记，比如`-Dname=value`）传入的参数
- `directory`
  - 定义资源所在的文件夹，默认是`${basedir}/src/main/resources`
- `includes`
  - 一组文件模式，使用*作为通配符，指定要包含在指定目录下的资源的文件
- `excludes`
  - 结构和`includes`类似，但是指定忽略的文件，优先级大于includes（如果在includes也包含该文件，excludes同样生效）
- `testResources`
  - `testResources`包含`testResource`元素
  - `testResource`
    - 定义类似`resource`，在测试阶段被使用
    - 默认test resources 路径在`${basedir}/src/test/resources`
    - test resources 不会被部署

#### Plugins

配置plugins示例如下：

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                      https://maven.apache.org/xsd/maven-4.0.0.xsd">
  <build>
    ...
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        <version>2.6</version>
        <extensions>false</extensions>
        <inherited>true</inherited>
        <configuration>
          <classifier>test</classifier>
        </configuration>
        <dependencies>...</dependencies>
        <executions>...</executions>
      </plugin>
    </plugins>
  </build>
</project>
```

## 配置项

**project element**

- packaging
  - 打包方式
  - 值
    - jar
    - war
    - pom

## POM Relationships

maven可以管理项目间关系：

- 继承（inheritance）
- 集成（aggregation）
- 依赖（dependencies）

**依赖版本指定**

- `1.0`: "Soft" requirement on 1.0 (just a recommendation, if it matches all other ranges for the dependency)
- `[1.0]`: "Hard" requirement on 1.0
- `(,1.0]`: x <= 1.0
- `[1.2,1.3]`: 1.2 <= x <= 1.3
- `[1.0,2.0)`: 1.0 <= x < 2.0
- `[1.5,)`: x >= 1.5
- `(,1.0],[1.2,)`: x <= 1.0 or x >= 1.2; multiple sets are comma-separated
- `(,1.1),(1.1,)`: this excludes 1.1 (for example if it is known not to work in combination with this library)

### 依赖

依赖列表是POM的基础。大多数项目的正确运行，需要依赖其他项目。在编译和执行其他goal时，Maven会下载并且链接相应的依赖。

添加依赖示例：

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                      https://maven.apache.org/xsd/maven-4.0.0.xsd">
  ...
  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <type>jar</type>
      <scope>test</scope>
      <optional>true</optional>
    </dependency>
    ...
  </dependencies>
  ...
</project>
```

**字段说明**

- groupId, artifactId, version
  - 依赖包信息
  - groupId, artifactId：定义相应的Maven coordinates
  - version：依赖项目的版本号

### dependencyManagement

dependencyManagement用于统一版本管理，在多模块项目中，如果父项目中dependencyManagement指定了依赖版本和scoope，那么在子模块中可以省略。

# 其他

## 依赖非Maven仓库中项目

有三种方式，可以在Maven项目中，引用非Maven库中的项目：

- 使用maven的install插件
  - `mvn install:install-file -Dfile=non-maven-proj.jar -DgroupId=some.group -DartifactId=non-maven-proj -Dversion=1 -Dpackaging=jar`
- 创建自己的`maven`仓库，并且将其部署在私有仓库中
- 设置依赖的`scope`为`system`，并且定义`systemPath`

## Tips

- 默认打包类型是`jar`
- 默认依赖库地址`http://repo.maven.apache.org/maven2`

## 打包可执行jar

- [点击查看参考](https://blog.sonatype.com/2009/08/how-to-make-an-executable-jar-in-maven/)

# 附录

## Super POM

Super POM for maven 2.1.x.

```xml
<project>
  <modelVersion>4.0.0</modelVersion>
  <name>Maven Default Project</name>
 
  <repositories>
    <repository>
      <id>central</id>
      <name>Maven Repository Switchboard</name>
      <layout>default</layout>
      <url>http://repo1.maven.org/maven2</url>
      <snapshots>
        <enabled>false</enabled>
      </snapshots>
    </repository>
  </repositories>
 
  <pluginRepositories>
    <pluginRepository>
      <id>central</id>
      <name>Maven Plugin Repository</name>
      <url>http://repo1.maven.org/maven2</url>
      <layout>default</layout>
      <snapshots>
        <enabled>false</enabled>
      </snapshots>
      <releases>
        <updatePolicy>never</updatePolicy>
      </releases>
    </pluginRepository>
  </pluginRepositories>
 
  <build>
    <directory>${project.basedir}/target</directory>
    <outputDirectory>${project.build.directory}/classes</outputDirectory>
    <finalName>${project.artifactId}-${project.version}</finalName>
    <testOutputDirectory>${project.build.directory}/test-classes</testOutputDirectory>
    <sourceDirectory>${project.basedir}/src/main/java</sourceDirectory>
    <!-- TODO: MNG-3731 maven-plugin-tools-api < 2.4.4 expect this to be relative... -->
    <scriptSourceDirectory>src/main/scripts</scriptSourceDirectory>
    <testSourceDirectory>${project.basedir}/src/test/java</testSourceDirectory>
    <resources>
      <resource>
        <directory>${project.basedir}/src/main/resources</directory>
      </resource>
    </resources>
    <testResources>
      <testResource>
        <directory>${project.basedir}/src/test/resources</directory>
      </testResource>
    </testResources>
   <pluginManagement>
       <plugins>
         <plugin>
           <artifactId>maven-antrun-plugin</artifactId>
           <version>1.3</version>
         </plugin>       
         <plugin>
           <artifactId>maven-assembly-plugin</artifactId>
           <version>2.2-beta-2</version>
         </plugin>         
         <plugin>
           <artifactId>maven-clean-plugin</artifactId>
           <version>2.2</version>
         </plugin>
         <plugin>
           <artifactId>maven-compiler-plugin</artifactId>
           <version>2.0.2</version>
         </plugin>
         <plugin>
           <artifactId>maven-dependency-plugin</artifactId>
           <version>2.0</version>
         </plugin>
         <plugin>
           <artifactId>maven-deploy-plugin</artifactId>
           <version>2.4</version>
         </plugin>
         <plugin>
           <artifactId>maven-ear-plugin</artifactId>
           <version>2.3.1</version>
         </plugin>
         <plugin>
           <artifactId>maven-ejb-plugin</artifactId>
           <version>2.1</version>
         </plugin>
         <plugin>
           <artifactId>maven-install-plugin</artifactId>
           <version>2.2</version>
         </plugin>
         <plugin>
           <artifactId>maven-jar-plugin</artifactId>
           <version>2.2</version>
         </plugin>
         <plugin>
           <artifactId>maven-javadoc-plugin</artifactId>
           <version>2.5</version>
         </plugin>
         <plugin>
           <artifactId>maven-plugin-plugin</artifactId>
           <version>2.4.3</version>
         </plugin>
         <plugin>
           <artifactId>maven-rar-plugin</artifactId>
           <version>2.2</version>
         </plugin>        
         <plugin>                
           <artifactId>maven-release-plugin</artifactId>
           <version>2.0-beta-8</version>
         </plugin>
         <plugin>                
           <artifactId>maven-resources-plugin</artifactId>
           <version>2.3</version>
         </plugin>
         <plugin>
           <artifactId>maven-site-plugin</artifactId>
           <version>2.0-beta-7</version>
         </plugin>
         <plugin>
           <artifactId>maven-source-plugin</artifactId>
           <version>2.0.4</version>
         </plugin>         
         <plugin>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>2.4.3</version>
         </plugin>
         <plugin>
           <artifactId>maven-war-plugin</artifactId>
           <version>2.1-alpha-2</version>
         </plugin>
       </plugins>
     </pluginManagement>
  </build>
 
  <reporting>
    <outputDirectory>${project.build.directory}/site</outputDirectory>
  </reporting>
  <profiles>
    <profile>
      <id>release-profile</id>
 
      <activation>
        <property>
          <name>performRelease</name>
          <value>true</value>
        </property>
      </activation>
 
      <build>
        <plugins>
          <plugin>
            <inherited>true</inherited>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-source-plugin</artifactId>
            <executions>
              <execution>
                <id>attach-sources</id>
                <goals>
                  <goal>jar</goal>
                </goals>
              </execution>
            </executions>
          </plugin>
          <plugin>
            <inherited>true</inherited>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-javadoc-plugin</artifactId>
            <executions>
              <execution>
                <id>attach-javadocs</id>
                <goals>
                  <goal>jar</goal>
                </goals>
              </execution>
            </executions>
          </plugin>
          <plugin>
            <inherited>true</inherited>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-deploy-plugin</artifactId>
            <configuration>
              <updateReleaseInfo>true</updateReleaseInfo>
            </configuration>
          </plugin>
        </plugins>
      </build>
    </profile>
  </profiles>
 
</project>
```

## Project Aggregation Example

**文件目录结构**

```shell
.
 |-- my-module
 |   `-- pom.xml
 `-- pom.xml
```

**./pom.xml**

```xml
<project>
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.mycompany.app</groupId>
  <artifactId>my-app</artifactId>
  <version>1</version>
  <packaging>pom</packaging>
 
  <modules>
    <module>my-module</module>
  </modules>
</project>
```

**./my-module/pom.xml**

```xml
<project>
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.mycompany.app</groupId>
  <artifactId>my-module</artifactId>
  <version>1</version>
</project>
```

## Project element

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                      http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
 
  <!-- The Basics -->
  <groupId>...</groupId>
  <artifactId>...</artifactId>
  <version>...</version>
  <packaging>...</packaging>
  <dependencies>...</dependencies>
  <parent>...</parent>
  <dependencyManagement>...</dependencyManagement>
  <modules>...</modules>
  <properties>...</properties>
 
  <!-- Build Settings -->
  <build>...</build>
  <reporting>...</reporting>
 
  <!-- More Project Information -->
  <name>...</name>
  <description>...</description>
  <url>...</url>
  <inceptionYear>...</inceptionYear>
  <licenses>...</licenses>
  <organization>...</organization>
  <developers>...</developers>
  <contributors>...</contributors>
 
  <!-- Environment Settings -->
  <issueManagement>...</issueManagement>
  <ciManagement>...</ciManagement>
  <mailingLists>...</mailingLists>
  <scm>...</scm>
  <prerequisites>...</prerequisites>
  <repositories>...</repositories>
  <pluginRepositories>...</pluginRepositories>
  <distributionManagement>...</distributionManagement>
  <profiles>...</profiles>
</project>
```

# 参考

- [Introduction to the POM](https://maven.apache.org/guides/introduction/introduction-to-the-pom.html)

- [POM Reference](https://maven.apache.org/pom.html#What_is_the_POM)
- [Getting Started](https://maven.apache.org/guides/getting-started/)