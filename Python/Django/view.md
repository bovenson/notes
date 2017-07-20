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

