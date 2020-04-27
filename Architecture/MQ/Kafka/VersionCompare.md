---
title: Kafka版本对比
tags:
	- Kafka
categories:
	- Kafka
---

# v2

- 支持前缀ACLs
- 添加认证框架(authenticate framework)
- 支持主机名认证
- 动态更新SSL信任库
- 改进复制协议避免leader和follower间日志差异
- 减少消息转换占用内存，提升broker弹性

- 减少消息分块内存使用，避免broker的OutOfMemory错误
- 在应用限流之前通知客户端，使得客户端在超过限额时，可以区分是网络错误还是限流
- 增加消费者配置选项，避免无限阻塞
- 放弃Java7
- Kafka Connect改进
  - 改进transformations异常处理方式
  - 日志包含更多信息
  - 增加密钥从连接器配置中移除扩展
- 增加Scala wrapper API
- 支持消息头信息添加读取
- Kafka Streams中的窗口聚合性能有大的提升

# v1.1

- Kafka Controller有大的改善
  - 加速controlled shutdown
  - 改善zookeeper会话过期处理
  - 允许单个集群支持更多分区
  - 引入了增量提取请求，当分区数量很大时提供更高效的复制
- 增加对日志目录副本移动支持，以便于JBOD实现数据平衡
- 动态更新某些broker配置
- 增加代理令牌身份验证，以支持大量客户端导致的其他身份验证服务器过载
- Kafka Connect功能更新
  - Connect REST接口中支持Header
  - SSL和kafka集群标识符
  - 连接器名称验证
  - 支持接收器主题正则表达式
  - 默认最大堆大小增加到2GB

- Kafka Streams API改进
  - 减少重新分区主题占用的分区空间
  - 异常处理可定制
  - 增强broker弹性

# v1.0

- Streams API改进
  - 增加API在运行时公开活动任务的状态
  - 新的cogroup API可以更轻松地处理代码中包含更少StateStore和更少移动部件的分区聚合
- 改进集群可监控性
- 支持Java9
- 区分身份验证错误和代理失败
- 更好得容忍磁盘故障

# 0.11.0.0

- **支持 Exactly-once 语义**。为了支持幂等**producer和EOS**，增加一些与事务相关的字段，使得单个record数据结构体积增加。但因为优化了RecordBatch使得整个batch所占体积反而减少，进一步降低了网络IO开销。
- 优化了对Snappy压缩的支持之前由于源代码中硬编码了blocksize
- 消息增加头部信息(Header)Record增加了Header，每个header是一个KV存储
- 空消费者组延时rebalance为了缩短多consumer首次rebalance的时间，增加了“group.initial.rebalance.delay.ms”用于设置group开启rebalance的延时时间。这段延时期间允许更多的consumer加入组，避免不必要的JoinGroup与SyncGroup之间的切换。当然凡事都是trade-off，引入这个必然带来消费延时。
- 新的分配算法：StickyAssignor比range和round-robin更加平衡的分配算法。
- 重构了controller，采用了单线程+基于事件队列的方式。

# 0.10.x

- 内置了机架感知以便隔离副本，使得Kafka保证副本可以跨越到多个机架或者是可用区域，显著提高了Kafka的弹性和可用性
- 所有Kafka中的消息都**包含了时间戳字段**，这个时间就是这条消息产生的时间。这使得Kafka Streams能够处理基于事件时间的流处理；而且那些通过时间寻找消息以及那些基于事件时间戳的垃圾回收特性能为可能。 

- Apache Kafka 0.9.0.0版本引入了新的安全特性，包括通过SASL支持Kerberos。Apache Kafka 0.10.0.0现在支持更多的SASL特性，包括外部授权服务器，在一台服务器上支持多种类型的SASL认证以及其他的改进。 
- Kafka Connect得到了持续提升。在此之前，用户需要监控日志以便看到各个connectors以及他们task的状态，现在Kafka已经支持了获取的状态API这样使得监控变得更简单。同时也添加了控制相关的API，这使得用户可以在进行维护的时候停止一个connector；或者手动地重启那些失败的task。这些能够直观的在用户界面展示和管理connector目前可以在控制中心(Control Center)看到。 
- Kafka Consumer Max Records，在Kafka 0.9.0.0，开发者们在新consumer上使用poll()函数的时候是几乎无法控制返回消息的条数。不过值得高兴的是，此版本的Kafka引入了max.poll.records参数，**允许开发者控制返回消息的条数**。
- 协议版本改进，Kafka brokers现在支持返回所有支持的协议版本的请求API，这个特点的好处就是以后将允许一个客户端支持多个broker版本。