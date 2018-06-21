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



**参考**

- [Stack overflow](https://stackoverflow.com/questions/23501292/get-a-specific-object-in-django-rest-framework)

