### Ubuntu

```shell
# 下载 Ubuntu16.04
  $ docker pull ubuntu:16.04

# check
$ docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
ubuntu              16.04               9361ce633ff1        3 days ago          118MB

# 创建container
$ docker run -it --name ubuntu1604 ubuntu:16.04 /bin/bash
# 更新
root@307052e95732:/# apt update
# 替换软件源
root@307052e95732:/# cd /etc/apt/
root@307052e95732:/etc/apt# apt install nano
root@307052e95732:/etc/apt# apt install apt-transport-https
root@307052e95732:/etc/apt# mv sources.list sources.list.back
root@307052e95732:/etc/apt# nano sources.list	# 写入tuna源配置信息
root@307052e95732:/etc/apt# apt update
root@307052e95732:/etc/apt# exit

# check
$ docker container ls --all
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS                      PORTS               NAMES
307052e95732        ubuntu:16.04        "/bin/bash"         6 minutes ago       Exited (0) 33 seconds ago                       Ubuntu1604

# Use
$ docker start Ubuntu1604
$ docker exec -it Ubuntu1604 /bin/bash
root@307052e95732:/# 

# 安装常用软件
## ip
root@307052e95732:/# apt install -y net-tools iputils-ping iproute2	software-properties-common
## jdk
$ wget -qO - https://adoptopenjdk.jfrog.io/adoptopenjdk/api/gpg/key/public | apt-key add -
$ add-apt-repository --yes https://adoptopenjdk.jfrog.io/adoptopenjdk/deb/
$ apt update
$ apt install adoptopenjdk-8-hotspot
## maven
root@307052e95732:/# apt install maven
```

**Ubuntu16.04 清华源配置**

```shell
# 默认注释了源码镜像以提高 apt update 速度，如有需要可自行取消注释
deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ xenial main restricted universe multiverse
# deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ xenial main restricted universe multiverse
deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ xenial-updates main restricted universe multiverse
# deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ xenial-updates main restricted universe multiverse
deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ xenial-backports main restricted universe multiverse
# deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ xenial-backports main restricted universe multiverse
deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ xenial-security main restricted universe multiverse
# deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ xenial-security main restricted universe multiverse

# 预发布软件源，不建议启用
# deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ xenial-proposed main restricted universe multiverse
# deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ xenial-proposed main restricted universe multiverse
```

**Ubuntu14.04 清华源配置**

```shell
# 默认注释了源码镜像以提高 apt update 速度，如有需要可自行取消注释
deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ trusty main restricted universe multiverse
# deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ trusty main restricted universe multiverse
deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ trusty-updates main restricted universe multiverse
# deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ trusty-updates main restricted universe multiverse
deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ trusty-backports main restricted universe multiverse
# deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ trusty-backports main restricted universe multiverse
deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ trusty-security main restricted universe multiverse
# deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ trusty-security main restricted universe multiverse

# 预发布软件源，不建议启用
# deb https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ trusty-proposed main restricted universe multiverse
# deb-src https://mirrors.tuna.tsinghua.edu.cn/ubuntu/ trusty-proposed main restricted universe multiverse
```

