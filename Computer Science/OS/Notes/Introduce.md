---
title: 操作系统引论
tags:
	- 操作系统
categories:
	- 操作系统
---

# 定义

操作系统是一组能有效组织和管理计算机硬件和软件资源，合理地对作业进行调度，以及方便用户使用的程序集合。

# 介绍

操作系统是配置在计算机硬件上的第一层软件，是对硬件系统的首次扩充。其主要作用是：

- 管理设备
- 提高设备利用率和系统吞吐量
- 为用户和应用程序提供简单的接口

# 目标

- 方便性
  - 方便用户使用
- 有效性
  - 提高系统资源利用率
  - 提供系统吞吐量
    - 合理地组织计算机的工作流程，加速程序的运行，缩短程序的运行周期
- 可扩充性
  - 方便添加新功能和模块
- 开放性
  - 遵循世界标准规范

# 作用

- 作为用户与计算机硬件系统之间的接口
  - 用户通过OS来使用计算机系统
- 作为计算机系统资源的管理者
  - 处理器
  - 存储器
  - I/O设备
  - 文件
    - 数据
    - 程序
- 实现了对计算机资源的抽象
  - 向用户提供了一个对硬件操作的抽象模型
    - I/O设备管理软件实现了对计算机硬件操作的第一个层次的抽象
    - 文件管理软件实现了对硬件资源操作的第二个层次的抽象
  - OS是铺设在计算机硬件上的多层软件的集合
    - 增强了计算机系统的功能
    - 隐藏了对硬件操作的具体细节
    - 实现了对计算机硬件操作的多个层次的抽象模型

# 发展过程

- 人工操作方式
  - 人工将纸带或卡片上的程序和数据输入计算机
- 脱机输入 / 输出方式
  - 程序和数据的输入输出在外围机的控制下完成
  - 解决了人机矛盾及CPU和I/O设备之间速度不匹配的矛盾
- 单道批处理系统
  - 减少了机器的空闲等待时间
  - 监督程序挨个执行磁带上的程序
  - 缺点
    - 系统中的资源得不到充分利用
      - 因为内存中仅有一道程序，I/O请求时，CPU处于等待状态
- 多道批处理系统
  - 为了进一步提高资源的利用率和系统吞吐量
    - 同时将多个作业调入内存
    - 作业I/O请求时执行其他作业
  - 优缺点
    - 资源利率高
    - 系统吞吐量大
    - 平均周转时间长
    - 无交互能力
  - 需要解决的问题
    - 处理机争用问题
    - 内存分配和保护问题
    - I/O设备分配问题
    - 文件的组织和管理问题
    - 作业管理问题
    - 用户系统的接口问题
- 分时系统
  - 分时系统是指，在一台主机上连接了多个配有显示器和键盘的终端并由此所组成的系统，该系统允许多个用户同时通过自己的终端，以交互方式使用计算机，共享主机中的资源
  - 为了满足用户对人机交互的需求
    - 人机交互
    - 共享主机
  - 分时系统的特征
    - 多路性
      - 指系统允许将多台终端同时连接到一台主机上，按分时原则为每个用户服务
    - 独立性
      - 每个用户在各自的终端上进行操作，彼此互不干扰
    - 及时性
      - 用户的请求可以在很短的时间内获得响应
    - 交互性
      - 用户通过终端与系统进行广泛的人机对话
- 实时系统
  - 实时系统是指，系统能及时响应外部事件的请求，在规定的时间内完成对该事件的处理，并控制所有实时任务协调一致地运行
  - 实时任务
    - 硬实时任务
      - 必须满足截止时间的要求，否则可能会出现不能接受的错误
    - 软实时任务
      - 对截止时间要求并不高的任务
  - 特征
    - 多路性
      - 值系统周期性地对多路现场信息进行采集，以及对多个对象或执行机构进行控制
    - 独立性
    - 及时性
    - 交互性
    - 可靠性
- 微机操作系统
  - 配置在微型机上的操作系统
  - 发展
    - 单用户单任务操作系统
    - 单用户多任务操作系统
    - 多用户多任务操作系统

# 基本特性

- 并发
  - 并发和并行
    - 并发是宏观上的并行
  - 进程
- 共享
- 虚拟
  - 在OS中，把通过技术将一个物理实体变为若干个逻辑上的对应物的功能称为虚拟
  - 实现方式
    - 时分复用技术
      - 在设备为一用户服务的空闲时间，转去为其他用户服务（设备的分时使用）
    - 空分复用技术
      - 利用存储器的空闲时间分区域存放和运行其他的多道程序，以此来提高内存的利用率
- 异步
  - 进程以人们不可预知的速度向前推进的特性

# 主要功能

引入OS的目的是，为多道程序的运行提供良好的运行环境，并能最大程度地提高系统中各种资源的利用率，方便用户使用。

- 处理机管理功能
  - 进程控制
  - 进程同步
  - 进程通信
  - 调度
- 存储器管理功能
  - 内存分配
  - 内存保护
  - 地址映射
  - 内存扩充
- 设备管理功能
  - 缓冲管理
  - 设备分配
  - 设备处理
- 文件管理功能
  - 文件存储空间的管理
  - 目录管理
  - 文件的读写管理和保护

# OS结构设计

- 传统结构
  - 无结构操作系统
  - 模块化结构操作系统
  - 分层式结构的操作系统
- 现代结构的操作系统
  - 微内核结构

## 微内核

### 基本概念

- 足够小的内核
- 基于客户/服务器模式
  - 优点
    - 数据的分布处理和存储
    - 便于集中处理
    - 灵活性和可扩充性
    - 易于改编应用软件
  - 将操作系统中最基本的部分放入内核中，而把操作系统的绝大部分功能都放在微内核外面的一组服务器（进程）中实现，比如：进程（线程）管理服务器、虚拟存储器服务器、I/O设备管理服务器。它们被作为进程来实现，运行在用户态。
  - 客户与服务器之间借助微内核提供的消息传递机制来实现信息交互
- 应用 **机制与策略分离** 原理
  - 机制是指实现某一功能的具体执行结构
  - 策略是指在机制的基础上借助于某些参数和算法来实现该功能的优化，或达到不同的功能目标
  - 在微内核操作系统中，将机制放在微内核中
- 面向对象技术

### 基本功能

- 进程（线程）管理
  - 通信
  - 调度
  - 切换
- 低级存储器管理
  - 页表机制
  - 地址变换机制
- 中断和陷入处理

### 微内核的优点

- 提高了系统的可扩展性
- 增强了系统的可靠性
- 可移植性强
- 提供了对分布式系统的支持
- 融入了面向对象技术

### 存在的问题

- 效率降低
  - 完成一次客户对操作系统提出的服务请求时，需要利用消息实现多次交互和进行用户/内核模式与上下文的多次切换

**解决办法**

- 重新把一些常用的基本功能放入微内核
  - 使微内核体积增大
  - 小型接口定义和适应性下降
  - 提高了微内核的设计代价