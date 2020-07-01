---
title: CentOS网络配置
tags: CentOS, 网络配置, Network
---

# 配置静态IP

```shell
#### 网络接口文件
vi /etc/sysconfig/network-scripts/ifcfg-ens160

#### 修改的配置
ONBOOT=yes
BOOTPROTO=static
IPADDR=202.118.26.34
GATEWAY=202.118.26.254
NETMASK=255.255.255.0
DNS1=202.118.1.29

#### 最终配置
TYPE=Ethernet
BOOTPROTO=static
DEFROUTE=yes
PEERDNS=yes
PEERROUTES=yes
IPV4_FAILURE_FATAL=no
IPV6INIT=yes
IPV6_AUTOCONF=yes
IPV6_DEFROUTE=yes
IPV6_PEERDNS=yes
IPV6_PEERROUTES=yes
IPV6_FAILURE_FATAL=no
IPV6_ADDR_GEN_MODE=stable-privacy
NAME=ens160
UUID=1514732d-e28d-4a8f-acb0-2bb598167651
DEVICE=ens160
ONBOOT=yes
IPADDR=202.118.26.34
GATEWAY=202.118.26.254
NETMASK=255.255.255.0
DNS1=202.118.1.29
```

## 参考

- [参考一](http://blog.csdn.net/johnnycode/article/details/40624403)

# 配置为DHCP

```shell
#### 网络接口文件
vi /etc/sysconfig/network-scripts/ifcfg-ens160

#### 网络接口配置
TYPE=Ethernet
BOOTPROTO=dhcp
DEFROUTE=yes
PEERDNS=yes
PEERROUTES=yes
IPV4_FAILURE_FATAL=no
IPV6INIT=yes
IPV6_AUTOCONF=yes
IPV6_DEFROUTE=yes
IPV6_PEERDNS=yes
IPV6_PEERROUTES=yes
IPV6_FAILURE_FATAL=no
IPV6_ADDR_GEN_MODE=stable-privacy
NAME=ens160
UUID=1514732d-e28d-4a8f-acb0-2bb598167651
DEVICE=ens160
ONBOOT=yes
```

# 配置硬件地址

```shell
HWADDR="00:15:5D:07:F1:02"  # 硬件地址
TYPE="Ethernet"  
BOOTPROTO="static" #dhcp改为static   
DEFROUTE="yes"  
PEERDNS="yes"  
PEERROUTES="yes"  
IPV4_FAILURE_FATAL="no"  
IPV6INIT="yes"  
IPV6_AUTOCONF="yes"  
IPV6_DEFROUTE="yes"  
IPV6_PEERDNS="yes"  
IPV6_PEERROUTES="yes"  
IPV6_FAILURE_FATAL="no"  
NAME="eth0"  
UUID="bb3a302d-dc46-461a-881e-d46cafd0eb71"  
ONBOOT="yes" #开机启用本配置  
IPADDR=192.168.7.106 #静态IP  
GATEWAY=192.168.7.1 #默认网关  
NETMASK=255.255.255.0 #子网掩码  
DNS1=192.168.7.1 #DNS 配置  
```

