---
title: vsftpd usage
categories:
	- [linux, software]
tags:
	- ftp
	- vsftpd
date: 2020/10/26 18:00:00
update: 2020/10/26 18:00:00
---

# 安装

```shell
# ubuntu
$ sudo apt install vsftpd
```

# 配置

## 配置文件路径

```shell
# ubuntu
/etc/vsftpd.conf
```

## 配置项

```shell
listen=YES    # 是否监听网络端口, 默认NO
```

# 用户

## 本地用户

```shell
local_enable=YES    # 允许使用本地用户账号密码登录
```

## 虚拟用户

仅用于登录ftp服务器的账号密码。

```shell
# [required] 安装 pam & htpasswd
$ sudo apt install libpam-pwdfile apache2-utils

# [required] 创建目录
$ sudo mkdir /home/ftp
$ sudo mkdir /etc/vsftpd

# [required] /etc/vsftpd.conf 修改为如下配置
listen=YES
anonymous_enable=NO
local_enable=YES
write_enable=YES
local_umask=022
nopriv_user=vsftpd
virtual_use_local_privs=YES
guest_enable=YES
user_sub_token=$USER
local_root=/var/www/$USER        # root, 登录前确保用户目录已存在
chroot_local_user=YES
hide_ids=YES
guest_username=vsftpd
user_config_dir=/etc/vsftpd_user_conf
allow_writeable_chroo=YES

# [required] 创建ftp用户
$ sudo htpasswd -cd /etc/vsftpd/ftpd.passwd user-name    # then enter password
# -c: create, 如需额外添加，去除该参数

# [required] /etc/pam.d/vsftpd 替换为如下内容
auth required pam_pwdfile.so pwdfile /etc/vsftpd/ftpd.passwd
account required pam_permit.so

# [required] 创建没有shell登录权限的本地用户 vsftpd，用于虚拟用户登录
$ sudo useradd --home /home/vsftpd --gid nogroup -m --shell /bin/false vsftpd
# [optional] 验证
$ id vsftpd

# [optional untested] 用户独立配置，配置文件路径为 ${user_config_dir}/${user}，以 wii 为例
$ sudo vim /etc/vsftpd_user_conf

# [required] 重启vsftpd
$ sudo service vsftpd restart
```

# 参考

- https://askubuntu.com/questions/575523/how-to-setup-virtual-users-for-vsftpd-with-access-to-a-specific-sub-directory