```shell
#### 内存对象占比分析
$ jmap -histo:live <pid>		# live: 存活对象

#### dump heap
$ jmap -dump:live,format=b,file=<file-name> <pid>
```

