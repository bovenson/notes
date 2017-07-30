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

