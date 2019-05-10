```shell
#### 内存对象占比分析
$ jmap -histo:live <pid>		# live: 存活对象

## 数据说明
[ : 数组
[[ : 二维数组

B : byte
C : Char
D : double
F : float
I : int
J : long
S : short
Z : boolean
L+ClassName : 对象

## For example
# [[I : int[][]
# [Ljava.lang.Object : Object对象数组

#### dump heap
$ jmap -dump:live,format=b,file=<file-name> <pid>
```

