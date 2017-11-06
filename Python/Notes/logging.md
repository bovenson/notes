# logging模块

## 什么时候使用logging



| Task you want to perform                 | The best tool for the task               |
| ---------------------------------------- | ---------------------------------------- |
| Display console output for ordinary usage of a command line script or program | `print()`                                |
| Report events that occur during normal operation of a program (e.g. for status monitoring or fault investigation) | `logging.info()` (or `logging.debug()` for very detailed output for diagnostic purposes) |
| Issue a warning regarding a particular runtime event | [`warnings.warn()`](http://www.szhkai.win/docs/Python/python-3.6.1rc1-docs-html/library/warnings.html#warnings.warn) in library code if the issue is avoidable and the client application should be modified to eliminate the warning[`logging.warning()`](http://www.szhkai.win/docs/Python/python-3.6.1rc1-docs-html/library/logging.html#logging.warning) if there is nothing the client application can do about the situation, but the event should still be noted |
| Report an error regarding a particular runtime event | Raise an exception                       |
| Report suppression of an error without raising an exception (e.g. error handler in a long-running server process) | [`logging.error()`](http://www.szhkai.win/docs/Python/python-3.6.1rc1-docs-html/library/logging.html#logging.error), [`logging.exception()`](http://www.szhkai.win/docs/Python/python-3.6.1rc1-docs-html/library/logging.html#logging.exception) or [`logging.critical()`](http://www.szhkai.win/docs/Python/python-3.6.1rc1-docs-html/library/logging.html#logging.critical) as appropriate for the specific error and application domain |

## 级别

| Level       | When it’s used                           | Numeric value |
| ----------- | ---------------------------------------- | ------------- |
| `DEBUG`     | Detailed information, typically of interest only when diagnosing problems. | 10            |
| `INFO`      | Confirmation that things are working as expected. | 20            |
| ` WARNING`  | An indication that something unexpected happened, or indicative of some problem in the near future (e.g. ‘disk space low’). The software is still working as expected. | 30            |
| ` ERROR`    | Due to a more serious problem, the software has not been able to perform some function. | 40            |
| ` CRITICAL` | A serious error, indicating that the program itself may be unable to continue running. | 50            |
| `NOTSET`    |                                          | 0             |



## 基本用法

### 代码

```python
import logging

logging.debug('This is debug message')
logging.info('This is info message')
logging.warning('This is warning message')
logging.error('This is error message')
logging.critical("This is critical message")
```

### 输出

```python
WARNING:root:This is warning message
ERROR:root:This is error message
CRITICAL:root:This is critical message
```

**注意**

- `DEBUG`和`INFO`级别日志没有在控制台显示, 是因为默认级别是`WARNING`

## 保存到文件

### 代码

```python
import logging

logging.basicConfig(filename='example.log',level=logging.DEBUG)		# 设置日志路径及文件名 和 记录日志的级别
logging.debug('This message should go to the log file')
logging.info('So should this')
logging.warning('And this, too')
```

## 输出

`example.log`内的内容

```shell
DEBUG:root:This message should go to the log file
INFO:root:So should this
WARNING:root:And this, too
```

