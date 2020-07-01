---
title: Django Rest Framework 笔记
tags:
	- Django
	- Django Rest Framework
categories:
	- Python
---

# Serializers

## RelationShip

```python
# coding: utf-8
# @author   bovenson
# @email    szhkai@qq.com
# @desc     Django Rest Framework Serializers

from rest_framework import serializers
from book.models import *
from cloudlibraryserver.settings import IMAGE_SOURCE, IMG_URL


class ScoreSerializer(serializers.ModelSerializer):
    """书籍评分"""
    class Meta:
        model = BookScore
        fields = '__all__'


class BookAuthorSerializer(serializers.ModelSerializer):
    """书籍作者"""
    class Meta:
        model = BookAuthor
        fields = '__all__'


class BookTagSerializer(serializers.ModelSerializer):
    """书籍标签"""
    class Meta:
        model = BookTag
        fields = '__all__'


class BookSerializer(serializers.ModelSerializer):
    """书籍"""
    score = ScoreSerializer(many=False, read_only=True)
    author = BookAuthorSerializer(many=True, read_only=True)
    tags = BookTagSerializer(many=True, read_only=True)

    class Meta:
        model = Book
        fields = ['name', 'summary', 'pic', 'score', 'author', 'tags']

    def to_representation(self, instance):
        # 修改图片URL
        ret = super().to_representation(instance)
        # 七牛储存
        if IMAGE_SOURCE == 'QINIU':
            ret['pic'] = IMG_URL + ret['pic'][len('/media'):]
        return ret


class BooklistSerializer(serializers.HyperlinkedModelSerializer):
    """书单"""
    class Meta:
        model = BookList
        fields = ['id']
```

## Order

```python
class Track(models.Model):
    album = models.ForeignKey(Album, related_name='tracks', on_delete=models.CASCADE)
    order = models.IntegerField()
    title = models.CharField(max_length=100)
    duration = models.IntegerField()

    class Meta:
        unique_together = ('album', 'order')
        ordering = ['order']	# 在Model定义中设置ordering
```

## 获取单个记录

设置`ModelViewSet`之后，会默认增加`view_url/pk/`

## 使用不同Serilizer同一模型

`urls.py`

```python
# route
router.register(r'book', BookViewSet)
router.register(r'bookdetail', BookDetailViewSet, 'bookdetail')

# view set
class BookViewSet(viewsets.ModelViewSet):
    """书籍 View Set"""
    queryset = Book.objects.all().order_by('id')
    serializer_class = BookSerializer
    pagination_class = StandardResultSetPagination
    
    
class BookDetailViewSet(viewsets.ModelViewSet):
    """书籍详情"""
    queryset = Book.objects.all().order_by('id')
    serializer_class = BookDetailSerializer
    pagination_class = StandardResultSetPagination
    
    
# serilizer
class BookSerializer(serializers.ModelSerializer):
    """书籍"""
    score = ScoreSerializer(many=False, read_only=True)
    author = BookAuthorSerializer(many=True, read_only=True)
    tags = BookTagSerializer(many=True, read_only=True)

    class Meta:
        model = Book
        fields = ['id', 'name', 'summary', 'pic', 'score', 'author', 'tags']

    def to_representation(self, instance):
        # 修改图片URL
        ret = super().to_representation(instance)
        # 七牛储存
        if IMAGE_SOURCE == 'QINIU':
            ret['pic'] = IMG_URL + ret['pic'][len('/media'):]
        return ret


class BookDetailSerializer(BookSerializer):	# 这里继承 BookSerializer
    info = BookInfoSerializer(many=False, read_only=True)
    """书籍详情"""
    class Meta:
        model = Book
        fields = ['id', 'name', 'summary', 'pic', 'score', 'author', 'tags', 'info']
```

# 返回树

**需求描述**

返回数据库表中描述的分类树

**Model**

```python
class ArticleCategory(models.Model):
    """文章分类"""
    name = models.CharField('分类名称', max_length=50)
    children = models.ManyToManyField('self', symmetrical=False, blank=True)

    def __str__(self):
        return self.name
```

**Serializer**

