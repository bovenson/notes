#########################################################################
# File Name: test-03.sh
# Author: bovenson
# Email:  szhkai@126.com
# Created Time: 2017-09-11 10:02:53
#########################################################################
#!/bin/bash

# 表达式测试

if test 2 > 1
then 
	echo 'True'
else
	echo 'False'
fi

if [ 3 -gt 2 -a 1 -gt 2 ]
then
	echo 'All True'
else
	echo 'Not all true'
fi


