---
title: Jupyter Usage
tags:
	- Jupyter
	- Python
categories:
	- 机器学习
---

# 安装

```shell
sudo pip3 install jupyter	# linux
```

# 运行notebook服务器

## 配置密码

**步骤**

- 生成配置文件

- 设置密码

  - 方式一：使用命令自动设置密码

    ```shell
    jupyter notebook password
    ```
    - 这种方式会将`hashed`密码保存在文件`jupyter_notebook_config.json`中

  - 方式二：手动设置使用`hashed password`

    - 获取

      ```shell
      bovenson@MBP:~/Git/notes/Machine Learning/Jupyter$ python3
      Python 3.6.4 (default, Mar  9 2018, 23:15:03) 
      [GCC 4.2.1 Compatible Apple LLVM 9.0.0 (clang-900.0.39.2)] on darwin
      Type "help", "copyright", "credits" or "license" for more information.
      >>> from notebook.auth import passwd
      >>> passwd()
      Enter password: 
      Verify password: 
      'sha1:04e1321856dd:2bb9632ec903b3dce6a99646f20920749a0dfb32'
      ```

    - 添加`hashed password`到配置文件`~/.jupyter/jupyter_notebook_config.py`

      ```shell
      c.NotebookApp.password = u'sha1:04e1321856dd:2bb9632ec903b3dce6a99646f20920749a0dfb32'
      ```


**完整示例**

```shell
# 生成配置文件
$ jupyter notebook --generate-config
# 设置密码
$ jupyter notebook password
Enter password: 
Verify password: 
[NotebookPasswordApp] Wrote hashed password to /Users/bovenson/.jupyter/jupyter_notebook_config.json
```



