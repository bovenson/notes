---
title: Linux 笔记
tags:
	- Linux
categories:
	- Linux
---

#  Network

## DHCP

**概念**

- DHCP(Dynamic Host Configuration Protocol, 动态主机配置协议)
- 一个局域网协议
- 使用UDP协议工作

**作用**

- 给内部网络主机分配IP地址
  - 保证任何IP地址在同一时刻只能由一台DHCP客户机所使用
  - 可以给用户分配永久固定的IP地址
  - 可以同用其他方法获得IP地址的主机共存（如手工配置IP地址的主机）
  - 向现有的BOOTP客户端提供服务
- 用户或内部网络管理员作为对所有主机中央管理的手段

**分配IP机制**

- 自动分配方式
- 动态分配方式
- 手工分配方式

**其他**

- DHCP有3个端口，其中UDP67和UDP68为正常的DHCP服务端口，分别作为DHCP Server和DHCP Client的服务端口；546号端口用于DHCPv6 Client，而不用于DHCPv4，是为DHCP failover服务，这是需要特别开启的服务，DHCP failover是用来做“双机热备”

- DHCP消息格式基于BOOTP(Bootstrap Protocol)消息格式

- DHCP服务器必须能将数据包送到255.255.255.255的IP地址上, 应在路由表中加入一个到地址255.255.255.255的路由

  - `route add -host 255.255.255.255 dev eth0`
  - 如果报错: `255.255.255.255：Unkown host` , 在`/etc/hosts`中添加一行: `255.255.255.255 dhcp`

- 指定DHCP服务器在那个设备上启动

  - 在`/etc/sysconfig/dhcpd`中把设备名称添加到DHCPDARGS列表中: `DHCPDARGS=ech0` or `echo "DHCPDARGS=eth0" >> /etc/sysconfig/dhcpd`
  - 命令: `$/usr/sbin/dhcpd eth1`

- 配置DHCP客户端

  通常网管员使用选择手工配置 DHCP 客户，需要修改 /etc/sysconfig/network 文件来启用联网；并修改 /etc/sysconfig/network-scripts 目录中每个网络设备的配置文件。在该目录中，每个设备都有一个叫做 ifcfg-eth？ 的配置文件，eth？是网络设备的名称。 如eth0等。如果你想在引导时启动联网，NETWORKING 变量必须 被设为 yes。 除了此处之外/etc/sysconfig/network 文件应该包含以下行：

  ```shell
  NETWORKING=yes
  DEVICE=eth0
  BOOTPROTO=dhcp
  ONBOOT=yes
  ```

- 客户机获得租约后，DHCPd会在dhcp.leases里建一条记录, 示例:

  ```shell
  lease 192.168.1.154 {
      starts 1 2000/05/15 13:36:42;
      ends 1 2000/05/15 21:36:42;
      hardware ethernet 00:00:21:4e:3f:58;
      uid 01:00:00:21:4e:3f:58;
      client-hostname "one";
  }
  ```

  dhcpd.leases的时间记录采用GMT时间，而不是本地时区的时间.

**dhcpd.conf**

`/etc/dhcpd.conf`通常包括三部分: parameters, declarations 和 option.

- DHCP配置文件中的parameters（参数）：表明如何执行任务，是否要执行任务，或将哪些网络配置选项发送给客户.

| 参数                       | 说明                                       |
| ------------------------ | ---------------------------------------- |
| ddns-update-style        | 配置DHCP-DNS 互动更新模式                        |
| default-lease-time       | 指定确省租赁时间的长度，单位是秒                         |
| max-lease-time           | 指定最大租赁时间长度，单位是秒                          |
| hardware                 | 指定网卡接口类型和MAC地址                           |
| server-name              | DHCP客户服务器名称                              |
| get-lease-hostnames flag | 检查客户端使用的IP地址                             |
| fixed-address ip         | 分配给客户端一个固定的地址; 用于指定一个或多个IP地址给一个DHCP客户。只能出现在host声明里 |
| authritative             | 拒绝不正确的IP地址的要求                            |

- DHCP配置文件中的declarations （声明）：用来描述网络布局、提供客户的IP地址等.

  | 声明                                       | 解释                                  |
  | ---------------------------------------- | ----------------------------------- |
  | shared-network                           | 用来告知是否一些子网络分享相同网络                   |
  | subnet                                   | 描述一个IP地址是否属于该子网                     |
  | range                                    | 起始IP 终止IP 提供动态分配IP 的范围              |
  | host                                     | 主机名称 参考特别的主机                        |
  | group                                    | 为一组参数提供声明                           |
  | allow unknown-clients;deny unknown-client | 是否动态分配IP给未知的使用者                     |
  | allow bootp;deny bootp                   | 是否响应激活查询; 指明DHCPd是否响应bootp查询，默认是允许的 |
  | allow booting;deny booting               | 是否响应使用者查询                           |
  | filename                                 | 开始启动文件的名称. 应用于无盘工作站                 |
  | next-server                              | 设置服务器从引导文件中装如主机名，应用于无盘工作站           |

  ```shell
  # share-network 语句
  shared-network name {
      [ 参数 ]
      [ 声明 ]
  }
  # share-network 用于告诉DHCP服务器某些IP子网其实是共享同一个物理网络。任何一个在共享物理网络里的子网都必须声明在 share-network 语句里。
  # 当属于其子网里的客户启动时，将获得在share-network语句里指定参数，除非这些参数被subnet 或 host 里的参数覆盖。
  # 用share-network是一种权宜之计，例如某公司用B类网络145.252，公司里的部门 A 被划在子网 145.252.1.0 里， 子网掩码为255.255.255.0，这里子网号为8个bit，主机号也为8个bit，但如果部门 A 急速增长，超过了254个节点，而物理网络还来不及增加，就要在原来这个物理网络上跑两个8bit掩码的子网，而这两个子网其实是在同一个物理网络上
  shared-network share1 {
      subnet 145.252.1.0 netmask 255.255.255.0 {
      range 145.252.1.10 145.252.1.253;
    }
      subnet 145.252.2.0 netmask 255.255.255.0 {
      range 145.252.2.10 145.252.1.253;
    }
  }

  # subnet 语句
  subnet subnet-number netmask netmask {
      [ 参数 ]
      [ 声明 ]
  }
  # subnet 语句用于提供足够的信息来阐明一个IP地址是否属于该子网。也可以提供指定的子网参数和指明那些属于该子网的IP地址可以动态分配给客户，这些IP地址必须在 range 声明里指定。subnet-number 可以是个IP地址或能被解析到这个子网的子网号的域名。netmask 可以是个IP地址或能被解析到这个子网的掩码的域名

  # range 语句
  range [ dynamic-bootp ] low-address [ high-address]
  # 对于任何一个有动态分配IP地址的subnet语句里，至少要有一个 range 语句，用来指明要分配的IP地址的范围。如果只指定一个要分配的IP地址，高地址部分可以省略

  # host 语句
  host hostname {
      [ 参数 ]
      [ 声明 ]
  }
  # host语句的作用是为特定的客户机提供网络信息

  # group 语句
  group {
      [ 参数 ]
      [ 声明 ]
  }
  # 组语句给一组声明提供参数

  ### default-lease-time time; 
  # 指定缺省租约时间，这里的time是以秒为单位的。如果DHCP客户在请求一个租约但没有指定租约的失效时间，租约时间就是缺省租约时间

  ### hardware 语句
  hardware hardware-type hardware-address
  # 指明物理硬件接口类型和硬件地址。硬件地址由6个8位组构成，每个8位组以“：”隔开。如00:00:E8:1B:54:97
  ```

  

