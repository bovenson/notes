---
title: Django Rest Framework Permissions
tags:	
	- Django Rest Framework
categories:
	- Python
---

# Customer Permissions

- `has_permission`
  - 判断Action
- `has_object_permission` : 通过`has_permission`之后，才会进行验证

```python
class IsUserSelfPermission(permissions.BasePermission):
    """如果访问的用户信息不是认证用户则拒绝"""
    message = '只能访问当前用户信息'

    def has_permission(self, request, view):
        self.message = '只有管理员可以访问用户列表'
        # 只有超级用户可以访问列表
        if view.action == 'list':
            return request.user.is_superuser
        return True

    def has_object_permission(self, request, view, obj):
        return request.user.id == obj.id or request.user.is_superuser
```

```python
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import authentication, permissions
from django.contrib.auth.models import User

class ListUsers(APIView):
    """
    View to list all users in the system.

    * Requires token authentication.
    * Only admin users are able to access this view.
    """
    authentication_classes = (authentication.TokenAuthentication,)
    permission_classes = (permissions.IsAdminUser,)

    def get(self, request, format=None):
        """
        Return a list of all users.
        """
        usernames = [user.username for user in User.objects.all()]
        return Response(usernames)
```

