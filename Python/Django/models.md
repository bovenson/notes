---
title: models
date: 2017-07-24 17:48:54
tags: Python, Django, models
---

# models

## 文档版本

`Documentation version: 1.10`

## 简单实例

### 定义

```python
from django.db import models

class Fruit(models.Model):
    name = models.CharField(max_length=100, primary_key=True)
```

### 使用

```python
>>> fruit = Fruit.objects.create(name='Apple')
>>> fruit.name = 'Pear'
>>> fruit.save()
>>> Fruit.objects.values_list('name', flat=True)
['Apple', 'Pear']
```

## 主键

```python
id = models.AutoField(primary_key=True)
```

## 获取所有项

```python
Fruit.objects.clear()
```

## 删除所有

```python
Fruit.objects.all().delete()
```

## 关系(Relationships)

### 多对一关系(Many-to-one relationships)

- 使用`django.db.models.ForeignKey`定义多对一关系

#### 示例

一辆车(`Car`)对应多个制造商(`Manufacturer` )

```python
from django.db import models

class Manufacturer(models.Model):
    # ...
    pass

class Car(models.Model):
    manufacturer = models.ForeignKey(Manufacturer, on_delete=models.CASCADE)
    # ...
```

- 定义递归关联(对象关联自身)

  ```python
  # 定义递归关联
  models.ForeignKey('self', on_delete=models.CASCADE)
  ```

- 使用未定义的`Model`

  ```python
  from django.db import models

  class Car(models.Model):
      manufacturer = models.ForeignKey(
          # Manufacturer模型还未定义, 可以在这里使用模型名称加上引号代替
          'Manufacturer',
          on_delete=models.CASCADE,
      )
      # ...

  class Manufacturer(models.Model):
      # ...
      pass
  ```

- 建议使用模型名称单数形式作为成员变量(如上例中的变量`manufacturer`)

### 多对多关系(Many-to-many relationships)

- 使用`ManyToManyField`定义多对多关系

#### 判断存不存在

```python
if _book in _booklist.books.all():
    print("书籍已存在")
```

#### 添加

```python
_booklist.books.add(_book)
```

#### 示例

一个Pizza(披萨)有多个Topping(配料), 一个Topping可以在多个Pizza上.

```python
from django.db import models

class Topping(models.Model):
    # ...
    pass

class Pizza(models.Model):
    # ...
    toppings = models.ManyToManyField(Topping)
```

- 可以像多对一关系一样定义`递归关联`和使用`未定义模型`

- 建议使用模型名称复数形式作为成员变量(如上例中的变量`toppings`)

- 多对多关系只能在两个相关模型中的一个内定义, 在哪一个里面定义无所谓

- 通常, 多对多字段(ManyToManyField)应该定义在将要被修改的表格中. 上例中, 在Pizza中定义Topping(配料), 因为相比认为配料在多个披萨里面, 披萨里面有多种配料更容易理解. 按照上例中多对多字段的定义方式, Pizza表可以让用户选择多种配料(而不是配料中选披萨).

  ```
  原文:
  Generally, ManyToManyField instances should go in the object that’s going to be edited on a form. In the above example, toppings is in Pizza (rather than Topping having a pizzas ManyToManyField ) because it’s more natural to think about a pizza having toppings than a topping being on multiple pizzas. The way it’s set up above, the Pizza form would let users select the toppings.
  ```

#### 另一个示例

文章和出版商.

```python
from django.db import models

class Publication(models.Model):
    title = models.CharField(max_length=30)

    def __str__(self):              # __unicode__ on Python 2
        return self.title

    class Meta:
        ordering = ('title',)

class Article(models.Model):
    headline = models.CharField(max_length=100)
    publications = models.ManyToManyField(Publication)

    def __str__(self):              # __unicode__ on Python 2
        return self.headline

    class Meta:
        ordering = ('headline',)
```

- **注意:** 如果使用模型中间件来定义多对多关系, 可能会禁用一些相关的管理方法, 以至于下面的示例可能无法工作

