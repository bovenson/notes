```
title: Docker搭建代理服务器
tags:
	- Squid
categories:
	- Linux
```

[TOC]

# 环境说明

| 项目 | 说明        |
| ---- | ----------- |
| 系统 | Deepin 15.5 |

# 步骤

- 安装Docker
- 安装Squid容器
- 生成认证文件
- 配置Squid服务器
- 启动Squid容器
- 设置代理地址
- 使用代理

# 安装Docker

```shell
# 下载安装脚本
$ curl -fsSL get.docker.com -o get-docker.sh

# 安装 docker
$ sudo sh get-docker.sh

# 启动 docker
$ sudo service docker start 
```

# 安装Squid容器

```shell
# 从docker hub下载容器
$ docker pull sameersbn/squid

# 在docker中创建容器
$ docker run --name squid -d --restart=always \
  --publish 3128:3128 \
  --volume /srv/docker/squid/cache:/var/spool/squid3 \
  sameersbn/squid
```

# 配置用户名密码认证

```shell
# 生成认证文件
$ sudo htpasswd squid_passwd your-username
## 在这里输入两次密码

# 将认证文件拷贝至容器
$ sudo docker cp squid_passwd squid:/etc/squid3/
```

# Squid配置

**配置文件大概如下**

```
acl localnet src 10.0.0.0/8	# RFC1918 possible internal network
acl localnet src 172.16.0.0/12	# RFC1918 possible internal network
acl localnet src 192.168.0.0/16	# RFC1918 possible internal network
acl localnet src fc00::/7       # RFC 4193 local private network range
acl localnet src fe80::/10      # RFC 4291 link-local (directly plugged) machines
acl localnet src 0.0.0.0/0.0.0.0
acl localnet src 0.0.0.0/8

acl SSL_ports port 443
acl Safe_ports port 80		# http
acl Safe_ports port 21		# ftp
acl Safe_ports port 443		# https
acl Safe_ports port 70		# gopher
acl Safe_ports port 210		# wais
acl Safe_ports port 1025-65535	# unregistered ports
acl Safe_ports port 280		# http-mgmt
acl Safe_ports port 488		# gss-http
acl Safe_ports port 591		# filemaker
acl Safe_ports port 777		# multiling http
acl CONNECT method CONNECT

# username&password auth config
auth_param basic program /usr/lib/squid3/basic_ncsa_auth /etc/squid3/squid_passwd
acl ncsa_users proxy_auth REQUIRED
http_access allow ncsa_users


http_access deny !Safe_ports
http_access deny CONNECT !SSL_ports
http_access allow localhost manager
http_access deny manager
http_access deny to_localhost
http_access allow localnet
http_access allow localhost
http_access deny all
http_port 3128

cache_dir ufs /var/spool/squid3 100 16 256
coredump_dir /var/spool/squid3

refresh_pattern ^ftp:		1440	20%	10080
refresh_pattern ^gopher:	1440	0%	1440
refresh_pattern -i (/cgi-bin/|\?) 0	0%	0
refresh_pattern (Release|Packages(.gz)*)$      0       20%     2880
refresh_pattern .		0	20%	4320
```

**将配置文件导入Squid容器**

```shell
$ sudo docker cp squid-simple.conf squid:/etc/squid3/squid.conf
```

**配置文件生成说明**

```shell
# 从Squid容器中导出默认配置文件
$ sudo docker cp squid:/etc/squid3/squid.conf ./

# 去掉注释
$ sudo awk '/^[^#]/' squid.conf > squid-simple.conf

# 编辑配置文件
$ sudo vim squid-simple.conf
## 在这里添加几行
acl localnet src 0.0.0.0/0.0.0.0
acl localnet src 0.0.0.0/8
auth_param basic program /usr/lib/squid3/basic_ncsa_auth /etc/squid3/squid_passwd
acl ncsa_users proxy_auth REQUIRED
http_access allow ncsa_users
```

# 启动或重启容器

```shell
# 启动容器
$ sudo docker start squid

# 重启容器
$ sudo docker restart squid
```

# 使用代理

```shell
## 代理地址
# http://{your-username}:{your-password}@{your-ip OR domain-name}:3128
## 例如
# http://root:root@202.118.1.100:3128

## 在Linux终端中使用代理
export ftp_proxy=http://{your-username}:{your-password}@{your-ip OR domain-name}:3128
export http_proxy=http://{your-username}:{your-password}@{your-ip OR domain-name}:3128
export https_proxy=http://{your-username}:{your-password}@{your-ip OR domain-name}:3128
## 例如
export ftp_proxy=http://root:root@202.118.1.100:3128
export http_proxy=http://root:root@202.118.1.100:3128
export https_proxy=http://root:root@202.118.1.100:3128
```

# 演示

## 代理成功

```shell
bovenson@ThinkCentre:~/Tmp$ export http_proxy=http://***:***@***.***.***.***:3128
bovenson@ThinkCentre:~/Tmp$ export https_proxy=http://***:***@***.***.***.***:3128
bovenson@ThinkCentre:~/Tmp$ wget www.baidu.com
--2018-04-15 00:40:52--  http://www.baidu.com/
正在连接 118.202.45.20:3128... 已连接。
已发出 Proxy 请求，正在等待回应... 200 OK
长度：2381 (2.3K) [text/html]
正在保存至: “index.html.1”

index.html.1                             100%[==================================================================================>]   2.33K  --.-KB/s  用时 0s      

2018-04-15 00:40:52 (333 MB/s) - 已保存 “index.html.1” [2381/2381])
```

## 认证失败

```shell
bovenson@ThinkCentre:~/Tmp$ export http_proxy=http://***.***.***.***:3128
bovenson@ThinkCentre:~/Tmp$ export https_proxy=http://***.***.***.***:3128
bovenson@ThinkCentre:~/Tmp$ wget www.so.com
--2018-04-15 00:42:01--  http://www.so.com/
正在连接 118.202.45.20:3128... 已连接。
已发出 Proxy 请求，正在等待回应... 407 Proxy Authentication Required
2018-04-15 00:42:01 错误 407：Proxy Authentication Required。
```

# 注意事项

- 如果需要，把上文中Squid路径由`/usr/lib/squid3`修改为`/usr/lib/squid`

# 参考

- [参考一](https://github.com/sameersbn/docker-squid)
- [参考二](https://blog.csdn.net/skylinethj/article/details/43837277)