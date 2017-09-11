# Network

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

  ​

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

  ### 指明在客户子网内的路由器的地址，可以有多个
  option routers ip-address[, ip-address]

  ### 指明时间服务器的地址
  option time-servers ip-address[, ip-address...];

  ### 指明DNS的地址
  option domain-name-servers ip-address[, ip-address...];

  ### 给客户指定主机名，string是个字符串
  option host-anme string;
  ```

  ​

- 注意: **

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

  ​

## 配置网络

- Redhat是放在/etc/sysconfig/network-scripts目录下面的一大堆文件里面
- Debian系的则是存在/etc/network/interfaces文件里面，无论有多少块网卡，都在这个文件里

### 配置DHCP



# 常用命令

|    命令    |     全称     | 说明                                   | 示例                              | 备注   |
| :------: | :--------: | ------------------------------------ | ------------------------------- | ---- |
|   `du`   | disk usage | 统计目录(或文件)所占磁盘空间的大小                   | `du file-name` or `du dir-name` |      |
|   `df`   | disk file  | 用于显示文件系统的磁盘使用情况                      |                                 |      |
|  `free`  |     -      | 显示当前系统未使用的和已使用的内存数目，还可以显示被内核使用的内存缓冲区 |                                 |      |
| `vmstat` |     -      | 报告关于内核线程、虚拟内存、磁盘、陷阱和 CPU 活动的统计信息     |                                 |      |

# Vim

**三种模式**: 输入模式, 末行模式, 命令模式.

**切换:**

| 按键      | 到达的模式        |
| ------- | ------------ |
| `i,o,r` | 输入模式 / 编辑模式  |
| `:`     | 末行模式 / 指令列模式 |
| `ESC`   | 命令模式 / 一般模式  |



# END