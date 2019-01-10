[TOC]

# docker-网络

- [官方文档](Network configuration reference)

## 自定义网桥

## 创建网络

```shell
# Create an overlay network `my-multi-host-network`.
$ docker network create \
  --driver overlay \
  --subnet 10.0.9.0/24 \
  my-multi-host-network

400g6bwzd68jizzdx5pgyoe95

# Create an nginx service and extend the my-multi-host-network to nodes where
# the service's tasks run.
$ docker service create --replicas 2 --network my-multi-host-network --name my-web nginx

716thylsndqma81j6kkkb5aus
```

## 自定义ip地址

```
version: '2.1'

services:
  app:
    image: busybox
    command: ifconfig
    networks:
      app_net:
        ipv4_address: 172.16.238.10
        ipv6_address: 2001:3984:3989::10
networks:
  app_net:
    driver: bridge
    enable_ipv6: true
    ipam:
      driver: default
      config:
      - subnet: 172.16.238.0/24
      - subnet: 2001:3984:3989::/64
```

## 使用外部网络

```
# 给特定网卡添加IP地址
root@default:~# ifconfig eth1:0 192.168.99.10 netmask 255.255.255.0 up
root@default:~# ifconfig eth1:1 192.168.99.11 netmask 255.255.255.0 up

# 不指定名称
# 如果 outside 网络不存在,使用 docker network create outside 创建
networks:
  outside:
    external: true
    
# 指定名称
networks:
  outside:
    external:
      name: actual-name-of-network
```