```python
from rest_framework import serializers
from django.contrib.auth.models import User, Group
from rest_framework_recursive.fields import RecursiveField

class ArticleCategorySerializer(serializers.ModelSerializer):
    """文章分类"""
    children = RecursiveField(many=True)

    class Meta:
        model = ArticleCategory
        fields = '__all__'
```

**ViewSet**

```python
class ArticleCategoryViewSet(viewsets.ModelViewSet):
    """文章分类列表"""
    queryset = ArticleCategory.objects.all()
    serializer_class = ArticleCategorySerializer

    @action(methods=['GET'], detail=False)
    def tree(self, request):
        prefetch = ArticleCategory.objects.all().prefetch_related()
        childs = list(map(lambda x: x.get('children'), prefetch.filter(children__isnull=False).values('children')))
        query = prefetch.exclude(pk__in=childs)
        serializer = self.get_serializer(query, many=True)
        return Response(serializer.data)
```

**预览**

`GET /api/article/category/tree/`

```python
HTTP 200 OK
Allow: GET, HEAD, OPTIONS
Content-Type: application/json
Vary: Accept

[
    {
        "id": 4,
        "children": [
            {
                "id": 3,
                "children": [
                    {
                        "id": 1,
                        "children": [],
                        "name": "A"
                    },
                    {
                        "id": 2,
                        "children": [],
                        "name": "Q"
                    }
                ],
                "name": "B"
            }
        ],
        "name": "D"
    }
]
```

# 分页

**File Tree**

```shell
bovenson@ThinkCentre:~/Git/isite/Server$ tree
.
├── db.sqlite3
├── isite
│   ├── admin.py
│   ├── apps.py
│   ├── core
│   │   ├── __init__.py
│   │   ├── pagination.py
│   ├── __init__.py
│   ├── models.py
│   ├── permissions
│   │   ├── permissions.py
│   ├── __pycache__
│   │   ├── admin.cpython-35.pyc
│   │   ├── apps.cpython-35.pyc
│   │   ├── __init__.cpython-35.pyc
│   │   ├── models.cpython-35.pyc
│   │   └── urls.cpython-35.pyc
│   ├── serializers
│   │   └── serializers.py
│   ├── tests.py
│   ├── urls.py
│   └── views
│       └── viewsets.py
├── isite_server
│   ├── __init__.py
│   ├── settings.py
│   ├── urls.py
│   └── wsgi.py
├── manage.py
└── templates
```

**自定义分页类**

`pagination.py`

```python
# coding: utf-8
# @author   bovenson
# @email    szhkai@qq.com
# @desc     分页
from rest_framework.pagination import PageNumberPagination
from rest_framework.response import Response


class StandardResultSetPagination(PageNumberPagination):
    """自定义分页"""
    page_size = 10
    page_query_param = 'page'
    max_page_size = 1000

    def get_paginated_response(self, data):
        """自定义返回信息"""
        return Response({
            'links': {
               'next': self.get_next_link(),
               'previous': self.get_previous_link()
            },
            'count': self.page.paginator.count,
            'pages': self.page.paginator.num_pages,
            'results': data
        })
```

**settings**

```python
REST_FRAMEWORK = {
    'DEFAULT_AUTHENTICATION_CLASSES': (
        'rest_framework.authentication.TokenAuthentication',
    ),
    'DEFAULT_PAGINATION_CLASS': 'isite.core.pagination.StandardResultSetPagination',  # 设置默认分页类
    'PAGE_SIZE': 10
}
```

**对于自定义action进行分页**

```python
class ArticleViewSet(viewsets.ModelViewSet):
    """文章ViewSet"""
    queryset = Article.objects.all().order_by('-create_datetime')
    serializer_class = ArticleSerializer
    authentication_classes = ()

    @action(methods=['GET'], detail=False)
    def summary(self, request):
        """获取带有限定字符简介的列表"""
        articles = Article.objects.all()
        for article in articles:
            article.content = article.content[:120]

        # 分页
        page = self.paginate_queryset(articles)
        if page is not None:
            serializer = self.get_serializer(page, many=True)
            return self.get_paginated_response(serializer.data)
        else:
            serializer = self.get_serializer(articles, many=True)
            return Response(serializer.data)
```



**参考**

- [Stack overflow](https://stackoverflow.com/questions/23501292/get-a-specific-object-in-django-rest-framework)

