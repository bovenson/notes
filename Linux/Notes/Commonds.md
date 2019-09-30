**netstat**

```shell
# 参数
-a	列出所有连接
-t	TCP连接
-u	UDP连接
-t	禁用反向DNS查找，提高输出速度
-l	只列出正在监听的端口
-p	列出PID和程序名称
-e	列出程序所属用户
-s	打印网络数据(接受、发送包统计等数据)
-r	打印路由信息
-i 	打印网络接口信息
-c	持续打印网络信息

# 常用
$ netstat -ct		# 获取持续输出
$ netstat -atnp	# 获取所有活动的TCP连接
$ netstat -ie		# 打印用户友好的网络接口信息
```

**watch**

```shell
# 参数
-n	设置间隔时间
-d	高亮显示变化区域
-t	关闭顶部的时间间隔、命令、当前时间信息

# 示例
$ watch -n 1 -d netstat -ant				# 观察每秒网络连接变化
$ watch -n 1 -d 'pstree|grep http'	# 观察每秒http链接的变化
$ watch -n 10 'cat /proc/loadavg'		# 每10秒输出一次系统平均负载
```

**awk**

```shell
# 示例
$ awk '{ print $1 }'	# 打印首列
```

**sed**

```shell
# 格式
$ sed [-Ealn] [-e command] [-f command_file] [-i extension] [file ...]
$ sed [-Ealn] command [file ...]
# command
[address[,address]]function[arguments]
# 示例
$ 1,20s/old/new/g
# 参数
-n	slilent模式，是输出处理行
-e	通过命令行参数附加编辑操作
-i  inplace 修改文件
-f 	指定sed命令文件
# funciont
a		新增（后）
i		插入（前）
c		替换
d		删除
p		打印选择数据
s		取代
# 匹配
## () 匹配模式，\1, \2 使用模式值
$ echo "http://localhost:8080/uri/path?p=v" | sed -e 's/^\([^:]*\):\/\/\([^:]*\):\([0-9]*\)\(.*\)$/protocol=[\1] host=[\2] port=[\3] pathAndParams=[\4]/g'
protocol=[http] host=[localhost] port=[8080] pathAndParams=[/uri/path?p=v]

# 示例
$ 1,20s/old/new/g		替换1~20行内的old为new
$ 2,5d	删除2~5行
$ 3,$d	删除第三行至结尾数据
```

**cut**

```shell
# 示例
$ cut -d ' ' -f3,5	# 打印第3，5列
```