- 创建几个Publications

  ```shell
  >>> p1 = Publication(title='The Python Journal')
  >>> p1.save()
  >>> p2 = Publication(title='Science News')
  >>> p2.save()
  >>> p3 = Publication(title='Science Weekly')
  >>> p3.save()
  ```

- 创建一个Article

  ```shell
  >>> a1 = Article(headline='Django lets you build Web apps easily')
  ```

- 与保存之前无法和Publication关联

  ```shell
  >>> a1.publications.add(p1)
  Traceback (most recent call last):
  ...
  ValueError: 'Article' instance needs to have a primary key value before a many-to-many relationship can be used.
  ```

- 保存

  ```shell
  >>> a1.save()
  ```

- 添加关联

  ```shell
  >>> a1.publications.add(p1)
  ```

- 重新创建一个Article并和多个Publication关联

  ```shell
  >>> a2 = Article(headline='NASA uses Python')
  >>> a2.save()
  >>> a2.publications.add(p1, p2)
  >>> a2.publications.add(p3)
  ```

- 重复添加也没问题

  ```shell
  >>> a2.publications.add(p3)
  ```

- 如果添加的对象类型不正确, 会抛出`TypeError`异常

  ```shell
  >>> a2.publications.add(a1)
  Traceback (most recent call last):
  ...
  TypeError: 'Publication' instance expected
  ```

- 一步完成创建Article并添加Publication

  ```python
  >>> new_publication = a2.publications.create(title='Highlights for Children')
  ```

- Article对象可以访问相关联的Publication

  ```shell
  >>> a1.publications.all()
  <QuerySet [<Publication: The Python Journal>]>
  >>> a2.publications.all()
  <QuerySet [<Publication: Highlights for Children>, <Publication: Science News>, <Publication: Science Weekly>, <Publication: The Python Journal>]>
  ```

- Publication对象也可以访问相关联的Article对象

  ```shell
  >>> p2.article_set.all()
  <QuerySet [<Article: NASA uses Python>]>
  >>> p1.article_set.all()
  <QuerySet [<Article: Django lets you build Web apps easily>, <Article: NASA uses Python>]>
  >>> Publication.objects.get(id=4).article_set.all()
  <QuerySet [<Article: NASA uses Python>]>
  ```

- 多对多关系可以通过使用跨关系查找(`lookups across relationships`)查询

  ```python
  >>> Article.objects.filter(publications__id=1)
  <QuerySet [<Article: Django lets you build Web apps easily>, <Article: NASA uses Python>]>
  >>> Article.objects.filter(publications__pk=1)
  <QuerySet [<Article: Django lets you build Web apps easily>, <Article: NASA uses Python>]>
  >>> Article.objects.filter(publications=1)
  <QuerySet [<Article: Django lets you build Web apps easily>, <Article: NASA uses Python>]>
  >>> Article.objects.filter(publications=p1)
  <QuerySet [<Article: Django lets you build Web apps easily>, <Article: NASA uses Python>]>

  >>> Article.objects.filter(publications__title__startswith="Science")
  <QuerySet [<Article: NASA uses Python>, <Article: NASA uses Python>]>

  >>> Article.objects.filter(publications__title__startswith="Science").distinct()
  <QuerySet [<Article: NASA uses Python>]>
  ```

- `count()`和`distinct()`方法

  ```shell
  >>> Article.objects.filter(publications__title__startswith="Science").count()
  2

  >>> Article.objects.filter(publications__title__startswith="Science").distinct().count()
  1

  >>> Article.objects.filter(publications__in=[1,2]).distinct()
  <QuerySet [<Article: Django lets you build Web apps easily>, <Article: NASA uses Python>]>
  >>> Article.objects.filter(publications__in=[p1,p2]).distinct()
  <QuerySet [<Article: Django lets you build Web apps easily>, <Article: NASA uses Python>]>
  ```