- DHCP配置文件中的option（选项）：用来配置DHCP可选参数，全部用option关键字作为开始

  | 选项                                  | 解释                                     |
  | ----------------------------------- | -------------------------------------- |
  | subnet-mask                         | 为客户端设定子网掩码                             |
  | domain-name                         | 为客户端指明DNS名字                            |
  | domain-name-servers                 | 为客户端指明DNS服务器IP地址                       |
  | host-name                           | 为客户端指定主机名称                             |
  | routers                             | 为客户端设定默认网关                             |
  | broadcast-address                   | 为客户端设定广播地址                             |
  | ntp-server                          | 为客户端设定网络时间服务器IP地址                      |
  | time－offset                         | 为客户端设定和格林威治时间的偏移时间，单位是秒。格林威治和北京时间相差8小时 |
  | interface-mtu mtu                   | 指明网络界面的MTU，这里mtu是个正整数                  |
  | option broadcast-address ip-address | 指定广播地址                                 |


  ```shell
  # 选项类语句以option 开头，后面跟一个选项名，选项名后是选项数据

  ### 指明在客户子网内的路由器的地址，可以有多个; 设置网关
  option routers ip-address[, ip-address]

  ### 指明时间服务器的地址
  option time-servers ip-address[, ip-address...];

  ### 指明DNS的地址
  option domain-name-servers ip-address[, ip-address...];

  ### 给客户指定主机名，string是个字符串
  option host-anme string
  ```

- **注意: **

    - 如果客户端使用的是windows操作系统，不要选择"host-name"选项，即不要为其指定主机名称
    - lease 开始租约时间和lease 结束租约时间是格林威治标准时间（GMT），不是本地时间


- 配置例子

  ```shell
  option routers 222.205.197.1;
  option subnet-mask 255.255.255.0;
  option domain-name "test.org";
  option domain-name-servers 202.101.112.55,202.101.98.55,222.205.193.2;
  option broadcast-address 222.205.197.255;
  default-lease-time 36000;
  max-lease-time 360000;
  subnet 222.205.197.0 netmask 255.255.255.0 {
      range 222.205.197.10 222.205.197.90;
      range 222.205.197.100 222.205.197.200;
  }
  host servername {
      hardware Ethernet 00:03:FF:B0:1E:02;
      fixed-address 192.168.1.20;
  }
  host servername {hardware ethernet 00:a0:c9:a6:96:33;fixed-address 192.168.1.12;}
  host servername {hardware ethernet 00:a0:c9:a6:96:33;fixed-address 192.168.1.12;option routers 192.168.11.5;}
  ddns-update-style ad-hoc; (style可以有三种更新方式,其他两种为:interim none)
  ```

  有时你需要在一台安装了两块网卡（作防火墙或网关）的机器上安装DHCP服务，下面的例子指出一台防火墙机器上的一种DHCP设置，因为对外的网卡（internet）不需要提供DHCP服务:

  ```shell
  subnet 192.168.1.0 netmask 255.255.255.0 {
      range 192.168.1.2 192.168.1.4;
      default-lease-time 86400;
      max-lease-time 259200;
      option subnet-mask 255.255.255.0;
      option broadcast-address 192.168.1.255;
      option routers 192.168.1.254;
      option domain-name-servers 192.168.1.254;
  }
  subnet 202.102.34.102 netmask 255.255.255.255 {
  }
  ```

  ```shell
  #example
  #全局参数

  shared-network 共享网络名 {
      共享网络特定参数...
      subnet 204.254.239.0 netmask 255.255.255.224 {
            子网特定参数... 
            range 204.254.239.10 204.254.239.30;
      }
      subnet 204.254.239.32 netmask 255.255.255.224 {
          子网特定参数...
          range 204.254.239.42 204.254.239.62;
      }
  }

  subnet 204.254.239.64 netmask 255.255.255.224 {
      子网特定参数...
      range 204.254.239.74 204.254.239.94;
  }
  group {
  组特定参数...
  host ws1.domain {
      特定主机参数...
      }
      host ws2.domain {
      特定主机参数...
      }
      host ws3.domain {
      特定主机参数...
      }
  }
  ```

  

## 配置网络

- Redhat是放在/etc/sysconfig/network-scripts目录下面的一大堆文件里面
- Debian系的则是存在/etc/network/interfaces文件里面，无论有多少块网卡，都在这个文件里



## 端口

| 端口   | 程序         | 说明                                |
| ---- | ---------- | --------------------------------- |
| 20   | ftp        | FTP服务器有两个端口，其中21端口用于连接，20端口用于传输数据 |
| 21   | ftp        | FTP服务器有两个端口，其中21端口用于连接，20端口用于传输数据 |
| 80   | web服务器默认端口 |                                   |
| 443  | ssl        | ssl加密                             |

- `ftp`: ftp的端口号20、21的区别一个是数据端口，一个是控制端口，控制端口一般为21，而数据端口不一定是20，这和FTP的应用模式有关，如果是主动模式，应该为20，如果为被动模式，由服务器端和客户端协商而定.
  - 主动模式建立: 1) 客户端打开一个随机的端口（端口号大于1024，在这里，我们称它为x），同时一个FTP进程连接至服务器的21号命令端口。此时，源端口为随机端口x，在客户端，远程端口为21，在服务器。 2)  客户端开始监听端口（x+1），同时向服务器发送一个端口命令（通过服务器的21号命令端口），此命令告诉服务器客户端正在监听的端口号并且已准备好从此端口接收数据。这个端口就是我们所知的数据端口。 3) 服务器打开20号源端口并且建立和客户端数据端口的连接。此时，源端口为20，远程数据端口为（x+1）。 4) 客户端通过本地的数据端口建立一个和服务器20号端口的连接，然后向服务器发送一个应答，告诉服务器它已经建立好了一个连接。

## 相关命令

```shell
service network stop; service network start	# 先关闭网络服务; 再启动
ifdonw eth0; ifup eth0;		# 先关闭eth0网络接口; 再启用
hdclient	# 获取IP
hdclient -r	# 释放IP
```

**hdclient**

- 使用动态主机配置协议动态的配置网络接口的网络参数
- dhclient(选项)(参数)

```shell
0：指定dhcp客户端监听的端口号
-d：总是以前台方式运行程序
-q：安静模式，不打印任何错误的提示信息
-r：释放ip地址
```

# Web服务器

## apache

**目录访问控制参数**

- authname
- authtype
- authuserfile

# 常用命令

