# 写磁盘空文件占用磁盘

```shell
for i in {1..16000}; do dd if=/dev/zero of=data_bak_part_${i} bs=50M count=1 ; done
```

# 查看磁盘占用

```shell
$ df -h
文件系统        容量  已用  可用 已用% 挂载点
udev            3.9G     0  3.9G    0% /dev
tmpfs           788M  2.9M  785M    1% /run
/dev/sda8        33G   11G   21G   34% /
tmpfs           3.9G  8.8M  3.9G    1% /dev/shm
...

# 查看文件/文件夹空间占用
$ du -h --max-depth=1 .
```

# 查看网络信息

```shell
# sar命令
$ sar -n DEV 1 1
```

