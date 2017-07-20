# CentOS: 防火墙

## firewall

```shell
# 停止firewall
systemctl stop firewalld.service 
# 禁止firewall开机启动
systemctl disable firewalld.service 
# 查看默认防火墙状态（关闭后显示notrunning，开启后显示running）
firewall-cmd --state 
```

## iptables

```shell
sudo service iptables stop
```