|     命令     |       全称       | 说明                                       | 示例                              | 备注                                       |
| :--------: | :------------: | ---------------------------------------- | ------------------------------- | ---------------------------------------- |
|  `accton`  |                | 打开或关闭进程账号记录功能                            |                                 |                                          |
|   `cut`    |                | 从文本文件的每一行中截取指定内容的数据                      |                                 |                                          |
|    `du`    |   disk usage   | 统计目录(或文件)所占磁盘空间的大小                       | `du file-name` or `du dir-name` |                                          |
|    `df`    |   disk file    | 用于显示文件系统的磁盘使用情况                          |                                 |                                          |
|    `dd`    |                | 将指定的输入文件拷贝到输出文件中，在拷贝的过程中可以进行格式转换         |                                 |                                          |
|   `fmt`    |                | 将所有非空白行的长度设置为几乎相同，来进行简单的文本格式化            |                                 |                                          |
|   `free`   |       -        | 显示当前系统未使用的和已使用的内存数目，还可以显示被内核使用的内存缓冲区     |                                 |                                          |
|    `ip`    |       -        |                                          |                                 |                                          |
|   `last`   |                | 显示最近登录过的用户及登录信息                          |                                 |                                          |
| `lastcomm` |                | 查看最近执行的命令                                |                                 |                                          |
| `netstat`  |                | 打印Linux中网络系统的状态信息，可让你得知整个Linux系统的网络情况    |                                 |                                          |
|    `ps`    | Process Status | 列出系统中当前运行的那些进程, 及进程的一些信息                 |                                 |                                          |
|  `rsync`   |  remote sync   | 类unix系统下的数据镜像备份工具                        |                                 | 支持本地复制，或者与其他SSH、rsync主机同步。可以使用scp、ssh等方式来传输文件，当然也可以通过直接的socket连接 |
|   `scp`    |                | 有Security的文件copy，可以把当前一个文件copy到远程另外一台主机上 |                                 | 基于SSH登录,SSH采用面向连接的TCP协议传输 ，应用22号端口 安全系数较高 |
|  `telnet`  |                | 用户提供了在本地计算机上完成远程主机工作的能力                  |                                 | 使用Telnet协议，是TCP/IP 协议族中的一员，是Internet远程登陆服务的标准协议和主要方式 |
|   `wget`   |                | 从网络上自动下载文件的自由工具                          |                                 | 支持通过HTTP、HTTPS、FTP三个最常见的TCP/IP协议下载，并可以使用HTTP代理 |
|  `vmstat`  |       -        | 报告关于内核线程、虚拟内存、磁盘、陷阱和 CPU 活动的统计信息         |                                 |                                          |



## `$() ${}`区别

```
$( )中放的是命令，相当于` `，例如todaydate=$(date +%Y%m%d)意思是执行date命令，返回执行结果给变量todaydate，也可以写为todaydate=`date +%Y%m%d`；
${ }中放的是变量，例如echo ${PATH}取PATH变量的值并打印，也可以不加括号比如$PATH。
```



## 查找命令

| 命令        | 说明                                       |
| --------- | ---------------------------------------- |
| `which`   | 查看可执行文件的位置                               |
| `whereis` | 命令只能用于程序名的搜索，而且只搜索二进制文件（参数-b）、man说明文件（参数-m）和源代码文件（参数-s） |
| `locate`  | 配合数据库查看文件位置, 并不真正对硬盘上的文件系统进行查找，而是对文件名数据库进行检索，而且可以使用通配符？和* |
| `find`    | 从指定的起始目录开始，递归地搜索其各个子目录，查找满足寻找条件的文件并对之采取相关的操作 |
| `type`    | 用来显示指定命令的类型，判断给出的指令是内部指令还是外部指令           |





## ip

Linux的ip命令和ifconfig类似，但前者功能更强大，并旨在取代后者。使用ip命令，只需一个命令，你就能很轻松地执行一些网络管理任务。ifconfig是net-tools中已被废弃使用的一个命令，许多年前就已经没有维护了。iproute2套件里提供了许多增强功能的命令，ip命令即是其中之一。

![](img/linux-other-01.jpg)

**命令示例**

