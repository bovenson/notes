# mysql-cluster

## 节点

| 节点类型    | 名称           | IP   |
| ------- | ------------ | ---- |
| 管理结点    | mysql-mgm    |      |
| 数据结点 1  | mysql-ndbd-1 |      |
| 数据结点 2  | mysql-ndbd-2 |      |
| SQL 结点1 | mysql-sql-1  |      |
| SQL 结点2 | mysql-sql-2  |      |

## 部署配置

### 下载 MySQL Cluster

[下载](https://dev.mysql.com/downloads/cluster/)

### 解压到指定目录

```shell
sudo tar -xvzf mysql-cluster-gpl-7.6.2-linux-glibc2.12-x86_64.tar.gz
sudo mv mysql-cluster-gpl-7.6.2-linux-glibc2.12-x86_64 /usr/local/mysql
```

### 关闭安全策略(CentOS等)

```shell
# 关闭iptables防火墙（或者打开防火墙的1186、3306端口），在Shell中运行以下命令
chkconfig --level 35 iptables off  
# 关闭SELinux
gedit /etc/selinux/config  
# 将config文件中的SELINUX项改为disabled

# This file controls the state of SELinux on the system.
# SELINUX= can take one of these three values:
#     enforcing - SELinux security policy is enforced.
#     permissive - SELinux prints warnings instead of enforcing.
#     disabled - No SELinux policy is loaded.
SELINUX=disabled
# SELINUXTYPE= can take one of these two values:
#     targeted - Targeted processes are protected,
#     mls - Multi Level Security protection.
SELINUXTYPE=targeted

# 最后重启系统
```

### 配置节点

#### 管理节点

##### 配置config.ini文件

```shell
mkdir /var/lib/mysql-cluster
cd /var/lib/mysql-cluster
vim config.ini
```

配置文件config.ini内容如下:

```ini
[ndbd default]
NoOfReplicas=2
DataMemory=80M
IndexMemory=18M

[ndb_mgmd]
NodeId=1
hostname=192.168.124.141
datadir=/var/lib/mysql-cluster

[ndbd]
NodeId=2
hostname=192.168.124.142
datadir=/usr/local/mysql/data

[ndbd]
NodeId=3
hostname=192.168.124.143
datadir=/usr/local/mysql/data

[mysqld]
NodeId=4
hostname=192.168.124.144

[mysqld]
NodeId=5
hostname=192.168.124.145
```

###### 安装管理结点

安装管理节点，不需要mysqld二进制文件，只需要MySQL Cluster服务端程序(ndb_mgmd)和监听客户端程序(ndb_mgm)。在shell中运行以下命令：

```shell
cp /usr/local/mysql/bin/ndb_mgm* /usr/local/bin
cd /usr/local/bin
chmod +x ndb_mgm*
```

#### 配置数据节点

###### 添加mysql组和用户

```shell
groupadd mysql
useradd -g mysql mysql
```

###### 配置my.cnf配置文件

`vim /etc/my.cnf`

配置文件my.cnf的内容如下：

```ini
[mysqld]
basedir=/usr/local/mysql
datadir=/usr/local/mysql/data
socket=/usr/local/mysql/sock/mysql.sock
user=mysql
# Disabling symbolic-links is recommended to prevent assorted security risks
symbolic-links=0

[mysqld_safe]
log-error=/var/log/mysqld.log
pid-file=/var/run/mysqld/mysqld.pid

[mysql_cluster]
ndb-connectstring=192.168.124.141
```

###### 创建系统数据库

```shell
cd /usr/local/mysql
mkdir sock
scripts/mysql_install_db --user=mysql --basedir=/usr/local/mysql --datadir=/usr/local/mysql/data
```

