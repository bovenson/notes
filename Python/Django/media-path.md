---
title: 设置media路径
date: 2017-07-24 17:48:54
tags: Python, Django, 设置media路径, media path
---

# 设置media路径

## setting中添加

```python
# 设置 media 路径
MEDIA_ROOT = os.path.join(BASE_DIR, 'media/')
MEDIA_URL = '/media/'
```

## 项目/app urls.py中添加

```python
# 设置静态文件路径
urlpatterns += static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)
```

## 注意

- you need also `"django.core.context_processors.media"` in your `TEMPLATE_CONTEXT_PROCESSORS `

  ​

