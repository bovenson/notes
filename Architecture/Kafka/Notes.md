---
title: Kafka
---

- 架构
  - 介绍
    - [Ref](https://blog.csdn.net/lp284558195/article/details/80297208)
  - Rebalance
    - Rebalance Listener
      - [Ref](https://www.learningjournal.guru/courses/kafka/kafka-foundation-training/rebalance-listener/)

- 名词

  - Broker
    - 消息中间件处理节点
    - 一个Kafka节点就是一个broker
    - 一个或多个Kafka节点组成Kafka集群
  - Topic
    - Kafka根据Topic对消息进行归类
    - 发布到Kafka集群的每条消息都需要指定一个topic
  - Producer
  - Consumer
  - ConsumerGroup
    - 每个Consumer属于一个特定的ConsumerGroup
    - 一条消息可以发送到多个不同的ConsumerGroup
    - 一个ConsumerGroup只能有一个Consumer消费该消息
  - Partition
    - 物理上的概念
    - 一个topic可以分为多个partition
    - 每个partition内部是有序的
    - 每个partition在存储层面是append log文件
    - 顺序写磁盘，效率非常高，这是Kafka高吞吐量的重要保证
  - Segment
    - partition细分的物理该概念
    - 包括：
      - `.index`文件
      - `.log`文件
    - [Ref](https://blog.csdn.net/lp284558195/article/details/80297208)

  - Offset
    - 发布到partition的消息被追加到log文件的尾部
    - 每条消息在文件中的位置成为offset
    - offset是一个整形数字，唯一标记一条消息
  - Leader & Follower
    - 为了提高消息可靠性，Kafka为每个topic的partition设置N个副本
    - N 副本中的一个选举为Leader，其他为Follower
    - Leader处理partition的所有读写请求，follower定期复制leader上的数据
    - 负责维护和跟踪ISR（副本同步队列）中所有follower滞后的状态
    - producer发送一条消息到broker后，leader写入消息并复制到所有follower

- 如何提高可靠性

  - 通过request.required.acks参数设置数据可靠性级别
    - 1：producer接受到leader成功收到数据并得到确认后发送下一条数据
      - 如果leader宕机，消息未同步到follower，可能会造成数据丢失
    - 0：producer无需等待来自broker的确认而继续发送下一条消息，可靠性最低
    - -1：producer等待ISR中所有follower都确认接收到数据才算一次发送成功，可靠性最高
      - 如果设置副本为1，也就是说只有leader，此时和设置`request.required.acks`等效，如果leader宕机，也会发生数据丢失
  - 保证高可靠性
    - topic的配置：replication.factor>=3,即副本数至少是个;2<=min.insync.replicas<=replication.factor
    - broker的配置：leader的选举条件unclean.leader.election.enable=false
    - producer的配置：request.required.acks=-1(all)，producer.type=sync

- 应用
  - 日志收集
  - 业务数据收集
  - page view
  - ...