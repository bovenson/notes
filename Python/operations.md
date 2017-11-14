---
title: Python操作
---

# 生成不重复的随机数组

```shell
#### 1
random.sample(range(20), 10)

#### 2 
a = numpy.arange(20)
numpy.random.shuffle(a)
print a[:10]
```

# 列表合并

```shell
>>> l1 = [1, 2]
>>> l2 = [3, 4]
>>> l1.extend(l2)
>>> l1
[1, 2, 3, 4]
>>> 
```

