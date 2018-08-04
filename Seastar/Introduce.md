---
title: Seastar概述
tags:
	- Seastar
categories:
	- Seastar
---

# 简介

Seastar 是一个开源，基于c++ 11/14 feature，支持高并发和低延迟的异步编程高性能库。**用于并发服务器的高性能C ++框架，在现代多核机器上编写高效复杂的服务器应用程序** 。旨在使用作为高性能的库，编写高复杂度的应用程序。

Seastar的灵感和第一用例是ScyllaDB，它是Apache Cassandra的重写。 

Seastar提供了一个完整的异步编程框架，它使用两个概念 - future 和 continuations - 来统一表示和处理每种类型的异步事件，包括网络I / O，磁盘I / O和其他事件的复杂组合。 

由于现代多核和多插槽机器在核心之间共享数据（原子指令，缓存线弹跳和内存防护）有严重的惩罚。Seastar程序使用**无共享编程模型**，即可用内存在核心之间划分每个核心在其自身的内存部分中处理数据，核心之间的通信通过显式消息传递发生（当然，这本身就是使用SMP的共享内存硬件发生的）。 

## **对比现有服务器程序**

对于并发请求，传统的网络服务器处理改善流程：

- 产生一个单独的进程来处理
- 使用进程池
- 使用线程代替进程

每个进程在同一时刻，只处理一个连接。哪怕一个进程阻塞，还会有其他进程来处理其他请求。

对每个连接使用进程（或线程）的服务器进行编程称为同步编程，因为代码是线性编写的，并且一行代码在前一行完成后开始运行。 

> 尽管同步服务器应用程序是以线性，非并行方式编写的，但在后台，内核有助于确保所有内容并行发生，并且机器的资源（CPU，磁盘和网络）得到充分利用。除了进程并行性（我们有多个进程并行处理多个连接）之外，内核甚至可以并行化一个连接的工作 - 例如，处理未完成的磁盘请求（例如，从磁盘文件中读取）与处理并行网络连接（发送缓冲但尚未发送的数据，并缓冲新接收的数据，直到应用程序准备好读取它）。 

启动一个新进程、上下文切换很慢，并且每个进程都带来了很大的开销 - 最明显的是它的堆栈大小。服务器和内核开发者努力减轻这些开销：从进程切换到线程，从创建新线程到线程池，他们降低了每个线程的默认堆栈大小，并增加了虚拟内存大小以允许更多部分利用的堆栈。但是，具有同步设计的服务器的性能并不令人满意，并且随着并发连接数量的增长而严重缩放。 

## 异步编程

编写异步服务器程序面临两大挑战：

- 复杂性
  - 编写一个简单的异步服务器非常简单。但是编写复杂的异步服务器是非常困难的。单个连接的处理，而不是简单易于读取的函数调用，现在涉及大量的小回调函数，以及复杂的状态机，以记住每个事件发生时需要调用哪个函数。
- 非阻塞
  - 每个核心只有一个线程对于服务器应用程序的性能很重要，因为上下文切换很慢。但是，如果每个核心只有一个线程，则事件处理函数必须永远不会阻塞，否则核心将保持空闲状态。但是一些现有的编程语言和框架让服务器作者别无选择，只能使用阻塞函数，因此使用多个线程。 
  - 例如，Cassandra被编写为异步服务器应用程序；但是因为磁盘I / O是用mmap ed文件实现的，这些文件在访问时会无法控制地阻塞整个线程，所以它们被迫每个CPU运行多个线程。 

此外，当需要最佳性能时，服务器应用程序及其编程框架别无选择，只能考虑以下因素： 

- 现代机器
  - 现代机器与10年前的机器截然不同
  - 具有许多内核和深层内存层次结构（从L1缓存到NUMA）
  - 奖励某些编程方式
  - 惩罚一些其他的编程方式
    - 不可扩展的编程方式（例如加锁）可能会破坏许多核处理器的性能
    - 共享内存和无锁同步原语是可用的（即原子操作和内存排序围栏），但是比仅涉及单个内核缓存中的数据的操作要慢得多，并且还阻止应用程序扩展到其他处理器核心
- 编程语言
  - Java，Javascript和类似的“现代”语言等高级语言很方便，但每种语言都有自己的一组假设，这些假设与上面列出的要求相冲突。这些语言旨在实现可移植性，这也使程序员无法控制关键代码的性能。
  - 为了获得真正的最佳性能，我们需要一种编程语言，它为程序员提供完全控制，零运行时间开销，另一方面 - 复杂的编译时代码生成和优化 

