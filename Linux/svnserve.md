# svn服务器搭建

## Subversion

[参考](http://www.tuicool.com/articles/3AfaEnu)

### 命令

| 命令            | 说明                   |
| ------------- | -------------------- |
| svn           | 命令行客户端               |
| svnadmin      | 用来创建、调整或修复版本库的工具     |
| svnserve      | svn服务程序              |
| svndumpfilter | 过滤svn版本库转储数据流的工具     |
| svnsync       | svn数据同步工具，实现另外存一份相同的 |
| svnlook       | 用来查看办本科中不同的修订版和事务    |

### 安装

```shell
sudo apt install subversion
```

### 创建版本库

```shell
sudo mkdir /home/svn
sudo svnadmin create /home/svn/repos
```

#### 版本库内容介绍

```shell
bovenson@Lenovo:/home/svn/repos$ tree
.
├── conf				# 存放主配置文件和用户、权限位置
│   ├── authz		 	# authz文件是设置用户权限
│   ├── hooks-env.tmpl
│   ├── passwd			# passwd文件是存储用户及密码
│   └── svnserve.conf	 # svnserve.conf是主配置文件
├── db					# 存放svn转储后的数据
│   ├── current
│   ├── format
│   ├── fsfs.conf
│   ├── fs-type
│   ├── min-unpacked-rev
│   ├── revprops
│   │   └── 0
│   │       └── 0
│   ├── revs
│   │   └── 0
│   │       └── 0
│   ├── transactions
│   ├── txn-current
│   ├── txn-current-lock
│   ├── txn-protorevs
│   ├── uuid
│   └── write-lock
├── format
├── hooks
│   ├── post-commit.tmpl
│   ├── post-lock.tmpl
│   ├── post-revprop-change.tmpl
│   ├── post-unlock.tmpl
│   ├── pre-commit.tmpl
│   ├── pre-lock.tmpl
│   ├── pre-revprop-change.tmpl
│   ├── pre-unlock.tmpl
│   └── start-commit.tmpl
├── locks
│   ├── db.lock
│   └── db-logs.lock
└── README.txt

10 directories, 28 files

```

### 配置版本库

```shell
# sudo vi svnserve.conf  # 将以下参数去掉注释
[general]
anon-access = none     #匿名访问权限，默认read，none为不允许访问
auth-access = write   #认证用户权限  
password-db = passwd  #用户信息存放文件，默认在版本库/conf下面，也可以绝对路径指定文件位置
authz-db = authz
```

```shell
# sudo vi passwd     #格式是用户名=密码，采用明文密码
[users]
xiaoming = 123
zhangsan = 123
lisi = 123
```

```shell
# sudo vi authz 
[groups]           #定义组的用户
manager = xiaoming
core_dev = zhangsan,lisi
[repos:/]          #以根目录起始的repos版本库manager组为读写权限
@manager = rw
[repos:/media]     #core_dev对repos版本库下media目录为读写权限
@core_dev = rw
```

### 启动svn服务

`sudo svnserve -d -r /home/svn`

#### 配置端口

`snvserver -d -r /path/to/svn/repos --listen-port 8443`

#### 重启脚本

```shell
#!/bin/bash
ps -e | grep svnserve | awk '{print $1}' | xargs kill -9
sudo svnserve -d -r /home/svn/repos --listen-port 8443
```

