---
title: making queries
date: 2017-07-24 17:48:54
tags: Python, Django, Making queries
---

# making queries

## 跨关系查找

跨关系查找(Lookups that span relationships)

查找所有名字为`Beatles Blog`的Blog的`Entry`对象

```shell
>>> Entry.objects.filter(blog__name='Beatles Blog')
```

查找所有 至少有一个 headline包含`Lennon`的Entry对象 的Blog对象

```shell
>>> Blog.objects.filter(entry__headline__contains='Lennon')
```

如果跨关系过滤对象, 并且中间模型不存在满足过滤条件的值, 那么Django会把该中间模型当作空(所有值为NULL), 但是是合法的, 也就是说不会抛出异常.例如:

```shell
Blog.objects.filter(entry__authors__name='Lennon')
```

在这个查询中, 如果没有author和entry关联, 这将会被当作name属性没有被赋值处理, 而不会因为没有author对象而抛出异常.

那如果想要查询作者名称为空的Blog对象怎么办哪, 答案是可以使用`isnull`:

```shell
Blog.objects.filter(entry__authors__name__isnull=True)
```

这将返回这样的Blog对象: author的name为空, 并且有一个空author的entry.

如果不想要空的author的entry对象, 可以使用:

```shell
Blog.objects.filter(entry__authors__isnull=False, entry__authors__name__isnull=True)
```

## 获取指定数量

### 前n个

`Entry.objects.all()[:5]`

### m至n个

`Entry.objects.all()[5:10]`

### 注意

- 负数索引不支持, 比如: `Entry.objects.all()[-1]`

## many to many field

### 按照数量过滤

```python
# 得到空
Category.objects.filter(parent__isnull=True)
```

