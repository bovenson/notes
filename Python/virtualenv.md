# virtualenv

## 安装

```shell
sudo apt install python3-pip # 安装pip3
sudo pip3 install virtualenv # 安装virtualenv
```

## 新建虚拟环境

```shell
virtualenv venv
```

## 激活新环境

```shell
# 进入环境
cd venv 
# 激活
source bin/activate
```

## 退出环境

```shell
deactivate
```

## 配合supervisor

### supervisor配置文件

```ini
[program:myproj-uwsgi]
process_name=myproj-uwsgi
command=/home/myuser/.virtualenvs/myproj/bin/uwsgi
    --chdir /home/myuser/projects/myproj
    -w myproj:app
environment=PATH="/home/myuser/.virtualenvs/myproj/bin:%(ENV_PATH)s"
user=myuser
group=myuser
killasgroup=true
startsecs=5
stopwaitsecs=10
```

