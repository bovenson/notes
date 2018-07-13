---
title: TODOS
---

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

  