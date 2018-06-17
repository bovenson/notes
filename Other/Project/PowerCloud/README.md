---
title: Readme
---

#  下载

- emq
- docker
- kafka

# 安装运行

## 安装docker

- Mac：下载dmg格式安装

## 安装emq

- 解压：` unzip emqttd-docker-v*.*.zip`

- 加载镜像：`docker laod < emqttd-docker-v*.*`

- 查看加载的镜像：

  ```shell
  bovenson@MBP:~/Downloads/PowerCloud$ docker images                                                                                      │bovenson@MBP:~/Git/notes/Other/Project/1/test$ ls
  REPOSITORY             TAG                 IMAGE ID            CREATED             SIZE                                                 │README.md               emq-recv.py             kafka-consumer.py
  emqttd-docker-v2.2.0   latest              a081827d0613        5 months ago        35.6MB
  ```

- 启动：`docker run -tid --name emq20 -p 1883:1883 -p 8083:8083 -p 8883:8883 -p 8084:8084 -p 18083:18083 emqttd-docker-v2.0`

- 停止容器：`docker stop emq20`

- 开启容器：`docker start emq20`

- 查看正在运行的容器：

  ```shell
  bovenson@MBP:~/Downloads/PowerCloud$ docker ps                                                                                          │sparkstreamingproject   test
  CONTAINER ID        IMAGE                  COMMAND                  CREATED             STATUS              PORTS                       │bovenson@MBP:~/Git/notes/Other/Project/PowerColud$ touch README.md
                                                                                                                      NAMES               │bovenson@MBP:~/Git/notes/Other/Project/PowerColud$ open README.md
  9299ceaae265        emqttd-docker-v2.2.0   "/opt/emqttd/start.sh"   11 seconds ago      Up 11 seconds       4369/tcp, 6000-6999/tcp, 0.0│bovenson@MBP:~/Git/notes/Other/Project/PowerColud$ ls
  .0.0:1883->1883/tcp, 0.0.0.0:8083-8084->8083-8084/tcp, 0.0.0.0:8883->8883/tcp, 0.0.0.0:18083->18083/tcp, 8080/tcp   emq20       
  ```

- 进入 Docker 控制台：`docker exec -it emq20 /bin/sh`

## 运行Kafka

- 解压：

  ```shell
  > tar -xzf kafka_2.11-1.0.0.tgz
  > cd kafka_2.11-1.0.0
  ```

- 运行：

  ```shell
  > bin/zookeeper-server-start.sh config/zookeeper.properties
  > bin/kafka-server-start.sh config/server.properties
  ```

  ​