[TOC]

# 安装Scrapy出错

## 安装

### 使用pip安装(Ubuntu)

```shell
# 安装pip
sudo apt install python-pip	# python2
sudo apt install python3-pip # python3
# python3
sudo pip3 install Scrapy
# python2
sudo pip install Scrapy
```



## 错误
### cryptography安装出错

使用pip安装Scrapy时:

```shell
...
...
Requirement already satisfied: pycparser in /usr/local/lib/python3.5/dist-packages (from cffi>=1.4.1->cryptography>=1.3.4->pyOpenSSL->Scrapy)
Installing collected packages: cryptography, pyOpenSSL, service-identity, Scrapy
  Found existing installation: cryptography 1.2.3
    Uninstalling cryptography-1.2.3:
      Successfully uninstalled cryptography-1.2.3
  Running setup.py install for cryptography ... error
    Complete output from command /usr/bin/python3 -u -c "import setuptools, tokenize;__file__='/tmp/pip-build-cye1ome_/cryptography/setup.py';f=getattr(tokenize, 'open', open)(__file__);code=f.read().replace('\r\n', '\n');f.close();exec(compile(code, __file__, 'exec'))" install --record /tmp/pip-q_y00_ho-record/install-record.txt --single-version-externally-managed --compile:
    running install
    running build
    running build_py
    creating build
    creating build/lib.linux-x86_64-3.5
    creating build/lib.linux-x86_64-3.5/cryptography
    copying src/cryptography/exceptions.py -> build/lib.linux-x86_64-3.5/cryptography
    copying src/cryptography/__init__.py -> build/lib.linux-x86_64-3.5/cryptography
    copying src/cryptography/fernet.py -> build/lib.linux-x86_64-3.5/cryptography
...
...
```

#### 解决方法

[点击查看原文](http://stackoverflow.com/questions/22073516/failed-to-install-python-cryptography-package-with-pip-and-setup-py)

可能的原因: 缺少依赖库.

##### for Ubuntu

**python2**

`sudo apt-get install build-essential libssl-dev libffi-dev python-dev`

**python3**

`sudo apt-get install build-essential libssl-dev libffi-dev python3-dev`