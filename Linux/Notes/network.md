# 网卡配置文件

```shell
# 目录
/etc/sysconfig/network-scripts/ 
```

## 配置示例

### 多个IP地址

```config
TYPE="Ethernet"
BOOTPROTO="none"
DEFROUTE="yes"
IPV4_FAILURE_FATAL="no"
IPV6INIT="yes"
IPV6_AUTOCONF="yes"
IPV6_DEFROUTE="yes"
IPV6_FAILURE_FATAL="no"
NAME="enp0s3"
UUID="933cdc9b-b383-4ddd-b219-5a72c69c9cf0"
ONBOOT="yes"
HWADDR="08:00:27:3F:AB:68"
IPADDR0="192.168.1.150"
IPADDR1="192.168.1.151"
IPADDR2="192.168.1.152"
PREFIX0="24"
GATEWAY0="192.168.1.1"
DNS1="192.168.1.1"
IPV6_PEERDNS="yes"
IPV6_PEERROUTES="yes"
```

# 配置静态IP

