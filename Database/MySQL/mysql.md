---
title: MySQL笔记
tags: 
	- MySQL
categories:
	- MySQL
---

# MySQL

**注 : **有些操作和操作系统有关。

## CLI登录

```shell
mysql -h localhost -u root -p

#### 示例
bovenson@MBP:~/Git/notes/MySQL$ mysql -h localhost -u root -p
Enter password: 
Welcome to the MySQL monitor.  Commands end with ; or \g.
Your MySQL connection id is 14
Server version: 5.7.20 MySQL Community Server (GPL)

Copyright (c) 2000, 2017, Oracle and/or its affiliates. All rights reserved.

Oracle is a registered trademark of Oracle Corporation and/or its
affiliates. Other names may be trademarks of their respective
owners.

Type 'help;' or '\h' for help. Type '\c' to clear the current input statement.

mysql> 
```

## 启动/停止

```shell
### Mac
sudo /usr/local/mysql/support-files/mysql.server start	# 启动
sudo /usr/local/mysql/support-files/mysql.server stop	# 停止
sudo /usr/local/mysql/support-files/mysql.server restart	# 重启
```

## 修改密码

### SET PASSWORD

```shell
# set password for 用户名@localhost = password('新密码'); 
set password for root@localhost = password('123'); 
```

## 系统用户

### 添加

```shell
CREATE USER 'myuser'@'localhost' IDENTIFIED BY 'mypass';
CREATE USER 'myuser'@'%' IDENTIFIED BY 'mypass';
```

### 赋予权限

```shell
GRANT ALL ON *.* TO 'myuser'@'localhost';
GRANT ALL ON *.* TO 'myuser'@'%';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
```

## 允许远程连接

### 设置bind-address

```shell
sudo vim /etc/mysql/mysql.conf.d/mysqld.cnf
# 修改bind-address
bind-address = 0.0.0.0
```

### 添加/修改账户

添加/修改账户允许的`Host`为`%`

## 命令行远程登录mysql服务器

```shell
mysql -u root -p -h 10.154.0.43 -P 3306
```

## workbench

### 时间设置默认当前

默认设置为`CURRENT_TIMESTAMP`

## 添加管理账户

- [参考1](https://stackoverflow.com/questions/6085455/restoring-deleted-root-user-and-password-for-mysql)
- [参考2](http://www.kevssite.com/deleted-rootlocalhost-account-in-mysql/)

## lower_case

## 错误

### Can't get hostname from your ip address

Just add below in `my.ini` or `my.cnf`.

```
[mysqld]
skip-name-resolve 
```

- [参考](https://stackoverflow.com/questions/5118151/mysql-error-cant-get-hostname-from-your-ip-address)

# CentOS安装MySQL

```shell
# 下载 rpm 源
$ wget https://repo.mysql.com//mysql57-community-release-el7-11.noarch.rpm

# 安装 rpm 源
$ yum localinstall mysql57-community-release-el7-11.noarch.rpm

# 安装 MySQL
$ yum install mysql-community-server

# 启动 MySQL
$ systemctl start mysqld

# 开机启动
$ systemctl enable mysqld
$ systemctl daemon-reload

# 获取root密码
$ cat /var/log/mysqld.log | grep password
... [Note] A temporary password is generated for root@localhost:  ...

# 登录mysql
$ mysql -u root -p	
# 输入获取到的临时密码

# 重置root密码
mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY '{password}'; 

# 新建远程登录用户
mysql> CREATE USER 'root'@'%' IDENTIFIED BY '{password}';

# 授权
mysql> GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;

# 刷新权限
mysql> FLUSH PRIVILEGES;

# 防火墙放行3306端口
$ firewall-cmd --zone=public --add-port=3306/tcp --permanent
$ firewall-cmd --reload
```

