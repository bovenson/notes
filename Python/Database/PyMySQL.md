---
title: PyMySQL
tags: MySQL, PyMySQL, Python
---

# 获取表字段

```python
#!/bin/python3
# coding: utf-8
import pymysql

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "2017-11-22 10:11"

HOST = 'localhost'
USER = 'root'
PASSWORD = 'root'
DB_SCHEMA = 'test'


if __name__ == "__main__":
    db = pymysql.connect(HOST, USER, PASSWORD, DB_SCHEMA)
    cursor = db.cursor()
    sql = 'SELECT * from cnvd'
    cursor.execute(sql)
    for i in cursor.description:
        # print(i)
        print(i[0])
        
#### output
"""
/usr/local/bin/python3 /Users/bovenson/Git/notes/ICS-Security/CNVD/spider/db.py
CNVD-ID
CVE-ID
Vulnerability name
Type
Level
Release time
Description
Refer link
Discoverer
Device
Update time
Vendor
Patch
"""
```

