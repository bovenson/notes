# 获取当前工作路径

```shell
### 1
# `pwd`
bovenson@ThinkCentre:~$ echo `pwd`
/home/bovenson

### 2
# ${PWD} or $PWD
bovenson@ThinkCentre:~$ echo ${PWD}
/home/bovenson
bovenson@ThinkCentre:~$ echo $PWD
/home/bovenson
```

# 获取脚本相对于当前工作路径的路径

```shell
$(dirname $0)
```

# 获取脚本绝对路径

```shell
${PWD}/$(dirname $0)
```

```shell
bovenson@ThinkCentre:~/Tmp$ pwd
/home/bovenson/Tmp
bovenson@ThinkCentre:~/Tmp$ cat a/b/c/test.sh
#########################################################################
# File Name: a/b/c/test.sh
# Author: bovenson
# Email:  szhkai@126.com
# Created Time: 2017-09-21 10:07:32
#########################################################################
#!/bin/bash

BASEDIR=$(dirname $0)
FULLPATH="${PWD}/$(dirname $0)"

echo "PWD:      $PWD"
echo "BASEDIR:  $BASEDIR"
echo "FULLPATH: $FULLPATH"

bovenson@ThinkCentre:~/Tmp$ bash a/b/c/test.sh 
PWD:      /home/bovenson/Tmp
BASEDIR:  a/b/c
FULLPATH: /home/bovenson/Tmp/a/b/c
```

