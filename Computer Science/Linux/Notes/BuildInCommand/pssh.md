# pssh使用

**pssh的使用需要先配置本机可以使用SSH对受控主机进行无密登陆**

**pssh工具主要有5个程序**

## 安装

### Ubuntu

`sudo apt-get install pssh`

## pssh

多主机并行运行命令

### 示例

`pssh -P -h slaves.txt ls`

slaves.txt内容:

```shell
192.168.124.6:22
192.168.124.7:22
```







