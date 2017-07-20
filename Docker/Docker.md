[TOC]

# Docker

## 简介

Docker 是一个开源的应用容器引擎，让开发者可以打包他们的应用以及依赖包到一个可移植的容器中，然后发布到任何流行的 [Linux](http://baike.baidu.com/item/Linux) 机器上，也可以实现[虚拟化](http://baike.baidu.com/item/%E8%99%9A%E6%8B%9F%E5%8C%96)。容器是完全使用[沙箱](http://baike.baidu.com/item/%E6%B2%99%E7%AE%B1/393318)机制，相互之间不会有任何接口。

## 查看运行的容器

使用`docker ps`命令可以查看所有正在运行中的容器列表，使用`docker inspect`命令我们可以查看更详细的关于某一个容器的信息。

```shell
docker ps	# need root
```

### 示例

```shell

```



## 停止运行容器

```shell
docker stop container-name
```

### 示例

为了显示更直观, 删除部分内容并使用省略号代替.

```shell
root@VirtualBox:/home/conpot# docker ps
CONTAINER ID        IMAGE        ...
5a794455532d        nginx:alpine    ...  
8518250908b5        voxxit/rsyslog  ...
77613e26eb6f        elk_logstash    ...
7effd23c7005        elk_kibana      ...
root@VirtualBox:/home/conpot# docker stop 5a794455532d
5a794455532d
root@VirtualBox:/home/conpot# docker ps
CONTAINER ID        IMAGE      ...
8518250908b5        voxxit/rsyslog   ...
77613e26eb6f        elk_logstash     ...
7effd23c7005        elk_kibana       ...
root@VirtualBox:/home/conpot# docker stop 8518
8518
root@VirtualBox:/home/conpot# docker ps
CONTAINER ID        IMAGE             ...
77613e26eb6f        elk_logstash      ...
7effd23c7005        elk_kibana        ...
```

#### 停止所有运行的容器

上续.

```shell
root@VirtualBox:/home/conpot# docker ps -q
77613e26eb6f
7effd23c7005
root@VirtualBox:/home/conpot# docker stop $(docker ps -q)
77613e26eb6f
7effd23c7005
root@VirtualBox:/home/conpot# docker ps -q
```

