---
title: Seastar SM
tags:
	- Seastar
categoires:
	- Seastar
---

# Steps

- 是什么
  - 定义
    - Seastar是支持高并发和低延迟的高性能异步编程库
  - 特点
    - 协同微任务调度程序（用户态的任务调度）
      - 每个硬件核心运行一个线程
      - 每个线程运行一个协同微任务
      - 事件驱动
        - Reactor
    - 无共享SMP架构
      - 内存、数据结构、CPU时间不共享
      - 核心间通讯通过消息队列，没有锁
    - 基于Future的APIs
      - uture编程模型允许程序员封装复杂的异步操作，Seastar引擎负责在适当的时候运行Future的Continuation
      - Seastar的Future和Promise用于管理细粒度、无阻塞的任务
    - 无共享TCP堆栈
      - 所有的网络连接在cores之间分片（shard） 
      - 每个core只负责处理自己那部分数据连接 
    - 基于DMA的存储APIs
- 干什么
  - 用于在现代多核机器上编写高效复杂的服务器应用程序
- 怎么干
  - 语言层面，减少了Runtime时的消耗
  - CPU耗时操作
    - 复制
    - 进程切换
    - 系统调用
    - 中断处理
    - 数据多核同步锁开销
  - Seastar是一个应用框架，它几乎将操作系统所提供的抽象完整地搬移到了用户态中，以减少操作系统的抽象开销，实现软硬件一体化，通过减少耗时操作优化CPU效率提高性能
    - 执行流抽象
      - 协同微任务调度程序
      - Future & Promise & Continuation
        - Promise 和 Future 简化了异步编程，因为它们将事件生成器（promise）和事件使用者（使用future的任何人）分离。 不管promise是否在future使用前完成，或者相反，都不会改变代码的输出结果
    - 文件抽象
      - 基于DMA的存储APIs
      - Seastar维护用户态PageCache，从而实现了Zero copy的文件操作
      - 它维护自己的IO调度策略，从而更好地使用磁盘
    - 内存抽象
      - 无共享SMP架构
    - 用户态网络栈抽象
      - 无共享TCP堆栈
- 不足
  - 并非所有应用程序都适合分片，这些应用程序根本无法受益
  - 由于分区不均匀导致核心之间的不平衡可能导致某些核心过载而其他核心相对空闲，从而浪费资源 
    - 尝试使用task调度程序做核心的负载均衡



