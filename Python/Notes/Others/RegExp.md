---
title: Python正则表达式
tags: Python, 正则表达式
---

# 组及命名组匹配

```python
s = "2013-02-12 2014-24-12"
p = re.compile(r'(?P<year>\d{4})-(?P<month>\d{2})-(?P<day>\d{2})', re.IGNORECASE)
m = p.search(s)
print(m.groups())
print(m.group('year'))
print(m.group('month'))
print(m.groupdict())

print('\n', '-' * 100, '\n')

m = p.findall(s)
print()
print(type(m))
print(m)
for g in m:
    print(type(g))
    # print(g.groups())
    # print(g.group('year'))
    # print(g.group('month'))
    # print(g.groupdict())

print('\n', '-' * 100, '\n')

m = p.finditer(s)
print(m)
for g in m:
    print(g.groups())
    print(g.group('year'))
    print(g.group('month'))
    print(g.groupdict())
```

**输出：**

```shell
/usr/local/bin/python3 /Users/bovenson/Git/notes/ICS-Security/CNVD/spider/test.py
('2013', '02', '12')
2013
02
{'year': '2013', 'month': '02', 'day': '12'}

 ---------------------------------------------------------------------------------------------------- 


<class 'list'>
[('2013', '02', '12'), ('2014', '24', '12')]
<class 'tuple'>
<class 'tuple'>

 ---------------------------------------------------------------------------------------------------- 

<callable_iterator object at 0x100f0a4e0>
('2013', '02', '12')
2013
02
{'year': '2013', 'month': '02', 'day': '12'}
('2014', '24', '12')
2014
24
{'year': '2014', 'month': '24', 'day': '12'}

Process finished with exit code 0
```

