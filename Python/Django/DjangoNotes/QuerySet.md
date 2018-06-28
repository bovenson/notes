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

