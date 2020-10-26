[toc]

# 创建项目

```shell
$ mvn archetype:generate
```

# 打包

```shell
$ mvn package
```

# 可执行jar

```xml
<build>
  <resources>
    <resource>
      <directory>
        ${project.basedir}/src/main/resources
      </directory>
      <filtering>true</filtering>
    </resource>
  </resources>

  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-assembly-plugin</artifactId>
      <executions>
        <execution>
          <goals>
            <goal>attached</goal>
          </goals>
          <phase>package</phase>
          <configuration>
            <descriptorRefs>
              <descriptorRef>jar-with-dependencies</descriptorRef>
            </descriptorRefs>
            <archive>
              <manifest>
                <mainClass>top.szhkai.mitest.TestZKFacade</mainClass>
              </manifest>
            </archive>
          </configuration>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```

# 搜索依赖包

```shell
 $ mvn dependency:tree | grep recommend-service-common
```

# 指定编译版本

```xml
<plugins>  
	  <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.5.1</version>
        <configuration>
            <source>1.8</source>
            <target>1.8</target>
        </configuration>
    </plugin>
	  ...
</plugins>
```

# 配置多源文件

## resources

```xml
   <resources>
        <resource>
            <directory>src/main/java</directory>
            <includes>
                <include>**/*.java</include>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
        </resource>

        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/*.java</include>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
        </resource>

        <resource>
            <directory>src/main/generated</directory>
            <includes>
                <include>**/*.java</include>
                <include>**/*.properties</include>
                <include>**/*.xml</include>
            </includes>
        </resource>
    </resources>
```

## sourceDirectory

```xm
<generatedSourcesDirectory>src/main/generated</generatedSourcesDirectory>
```

## maven-compiler-plugin

```xml
<build>
    <sourceDirectory>.</sourceDirectory>
    <plugins>
        <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <configuration>
        <includes>
            <include>src/main/java/**/*.java</include>
            <include>src/main/scala/**/*.scala</include>
        </includes>
        </configuration>
        </plugin>
    </plugins>
</build>
```

## build-helper-maven-plugin

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.codehaus.mojo</groupId>
            <artifactId>build-helper-maven-plugin</artifactId>
            <executions>
                <execution>
                    <phase>generate-sources</phase>
                    <goals>
                        <goal>add-source</goal>
                    </goals>
                    <configuration>
                        <sources>
                            <source>src/main/generated</source>
                        </sources>
                    </configuration>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

