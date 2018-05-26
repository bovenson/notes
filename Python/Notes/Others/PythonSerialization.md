---
title: Python 序列化
tags:	
	- 序列化
categories:
	- Python
---

# pickle

## 将对象存储至文件

```python
# 序列化
with open('obj.pkl', 'wb') as f:
    dic = {'age': 23, 'job': 'student'}
    pickle.dump(dict, f
                
# 反序列化
with open('obj.pkl', 'rb') as f:
    dic = pickle.load(f)
    print(dic)
    print(type(dic))	# <class 'dict'>
```

# 参考

- [参考一](https://www.cnblogs.com/sun-haiyu/p/7087088.html)