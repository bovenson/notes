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