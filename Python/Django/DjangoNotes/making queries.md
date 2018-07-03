---
title: Making Queries
date: 2017-07-24 17:48:54
tags: 
	- Python
	- Django
	- Making queries
categories:
	- Python
---

# 示例模型

```python
from django.db import models

class Blog(models.Model):
    name = models.CharField(max_length=100)
    tagline = models.TextField()

    def __str__(self):
        return self.name

class Author(models.Model):
    name = models.CharField(max_length=200)
    email = models.EmailField()

    def __str__(self):
        return self.name

class Entry(models.Model):
    blog = models.ForeignKey(Blog, on_delete=models.CASCADE)
    headline = models.CharField(max_length=255)
    body_text = models.TextField()
    pub_date = models.DateField()
    mod_date = models.DateField()
    authors = models.ManyToManyField(Author)
    n_comments = models.IntegerField()
    n_pingbacks = models.IntegerField()
    rating = models.IntegerField()

    def __str__(self):
        return self.headline
```

- [参考文档](https://docs.djangoproject.com/en/2.0/topics/db/queries/)

# CRUD

## 增

```shell
>>> from blog.models import Blog
>>> b = Blog(name='Beatles Blog', tagline='All the latest Beatles news.')
>>> b.save()
```

## 改

```shell
>>> b5.name = 'New name'
>>> b5.save()
```

**更新`ForeignKey`字段和`ManyToManyField`字段**

更新`ForeignKey`字段和更新普通字段一样，只需要把一个对象赋值给该字段即可。

```shell
>>> from blog.models import Blog, Entry
>>> entry = Entry.objects.get(pk=1)
>>> cheese_blog = Blog.objects.get(name="Cheddar Talk")
>>> entry.blog = cheese_blog
>>> entry.save()
```

更新`ManyToManyField`字段有所不同，需要使用`add()`方法想关系中添加记录。

```shell
>>> from blog.models import Author
>>> joe = Author.objects.create(name="Joe")
>>> entry.authors.add(joe)
```

一次向`ManyToManyField`字段添加多个字段，可以这样做：

```shell
>> john = Author.objects.create(name="John")
>>> paul = Author.objects.create(name="Paul")
>>> george = Author.objects.create(name="George")
>>> ringo = Author.objects.create(name="Ringo")
>>> entry.authors.add(john, paul, george, ringo)
```

# 检索对象

从数据库中检索对象，会在你的模型上通过`Manager`构造一个`QuerySet`。

一个`QuerySet`代表数据库对象的集合。可以有零个、一个或者多个`filters`。

可以通过模型的`Manager`获取`QuerySet`。每个模型至少有一个`Manager`，默认叫做`objects`。

```python
>>> Blog.objects
<django.db.models.manager.Manager object at ...>
>>> b = Blog(name='Foo', tagline='Bar')
>>> b.objects
Traceback:
    ...
AttributeError: "Manager isn't accessible via Blog instances."
```

**Manager**只能通过模型类访问，而不是示例对象。

## 检索所有对象

```shell
>>> all_entries = Entry.objects.all()
```

## 使用`filters`检索特定的对象

提取`QuerySet`最常用的两个方法：

- `filter(**kwargs)` : 返回一个包含符合查找参数的`QuerySet`
- `exclude(**kwargs)` : 返回一个包含不符合查找参数的`QuerySet`

比如，要获取2006年的博客：

```shell
Entry.objects.filter(pub_date__year=2006)
# 等价得有
Entry.objects.all().filter(pub_date__year=2006)
```

### 链式过滤

提炼一个`QuerySet`的结果是他自身，所以可以使用链式过滤的方法，来处理`QuerySet`。

```shell
>>> Entry.objects.filter(
...     headline__startswith='What'
... ).exclude(
...     pub_date__gte=datetime.date.today()
... ).filter(
...     pub_date__gte=datetime.date(2005, 1, 30)
... )
```

上例，把数据库中所有`Entry`作为初始`QuerySet`，添加一个`filter`，然后是一个`exclusion`，然后是另一个`filter`。最终的结果是满足下面这些条件的所有`Entry`的`QuerySet`。

- `headline`以`What`开头
- 发表时间介于`2005-1-30`和`今日`之间

### 使用`get()`检索单个元素

```shell
>>> one_entry = Entry.objects.get(pk=1)
```

# 限定`QuerySet`

**LIMIT**

```shell
>>> Entry.objects.all()[:5]
```

**OFFSET**

```shell
>>> Entry.objects.all()[5:10]
```

**不支持负数索引。**

# 查找`Field`

基本的关键词查找参数形如 `field__lookuptype=value`，比如：

```shell
>>> Entry.objects.filter(pub_date__lte='2006-01-01')
```

大致转换成SQL语句是：

```SQL
SELECT * FROM blog_entry WHERE pub_date <= '2006-01-01';
```

## Field Lookups

- [field lookup reference](https://docs.djangoproject.com/zh-hans/2.0/ref/models/querysets/#field-lookups)

# 使用`Q`对象进行复杂查找

- [官方文档](https://docs.djangoproject.com/en/2.1/topics/db/queries/#complex-lookups-with-q-objects)

`OR`

```python
Q(question__startswith='Who') | Q(question__startswith='What')
```

**对应SQL**

```sql
WHERE question LIKE 'Who%' OR question LIKE 'What%'
```

非 `~`

```python
Q(question__startswith='Who') | ~Q(pub_date__year=2005)
```

**Q对象必须要在查找关键词之前**

合法：

```python
Poll.objects.get(
    Q(question__startswith='Who'),
    Q(pub_date=date(2005, 5, 2)) | Q(pub_date=date(2005, 5, 6))
)
```

不合法：

```python
# INVALID QUERY
Poll.objects.get(
    question__startswith='Who',
    Q(pub_date=date(2005, 5, 2)) | Q(pub_date=date(2005, 5, 6))
)
```

# 跨关系查找

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

# 获取指定数量

## 前n个

`Entry.objects.all()[:5]`

## m至n个

`Entry.objects.all()[5:10]`

** 注意 **

- 负数索引不支持, 比如: `Entry.objects.all()[-1]`

## ManyToManyField

### 按照数量过滤

```python
# 得到空
Category.objects.filter(parent__isnull=True)
```