```shell
### 设置IP
sudo ip addr add 192.168.0.193/24 dev wlan0
# 注意: IP地址要有一个后缀，比如/24。这种用法用于在无类域内路由选择（CIDR）中来显示所用的子网掩码。

### 查看网卡信息
$ ip addr show eno1
2: eno1: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP group default qlen 1000
    link/ether 4c:cc:6a:75:87:58 brd ff:ff:ff:ff:ff:ff
    inet 202.118.26.129/24 brd 202.118.26.255 scope global eno1
       valid_lft forever preferred_lft forever
    inet6 2001:da8:9000:a131:5c0e:9101:10cd:5642/64 scope global noprefixroute dynamic 
       valid_lft 2591766sec preferred_lft 604566sec
    inet6 fe80::8e92:ab7:44d3:1ae8/64 scope link 
       valid_lft forever preferred_lft forever

### 删除IP
sudo ip addr del 192.168.0.193/24 dev wlan0

### 获取不同网络接口的统计数据
$ ip -s link
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN mode DEFAULT group default qlen 1
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    RX: bytes  packets  errors  dropped overrun mcast   
    10066246   121423   0       0       0       0       
    TX: bytes  packets  errors  dropped carrier collsns 
    10066246   121423   0       0       0       0       
2: eno1: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP mode DEFAULT group default qlen 1000
    link/ether 4c:cc:6a:75:87:58 brd ff:ff:ff:ff:ff:ff
    RX: bytes  packets  errors  dropped overrun mcast   
    17397865038 12747222 0       0       0       236406  
    TX: bytes  packets  errors  dropped carrier collsns 
    427483925  5772784  0       0       0       0       
...

### 查看特定网络接口信息
$ ip -s link ls eno1
2: eno1: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP mode DEFAULT group default qlen 1000
    link/ether 4c:cc:6a:75:87:58 brd ff:ff:ff:ff:ff:ff
    RX: bytes  packets  errors  dropped overrun mcast   
    17398010799 12748128 0       0       0       236660  
    TX: bytes  packets  errors  dropped carrier collsns 
    427520641  5772921  0       0       0       0       
$ ip -s -s link ls eno1
2: eno1: <BROADCAST,MULTICAST,UP,LOWER_UP> mtu 1500 qdisc pfifo_fast state UP mode DEFAULT group default qlen 1000
    link/ether 4c:cc:6a:75:87:58 brd ff:ff:ff:ff:ff:ff
    RX: bytes  packets  errors  dropped overrun mcast   
    17398014866 12748162 0       0       0       236668  
    RX errors: length   crc     frame   fifo    missed
               0        0       0       0       0       
    TX: bytes  packets  errors  dropped carrier collsns 
    427520641  5772921  0       0       0       0       
    TX errors: aborted  fifo   window heartbeat transns
               0        0       0       0       4       
# 当你需要获取一个特定网络接口的信息时，在网络接口名字后面添加选项ls即可。使用多个选项-s会给你这个特定接口更详细的信息。特别是在排除网络连接故障时，这会非常有用

### ARP条目
# 地址解析协议（ARP）用于将一个IP地址转换成它对应的物理地址，也就是通常所说的MAC地址。使用ip命令的neigh或者neighbour选项，你可以查看接入你所在的局域网的设备的MAC地址
$ ip neigh
202.118.26.56 dev eno1 lladdr 00:25:b3:f1:da:d0 STALE
202.118.26.122 dev eno1 lladdr 4c:cc:6a:aa:2f:fb STALE
202.118.26.254 dev eno1 lladdr 00:e0:fc:40:d7:78 REACHABLE
202.118.26.230 dev eno1 lladdr ec:a8:6b:c7:f1:ba STALE
202.118.26.238 dev eno1 lladdr f4:4d:30:57:6f:36 STALE
202.118.26.251 dev eno1 lladdr 4c:cc:6a:75:86:da STALE
202.118.26.196 dev eno1 lladdr 3c:97:0e:2a:4a:b5 STALE
202.118.26.27 dev eno1 lladdr 74:25:8a:ea:41:ed STALE
202.118.26.38 dev eno1 lladdr a0:8c:fd:ce:50:a5 STALE
202.118.26.201 dev eno1 lladdr 3c:a8:2a:07:1c:a1 STALE
202.118.26.191 dev eno1 lladdr 70:f3:95:14:24:a7 STALE
202.118.26.136 dev eno1 lladdr 6c:4b:90:03:f5:6a STALE
202.118.26.70 dev eno1 lladdr 00:11:25:3f:44:97 STALE
169.254.254.251 dev eno1 lladdr 70:5a:0f:12:f9:d4 STALE
202.118.26.124 dev eno1 lladdr 44:39:c4:91:42:53 STALE
202.118.26.221 dev eno1 lladdr 5c:b9:01:13:76:d7 STALE
202.118.26.197 dev eno1 lladdr 4c:cc:6a:aa:2f:fe STALE
202.118.26.114 dev eno1 lladdr f4:30:b9:ef:6a:90 STALE
192.168.12.51 dev ap0  FAILED
fe80::3ea8:2aff:fe07:1ca1 dev eno1 lladdr 3c:a8:2a:07:1c:a1 STALE
2001:da8:9000:a131:5eb9:1ff:fe13:76d7 dev eno1 lladdr 5c:b9:01:13:76:d7 STALE
fe80::5eb9:1ff:fe13:76d7 dev eno1 lladdr 5c:b9:01:13:76:d7 STALE
2001:da8:9000:a131::1 dev eno1 lladdr d8:67:d9:04:c0:43 router STALE
2001:da8:9000:a131:725a:fff:fe12:f9d4 dev eno1 lladdr 70:5a:0f:12:f9:d4 STALE
2001:da8:9000:a131:3ea8:2aff:fe07:1ca1 dev eno1 lladdr 3c:a8:2a:07:1c:a1 STALE
fe80::725a:fff:fe12:f9d4 dev eno1 lladdr 70:5a:0f:12:f9:d4 STALE
fe80::da67:d9ff:fe04:c043 dev eno1 lladdr d8:67:d9:04:c0:43 router REACHABLE

### 监控netlink消息
# 也可以使用ip命令查看netlink消息。monitor选项允许你查看网络设备的状态。比如，所在局域网的一台电脑根据它的状态可以被分类成REACHABLE或者STALE。使用下面的命令：
$ ip monitor all
[NEIGH]fe80::da67:d9ff:fe04:c043 dev eno1 lladdr d8:67:d9:04:c0:43 router STALE
[NEIGH]192.168.12.143 dev ap0 lladdr 64:cc:2e:6a:fd:23 DELAY
[NEIGH]192.168.12.143 dev ap0 lladdr 64:cc:2e:6a:fd:23 STALE
[NEIGH]192.168.12.143 dev ap0 lladdr 64:cc:2e:6a:fd:23 PROBE
[NEIGH]192.168.12.143 dev ap0 lladdr 64:cc:2e:6a:fd:23 REACHABLE
[LINK]3: wls3: <NO-CARRIER,BROADCAST,MULTICAST,UP> 
    link/ether 
[NEIGH]fe80::da67:d9ff:fe04:c043 dev eno1 lladdr d8:67:d9:04:c0:43 router PROBE
[NEIGH]fe80::da67:d9ff:fe04:c043 dev eno1 lladdr d8:67:d9:04:c0:43 router REACHABLE
[ADDR]2: eno1    inet6 2001:da8:9000:a131:5c0e:9101:10cd:5642/64 scope global noprefixroute dynamic 
       valid_lft 2592000sec preferred_lft 604800sec
[ADDR]2: eno1    inet6 fe80::8e92:ab7:44d3:1ae8/64 scope link 
       valid_lft forever preferred_lft forever
[ROUTE]2001:da8:9000:a131::/64 dev eno1 proto ra metric 100  pref medium
[ROUTE]fe80::da67:d9ff:fe04:c043 dev eno1 proto static metric 100  pref medium
[ROUTE]default via fe80::da67:d9ff:fe04:c043 dev eno1 proto static metric 100  pref medium

### 激活和停止网络接口
$ sudo ip link set eno1 down
$ sudo ip link set eno1 up
```

### 路由

```shell
### 查看路由信息
$ ip route show
default via 202.118.26.254 dev eno1 proto static metric 100 
172.16.22.0/24 dev vmnet1 proto kernel scope link src 172.16.22.1 
172.17.0.0/16 dev docker0 proto kernel scope link src 172.17.0.1 linkdown 
172.18.0.0/16 dev br-79d65a0af211 proto kernel scope link src 172.18.0.1 linkdown 
192.168.12.0/24 dev ap0 proto kernel scope link src 192.168.12.1 
192.168.115.0/24 dev vmnet8 proto kernel scope link src 192.168.115.1 
202.118.26.0/24 dev eno1 proto kernel scope link src 202.118.26.129 metric 100

### 查看给定IP的路由选择
$ ip route get 202.118.26.41
202.118.26.41 dev eno1 src 202.118.26.129 
    cache 
$ ip route get 172.17.1.100
172.17.1.100 dev docker0 src 172.17.0.1 
    cache 
    
### 更改默认路由
sudo ip route add default gw 20.0.0.1			# 添加网关
sudo ip route add default via 192.168.0.196
```

### 网关

```shell
### 添加网关
ip route add default gw 20.0.0.1
```

### 路由表

```shell
### 添加路由表
ip route add table 3 via 10.0.0.1 dev ethX 	# ethx 是10.0.0.1所在的网卡,3 是路由表的编号
```

### 规则(rule)

```shell
### 添加规则
ip rule add fwmark 3  table 3	# fwmark 3 是标记，table 3 是路由表3 上边。 意思就是凡事标记了 3 的数据使用table3 路由表

### 之后使用iptables给相应的数据打上标记 
iptables -A PREROUTING -t mangle -i eth0 -s 192.168.0.1 - 192.168.0.100 -j MARK --set-mark 3

### 示例
ip rule add from 192.168.3.112/32 [tos 0x10] table 2 pref 1500
# 向规则链增加一条规则，规则匹配的对象是IP为192.168.3.112，tos等于0x10的包，使用路由表2，这条规则的优先级是1500
```

### 案例

```shell
### 1
# 要求192.168.0.100以内的使用 10.0.0.1 网关上网 （电信），其他IP使用 20.0.0.1 （网通）上网
# 首先要在网关服务器上添加一个默认路由，当然这个指向是绝大多数的IP的出口网关
ip route add default gw 20.0.0.1
# 之后通过 ip route 添加一个路由表
ip route add table 3 via 10.0.0.1 dev ethX 	# ethx 是10.0.0.1所在的网卡,3 是路由表的编号
# 之后添加 ip  rule 规则
ip rule add fwmark 3  table 3 # fwmark 3 是标记，table 3 是路由表3 上边。 意思就是凡事标记了 3 的数据使用table3 路由表
# 之后使用iptables给相应的数据打上标记
iptables -A PREROUTING -t mangle -i eth0 -s 192.168.0.1 - 192.168.0.100 -j MARK --set-mark 3
# 因为mangle的处理是优先于 nat 和 fiter表的，所以相依数据包到达之后先打上标记，之后在通过ip rule 规则
# 对应的数据包使用相应的路由表进行路由，最后读取路由表信息，将数据包送出网关
```



