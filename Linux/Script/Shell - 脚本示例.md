# 字符串

## 拼接

```shell
#### 1
"adgfdf""ccc"

#### 2
a="abc"
b="cde"
echo $a$b
abccde
```

# 循环

# 脚本参数

# 执行脚本

```shell
$command
eval $command
`$command`

#### 上面三条命令是有区别的
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaNotes$ command="echo string"
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaNotes$ $command
string
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaNotes$ eval $command
string
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaNotes$ `$command`
```

