[TOC]

# Python re 模块

## 替换字符串内字符

`re.sub(pattern, repl, string, count=0, flags=0)`

## 从字符串中提取特定内容

### 提取数字

#### 示例一

```python
# 代码
s = "https://book.douban.com/subject/27031874/?icn=index-editionrecommend/458154564515/"
print(re.search(r'/(\d+)/', s).group())
print(re.search(r'/(\d+)/', s).group(1))
# 输出
/27031874/
27031874
```

