# 防火墙

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

# SELinux

**查看**

`sestatus -v`

```shell
# sestatus -v
SELinux status:                 enabled
SELinuxfs mount:                /sys/fs/selinux
SELinux root directory:         /etc/selinux
Loaded policy name:             targeted
Current mode:                   enforcing
Mode from config file:          enforcing
Policy MLS status:              enabled
Policy deny_unknown status:     allowed
Max kernel policy version:      28

Process contexts:
Current context:                unconfined_u:unconfined_r:unconfined_t:s0-s0:c0.c1023
Init context:                   system_u:system_r:init_t:s0
/usr/sbin/sshd                  system_u:system_r:sshd_t:s0-s0:c0.c1023

File contexts:
Controlling terminal:           unconfined_u:object_r:user_devpts_t:s0
/etc/passwd                     system_u:object_r:passwd_file_t:s0
/etc/shadow                     system_u:object_r:shadow_t:s0
/bin/bash                       system_u:object_r:shell_exec_t:s0
/bin/login                      system_u:object_r:login_exec_t:s0
/bin/sh                         system_u:object_r:bin_t:s0 -> system_u:object_r:shell_exec_t:s0
/sbin/agetty                    system_u:object_r:getty_exec_t:s0
/sbin/init                      system_u:object_r:bin_t:s0 -> system_u:object_r:init_exec_t:s0
/usr/sbin/sshd                  system_u:object_r:sshd_exec_t:s0
```

**关闭**

```shell
# 临时关闭
$ setenforce 0

# 永久关闭
# 修改配置文件 /etc/selinux/config
# 将SELINUX=enforcing改为SELINUX=disabled
# 重启
```

