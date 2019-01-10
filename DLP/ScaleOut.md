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



# 参考

- [[1]实现Tensorflow多机并行线性加速](https://zhuanlan.zhihu.com/p/29149294)
- [[2]十分钟了解分布式计算:Petuum](https://www.cnblogs.com/wei-li/p/Petuum.html)
- [[3]参数服务器——分布式机器学习的新杀器](https://t.cj.sina.com.cn/articles/view/6464244551/1814c8b47001006smv)
- [4] https://www.zybuluo.com/Dounm/note/517675