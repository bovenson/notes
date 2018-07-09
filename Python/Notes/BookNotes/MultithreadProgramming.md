---
title: Python 中的多线程编程
tags:
	- 多线程
categories:
	- Python
---

# 线程和Python

## 全局解释器锁

Python代码的执行是由Python虚拟机进行控制的。Python在设计时是这样考虑的，在主循环中同时只能有一个控制线程执行。尽管Python解释器中可以运行多个线程，但是在任意给定时刻只有一个线程会被解释器执行。

对Python虚拟机的访问是由**全局解释器锁（GIL）**控制的。这个锁就是用来保证同时只能有一个线程运行。

在多线程环境中，Python虚拟机将按照下面所述的方式执行：

- 设置GIL
- 切换进一个线程去运行
- 执行下面操作之一
  - 指定数量的字节码指令
  - 线程主动让出控制权（可以调用`time.sleep(0)`来完成）
- 把线程设置回睡眠状态（切换出线程）
- 解锁GIL
- 重复上述步骤

在调用外部代码（比如任意C/C++扩展的内置函数）时，GIL会保持锁定，直至函数执行结束。

## 退出线程

当一个线程完成函数的执行时，它就会退出。

另外还可以使用如下方法退出线程：

- 调用`thread.exit()`
- 调用`sys.exit()`
- 抛出`SystemExit`异常

避免使用`thread` 模块，因为其，在主线程退出之后，所有其他线程都会在没有清理的情况下直接退出。而另一个模块`threading`会确保在所有重要的子线程推出前，保证整个进程的存活。

## 相关模块

Python提供了多个模块来支持多线程编程，包括`thread`、`threading`、`Queue`模块等。程序可以使用`thread`和`threading`模块来创建与管理线程。

`thread`模块提供了基本的线程和锁定支持；而`threading`模块提供了更高级别、功能更全面的线程管理。使用`Queue`模块，用户可以创建一个队列数据结构，用于在多线程之间进行共享。

> **避免使用thread模块**
>
> 推荐使用更高级别的threading模块，而不使用thread模块有很多原因。threading模块更加先进，有更好的线程支持，并且thread模块中的一些属性会和threading模块有冲突，另一个原因是低级别的thread模块拥有的同步原语很少，而threading模块则很多。
>
> 另一个原因是，thread模块对于进程何时退出没有控制。当主线程结束时，所有其他线程也都强制结束。

# `thread`模块

除了派生线程外，thread模块还提供了基本的同步数据结构，称为**锁对象**（lock object，也叫原语锁、简单锁、互斥锁、互斥和二进制信号量）。

thread模块的核心函数是`start_new_thread()`。它的参数包括函数（对象）、函数的参数以及可选的关键字参数，将专门派生新的线程来调用这个函数。

# 示例

## 使用单线程执行循环

```python
#!/bin/python
# coding: utf-8


from time import sleep, ctime


def loop0():
    print('start loop 0 at:', ctime())
    sleep(4)
    print('loop 0 done at:', ctime())


def loop1():
    print('start loop 1 at:', ctime())
    sleep(2)
    print('loop 1 done at:', ctime())


def main():
    print('starting at:', ctime())
    loop0()
    loop1()
    print('all DONE at:', ctime())


if __name__ == '__main__':
    main()

```

**执行结果**

```shell
/usr/bin/python3.5 /home/bovenson/Git/notes/Python/Code/LearnPythonCode/multithread_programming/onethr.py
starting at: Tue Jun  5 16:40:41 2018
start loop 0 at: Tue Jun  5 16:40:41 2018
loop 0 done at: Tue Jun  5 16:40:45 2018
start loop 1 at: Tue Jun  5 16:40:45 2018
loop 1 done at: Tue Jun  5 16:40:47 2018
all DONE at: Tue Jun  5 16:40:47 2018

Process finished with exit code 0
```

