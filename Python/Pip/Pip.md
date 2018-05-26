---
title: Pip
tags:
	- Pip
categories:
	- Python
---

# 更改源

## 使用清华大学镜像

**临时使用**

```shell
pip install -i https://pypi.tuna.tsinghua.edu.cn/simple package-names
```

**默认使用**

- 修改配置文件（如果没有则新建）

  - `Linux`:  `~/.config/pip/pip.conf`
  - `Windows`: `%APPDATA%\pip\pip.ini`
  - `MacOS`: `$HOME/Library/Application Support/pip/pip.conf`
  - `Linux中,pip和pip3共存时`: `~/.pip/pip.conf`

- 修改

  ```ini
  [global]
  index-url = https://pypi.tuna.tsinghua.edu.cn/simple
  ```

  ​