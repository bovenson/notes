---
title: 多线程编程
tags:
	- 多线程
categories:
	- 编程基础
---

# 简介

多线程编程对于具有如下特点的编程任务而言是非常理想的：

- 本质上是异步的
- 需要多个并发活动
- 每个活动的处理顺序可能是不确定的，或者说是随机的、不可预测的

这种编程任务可以被组织或划分成多个执行流，其中每个执行流都有一个指定要完成的任务。

# 线程和进程

## 进程

计算机**程序**只是存储在磁盘上的可执行二进制文件。只有把它们加载到内存中并被操作系统调用，才拥有其生命期。

**进程**则是一个执行中的程序。

每个进程都拥有自己的地址空间、内存、数据栈以及其他用于跟踪执行的辅助数据。操作系统管理其上所有进程的执行，并为这些进程合理地分配时间。

进程也可以通过派生（fork或spawn）新的进程来执行其他任务，不过因为每个新进程也都拥有自己的内存和数据栈等，所以只能采用**进程间通信**的方式共享信息。

## 线程

**线程**与进程类似，不过它们是在同一个进程下执行的，并共享相同的上下文。

线程包括开始、执行顺序和结束三部分。它有一个指令指针，用于记录当前运行的上下文。当其他线程运行时，它可以被抢占（中断）和临时挂起（也称为睡眠），这种做法叫让步（yielding）。

一个进程中的各个线程与主线程共享同一片数据空间；线程一般是以并发方式执行的。正是由于这种并行和数据共享机制，使得多任务间的协作成为可能。

如果两个或多个线程访问同一片数据，由于数据访问顺序不同，可能导致结果不一致。这种情况通常称为 **竞态条件**。幸运的是，大多数线程库都有一些同步原语，以允许线程管理器控制执行和访问。

线程无法给予公平的执行时间，因为一些方法会在完成前保持阻塞状态。

## 区别

- 系统开销
  - 进程拥有独立的堆栈空间和数据段，需要建立众多数据表来维护它的代码段、堆栈段、数据段
  - 线程拥有独立的堆栈空间，共享数据段，彼此间使用相同的地址空间
- 安全性
  - 一个进程崩溃，保护模式下不会影响其他进程
  - 一个线程崩溃，会导致整个进程崩溃
- 通讯
  - 进程间相互独立，通讯机制复杂，譬如：
    - 管道、信号、消息队列、共享内存、套接字
  - 线程可以使用共享数据段