Seastar是一个用于编写异步服务器应用程序的框架，旨在解决上述所有四个挑战：

- 它是一个用于编写涉及网络和磁盘I / O的复杂异步应用程序的框架
- 该框架的快速路径完全是单线程（每个核心）
- 可扩展到许多核心
- 并最大限度地减少核心之间昂贵的内存共享
- 它是一个C ++ 14库，为用户提供了复杂的编译时功能和完全的性能控制
- 没有运行时开销

## **特点**

- 事件驱动（event-driven）框架
- 可以更简单地编写非阻塞、异步代码
- 基于futures （附录一）
- 使用DPDK作为网络后端（可选）
  - DPDK使用了轮询(polling)而不是中断来处理数据包。在收到数据包时，经DPDK重载的网卡驱动不会通过中断通知CPU，而是直接将数据包存入内存，交付应用层软件通过DPDK提供的接口来直接处理，这样节省了大量的CPU中断时间和内存拷贝时间。
- 使用自己的原生 TCP/IP 栈
  - Seastar附带一个原生、分片的TCP / IP堆栈，通常和DPDK环境一起使用
  - 对于Posix network stack，尽管在Seastar这一层是shar-nothing设计，由于Seastar需要和下方OS network stack进行交互，这样就可能有锁，原子操作，CPU 缓存的miss，性能不可避免地受到损失。所以要想获得最佳性能，推荐配置Seastar native network stack 。

# 特性

- 采用了一套新的编程模式，从而最大化去利用硬件的性能
  - **架构**
    - 完全分片(share-nothing)的设计
      - 每个logic core一个thread
      - 每个logic  core有自己的资源
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
      - 提供独立的存储器给各个core
      - 避免当多个处理器访问同一个存储器产生的性能损失
    - 异步编程框架

# Seastar基石

## 架构

Seastar借助一下概念，实现极致性能：

- 协同微任务调度程序 （Cooperative micro-task scheduler）
  - 每个核心运行一个协作任务调度程序，而不是运行线程
  - 每个任务通常都非常轻量级 - 只运行处理最后一个I / O操作的结果并提交一个新任务
- 无共享SMP架构 
  - 每个核心独立于SMP系统中的其他核心运行。内存，数据结构和CPU时间不共享;相反，核心间通信使用显式消息传递。Seastar核心通常被称为碎片。 

## 异步

- Future
- Promise
- Continuation
  - 代表一段计算，最常用的就是Lambda函数
  - Continuation通过future的then函数绑定到future上 
  - then函数的输入参数就是绑定的future对象 
  - 当future 的值available时，这些绑定的continuations就会自动执行 
- 高阶接口
  - Seastar在f-p-c基础上还实现了更高级的接口：
    - 异步操作的并行执行parallel_for_each 
    - 异步操作的循环执行repeat 
    - 同步等待异步操作的执行when_all 
    - 对于map reduce支持 
    - Semaphore，gate和pipe等接口。 

# 架构

- 基于分片的异步编程框架 
  - 能够实现复杂的服务器逻辑 
  - 保证网络和存储操作 
  - 多核之间操作的异步性 
  - 达到**高性能和低延迟**的目标 
- 对比传统数据栈
  - ![](imgs/01.jpg)
- **内存切片（shard）**
  - Seastar运行后会保留（--reserve-memory）小部分内存给操作系统或者预留（-m）一定数量的内存给自己 
  - Seastar对分配给自己的物理内存也进行了分片（shard） 
  - 每个core有
    - 自己的内存空间
    - 自己的memory allocator（log-structured）
      - 对内存区域进行分配和释放管理
      - 无需考虑thread safe和内存碎片化（定期compact，移动object，合并memory holes） 
  - 对比JVM
    - ![](imgs/02.png)
- 网络分片
  - 所有的网络连接在cores之间分片（shard） 
  - 每个core只负责处理自己那部分数据连接 
- 用户态task调度
  - 

# 推荐配置

- CPU
  - 按需，很好地支持多核CPU和NUMA
- 网卡
  - 尽可能快
  - 每个CPU的硬件队列越多，SeaStar就越好。否则我们必须在软件中模仿它 

# 附录

## 附录一：Futures and promises

计算机科学中，future、promis、delay、deferred，是指用于在一些并发编程语言中**同步程序执行的方式**。他们描述了一个对象，该对象充当最初未知的结果的代理，通常是因为其值的计算尚未完成。

