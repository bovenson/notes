---
title: Python语言技巧
---

# 列表

```python
#### nums[:]
## 注意id
>>> nums = [1, 2, 2, 3, 3, 3, 3, 6]
>>> id(nums)
4362265608
>>> nums[:] = sorted(set(nums))
>>> id(nums)
4362265608
>>> nums = [1, 2, 2, 3, 3, 3, 3, 6]
>>> id(nums)
4362287304
>>> nums = sorted(set(nums))
>>> id(nums)
4362265608
```

