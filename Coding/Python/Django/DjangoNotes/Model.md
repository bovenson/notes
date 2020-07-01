---
title: Django Model 笔记
tags:
	- Django
categories:
	- Python
---

# Model对象转化为Dict

```python
from django.http import JsonResponse
from django.forms.models import model_to_dict


return JsonResponse(  model_to_dict(modelinstance) )
```

**参考**

- [Stack Overflow](https://stackoverflow.com/questions/16790375/django-object-is-not-json-serializable)

