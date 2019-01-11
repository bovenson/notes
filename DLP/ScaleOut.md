---
title: 并行线性加速
tags:
	- 深度学习平台
categories:
	- 深度学习
---

# 挑战

- 集群本身的 Unequal performance machines
- 网络通讯的Low bandwidth & high delay

# 参数服务器

机器学习算法具有一些独特的特点^[1]^：

- 迭代性，模型的更新并非一次完成，需要循环迭代多次。
- 容错性，即使在每个循环中产生一些错误，模型最终的收敛不受影响。
- 参数收敛的非均匀性，模型中有些参数经过几个循环便不再改变，其他参数需要很长时间收敛。

分布式机器学习任务，并不能随着机器的增加而能力线性提升，因为大量资源都会浪费在通讯，等待，协调，这些时间可能会占据大部分比例。

参数服务器就是被提出来专门用于大规模最优化处理的框架，它特定用于这种需求：大规模的训练数据，比如TB甚至PB级别的；大规模的模型参数，在大规模的优化框架中，常常会有数十亿乃至千亿级别的参数需要估计。

## 发展历史

Alex Smola 于2010年提出的并行LDA的框架。它通过采用一个分布式的Memcached作为存放参数的存储，这样就提供了有效的机制用于分布式系统中不同的Worker之间同步模型参数，而每个Worker只需要保存他计算时所以来的一小部分参数即可。^[4]^

Google的Jeff Dean进一步提出了第一代Google大脑的解决方案：**DistBelief**。DistBelief将巨大的深度学习模型分布存储在全局的参数服务器中，计算节点通过参数服务器进行信息传递，很好地解决了SGD和L-BFGS算法的分布式训练问题。^[4]^

李沐所在的DMLC组所设计的参数服务器。根据论文中所写，该parameter server属于第三代参数服务器，提供了更加通用的设计。架构上包括一个Server Group和若干个Worker Group。^[4]^

![](imgs/001.png)

## 特点

- Efficient Communication：高效的通信。网络通信开销是机器学习分布式系统中的大头，因此parameter server基本尽了所有的努力来降低网络开销。 其中最重要的一点就是：异步通信。因为是异步通信，所以不需要停下来等一些慢的机器执行完一个iter，这就大大减少了延迟。 当然并非所有算法都天然的支持异步和随机性，有的算法引入异步后可能收敛会变慢，因此就需要自行在算法收敛和系统效率之间权衡。
- Elastic Scalability：使用一致性哈希算法，使得新的Server可以随时动态插入集合中，无需重新运行系统。
- Fault Tolerance and Durability： 节点故障是不可避免的。对于server节点来说，使用链备份来应对；而对于Worker来说，因为worker之间互相不通信，因此在某个worker失败后，新的worker可以直接加入。
- Ease of Use：全局共享的参数可以被表示成各种形式：vector, matrices或是sparse类型，同时框架还提供对线性代数类型提供高性能的多线程计算库。

# 参考

- [[1] 实现Tensorflow多机并行线性加速](https://zhuanlan.zhihu.com/p/29149294)
- [[2] 十分钟了解分布式计算:Petuum](https://www.cnblogs.com/wei-li/p/Petuum.html)
- [[3] 参数服务器——分布式机器学习的新杀器](https://t.cj.sina.com.cn/articles/view/6464244551/1814c8b47001006smv)
- [[4] ParameterServer入门和理解](https://www.zybuluo.com/Dounm/note/517675)