[toc]

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
$ du -h --max-depth=1 . --exclude "./.*"
```

# 查看网络信息

```shell
# sar命令
$ sar -n DEV 1 1
```

# 查看系统配置

```shell
$ lscpu	# 硬件信息
$ uname -a	# 系统信息
```

# 搜索文件名

```shell
$ find . -name "foo*"
```

# 搜索指定路径下文件中的字符串

```shell
$ grep -r "word" /path/to/search
```

# 替换目录下所有字符串

```shell
$ find . -type f -exec sed -i 's/foo/bar/g' {} +
$ find . -type f -exec sed -i '' 's/foo/bar/g' {} +		# mac os x

$ find . -type f -name '*.h' -exec sed -i 's/foo/bar/g' {} +	
$ find . -type f -not -name '*.h' -exec sed -i '' 's/foo/bar/g' {} + # exclude
```

# 传文件

```shell
# python
## sender
$ python -m SimpleHTTPServer
## receiver
$ wget <sender-host>:8000/path/to/file

# nc
## receiver
$ nc -l <port>
## sender
$ nc <receiver-host> <reveiver-port>
```

# 设置代理

```shell
$ export http_proxy=http://user:password@[host/domain]:port
$ export https_proxy=https://user:password@[host/domain]:port
```

# 时间

```shell
# 产看当前时区
date -R

# 设置时区
tzselect

# 查看时间
date

# 设置
date -s 07/25/2017
date -s 00:00:00
```

