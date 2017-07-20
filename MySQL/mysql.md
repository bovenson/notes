# mysql

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

