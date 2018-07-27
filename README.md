# Description
notes.

# TODO

- [ ] `Python`中`set`原理及效率

- [ ] 切片操作的效率(对比遍历)

- [ ] 内置函数`sum`的效率

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

- [ ] `all() / any()`

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



