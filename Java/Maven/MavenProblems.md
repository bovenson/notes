# 项目RUL路径问题

解决访问WEB项目需要添加项目名称问题。

```xml
<!-- 配置Tomcat插件 -->
<plugin>
  <groupId>org.apache.tomcat.maven</groupId>
  <artifactId>tomcat7-maven-plugin</artifactId>
  <version>2.2</version>
  <configuration>
    <port>8080</port>
    <path>/</path>
    <url>http://127.0.0.1:8080/manager/text</url>
    <username>tomcat</username>
    <password>tomcat</password>
  </configuration>
</plugin>
```

