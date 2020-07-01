---
title: Django QuerySet 笔记
tags:
	- Django
categories:
	- Python
---

# 遍历ManyToManyField

```python
# Model
class Article(models.Model):
    ...
    tags = models.ManyToManyField(ArticleTag, blank=True)
    ...

# Reverse
instance = Article.objects.get(pk=1)
for item in instance.tags.all():
    # DO SOMETHING
```

# Filter

## Datetime

```shell
# 比当前更早
User.objects.filter(join_date__lte=datetime.now()).order_by('-join_date')
# 比三天前更早
User.objects.filter(join_date__lte=datetime.now()-timedelta(days=3)).order_by('-join_date')
```

# Query

```shell
# model
class Album(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    title = models.CharField('标题', max_length=200)
    summary = models.CharField('简介', max_length=500, default='', blank=True)
    author = models.ForeignKey(User, on_delete=models.CASCADE, null=True)
    create_date = models.DateTimeField(auto_now_add=True)
    
class AlbumSubscribe(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    subscriber = models.ForeignKey(User, on_delete=models.CASCADE, null=True)
    album = models.ForeignKey(Album, on_delete=models.CASCADE, null=True)
    create_date = models.DateTimeField(auto_now_add=True)
    
    
# query
from django.db.models import Q
class AlbumViewSet(viewsets.ModelViewSet):
    queryset = Album.objects.all()
    serializer_class = AlbumSerializer
    authentication_classes = [TokenAuth]

		# save user infomation
    def perform_create(self, serializer):
        serializer.save(author=self.request.user)

		# query album as creator or subscriber
    def list(self, request, *args, **kwargs):
        qs = Album.objects.filter(Q(author=self.request.user) | Q(albumsubscribe__subscriber=self.request.user))
        serializer = AlbumSerializer(qs, many=True, context={'request': request})
        return Response(serializer.data)

```

