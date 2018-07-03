---
title: views
date: 2017-07-24 17:48:54
tags: Python, Django, views
---

# 获取客户端ip

```python
def get_remote_ip(request):
    try:
        if request.META.get('HTTP_X_FORWARDED_FOR'):
            ip = request.META['HTTP_X_FORWARDED_FOR']
        else:
            ip = request.META['REMOTE_ADDR']
    except:
        ip = ""
    return ip
```

# 限制HTTP请求方法

```python
from django.views.decorators.http import require_http_methods

@require_http_methods(["GET", "POST"])
def my_view(request):
    # I can assume now that only GET or POST requests make it this far
    # ...
    pass
```

# 从request获取用户

```python
request.user

#### 判断是否登录
request.user.is_authenticated()
```

