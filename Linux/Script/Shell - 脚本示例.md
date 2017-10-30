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

# 删除除例外文件外所有文件及文件夹

```shell
#### 1
find . ! -name 'filename-pattern' -type f -exec rm -f {} +
# 删除除匹配'filename-pattern'外的所有常规文件(!递归删除所有文件只剩文件夹, 包括隐藏文件)
# 如果要删除文件夹, 把 -type f 改为 -type d, rm -f 改为 rm -rf
```

