# Ubuntu 服务器配置部署

## 系统版本

`Ubuntu 16.04.1`, 非`server`版

## 事务清单

### 更新

#### 更改源

`设置(System Setting)->软件和更新(Software&Updates)->下载源(Download from)->选择快速的源`

**如果使用按流量计费的网络,且IPv6不计入流量的话,可以选择清华和中科大的源,东大的就算了**

#### 更新及升级

```bash
sudo apt update
sudo apt upgrade
```



### 安装Vim

`sudo apt install vim`



### 安装`openssh-server` 

`sudo apt insatll openssh-server`



### 安装FTP服务器-vsftpd

`sudo apt install vsftpd`

####  配置文件位置

`/etc/vsftpd.conf`

按需设置



## 安装nginx

`sudo apt install nginx`



## 常用命令

### 关机及重启

```shell
sudo shutdown -h now	# 马上关机
sudo shutdown -r now	# 马上重启
```

