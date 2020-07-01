---
title: Awk笔记
tags:
	- Awk
categories:
	- Linux
---

# 使用

# 示例

## 删除文件注释

```shel
$ awk '\^[^#]\' file-name > new-file-name
```