- 支持反向多对多查询

  ```shell
  >>> Publication.objects.filter(id=1)
  <QuerySet [<Publication: The Python Journal>]>
  >>> Publication.objects.filter(pk=1)
  <QuerySet [<Publication: The Python Journal>]>

  >>> Publication.objects.filter(article__headline__startswith="NASA")
  <QuerySet [<Publication: Highlights for Children>, <Publication: Science News>, <Publication: Science Weekly>, <Publication: The Python Journal>]>

  >>> Publication.objects.filter(article__id=1)
  <QuerySet [<Publication: The Python Journal>]>
  >>> Publication.objects.filter(article__pk=1)
  <QuerySet [<Publication: The Python Journal>]>
  >>> Publication.objects.filter(article=1)
  <QuerySet [<Publication: The Python Journal>]>
  >>> Publication.objects.filter(article=a1)
  <QuerySet [<Publication: The Python Journal>]>

  >>> Publication.objects.filter(article__in=[1,2]).distinct()
  <QuerySet [<Publication: Highlights for Children>, <Publication: Science News>, <Publication: Science Weekly>, <Publication: The Python Journal>]>
  >>> Publication.objects.filter(article__in=[a1,a2]).distinct()
  <QuerySet [<Publication: Highlights for Children>, <Publication: Science News>, <Publication: Science Weekly>, <Publication: The Python Journal>]>
  ```

- 执行一个关联工作是非常简单的

  ```shell
  >>> Article.objects.exclude(publications=p2)
  <QuerySet [<Article: Django lets you build Web apps easily>]>
  ```

- 如果我们删除了一个Publication, 它关联的Articles将不能再访问它

  ```shell
  >>> p1.delete()
  >>> Publication.objects.all()
  <QuerySet [<Publication: Highlights for Children>, <Publication: Science News>, <Publication: Science Weekly>]>
  >>> a1 = Article.objects.get(pk=1)
  >>> a1.publications.all()
  <QuerySet []>
  ```

- 如果我们删除了一个Article, 它关联的Publications将不能再访问它

  ```shell
  >>> a2.delete()
  >>> Article.objects.all()
  <QuerySet [<Article: Django lets you build Web apps easily>]>
  >>> p2.article_set.all()
  <QuerySet []>
  ```

- 添加关系

  ```shell
  >>> a4 = Article(headline='NASA finds intelligent life on Earth')
  >>> a4.save()
  >>> p2.article_set.add(a4)
  >>> p2.article_set.all()
  <QuerySet [<Article: NASA finds intelligent life on Earth>]>
  >>> a4.publications.all()
  <QuerySet [<Publication: Science News>]>
  ```

  ```shell
  >>> new_article = p2.article_set.create(headline='Oxygen-free diet works wonders')
  >>> p2.article_set.all()
  <QuerySet [<Article: NASA finds intelligent life on Earth>, <Article: Oxygen-free diet works wonders>]>
  >>> a5 = p2.article_set.all()[1]
  >>> a5.publications.all()
  <QuerySet [<Publication: Science News>]>
  ```

- 从Publication中移除Article

  ```shell
  >>> a4.publications.remove(p2)
  >>> p2.article_set.all()
  <QuerySet [<Article: Oxygen-free diet works wonders>]>
  >>> a4.publications.all()
  <QuerySet []>
  ```

  也可以通过在Article中移除Publication

  ```shell
  >>> p2.article_set.remove(a5)
  >>> p2.article_set.all()
  <QuerySet []>
  >>> a5.publications.all()
  <QuerySet []>
  ```

- 关系集合可以被设置

  ```shell
  >>> a4.publications.all()
  <QuerySet [<Publication: Science News>]>
  >>> a4.publications.set([p3])
  >>> a4.publications.all()
  <QuerySet [<Publication: Science Weekly>]>
  ```

