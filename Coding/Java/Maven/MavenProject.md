[toc]

Maven项目实践。

# 组织结构

```shell
project-parent
	- project-foo
	- project-bar
pom.xml
```

项目包含一个父项目，所有子模块parent指定为该项目。

# DependencyManagement

- 统一依赖版本
- 方便依赖版本变更

# revision

项目版本定义在父项目中的properties中，变量名为revision，子模块版本使用`${revision}`变量，并在父项目引用 `flatten-maven-plugin` 插件，更多可参考[这里](https://stackoverflow.com/questions/10582054/maven-project-version-inheritance-do-i-have-to-specify-the-parent-version/51969067#51969067)。这样，整个项目的版本号可通过revision变量来设置。

```xml
<plugin>
    <groupId>org.codehaus.mojo</groupId>
    <artifactId>flatten-maven-plugin</artifactId>
    <version>1.2.5</version>
    <configuration>
        <updatePomFile>true</updatePomFile>
        <flattenMode>resolveCiFriendliesOnly</flattenMode>
    </configuration>
    <executions>
        <execution>
            <id>flatten</id>
            <phase>process-resources</phase>
            <goals>
                <goal>flatten</goal>
            </goals>
        </execution>
        <execution>
            <id>flatten.clean</id>
            <phase>clean</phase>
            <goals>
                <goal>clean</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```



