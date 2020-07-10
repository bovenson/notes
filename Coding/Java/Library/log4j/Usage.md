---
title: log4j使用
---

# 使用

```java
// 引用
import org.apache.log4j.Logger;

// 获取实例
public class UserManageServiceImplTest {
    private Logger logger = Logger.getLogger(UserManageServiceImpl.class);
  	...
}

// 使用
logger.debug(...);
```

# 配置

文件 `log4j.properties`

```
log4j.rootLogger=DEBUG,A1
log4j.logger.org.mybatis = DEBUG
log4j.appender.A1=org.apache.log4j.ConsoleAppender
log4j.appender.A1.layout=org.apache.log4j.PatternLayout
log4j.appender.A1.layout.ConversionPattern=%-d{yyyy-MM-dd HH:mm:ss,SSS} [%t] [%c]-[%p] %m%n

# 设置日志输出级别
log4j.category.org.springframework=WARN
log4j.category.org.mybatis=WARN
#log4j.category.com.neu.cse=WARN
log4j.category.com.neu.cse.powercloud.mapper=WARN
log4j.category.com.alibaba.druid=WARN
log4j.category.org.apache.ibatis=WARN
```

