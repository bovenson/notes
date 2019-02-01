---
title: Python logging
tags:
	- logging
categories:
	- Python
---

# 级别

- DEBUG
- INFO
- WARNING
- ERROR
- CRITICAL

# 配置

- asctime
  - 时间
- levelname
  - 级别
- message
  - 消息
- funcName
  - 函数名称
- lineno
  - 代码行

```python
import logging

LOG_FORMAT = "[%(asctime)s %(funcName)s() %(lineno)i] [%(levelname)s]  %(message)s"
DATE_FORMAT = "%m/%d/%Y %H:%M:%S %p"

logging.basicConfig(level=logging.DEBUG, filename="i.log", format=LOG_FORMAT, datefmt=DATE_FORMAT)
```

# 问题

- 无法输出到文件/打印日志？
  - 要确保在调用`logging.basicConfig`之前没有使用`logging`