---
tltie: Linux - Sed
tags: 
   - Sed
categories:
   - Linux
---



# example

```shell
$ cat msg
{
'localhost' => ['domain_name' => 'default', 'domain_type' => 'mobile'],
}

# 替换文件内容并打印
$ sed '/localhost/p; s/localhost/dev.ops/' msg
{
'localhost' => ['domain_name' => 'default', 'domain_type' => 'mobile'],
'dev.ops' => ['domain_name' => 'default', 'domain_type' => 'mobile'],
}

# 替换文件内容并将输出重定向到新文件
$ sed '/localhost/p; s/localhost/dev.ops/' msg > new_msg
$ cat new_msg
{
'localhost' => ['domain_name' => 'default', 'domain_type' => 'mobile'],
'dev.ops' => ['domain_name' => 'default', 'domain_type' => 'mobile'],
}
# 源文件并未更改
$ cat msg
{
'localhost' => ['domain_name' => 'default', 'domain_type' => 'mobile'],
}

# 在源文件修改并保存源文件至备份文件
$ sed -i 'msg_backup' '/localhost/p; s/localhost/dev.ops/' msg
$ ls
msg		msgmsg_backup	new_msg
# 打印源文件备份
$ cat msgmsg_backup
{
'localhost' => ['domain_name' => 'default', 'domain_type' => 'mobile'],
}
# 打印源文件
$ cat msg
{
'localhost' => ['domain_name' => 'default', 'domain_type' => 'mobile'],
'dev.ops' => ['domain_name' => 'default', 'domain_type' => 'mobile'],
}

# 直接修改源文件并且不保存备份
$ sed -i '/localhost/p; s/localhost/dev.worker/' msgmsg_backup
# $ sed -i '' '/localhost/p; s/localhost/dev.worker/' msgmsg_backup	# os x
$ cat msgmsg_backup
{
'localhost' => ['domain_name' => 'default', 'domain_type' => 'mobile'],
'dev.worker' => ['domain_name' => 'default', 'domain_type' => 'mobile'],
}

$ ls
msg		msgmsg_backup	new_msg

#### Other Examples
## 正则替换
$ cat script/config.sh
workers=6666
threads_compute=10
threads=101
$ sed -i "s/workers=[0-9]*/workers=9/" ./script/config.sh
$ cat script/config.sh
workers=9
threads_compute=10
threads=101
```

# replace string with another string

```shell
sed -i 's/head/heap/g' Heap.cpp
# sed -i '' 's/head/heap/g' Heap.cpp	# 不保留文件备份
```

# copy line before replace

```shell
sed '/localhost/p; s/localhost/dev.ops/' FILENAME
```

# 问题

`sed: 1:  ... : extra characters at the end of H command`

- edit in place on OSX without keeping a backup, use `-i ''`:

  ```shell
  sed -i '' -e '/localhost/p; s/localhost/dev.ops/' FILENAME
  ```

# **参考**

- [Ref 1](https://unix.stackexchange.com/questions/112023/how-can-i-replace-a-string-in-a-files)