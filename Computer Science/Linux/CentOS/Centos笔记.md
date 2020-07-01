[TOC]

# 应用

## 安装

# 查看系统

## `uname`

- 打印内核名称

  `uname -s`

- 打印内核release

  `uname -r`

- 打印网络节点名称(network node hostname)

  `uname -n`

- 打印操作系统名称

  `uname -o`

## `cat /proc/version`

```shell
[bovenson@IP-202-118-26-19 ~]$ cat /proc/version 
Linux version 3.10.0-514.el7.x86_64 (builder@kbuilder.dev.centos.org) (gcc version 4.8.5 20150623 (Red Hat 4.8.5-11) (GCC) ) #1 SMP Tue Nov 22 16:42:41 UTC 2016
```



## 查看发行版本

`cat /etc/redhat-release`

```shell
[bovenson@IP-202-118-26-19 ~]$ cat /etc/redhat-release 
CentOS Linux release 7.3.1611 (Core)
```

`rpm -q centos-release`

```shell
[bovenson@IP-202-118-26-19 ~]$ rpm -q centos-release 
centos-release-7-3.1611.el7.centos.x86_64
```

# 更换为清华源

- 首先备份 CentOS-Base.repo `sudo mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.bak`

- 将以下内容写入 /etc/yum.repos.d/CentOS-Base.repo

- ```
  # CentOS-Base.repo
  #
  # The mirror system uses the connecting IP address of the client and the
  # update status of each mirror to pick mirrors that are updated to and
  # geographically close to the client.  You should use this for CentOS updates
  # unless you are manually picking other mirrors.
  #
  # If the mirrorlist= does not work for you, as a fall back you can try the
  # remarked out baseurl= line instead.
  #
  #

  [base]
  name=CentOS-$releasever - Base
  baseurl=https://mirrors.tuna.tsinghua.edu.cn/centos/$releasever/os/$basearch/
  #mirrorlist=http://mirrorlist.centos.org/?release=$releasever&arch=$basearch&repo=os
  gpgcheck=1
  gpgkey=file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-7

  #released updates
  [updates]
  name=CentOS-$releasever - Updates
  baseurl=https://mirrors.tuna.tsinghua.edu.cn/centos/$releasever/updates/$basearch/
  #mirrorlist=http://mirrorlist.centos.org/?release=$releasever&arch=$basearch&repo=updates
  gpgcheck=1
  gpgkey=file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-7

  #additional packages that may be useful
  [extras]
  name=CentOS-$releasever - Extras
  baseurl=https://mirrors.tuna.tsinghua.edu.cn/centos/$releasever/extras/$basearch/
  #mirrorlist=http://mirrorlist.centos.org/?release=$releasever&arch=$basearch&repo=extras
  gpgcheck=1
  gpgkey=file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-7

  #additional packages that extend functionality of existing packages
  [centosplus]
  name=CentOS-$releasever - Plus
  baseurl=https://mirrors.tuna.tsinghua.edu.cn/centos/$releasever/centosplus/$basearch/
  #mirrorlist=http://mirrorlist.centos.org/?release=$releasever&arch=$basearch&repo=centosplus
  gpgcheck=1
  enabled=0
  gpgkey=file:///etc/pki/rpm-gpg/RPM-GPG-KEY-CentOS-7
  ```

- 更新软件包缓存 `sudo yum makecache`