# Vim

**三种模式**: 输入模式, 末行模式, 命令模式.

**切换:**

| 按键      | 到达的模式        |
| ------- | ------------ |
| `i,o,r` | 输入模式 / 编辑模式  |
| `:`     | 末行模式 / 指令列模式 |
| `ESC`   | 命令模式 / 一般模式  |



# 文件

linux下面，一切都是文件。

## 根目录下文件夹说明

| 路径          | 说明                                                         |
| ------------- | ------------------------------------------------------------ |
| ` /bin `      | 存放可执行二进制文件(ls,cat,mkdir等)，常用命令一般都在这里。 |
| ` /etc `      | 存放系统管理和配置文件                                       |
| ` /home `     | 存放所有用户文件的根目录，是用户主目录的基点，比如用户user的主目录就是/home/user，可以用~user表示 |
| `/usr`        | 用于存放系统应用程序，比较重要的目录/usr/local 本地系统管理员软件安装目录（安装系统级的应用）。这是最庞大的目录，要用到的应用程序和文件几乎都在这个目录。 |
| `/opt`        | 额外安装的可选应用程序包所放置的位置。一般情况下，我们可以把tomcat等都安装到这里。 |
| `/proc`       | 虚拟文件系统目录，是系统内存的映射。可直接访问这个目录来获取系统信息。 |
| `/root`       | 超级用户（系统管理员）的主目录                               |
| `/sbin`       | 存放二进制可执行文件，只有root才能访问。这里存放的是系统管理员使用的系统级别的管理命令和程序。如ifconfig等。 |
| `/dev`        | 用于存放设备文件                                             |
| `/mnt`        | 系统管理员安装临时文件系统的安装点，系统提供这个目录是让用户临时挂载其他的文件系统。 |
| `/boot`       | 存放用于系统引导时使用的各种文件                             |
| `/lib`        | 存放跟文件系统中的程序运行所需要的共享库及内核模块。共享库又叫动态链接共享库，作用类似windows里的.dll文件，存放了根文件系统程序运行所需的共享文件。 |
| `/tmp`        | 用于存放各种临时文件，是公用的临时文件存储点。               |
| `/var`        | 用于存放运行时需要改变数据的文件，也是某些大文件的溢出区，比方说各种服务的日志文件（系统启动日志等。）等。 |
| `/lost+found` | 这个目录平时是空的，系统非正常关机而留下“无家可归”的文件（windows下叫什么.chk）就在这里 |

**参考**

