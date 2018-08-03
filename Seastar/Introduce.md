---
title: Seastar概述
tags:
	- Seastar
categories:
	- Seastar
---

# 简介

Seastar 是一个开源，基于c++ 11/14 feature，支持高并发和低延迟的异步编程高性能库。

**特点**

- 事件驱动（event-driven）框架
- 可以更简单地编写非阻塞、异步代码
- 基于futures （附录一）

# 特性

- 采用了一套新的编程模式，从而最大化去利用硬件的性能
  - **架构**
    - 完全分片(share-nothing)的设计
      - 每个logic core一个thread
      - 每个core有自己的资源
        - CPU
        - Network
        - Dist I/O
        - Memory
      - 多个core之间没有资源的竞争
      - 随着core数量增加，扩展性和性能也随之提升
    - 多core之间的通讯
      - 采用point-to-point queue发送和接受异步消息
        - 消息队列的两种模式
          - 点对点（point to point, queue）
            - 不可重复消费
            - 生产者发送一条消息到queue，一个queue可以有很多消费者，但是一个消息只能被一个消费者接受，当没有消费者可用时，这个消息会被保存直到有 一个可用的消费者，所以Queue实现了一个可靠的负载均衡
          - 发布/订阅（publish / subscribe, topic）
            - 可以重复消费
            - 发布者发送到topic的消息，只有订阅了topic的订阅者才会收到消息。topic实现了发布和订阅，当你发布一个消息，所有订阅这个topic的服务都能得到这个消息，所以从1到N个订阅者都能得到这个消息的拷贝。
    - core之间没有数据共享
    - 没有锁
    - 没有cache lines频繁的miss
    - 异步编程框架

# 附录

## 附录一：Futures and promises

计算机科学中，future、promis、delay、deferred，

# 参考

- [Seastar Github](https://github.com/scylladb/seastar)
- [Futures and promises](https://en.wikipedia.org/wiki/Futures_and_promises)
- [现代硬件上的高性能C++异步框架 - SeaStar](https://www.sohu.com/a/190357942_617676)