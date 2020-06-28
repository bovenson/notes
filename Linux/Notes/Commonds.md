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
$ echo "localhost:8080" | cut -d ':' -f1	# 提取host
localhost
```

**tr**

```shell
# tr: translate characters, 转换和删除字符
# 格式
tr [-Ccsu] string1 string2	# 替换 string1 中字符为 string2 中位置对应的字符
tr [-Ccu] -d string1
tr [-Ccu] -s string1
tr [-Ccu] -ds string1 string2
# 参数
-d	删除指令字符
-c	反选指令字符串
-C  类似-c,反选指令集中字符

# class
[:class:]  Represents all characters belonging to the defined character class.  alnum        <alphanumeric characters>
alpha        <alphabetic characters>
blank        <whitespace characters>
cntrl        <control characters>
digit        <numeric characters>
graph        <graphic characters>
ideogram     <ideographic characters>
lower        <lower-case alphabetic characters>
phonogram    <phonographic characters>
print        <printable characters>
punct        <punctuation characters>
rune         <valid characters>
space        <space characters>
special      <special characters>
upper        <upper-case characters>
xdigit       <hexadecimal characters>

# 示例
$ echo "What a cute dog" | tr a-z A-Z
WHAT A CUTE DOG
$ $ echo "What a cute dog" | tr [:lower:] [:upper:]
WHAT A CUTE DOG
```

**sort**

```shell
# 排序
# 格式
sort [-bcCdfghiRMmnrsuVz] [-k field1[,field2]] [-S memsize] [-T dir] [-t char] [-o output] [file ...]

# 参数
-u	删除重复key
-s	稳定排序
-b	忽略开头空白符
-d	字典序输出
-i  忽略不可打印字符
-R	乱序输出
-n	数字排序
-t	指定分隔符
-k	指定排序字段

# 示例
$ cat t2
10.0.0.1:8080
10.0.0.2:8080
10.0.0.1:8090
10.0.0.3:8070
10.0.0.1:8060
$ cat t2 | sort -t ':' -k 1
10.0.0.1:8060
10.0.0.1:8080
10.0.0.1:8090
10.0.0.2:8080
10.0.0.3:8070
$ cat t2 | sort -t ':' -k 2
10.0.0.1:8060
10.0.0.3:8070
10.0.0.1:8080
10.0.0.2:8080
10.0.0.1:8090
```

**uniq**

```shell
# 删除重复行，一般与sort结合使用
```

**date**

```shell
# format
date +"%Y%m%d"
# minus
-d "-1 days"
```

