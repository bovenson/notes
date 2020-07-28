---
tiles: Jetbrain IDEs
tags:
	- Jetbrain
categories:
	- Jetbrain
---

# 快捷键

```shell
# 函数参数提示
Command + P		# MAC
```

# 文件和代码模板

- [File and Code Templates](https://www.jetbrains.com/help/idea/file-and-code-templates.html)
- [变量](https://www.jetbrains.com/help/webstorm/file-template-variables.html)

## Python

```python
#!/usr/bin python3
# coding: utf-8

"""
FILE: ${FILE_NAME}
DATE: ${DATE} ${TIME}
DESC: 
"""

__author__ = "bovenson"
__maintainer__ = "bovenson"
__version__ = "1.0"
__email__ = "szhkai@qq.com"
__date__ = "${DATE}"
```



```python
#!/bin/python3
# coding: utf-8

"""
FILE: ${FILE_NAME}
DESC: 
"""

__author__ = "bovenson"
__email__ = "szhkai@qq.com"
__date__ = "${YEAR}-${MONTH}-${DAY} ${HOUR}:${MINUTE}"
```

## Js

```javascript
/*
* @author   bovenson
* @email    szhkai@qq.com
* @file     ${NAME}
* @date     ${DATE}
* @desc     DESCRIPTION
**/
```

# Copyright

- 设置`Preferences -> Editor -> Copyright -> Copyright Profiles -> Add`

```shell
Copyright (c) $today.year. XiaoMi Inc. All Rights Reserved.
Authors: Sun Zhenkai <sunzhenkai@xiaomi.com>.
Created on $today.year/$today.month/$today.day $today.hour24:$today.minute:$today.second.
```

- 添加 Scope - Copyright Profile
- 右键 -> generate -> copyright

# 注释符在行首的问题

```shell
Setting -> Editor -> Code Style -> Comment Code
	- 取消 Line comment in first column
	- 勾选 Add a space at comment start 
```

# Hide File & Directory

- Open Settings
  - **File | Settings | Editor | File Types** 
  - **IntelliJ IDEA | Preferences | Editor | File Types** for OS X
- Modify Config
  - Add patterns for ignoring files