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

