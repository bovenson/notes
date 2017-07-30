---
title: setting.py 文件配置说明
date: 2017-07-24 17:48:54
tags: Python, Django, setting.py, 配置文件
---

# setting.py 文件配置说明

## 设置中文

```python
LANGUAGE_CODE = 'zh-Hans'	# 如果不可以, 尝试 'zh_CN', 'zh-CN'
TIME_ZONE = 'UTC'
USE_I18N = Trues
USE_L10N = True
USE_TZ = True
```

## 修改时区

```shell
TIME_ZONE = 'Asia/Shanghai'
```