- 关系集合可以被清空

  ```shell
  >>> p2.article_set.clear()
  >>> p2.article_set.all()
  <QuerySet []>
  ```

  或者从关联的对象清空:

  ```shell
  >>> p2.article_set.add(a4, a5)
  >>> p2.article_set.all()
  <QuerySet [<Article: NASA finds intelligent life on Earth>, <Article: Oxygen-free diet works wonders>]>
  >>> a4.publications.all()
  <QuerySet [<Publication: Science News>, <Publication: Science Weekly>]>
  >>> a4.publications.clear()
  >>> a4.publications.all()
  <QuerySet []>
  >>> p2.article_set.all()
  <QuerySet [<Article: Oxygen-free diet works wonders>]>
  ```

- 批量删除Publications 

  ```shell
  >>> Publication.objects.filter(title__startswith='Science').delete()
  >>> Publication.objects.all()
  <QuerySet [<Publication: Highlights for Children>, <Publication: The Python Journal>]>
  >>> Article.objects.all()
  <QuerySet [<Article: Django lets you build Web apps easily>, <Article: NASA finds intelligent life on Earth>, <Article: NASA uses Python>, <Article: Oxygen-free diet works wonders>]>
  >>> a2.publications.all()
  <QuerySet [<Publication: The Python Journal>]>
  ```

- 批量删除Articles

  ```shell
  >>> q = Article.objects.filter(headline__startswith='Django')
  >>> print(q)
  <QuerySet [<Article: Django lets you build Web apps easily>]>
  >>> q.delete()
  ```

  ​

#### 多对多关系额外字段



## Migrations

### 生成变更

`python manage.py makemigrations`

### 应用变更

```shell
python manage.py migrate
```

## 字段

### datetimefield

```python
from django.utils import timezone
add_time = models.DateTimeField('添加时间', default=timezone.now)   # 默认当前时间
```

## user migrations位置

```shell
/usr/local/lib/python3.5/dist-packages/django/contrib/auth/migrations
```



## 验证字段

### 自定义验证函数

[参考一](http://www.tuicool.com/articles/NZrInuI)

```python
# 在上面的例子中, 如果我们希望每篇article title的开头都是"new", 那么应该怎么做呢? 首先我们需要建立自定义的验证(validator):
# utils/validator.py
    from django.core.exceptions import ValidationError

    def validate_begins(value):
        if not value.startswith(u'new'):
            raise ValidationError(u'Must start with new')
# 可见, 在django中的验证程序就是不符合条件便抛出ValidationError的function, 为了方便重复使用, 我们将它们放在django app utils的validators.py中.
# 接下来, 我们可以在model中加入这些validator, 但为了今后的方便修改和维护, 我们更倾向于加入到ModelForm中:
# myapp/forms.py
    from django import forms

    from utils.validators import validate_begin
    from .models import Article

    class ArticleForm(forms.ModelForm):
        dev __init__(self, *args, **kwargs):
            super(ArticleForm, self).__init__(8args, **kwargs)
            self.fields["title"].validators.append(validate_begin)

    class Meta:
        model = Article
# Django的edit views(UpdateView和CreateView等)的默认行为是根据view中model属性, 自动创建ModelForm. 因此, 我们需要调用我们自己的Modelform来覆盖自动创建的:
# myapp/views.py
    from django.views.generic import CreateView, UpdateView

    from braces.views import LoginRequiredMixin

    from .models import Article
    from .forms import ArticleForm

    class ArticleCreateView(LoginRequiredMixin, CreateView):
        model = Article
        fields = ('title', 'slug', 'review_num')
        form_class = ArticleForm

    class ArticleUpdateView(LoginRequiredMixin, UpdateView):
        model = Article
        fields = ('title', 'slug', 'review_num')
        form_class = ArticleForm
```





## 问题: 将model写在不同文件及文件夹下, 无法生成数据表

Django会搜索所有继承`django.db.models.Model`的类, 并生成相应的数据表,如果没能正常生成, 可能的原因:

- 定义`model`的源文件的路径中有文件夹下不包含`__init__.py`, 这样python不会把该文件夹当作`package`