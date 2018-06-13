---
title: nginx+virtualenv+uwsgi
date: 2017-07-24 17:48:54
tags: Python, Django, Deploy
---

# nginx+virtualenv+uwsgi

## virtualenv

### 安装

```shell
sudo apt install python3-pip # 安装pip3
sudo pip3 install virtualenv # 安装virtualenv
```

### 新建虚拟环境

```shell
virtualenv venv
```

### 激活新环境

```shell
# 进入环境
cd venv 
# 激活
source bin/activate
```
### 安装uwsgi

```shell
sudo pip3 install uwsgi
```

## 部署nginx

### nginx配置

```conf
upstream wildbook {
        server unix:///tmp/wildbook.sock;
}

server{
        listen 9001;
        server_name book.szhkai.win;

        location /media {
                alias /home/ubuntu/project/wildbook/media;
        }

        location /static {
                alias /home/ubuntu/project/wildbook/serverstatic;
        }

        location / {
                uwsgi_pass wildbook;
                include /etc/nginx/uwsgi_params;
        }
}
```

## uwsgi配置

### 示例

```ini
[uwsgi]
chdir = /home/ubuntu/project/wildbook
module = wildteam.wsgi
master = true
processes = 5
socket = /tmp/wildteam.sock
chmod-socket = 666
vacuum = true
```

## 运行

### 示例

```shell
#!/bin/bash
uwsgi /home/ubuntu/virtualenv/wildbook/wildbook/wildteam/deport/wfw/wildteam_uwsgi.ini -d log/uwsgi.log
```

## supervisor配置

### 示例

```conf
[program:wildbook]
command=uwsgi /home/ubuntu/virtualenv/wildbook/wildbook/wildteam/deport/wfw/wildteam_uwsgi_wfw.ini -d log/uwsgi.log
```

### 部署脚本

### 示例

```shell
#!/bin/bash
sudo rm /etc/nginx/sites-enabled/wildbook_nginx.conf
sudo ln -s /home/ubuntu/project/wildbook/deport/szk/wildbook_nginx.conf /etc/nginx/sites-enabled/wildbook_nginx.conf
sudo service nginx restart
```

# nginx + supervisor

