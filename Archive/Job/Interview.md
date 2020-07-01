---
title: 面试
tags:
	- 面试
categories:	
	- JOB
---

# 算法

## 大文件排序 & 重复数据top k & top k

- 外部排序
- 归并排序
  - 分段
  - 归并
    - 假设内存容纳N条记录
      - N/3 来自文件一
      - N/3 来自文件二
      - N/3 作为输出缓冲
  - 重复归并，每次归并分段数量减半
- 优化
  - 对于大文件重复数据top k，如果有必要可以计算数据hash值，加快排序速度
- 参考
  - [Ref 1](https://www.cnblogs.com/codeMedita/p/7425291.html)