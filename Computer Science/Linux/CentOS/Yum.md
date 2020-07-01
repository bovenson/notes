---
title: Yum
tags:
	- Yum
categories:	
	- Linux
---

# 源

## 更换国内源

```shell
#### Base
# 备份
sudo mv /etc/yum.repos.d/CentOS-Base.repo /etc/yum.repos.d/CentOS-Base.repo.bak
# 阿里源
sudo wget -O /etc/yum.repos.d/CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo

#### epel
# epel(RHEL 7)
sudo wget -O /etc/yum.repos.d/epel.repo http://mirrors.aliyun.com/repo/epel-7.repo
# epel(RHEL 6)
sudo wget -O /etc/yum.repos.d/epel.repo http://mirrors.aliyun.com/repo/epel-6.repo
# epel(RHEL 5)
sudo wget -O /etc/yum.repos.d/epel.repo http://mirrors.aliyun.com/repo/epel-5.repo

# 清理缓存
sudo yum clean all

# 重新生成缓存
sudo yum makecache
```

