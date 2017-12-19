# 正则表达式

## 匹配任意不为空的字符串

`^\S+`

## 分组及命名

```python
s = "2013-02-12 2014-24-12"
p = re.compile(r'(?P<year>\d{4})-(?P<month>\d{2})-(?P<day>\d{2})')
m = p.search(s)
print(m.groups())
print(m.group('year'))
print(m.group('month'))
print(m.groupdict())

### OUTPUT
('2013', '02', '12')
2013
02
{'year': '2013', 'month': '02', 'day': '12'}
```

