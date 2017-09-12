## shell变量

### 定义

```shell
v="This is a variable."
```

### 注意

- 变量名和等号之间不能有空格
- 首个字符必须为字母（a-z，A-Z）
- 中间不能有空格，可以使用下划线（_）
- 不能使用标点符号
- 不能使用bash里的关键字（可用help命令查看保留关键字）
- 已定义的变量，可以被重新定义

### 使用

使用一个定义过的变量，只要在变量名前面加美元符号即可

```shell
#!/bin/bash
# File: hello.sh

v="This is a variable."

echo $v
echo ${v}	# 变量名外面的花括号是可选的，加不加都行，加花括号是为了帮助解释器识别变量的边界
```

```shell
bovenson@ThinkCentre:~/Tmp$ bash hello.sh 
This is a variable.
This is a variable.
bovenson@ThinkCentre:~/Tmp$ 
```

**变量名外面的花括号是可选的，加不加都行，加花括号是为了帮助解释器识别变量的边界**, 推荐给所有变量加上花括号，这是个好的编程习惯, 比如下面这种情况:

```shell
for skill in Ada Coffe Action Java; do
    echo "I am good at ${skill}Script"
done
```

### 只读变量

使用 readonly 命令可以将变量定义为只读变量，只读变量的值不能被改变。

```shell
#!/bin/bash
# File: hello.sh

my_name="szk"
readonly my_name
my_name="bovenson"
```

```shell
bovenson@ThinkCentre:~/Tmp$ bash hello.sh 
hello.sh:行6: my_name: 只读变量
bovenson@ThinkCentre:~/Tmp$ 
```

### 删除变量

```shell
unset variable_name
```

**注意**:

- 变量被删除后不能再次使用
- unset命令不能删除只读变量

### 变量类型

- **局部变量:** 局部变量在脚本或命令中定义，仅在当前shell实例中有效，其他shell启动的程序不能访问局部变量
- **环境变量:** 所有的程序，包括shell启动的程序，都能访问环境变量，有些程序需要环境变量来保证其正常运行。必要的时候shell脚本也可以定义环境变量
- **shell变量:** shell变量是由shell程序设置的特殊变量。shell变量中有一部分是环境变量，有一部分是局部变量，这些变量保证了shell的正常运行