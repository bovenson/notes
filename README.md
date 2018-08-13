# Description
notes.

# TODO

- [ ] ~~`Python`中`set`原理及效率~~

- [ ] ~~切片操作的效率(对比遍历)~~

- [x] 内置函数`sum`的效率

  ```python
  s = [1, 2, 3] * 1000000
  print(time.clock())
  sum(s)
  print(time.clock())
  print(time.clock())
  res = 0
  for i in s:
      res += i
  print(time.clock())
  
  # output
  0.032466
  0.046174
  0.046191
  0.220772
  ```

- [ ] ~~`all() / any()`~~

- [x] `set() == set()`

  ```shell
  >>> set([1, 2]) == set([1, 2])
  True
  >>> s1 = set([1, 2])
  >>> s2 = set([1, 2])
  >>> s1 == s2
  True
  >>> s1.add(3)
  >>> s1 == s2
  False
  >>> l1 = [1, 2]
  >>> l2 = [1, 2]
  >>> l1 == l2
  True
  >>> l1.append(3)
  >>> l1 == l2
  False
  ```

- [x] 整理MVC笔记

- [x] 整理函数式编程笔记

- [ ] Java

  - [x] Java函数式编程

- [ ] ~~JVM~~

  - [ ] ~~垃圾回收~~

- [x] SpringMVC

- [ ] 剑指Offer

  - [x] 数组
  - [ ] 字符串
  - [ ] 链表
  - [ ] 树
  - [ ] 栈和队列

- [ ] C++

  - [ ] 基础
    - [ ] 面向对象
  - [ ] 特性
    - [ ] STL
      - [ ] 算法
      - [ ] 容器
      - [ ] 迭代器

- [ ] 操作系统

  - [x] 引论
  - [ ] 进程管理
  - [ ] 处理机调度
  - [ ] 存储器管理
  - [ ] 虚拟存储器
  - [ ] 输入输出系统
  - [ ] 文件管理
  - [ ] 磁盘存储器的管理

- [ ] 内核

- [ ] 计算机物理结构

  - [ ] CPU