- [博客](https://blog.csdn.net/u013239236/article/details/48845251)

## 目录

**结构**

- 一般常说UNIX的文件系统是树形结构，其实是指带链接的树形结构，而不是纯树形目录结构。
- 带链接树形目录结构又称非循环图目录结构,它是指访问一文件(或目录)可以有多条路径。
- linux中的文件除了本身包含的内容以外，还会有一个名字和一些属性，即管理信息，包括文件的创建/修改日期和它的访问权限，这些属性被保存在文件得inode中，它是文件系统中一个特殊的数据块，它同时还包含文件的长度和文件在磁盘上的存放位置。系统使用的是文件得inode编号，目录结构为文件命名仅仅是为了便于人们使用。
- 目录是用于保存其他文件的节点号和名字的文件，目录文件中的每个数据项都是指向某个文件节点的链接，删除文件名就等于删除与之对应的链接。

**目录文件**

- Unix/Linux系统中，目录（directory）也是一种文件。打开目录，实际上就是打开目录文件。
- 目录文件的结构非常简单，就是一系列目录项（dirent）的列表。每个目录项，由两部分组成：所包含文件的文件名，以及该文件名对应的inode号码。



## inode

- inode包含文件的元信息，具体来说有以下内容：

  - 文件的字节数
  - 文件拥有者的User ID
  - 文件的Group ID
  - 文件的读、写、执行权限
  - 文件的时间戳，共有三个：ctime指inode上一次变动的时间，mtime指文件内容上一次变动的时间，atime指文件上一次打开的时间。
  - 链接数，即有多少文件名指向这个inode
  - 文件数据block的位置

- 可以用stat命令，查看某个文件的inode信息：

  `stat example.txt`

- 除了文件名以外的所有文件信息，都存在inode之中.

- inode也会消耗硬盘空间，所以硬盘格式化的时候，操作系统自动将硬盘分成两个区域。一个是数据区，存放文件数据；另一个是inode区（inode table），存放inode所包含的信息。

  - 每个inode节点的大小，一般是128字节或256字节。inode节点的总数，在格式化时就给定，一般是每1KB或每2KB就设置一个inode。假定在一块1GB的硬盘中，每个inode节点的大小为128字节，每1KB就设置一个inode，那么inode table的大小就会达到128MB，占整块硬盘的12.8%。
  - 查看每个硬盘分区的inode总数和已经使用的数量，可以使用df命令。`df -i`.

- inode号码

  - 每个inode都有一个号码，操作系统用inode号码来识别不同的文件。
  - Unix/Linux系统内部不使用文件名，而使用inode号码来识别文件。对于系统来说，文件名只是inode号码便于识别的别称或者绰号。表面上，用户通过文件名，打开文件。实际上，系统内部这个过程分成三步：首先，系统找到这个文件名对应的inode号码；其次，通过inode号码，获取inode信息；最后，根据inode信息，找到文件数据所在的block，读出数据。
  - 使用ls -i命令，可以看到文件名对应的inode号码：`ls -i example.txt`

- inode的特殊现象

  - 有时，文件名包含特殊字符，无法正常删除。这时，直接删除inode节点，就能起到删除文件的作用。
  - 移动文件或重命名文件，只是改变文件名，不影响inode号码。
  - 打开一个文件以后，系统就以inode号码来识别这个文件，不再考虑文件名。因此，通常来说，系统无法从inode号码得知文件名。


## 权限

```shell
### 示例
# drwxrwxrwx  2 bovenson bovenson 4096 5月  15 10:39 C#
drwxrwxrwx

# 说明; 10位, 分为四部分: 标识是否为文件, 用户权限, 组内权限, 其他用户权限.

### 改变权限
# 使用 -R 选项递归更改文件夹及子文件下文件夹及文件权限
chmod 777 filepath
chmod 777 -R dirpath	# chmod -R 777 dirpath
# 改变所有者
chown test filepath 
chown 777 -R dirpath	# chmod -R 777 dirpath
# 改变所属组
chgrp user filepath
chgrp 777 -R dirpath	# chmod -R 777 dirpath

### 数字表示权限(忽略第一位, 第一位标识是否为文件)
d--------- # 000
dr---w---x # 421
drwx-wxr-w # 735
```

## 连接

![](img/linux-other-01.png)

**硬连接**

- 硬链接是有着相同 inode 号仅文件名不同的文件
- 硬连接不管有多少个，都指向的是同一个I节点，会把结点连接数增加，只要结点的连接数不是0，文件就一直存在不管你删除的是源文件还是连接的文件
- 只要有一个存在文件就存在。 当你修改源文件或者连接文件任何一个的时候，其他的文件都会做同步的修改
- 文件有相同的 inode 及 data block
- 不能交叉文件系统进行硬链接的创建
- 只能对已存在的文件进行创建
- 不能对目录进行创建，只可对文件创建
- 删除一个硬链接文件并不影响其他有相同 inode 号的文件

**软连接 (又称符号链接)**

- 若文件用户数据块中存放的内容是另一文件的路径名的指向，则该文件就是软连接。软链接就是一个普通文件，只是数据块内容有点特殊。软链接有着自己的 inode 号以及用户数据块。因此软链接的创建与使用没有类似硬链接的诸多限制
- 软链接有自己的文件属性及权限等
- 可对不存在的文件或目录创建软链接
- 软链接可交叉文件系统
- 软链接可对文件或目录创建
- 创建软链接时，链接计数 i_nlink 不会增加


- 软链接不直接使用i节点号作为文件指针, 而是使用文件路径名作为指针
- 删除连接文件对源文件无影响，但是删除源文件，连接文件就会找不到要指向的文件
- 软链接有自己的inode, 并在磁盘上有一小片空间存放路径名
- 删除软链接并不影响被指向的文件，但若被指向的原文件被删除，则相关软连接被称为死链接（即 dangling link，若被指向路径文件被重新创建，死链接可恢复为正常的软链接）

**硬链接和软连接区别**

- 软连接可以跨文件系统，硬连接不可以
- 软连接可以对一个不存在的文件名进行连接 ，硬连接不可以
- 软连接可以对目录进行连接 ，硬连接不可以
- 硬连接不管有多少个，都指向的是同一个I节点， 只有软连接才产生新的inode节点




# 新建文件

**可能会创建新文件的命令**

- `touch`
- `cat`
  - `cat filename` : 一次显示整个文件
  - `cat > filename` : 创建新文件
  - `cat file1 file2 > file` : 将几个文件合并为一个 
- `vi/vim`
- `>` or `>>` : 定向输出到文件，如果文件不存在，就创建文件


#  查看系统信息

## 内存

```shell
### 1
$ cat /proc/meminfo
MemTotal:       16364784 kB
MemFree:          314304 kB
MemAvailable:   10970564 kB
Buffers:         4819260 kB
...

### 2
$ free
              total        used        free      shared  buff/cache   available
Mem:       16364784     4787128      313344      266576    11264312    10969864
Swap:             0           0           0
# 没有 cat /proc/meminfo 获取的信息详细

### 3
$ top
Tasks: 332 total,   1 running, 329 sleeping,   0 stopped,   2 zombie
%Cpu(s):  2.6 us,  1.2 sy,  0.0 ni, 96.0 id,  0.3 wa,  0.0 hi,  0.0 si,  0.0 st
KiB Mem : 16364784 total,   310184 free,  4788820 used, 11265780 buff/cache
KiB Swap:        0 total,        0 free,        0 used. 10968192 avail Mem 

  PID USER      PR  NI    VIRT    RES    SHR S  %CPU %MEM     TIME+ COMMAND                                                              
  831 root      20   0  524400  82572  25620 S  11.6  0.5  49:14.30 Xorg                                                                
 2458 bovenson  20   0 1243304 156836  22020 S   8.0  1.0  28:37.14 deepin-wm     
# 没有 cat /proc/meminfo 获取的信息详细; 可以看到内存总体使用及每个进程使用内存情况
```



# 内核

**子系统:**

- 进程管理系统
- 内存管理系统
- I/O管理系统
- 文件管理系统

**更新**

- Linux内核版本是不断更新的，通常，更新的内核会支持更多的硬件，具备更好的进程管理能力，运行速度更快、 更稳定，并且一般会修复老版本中发现的许多漏洞等。而已安装好的Linux系统如果不是滚动升级的，或者没有内核更新选择的话，如果用户想要使用这些新特性，或想根据自己的硬件平台定制一个更高效，更稳定，更快速的内核，就需要重新编译内核。
- 只要你代码或配置有改动，都要重新编译的，只是如果代码写成块模式的，不用整个代码都重新编译，编译器会自动编译改动了的代码。
- 需要编译内核情况举例:
  - 升级内核
  - 删除系统不用的设备驱动程序 (如果硬件相关的驱动是编译成模块, 则不需要动内核, 如果驱动被编译到内核里, 则要动内核)
  - 添加新硬件 (如果硬件相关的驱动是编译成模块, 则不需要动内核, 如果驱动被编译到内核里, 则要动内核)

**对内核进行配置**

- `make config`
- `make menuconfig`
- `make oldconfig`
- `make xconfig`

# 内存

## 屏障

- rmb （读内存屏障）:  保证了屏障之前的读操作一定会在后来的读操作执行之前完成
- wmb（写内存屏障）:  保证写操作不会乱序
- mb(通用屏障) : 对读写操作都有作用, 指令保证了两者都不会乱序
- 优化屏障则用于限制编译器的指令重排



# 缺页异常(page fault)

- page fault由硬件产生，是一种“异常”
- 产生条件为：当CPU访问某线性地址，而该线性地址还没有对应的页表项，即还没有分配相应的物理内存并进行映射时，自动产生异常。
- 情景
  - 用户态按需调页
  - 主内核页目录的同步
  - 对exception table中的异常操作的处理 
  - 堆栈自动扩展
  - 对用户态指针越界的检查



# UNIX系统

## 数据段

- `代码段`：代码段是用来存放可执行文件的操作指令，也就是说是它是可执行程序在内存中的镜像。代码段需要防止在运行时被非法修改，所以只准许读取操作，而不允许写入（修改）操作——它是不可写的。
- `数据段`：数据段用来存放可执行文件中已初始化全局变量，换句话说就是存放程序静态分配的变量和全局变量。
- `BSS段`：BSS段包含了程序中未初始化的全局变量，在内存中 bss段全部置零。
- `堆（heap）`：堆是用于存放进程运行中被动态分配的内存段，它的大小并不固定，可动态扩张或缩减。当进程调用malloc等函数分配内存时，新分配的内存就被动态添加到堆上（堆被扩张）；当利用free等函数释放内存时，被释放的内存从堆中被剔除（堆被缩减）
  它的物理内存空间是由程序申请的，并由程序负责释放。
- `栈`：栈是用户存放程序临时创建的局部变量，也就是说我们函数括弧“{}”中定义的变量（但不包括static声明的变量，static意味着在数据段中存放变量）。除此以外，在函数被调用时，其参数也会被压入发起调用的进程栈中，并且待到调用结束后，函数的返回值也会被存放回栈中。由于栈的先进先出特点，所以栈特别方便用来保存/恢复调用现场。从这个意义上讲，我们可以把堆栈看成一个寄存、交换临时数据的内存区。

实际上,在Linux系统下并没有强调分段,每个进程缺省情况下“数据段”，“堆栈段”和“代码段”都使用等价的段描述,映射到相同的线性地址空间,除非你个别设置LDT.

### 代码段和数据段分开

- 可共享正文
- 可共享数据
- 共享指令，当系统运行多个程序的副本时，它们指令时一样的，因此内存只需保存一份程序的指令代码。每个副本进程的数据区是进程私有，可以节省内存。
- 可重入
- 可保护代码为只读; 防止程序指令被修改，设置代码段权限为只读，设置数据段权限为可读写  
- 更好支持内存回收策略
- 代码段和数据段分开有利于提高程序的局部性，现代CPU缓存一般设计为数据缓存和指令缓存分离，指令和数据分开存放可以提高CPU缓存命中率。



## 系统调用

- 系统调用把应用程序的请求传输给系统内核执行
- 利用系统调用能够得到操作系统提供的多种服务
- 是操作系统提供给编程人员的接口
- 系统调用给用户屏蔽了设备访问的细节
- 系统调用保护了一些只能在内核模式执行的操作指令
- 系统调用通过中断完成，这一过程中系统由用户态变为内核态。 在内核态下，系统可以无限制的访问内核资源



# 交换内存(swap 分区)

**大小**

目前Red Hat（红帽官方）推荐交换分区的大小应当与系统物理内存的大小保持线性比例关系。不过在小于2GB物理内存的系统中，交换分区大小应该设置为内存大小的 两倍，如果内存大小多于2GB，交换分区大小应该是物理内存大小加上2GB。其原因在于，系统中的物理内存越大， 对于内存的负荷可能也越大。

**总结:**

- 在内存小于2G的情况下，交换分区应为内存的2倍
- 超过2G的话，交换分区为物理内存加上2G



# 进程

## 相关知识

### 僵尸进程和孤儿进程

正常情况下， 当一个进程完成它的工作终止之后，它的父进程需要调用wait()或者waitpid()系统调用取得子进程的终止状态。

**僵尸进程**

- 一个子进程在其父进程还没有调用wait()或waitpid()的情况下退出。这个子进程就是僵尸进程.

**孤儿进程**

- 父进程先于子进程退出，那么子进程会变成孤儿进程
- 孤儿进程将被init进程(进程号为1)所收养，并由init进程对它们完成状态收集工作。

**区别**

- 僵尸进程将会导致资源浪费，而孤儿则不会
- 孤儿进程最后会被init接管，而僵尸进程会继续占用资源

### 进程间通讯技术

- 消息传递(管道,FIFO,posix和system v消息队列) 
- 同步(互斥锁,条件变量,读写锁,文件和记录锁,Posix和System V信号灯) 
- 共享内存区(匿名共享内存区,有名Posix共享内存区,有名System V共享内存区)
- 过程调用(Solaris门,Sun RPC)

### 进程/线程间通讯

- Linux进程间通信：管道、信号、消息队列、共享内存、信号量、套接字(socket)
- Linux线程间通信：互斥量（mutex），信号量，条件变量
- Windows进程间通信：管道、消息队列、共享内存、信号量 （semaphore） 、套接字(socket)
- Windows线程间通信：互斥量（mutex），信号量（semaphore）、临界区（critical section）、事件（event）

| 方式                  | 说明                                       |
| ------------------- | ---------------------------------------- |
| 管道（Pipe）            | 管道可用于具有亲缘关系进程间的通信，允许一个进程和另一个与它有共同祖先的进程之间进行通信 |
| 命名管道（named pipe）    | 命名管道克服了管道没有名字的限制，因此，除具有管道所具有的功能外，它还允许无亲缘关系进程间的通信。命名管道在文件系统中有对应的文件名。命名管道通过命令mkfifo或系统调用mkfifo来创建。 |
| 信号（Signal）          | 信号是比较复杂的通信方式，用于通知接受进程有某种事件发生，除了用于进程间通信外，进程还可以发送信号给进程本身；linux除了支持Unix早期信号语义函数sigal外，还支持语义符合Posix.1标准的信号函数sigaction（实际上，该函数是基于BSD的，BSD为了实现可靠信号机制，又能够统一对外接口，用sigaction函数重新实现了signal函数）。 |
| 消息（Message）队列       | 消息队列是消息的链接表，包括Posix消息队列system V消息队列。有足够权限的进程可以向队列中添加消息，被赋予读权限的进程则可以读走队列中的消息。消息队列克服了信号承载信息量少，管道只能承载无格式字节流以及缓冲区大小受限等缺 |
| 共享内存                | 使得多个进程可以访问同一块内存空间，是最快的可用IPC形式。是针对其他通信机制运行效率较低而设计的。往往与其它通信机制，如信号量结合使用，来达到进程间的同步及互斥。 |
| 内存映射（mapped memory） | 内存映射允许任何多个进程间通信，每一个使用该机制的进程通过把一个共享的文件映射到自己的进程地址空间来实现它。 |
| 信号量（semaphore）      | 主要作为进程间以及同一进程不同线程之间的同步手段。                |
| 套接口（Socket）         | 更为一般的进程间通信机制，可用于不同机器之间的进程间通信。起初是由Unix系统的BSD分支开发出来的，但现在一般可以移植到其它类Unix系统上：Linux和System V的变种都支持套接字。 |



## 系统进程

| 进程名     | 说明                  | 备注   |
| ------- | ------------------- | ---- |
| syslogd | 系统日志服务的进程           |      |
| init    | 所有进程的发起者和控制者，是终极父进程 |      |



# 关机/重启

| 命令              | 操作   | 备注                                       |
| --------------- | ---- | ---------------------------------------- |
| shutdown -r now | 重启   |                                          |
| halt            | 关机   | halt是最简单的关机命令，其实际上是调用shutdown -h命令。halt执行时，杀死应用进程，文件系统写操作完成后就会停止内核。 |
| reboot          | 重新   | reboot命令重启动系统时是删除所有的进程，而不是平稳地终止它们。因此，使用reboot命令可以快速地关闭系统，但如果还有其它用户在该系统上工作时，就会引起数据的丢失。所以使用reboot命令的场合主要是在单用户模式。 |
| init 0          | 关机   |                                          |
| init 6          | 重启   |                                          |

**注意:**

- 在linux命令中reboot是重新启动，**shutdown -r now是立即停止然后重新启动，都说他们两个是一样的，其实是有一定的区别的**。

  **shutdown**命令可以**安全地关闭**或重启Linux系统，它在系统关闭之前给系统上的所有登录用户提示一条警告信息。

- init是所有进程的祖先，其进程号始终为1。init用于切换系统的运行级别，切换的工作是立即完成的。init 0命令用于立即将系统运行级别切换为0，即关机；init 6命令用于将系统运行级别切换为6，即重新启动。

**shutdown**

- 该命令允许用户指定一个时间参数，可以是一个精确的时间，也可以是从现在开始的一个时间段。
- 精确时间的格式是hh:mm，表示小时和分钟，时间段由+ 和分钟数表示。系统执行该命令后会自动进行数据同步的工作。
- 该命令的一般格式: shutdown \[选项\]\[时间\]\[警告信息\]

| 选项   | 说明                   |
| ---- | -------------------- |
| -k   | 并不真正关机而只是发出警告信息给所有用户 |
| -r   | 关机后立即重新启动            |
| -h   | 关机后不重新启动             |
| -f   | 快速关机重启动时跳过fsck       |
| -n   | 快速关机不经过init 程序       |
| -c   | 取消一个已经运行的shutdown    |

示例:

```shell
shutdown –r +10	# 系统在十分钟后关机并且马上重新启动
shutdown –h now # 系统马上关机并且不重新启动
```

**halt**

- halt执行时，杀死应用进程，文件系统写操作完成后就会停止内核

| 选项   | 说明                     |
| ---- | ---------------------- |
| -f   | 没有调用shutdown而强制关机或重启   |
| -i   | 关机或重新启动之前，关掉所有的网络接口    |
| -p   | 关机时调用poweroff，此选项为缺省选项 |



# find

详见`find.md`.



# 输出重定向

- `command &> logfile` or `commond >& logfile` : 重定向标准输出及标准错误输出
- `command 1> logfile` : 重定向标准输出
- `command 2> logfile` : 重定向标准错误输出

```shell
### 重定向标准输出及标准错误输出到指定文件
bash demo.sh &> demo.log
bash demo.sh >& demo.log
bash demo.sh 1 > demo.log 2 >& 1
```





# 管道

- 管道实际上是一种固定大小的缓冲区，管道对于管道两端的进程而言，就是一个文件，但它不是普通的文件，它不属于某种文件系统，而是自立门户，单独构成一种文件系统，并且只存在于内存中。它类似于通信中半双工信道的进程通信机制，一个管道可以实现双向 的数据传输，而同一个时刻只能最多有一个方向的传输，不能两个方向同时进行。管道的容 量大小通常为内存上的一页，它的大小并不是受磁盘容量大小的限制。当管道满时，进程在 写管道会被阻塞，而当管道空时，进程读管道会被阻塞
- 管道是一个固定大小的缓冲区 相当于半双工进程通信机制 管道满时 写操作阻塞 管道空时 读管道阻塞
- 管道是半双工的，读写不能同时双向进行数据操作。管道的容量还受其它因素的影响，管道缓冲区的大小也会影响程序的执行结果。管道必须打开一个读端和一个写端。若写端关闭，读端只能返回0。如读端关闭，写端返回错误值-1

# 脚本

- `$#` 是传给脚本的参数个数
- `$0`是脚本本身的名字
- `$1`是传递给该shell脚本的第一个参数
- `$2`是传递给该shell脚本的第二个参数
- `$@` 是传给脚本的所有参数的列表
- `$*` 是以一个单字符串显示所有向脚本传递的参数，与位置变量不同，参数可超过9个
- `$$` 是脚本运行的当前进程ID号
- `$?` 是显示最后命令的退出状态，0表示没有错误，其他表示有错误
- `$!` Shell最后运行的后台Process的PID 
- `$-` 使用Set命令设定的Flag一览 

# makefile

makefile文件保存了编译器和连接器的参数选项,还表述了所有源文件之间的关系(源代码文件需要的特定的包含文件,可执行文件要求包含的目标文件模块及库等).创建程序(make程序)首先读取makefile文件,然后再激活编译器,汇编器,资源编译器和连接器以便产生最后的输出,最后输出并生成的通常是可执行文件.创建程序利用内置的推理规则来激活编译器,以便通过对特定CPP文件的编译来产生特定的OBJ文件. 
Makefile里主要包含了五个东西：显式规则、隐晦规则、变量定义、文件指示和注释。

- makefile文件保存了编译器和连接器的参数选项
- 主要包含了五个东西：显式规则、隐晦规则、变量定义、文件指示和注释
- 默认的情况下，make命令会在当前目录下按顺序找寻文件名为“GNUmakefile”、“makefile”、“Makefile”的文件， 找到了解释这个文件
- 在Makefile可以使用include关键字把别的Makefile包含进来



```shell
1、显式规则。显式规则说明了，如何生成一个或多的的目标文件。这是由Makefile的书写者明显指出，要生成的文件，文件的依赖文件，生成的命令。 2、隐晦规则。由于我们的make有自动推导的功能，所以隐晦的规则可以让我们比较粗糙地简略地书写Makefile，这是由make所支持的。 

3、变量的定义。在Makefile中我们要定义一系列的变量，变量一般都是字符串，这个有点你C语言中的宏，当Makefile被执行时，其中的变量都会被扩展到相应的引用位置上。 

4、文件指示。其包括了三个部分，一个是在一个Makefile中引用另一个Makefile，就像C语言中的include一样；另一个是指根据某些情况指定Makefile中的有效部分，就像C语言中的预编译#if一样；还有就是定义一个多行的命令。有关这一部分的内容，我会在后续的部分中讲述。 

5、注释。Makefile中只有行注释，和UNIX的Shell脚本一样，其注释是用“#”字符，这个就像C/C++中的“//”一样。如果你要在你的Makefile中使用“#”字符，可以用反斜框进行转义，如：“#”。 

默认的情况下，make命令会在当前目录下按顺序找寻文件名为“GNUmakefile”、“makefile”、“Makefile”的文件，找到了解释这个文件。在这三个文件名中，最好使用“Makefile”这个文件名，因为，这个文件名第一个字符为大写，这样有一种显目的感觉。最好不要用 “GNUmakefile”，这个文件是GNU的make识别的。有另外一些make只对全小写的“makefile”文件名敏感，但是基本上来说，大多数的make都支持“makefile”和“Makefile”这两种默认文件名。

在Makefile使用include关键字可以把别的Makefile包含进来，这很像C语言的#include，被包含的文件会原模原样的放在当前文件的包含位置。include的语法是： 

include <filename>; filename可以是当前操作系统Shell的文件模式（可以保含路径和通配符）
```



# rpm

**选项**

| 选项     | 说明                                       | 示例   |
| ------ | ---------------------------------------- | ---- |
| `-ivh` | 安装显示安装进度--install--verbose--hash         |      |
| `-Uvh` | 升级软件包--Update                            |      |
| `-qpl` | 列出RPM软件包内的文件信息[Query Package list]       |      |
| `-qpi` | 列出RPM软件包的描述信息[Query Package install package(s)] |      |
| `-qf`  | 查找指定文件属于哪个RPM软件包[Query File]             |      |
| `-Va`  | 校验所有的RPM软件包，查找丢失的文件[View Lost]           |      |
| `-e`   | 删除包                                      |      |
| `-i`   | 安装软件包                                    |      |
| `-U`   | 更新软件包                                    |      |
| `-q`   | 判断软件包是否安装                                |      |
| `-qa`  | 列出已安装的软件包列表                              |      |

# man

**man的级别**

- 1：**用户命令**; 查看命令的帮助 
- 2：C的系统调用; 查看可被内核调用的函数的帮助 
- 3：例程，C的库函数调用; 查看函数和函数库的帮助 
- 4：设备; 查看特殊文件的帮助（主要是/dev目录下的文件） 
- 5：**文件格式描述与协议**; 查看配置文件的帮助 
- 6：查看游戏的帮助 
- 7：杂项; 查看其它杂项的帮助 
- 8：系统管理员工具; 查看系统管理员可用命令的帮助 
- 9： 其他（Linux特定的）， 用来存放内核例行程序的文档
- n：新文档， 可能要移到更适合的领域
- o：老文档， 可能会在一段期限内保留
- l：本地文档， 与本特定系统有关的
- `man -f 示例程序名称` : 可以查看这个命令有哪些级别

# sed

- Stream EDitor
- 详情查看`sed.md`.

# awk

- AWK 是一种优良的文本处理工具， Linux 及 Unix 环境中现有的功能最强大的数据处理引擎之一, AWK 提供了极其强大的功能：可以进行样式装入、 流控制 、数学 运算符 、进程 控制语句 甚至于内置的变量和函数。
- 详情查看`awk.md`.

# END