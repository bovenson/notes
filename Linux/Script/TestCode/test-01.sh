#########################################################################
# File Name: test-01.sh
# Author: bovenson
# Email:  szhkai@126.com
# Created Time: 2017-09-11 09:18:42
#########################################################################
#!/bin/bash

# 数值测试

num1=100
num2=200

if test $[num1] -eq $[num2]
then
	echo 'EQUAL'
else
	echo 'NOT EQUAL'
fi

