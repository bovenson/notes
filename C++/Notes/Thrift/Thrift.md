---
title: Thrift笔记
tags:	
	- Thrift
categories:
	- Thrift
---

# 简介

thrift是一个软件框架，用来进行可扩展且跨语言的服务的开发。它结合了功能强大的软件堆栈和代码生成引擎，以构建在 C++、Java、 Go、Python，等编程语言间无缝结合的、高效的远程调用服务。

**Steps**

- 通过IDL（Interface Description Language，接口定义语言）来定义RPC（Remote Procedure Call，远程过程调用）的接口和数据类型
- 通过Thrift编译器生成不同语言的代码
- 由生成的代码负责RPC协议层和传输层的实现

# 架构

![](imgs/01.jpg)

```shell
  # 简易结构
  
  +-------------------------------------------+
  | Server                                    |
  | (single-threaded, event-driven etc)       |
  +-------------------------------------------+
  | Processor                                 |
  | (compiler generated)                      |
  +-------------------------------------------+
  | Protocol                                  |
  | (JSON, compact etc)                       |
  +-------------------------------------------+
  | Transport                                 |
  | (raw TCP, HTTP etc)                       |
  +-------------------------------------------+
```

- TProtocal（协议层）
  - 协议定义了一个抽象机制将内存中的数据结构映射到一个有线形式。换句话说，一个协议指定数据类型如何使用底层传输编码/解码。
  - 数据传输格式
    - TBinaryProtocal：二进制格式
    - TCompactProtocol：压缩格式
    - TJSONProtocol：JSON格式
    - TSimpleJSONProtocol：JSON只写协议，生成的文件很容易通过脚本语言解析
    - TDebugProtocaol：使用易懂的可读的文件格式，以便于Debug
- TTransport（传输层）
  - 数据传输方式
    - TSocket：阻塞式Socker
    - TFrameTransport：以Frame为单位进行传输，非阻塞式服务中使用
    - TFileTransport：以文件形式进行传输
    - TMemoryTransport：将内存用于I/O
    - TZlibTransport：使用zlib进行压缩，与其他传输方式联合使用（无Java实现）
  - 方法
    - Transport 接口
      - open
      - close
      - read
      - write
      - flush
    - ServerTransport 接口
      - open
      - listen
      - accept
      - close
- Processor
  - Processor封装能力从输入流读取数据并写入输入流。
- Server
  - Server把上述功能聚合在一起
    - 创建transport
    - 创建transport使用的输入输出协议
    - 基于输入输出协议创建Processor
    - 等待连接请求，交给Processor处理
- 服务类型
  - TSimpleServer：简单的单线程服务模型，常用于测试
  - TThreadPoolServer：多线程服务模型，使用标准的阻塞式I/O
  - TNonblockingServer：多线程服务模型，使用非阻塞I/O（需使用TFrameTransport数据传输方式）

# 特征

- IDL（Interface Description Language）
- 语言绑定：Thrift支持很多语言和环境
- namespace：每个Thrift文件在它自己的命名空间内，允许在多个Thrift文件中使用相同的标识
- language namespace：每个Thrift文件可以指定每个语言使用的命名空间
- base types
  - bool
  - byte
  - i16
  - i32
  - i64
  - double
  - string
- 常量和枚举
- structs：使用structs把相关数据分组
- sparse structs
- struct evolution
- containers
- type definitions
- services
- service inheritance
- asynchronous invocations
- exceptions
- cyclic structs

# 参考

- [thrift 的原理和使用](https://www.cnblogs.com/chenny7/p/4224720.html)

- [Thrift](https://segmentfault.com/a/1190000010938841)
- [Apache Thrift Documents](http://thrift.apache.org/docs/concepts)