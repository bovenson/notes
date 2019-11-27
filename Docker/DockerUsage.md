[TOC]

# Docker

## 简介

Docker 是一个开源的应用容器引擎，让开发者可以打包他们的应用以及依赖包到一个可移植的容器中，然后发布到任何流行的 [Linux](http://baike.baidu.com/item/Linux) 机器上，也可以实现[虚拟化](http://baike.baidu.com/item/%E8%99%9A%E6%8B%9F%E5%8C%96)。容器是完全使用[沙箱](http://baike.baidu.com/item/%E6%B2%99%E7%AE%B1/393318)机制，相互之间不会有任何接口。

# 容器

## 从镜像创建新容器

```shell
$ docker run -d --restart=always ubuntu:16.04
```

## 启动容器

```shell
$ docker start container_id/container_name
```

## 查看运行的容器

使用`docker ps`命令可以查看所有正在运行中的容器列表，使用`docker inspect`命令我们可以查看更详细的关于某一个容器的信息。

```shell
docker ps	# need root
```

## 停止运行容器

```shell
docker stop container-name
```

**示例**

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

**停止所有运行的容器**

```shell
root@VirtualBox:/home/conpot# docker ps -q
77613e26eb6f
7effd23c7005
root@VirtualBox:/home/conpot# docker stop $(docker ps -q)
77613e26eb6f
7effd23c7005
root@VirtualBox:/home/conpot# docker ps -q
```

## 重命名容器

```shell
$ docker rename CONTAINER NEW_NAME
```
## 更新容器设置

```shell
$ docker update --restart=always container_name/container_id	# --restart=no
```

## 执行命令

```shell
$ docker exec -it [container] /bin/bash
```

## 列出所有容器

```shell
$ docker ps -a
```

## 删除容器

```shell
$ docker docker rm container-name
```

# 镜像

## 导出镜像

```shell
$ docker save busybox > busybox.tar
$ docker save -o fedora-all.tar fedora
$ docker save -o fedora-latest.tar fedora:latest
# 压缩
$ docker save myimage:latest | gzip > myimage_latest.tar.gz
```

## 加载镜像

```shell
$ docker load -i docker-output.tar
$ docker load < docker-output.tar
```

## Search

```shell
$ docker search ubuntu
```

## 下载

```shell
$ docker pull
```

## 查看下载的容器

```shell
$ docker images
$ docker images ubuntu	# 查看单个镜像
```

# 参考

- [[1] Docker容器的创建、启动、和停止](http://www.cnblogs.com/linjiqin/p/8608975.html)