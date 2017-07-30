---
title: manage.py使用
date: 2017-07-24 17:48:54
tags: Python, Django, manage.py
---

# manage.py使用

## 创建项目超级用户

```shell
bovenson@ThinkCentre:/media/bovenson/Data/workspace/Pycharm/cloudlibrary$ python3 manage.py createsuperuser
Username (leave blank to use 'bovenson'): bovenson
Email address: szhkai@qq.com
Password: 
Password (again): 
Superuser created successfully.
```

## 同步数据库

```shell
python3 manage.py makemigrations
python3 manage.py migrate
```

## 数据库备份及恢复

### 手动

```shell
# 备份一个APP
python3 manage.py dumpdata app_name --format=json > app.json

# 备份整个数据库
python3 manage.py dumpdata --format=json > app.json

# 恢复
python3 manage.py loaddata app.json
```

