---
title: Kafka 注意事项
---

- 传输大文件
  - [Ref](https://www.jianshu.com/p/61b6220a9ef2)
- 每个partition的一个消息只能被一个consumer group中的一个consumer消费
- consumer group 中的一个consumer可以消费多个partition
- **一个consumer group 中的cunsomer processes必须小于partition的数量**
- each message is delivered exactly to one consumer from a group (with a same group id)