通常，future、promis、delay、deferred 可以互换使用。future是变量的只读占位符，而promise是可写的单个赋值容器，用于设置future的值。

可以定义 future 而不指定特定的 promise 设置其值。future 和 promise 相互关联：future 是值，promise 是设置值得函数。

future 和 promise 起源于函数式编程，**将值（a future）与其计算方式（a promise）分离**，从而允许更灵活地进行计算，特别是通过并行计算。

常见 Future 比如有：

- 正在从网络读取的数据缓冲区
- 计时器到期
- 磁盘写操作完成
- 计算结果来自一个或多个其他 future 值

**Promise 和 Future 简化了异步编程，因为它们将事件生成器（promise）和事件使用者（使用future的任何人）分离。 不管promise是否在future使用前完成，或者相反，都不会改变代码的输出结果。**

**消费Future**

使用`then()`方法消费一个Future，并提供一个回调函数。

```c++
future<int> get();   // promises an int will be produced eventually
future<> put(int)    // promises to store an int

void f() {
    get().then([] (int value) {
        put(value + 1).then([] {
            std::cout << "value stored successfully\n";
        });
    });
}
```

**链式Future**

`then()`可以返回一个future。

```c++
future<int> get();   // promises an int will be produced eventually
future<> put(int)    // promises to store an int

void f() {
    get().then([] (int value) {
        return put(value + 1);
    }).then([] {
        std::cout << "value stored successfully\n";
    });
}
```

**Loops**

通过尾调用实现循环 

```c++
future<int> get();   // promises an int will be produced eventually
future<> put(int)    // promises to store an int

future<> loop_to(int end) {
    if (value == end) {
        return make_ready_future<>();
    }
    get().then([end] (int value) {
        return put(value + 1);
    }).then([end] {
        return loop_to(end);
    });
}
```

**异常捕获**

如果一个`then()`抛出异常，调度器会捕获异常，并取消所有依赖的`then()`的调用。如果想捕获异常，可以在语句最后添加`.then_wrapped()`。

```c++
future<buffer> receive();
request parse(buffer buf);
future<response> process(request req);
future<> send(response resp);

void f() {
    receive().then([] (buffer buf) {
        return process(parse(std::move(buf));
    }).then([] (response resp) {
        return send(std::move(resp));
    }).then([] {
        f();
    }).then_wrapped([] (auto&& f) {
        try {
            f.get();
        } catch (std::exception& e) {
            // your handler goes here
        }
    });
}
```



## 附录二：DPDK

数据平面开发套件（DPD，Data Plane Development Kit），主要基于Linux系统运行，**用于快速数据包处理的函数库与驱动集合，可以极大提高数据处理性能和吞吐量，提高数据平面应用程序的工作效率**。

DPDK架构通过创建EAL(Environment Abstraction Layer,环境抽象层)来为不同的工作环境创造函数库集，创建后开发者即可把自己的应用与函数库进行链接。

**工作原理**

**DPDK使用了轮询(polling)而不是中断来处理数据包**。在收到数据包时，经DPDK重载的网卡驱动不会通过中断通知CPU，而是直接将数据包存入内存，交付应用层软件通过DPDK提供的接口来直接处理，这样节省了大量的CPU中断时间和内存拷贝时间。

**环境抽象层**

DPDK的创造的环境抽象层(EAL, Environment Abstraction Layer)主要负责对计算机底层资源（如硬件和内存空间）的访问，并对提供给用户的接口实施了实现细节的封装。其初始化例程决定了如何分配这些资源（PCI设备、计时器、控制台等）。 

**轮询模式驱动**

DPDK包括1Gb,10Gb,40Gb和半虚拟化抽象层的轮询模式驱动(PMD, Poll Mode Driver)。PMD由用户空间的特定的驱动程序提供的API组成，用于对设备和它们相应的队列进行设置。抛弃了基于中断的异步信号发送机制为该架构带来很大的开销节省。避免中断性能瓶颈是DPDK提升数据包处理速度的关键之一。

DPDK环境为数据包处理应用考虑了两种模型：运行至完成(run-to-completion)模型和管道(pipeline)模型。在运行至完成模型中，一个API向某个特定端口的接收描述符环轮询以接收数据包。接着这个数据包在同一个核上被处理，之后被一个发送用API放到端口的传输描述符环上；在管道模型中，一个核心会通过API对一个或多个端口的接收描述符环进行轮询，数据包通过环被接收和传递给另一个核心，然后在这个核心上被处理，之后可能被发送用API放到端口的传输描述符环上。

