---
titlt: Python Exception
tags:
	- Python
categories:
	- Python
---

# 捕获异常

```python
try:
    pass
except Exception as e:
    pass
finally:
    pass
```

# 抛出异常

```python
raise Exception('Exception description.')
```

# 追踪异常

```python
import traceback
traceback.print_exc()
```

# 内建异常

```python
BaseException
 +-- SystemExit
 +-- KeyboardInterrupt
 +-- GeneratorExit
 +-- Exception
      +-- StopIteration
      +-- StopAsyncIteration
      +-- ArithmeticError
      |    +-- FloatingPointError
      |    +-- OverflowError
      |    +-- ZeroDivisionError
      +-- AssertionError
      +-- AttributeError
      +-- BufferError
      +-- EOFError
      +-- ImportError
      |    +-- ModuleNotFoundError
      +-- LookupError
      |    +-- IndexError
      |    +-- KeyError
      +-- MemoryError
      +-- NameError
      |    +-- UnboundLocalError
      +-- OSError
      |    +-- BlockingIOError
      |    +-- ChildProcessError
      |    +-- ConnectionError
      |    |    +-- BrokenPipeError
      |    |    +-- ConnectionAbortedError
      |    |    +-- ConnectionRefusedError
      |    |    +-- ConnectionResetError
      |    +-- FileExistsError
      |    +-- FileNotFoundError
      |    +-- InterruptedError
      |    +-- IsADirectoryError
      |    +-- NotADirectoryError
      |    +-- PermissionError
      |    +-- ProcessLookupError
      |    +-- TimeoutError
      +-- ReferenceError
      +-- RuntimeError
      |    +-- NotImplementedError
      |    +-- RecursionError
      +-- SyntaxError
      |    +-- IndentationError
      |         +-- TabError
      +-- SystemError
      +-- TypeError
      +-- ValueError
      |    +-- UnicodeError
      |         +-- UnicodeDecodeError
      |         +-- UnicodeEncodeError
      |         +-- UnicodeTranslateError
      +-- Warning
           +-- DeprecationWarning
           +-- PendingDeprecationWarning
           +-- RuntimeWarning
           +-- SyntaxWarning
           +-- UserWarning
           +-- FutureWarning
           +-- ImportWarning
           +-- UnicodeWarning
           +-- BytesWarning
           +-- ResourceWarning
```

- [参考文档](https://docs.python.org/3/library/exceptions.html#bltin-exceptions)

```matlab
for k=1:m
    for i=1:n+1
        for j=1:n+1
            L = *
            if i == j && x(L) != 0
            	seq(1) = 1
            	c(1) = 1
            	return
            elseif x(L) != 0 && x(L) != 1
            	seq(1) = 1
            	c(1) = 1
            	return
            elseif 
            
            

```

# 遗传算法

- 配置遗传算法

  - 数量
  - 取值范围
  - ...

- ```matlab
  for 生成待判断的数 x
  	if nonLinearCons(x) 符合要求
  		保存
  	else
  		重新生成
  	
  function [c, ceq] = nonLinearCons(x)

  ```

- ​