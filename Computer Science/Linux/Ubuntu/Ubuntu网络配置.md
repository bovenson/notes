---
title: Ubuntu配置网络
---

# 配置

```shell
#### 查看网络接口
$ ip a
```

# 配置静态IP

## 配置文件位置

`/etc/network/interfaces`

# 实例配置

```
auto lo
iface lo inet loopback
# 上面配置不要动

# 手动配置连接,第一行为网络设备,不要改动
auto eth0
iface eth0 inet static
address 192.168.124.5
netmask 255.255.255.0
gateway 192.168.124.2
```

## 配置DNS服务器

## 临时修改

可以修改配置文件`/etc/resolv.conf`

修改的内容会被覆盖

## 持久修改DNS服务器

### 配置文件位置

`/etc/resolvconf/resolv.conf.d/base`

### 配置内容

以增加`114.114.114.114`为例

```
nameserver 114.114.114.114
```