## 附录三：异步编程

**使用异步编程的原因**

- 单核性能限制
- 多核利用率不高
- 锁的开销大
  - 在传统的多进程/线程的编程中，锁是保证数据安全的重要手段。由于资源(file, memory)的竞争, 进程/线程不得不阻塞等待。据实验测试，一个高并发的应用，20%~70%的时间可能耗在无谓的锁等待上。 
- 数据分配在一个核上
  - 可能复制和使用在别的核上例如一个网卡的中断程序运行在一个core上，而后续的数据包的处理可能迁移到别的core上，这样CPU的cache line频繁的miss，造成性能的penalty。 数据分配在一个核上，可能复制和使用在别的核上例如一个网卡的中断程序运行在一个core上，而后续的数据包的处理可能迁移到别的core上，这样CPU的cache line频繁的miss，造成性能的penalty。 
- 用户态/内核态、进程线程/中断上下文切换的开销 

## 附录四：NUMA

NUMA是一种CPU架构。

NUMA（Non Uniform Memory Access Architecture, 非统一内存访问）技术可以使众多服务器像单一系统那样运转，同时保留小系统便于编程和管理的优点。基于电子商务应用对内存访问提出的更高的要求，NUMA也向复杂的结构设计提出了挑战。 

NUMA架构在逻辑上遵循对称多处理（SMP）架构。

限制访问存储器的次数是现代计算机提高性能的要点。 **NUMA通过提供分离的存储器给各个处理器，避免当多个处理器访问同一个存储器产生的性能损失来试图解决这个问题**。对于涉及到分散的数据的应用（在服务器和类似于服务器的应用中很常见），NUMA可以通过一个共享的存储器提高性能至n倍,而n大约是处理器（或者分离的存储器）的个数。

## 附录五：状态机

关于状态机的一个极度确切的描述是它是一个有向图形，由一组节点和一组相应的转移函数组成。状态机通过响应一系列事件而“运行”。每个事件都在属于“当前” 节点的转移函数的控制范围内，其中函数的范围是节点的一个子集。函数返回“下一个”（也许是同一个）节点。这些节点中至少有一个必须是终态。当到达终态， 状态机停止。 

## 附录六：SMP

并行的标准方法是使用线程。然而，该模型具有许多性能缺陷，因此Seastar使用不同的模型。

 线程和进程是操作系统提供的抽象。操作系统不是拥有一个或少量固定数量的处理器，而是允许用户根据自己的喜好创建尽可能多的虚拟处理器，并在物理处理器之上复用这些虚拟处理器。这些虚拟处理器称为线程（如果它们彼此共享内存）或进程（如果它们不共享）。

最简单的线程方法是每个连接线程。对于需要提供的每个连接，都会创建一个线程，在该线程中运行读取进程响应循环。这种方法存在许多问题，将其局限于最简单的应用程序： 



 

SMP的全称是 **对称多处理**（Symmetrical Multi-Processing）技术，是指在一个计算机上汇集了一组处理器(多CPU)，各CPU之间共享内存子系统以及总线结构。

它是相对非对称多处理技术而言的、应用十分广泛的并行技术。在这种架构中，一台电脑不再由单个CPU组成，而同时由多个处理器运行操作系统的单一复本，并共享内存和一台计算机的其他资源。虽然同时使用多个CPU，但是从管理的角度来看，它们的表现就像一台单机一样。系统将任务队列对称地分布于多个CPU之上，从而极大地提高了整个系统的数据处理能力。所有的处理器都可以平等地访问内存、I/O和外部中断。在对称多处理系统中，系统资源被系统中所有CPU共享，工作负载能够均匀地分配到所有可用处理器之上。

# 参考

- [Seastar Github](https://github.com/scylladb/seastar)
- [Seastar tutorial](https://github.com/scylladb/seastar/blob/master/doc/tutorial.md)
- [Futures and promises](https://en.wikipedia.org/wiki/Futures_and_promises)
- [现代硬件上的高性能C++异步框架 - SeaStar](https://www.sohu.com/a/190357942_617676)
- [DPDK](https://baike.baidu.com/item/DPDK/20804798)
- [NUMA](https://baike.baidu.com/item/NUMA/6906025)
- [SMP对称多处理结构 ](https://baike.baidu.com/item/SMP%E5%AF%B9%E7%A7%B0%E5%A4%9A%E5%A4%84%E7%90%86%E7%BB%93%E6%9E%84/7213852?fr=aladdin)