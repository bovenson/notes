---
title: Django Rest Framework Serializers 笔记
tags:
	- Django Rest Framework
categories:
	- Python
---

# ManyToMany Fields

## 存储对象时处理多对多关系

**需求**

对于提交一个新文章，其标签有则关联，没有则新建

**Model**

```python
class ArticleTag(models.Model):
    """标签"""
    name = models.CharField('标签名', max_length=50, primary_key=True)

    def __str__(self):
        return self.name


class Article(models.Model):
    """文章"""
    MARKDOWN = 'MD'
    RICH_TEXT = 'RT'
    ARTICLE_CONTENT_TYPE = (
        (MARKDOWN, 'Markdown'),
        (RICH_TEXT, 'RichText'),
    )

    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    title = models.CharField('标题', max_length=200)
    category = models.ForeignKey(ArticleCategory, on_delete=models.SET_NULL, null=True)
    content = models.TextField('文章内容')
    content_type = models.CharField('文章内容类型', choices=ARTICLE_CONTENT_TYPE, max_length=20)
    tags = models.ManyToManyField(ArticleTag, blank=True)
    author = models.ForeignKey(User, on_delete=models.SET_NULL, null=True)
    public = models.BooleanField('是否公开', default=True)
    create_datetime = models.DateTimeField(auto_now_add=True)
    update_datetime = models.DateTimeField(auto_now=True)

    def __str__(self):
        return self.title
```

**Serializer**

```python
class ArticleSerializer(serializers.ModelSerializer):
    """文章"""
    class Meta:
        model = Article
        fields = '__all__'

    def run_validation(self, data=serializers.empty):
        """验证数据"""
        if 'tags' in data:
            # 标签判断; 如果不存在则新建
            for tag in data['tags']:
                ArticleTag.objects.get_or_create(name=tag)
        return super(ArticleSerializer, self).run_validation(data)

    def create(self, validated_data):
        """新建文章"""
        tags = validated_data.pop('tags')
        instance = Article.objects.create(**validated_data)
        instance.tags.set(tags)
        instance.save()
        return instance
```

**示例**

```python
# Request body (JSON)
{
	"title": "HELLO",
	"content_type": "MD",
	"content": "This is content.",
	"tags": ["sadfasdf", "asdfasdf"]
}

# response
{
    "id": "2970dd7f-2169-46f3-b2de-571cd83be778",
    "title": "HELLO",
    "content": "This is content.",
    "content_type": "MD",
    "public": true,
    "create_datetime": "2018-06-25T02:56:36.327163Z",
    "update_datetime": "2018-06-25T02:56:36.349320Z",
    "category": null,
    "author": null,
    "tags": [
        "asdfasdf",
        "sadfasdf"
    ]
}
```

# 清空返回的Null和空列表字段

```python
class ArticleCategorySerializer(serializers.ModelSerializer):
    """文章分类"""
    children = RecursiveField(many=True)

    class Meta:
        model = ArticleCategory
        fields = '__all__'

    def to_representation(self, instance):
        """
        Object instance -> Dict of primitive datatypes.
        """
        ret = OrderedDict()
        fields = [field for field in self.fields.values() if not field.write_only]

        for field in fields:
            try:
                attribute = field.get_attribute(instance)
            except SkipField:
                continue

            if attribute is not None:
                represenation = field.to_representation(attribute)
                if represenation is None:
                    # Do not seralize empty objects
                    continue
                if isinstance(represenation, list) and not represenation:
                    # Do not serialize empty lists
                    continue
                ret[field.field_name] = represenation

        return ret
```

# Context

send context infomation to serializers from view set

```shell
# send: serializer = AlbumSerializer(qs, many=True, context={'request': request})
class AlbumViewSet(viewsets.ModelViewSet):
    queryset = Album.objects.all()
    serializer_class = AlbumSerializer
    authentication_classes = [TokenAuth]

    def perform_create(self, serializer):
        serializer.save(author=self.request.user)

    def list(self, request, *args, **kwargs):
        qs = Album.objects.filter(Q(author=self.request.user) | Q(albumsubscribe__subscriber=self.request.user))
        serializer = AlbumSerializer(qs, many=True, context={'request': request})
        return Response(serializer.data)

# obtain: self.context['request']
class AlbumSerializer(serializers.ModelSerializer):
    photos = AlbumPhotoSerializer(many=True, allow_null=True, read_only=True)

    class Meta:
        model = Album
        fields = '__all__'

    def to_representation(self, instance):
        response = super().to_representation(instance)
        response['ownership'] = 'creator' if self.context['request'].user == instance.author else 'subscribe'
        return response
```

