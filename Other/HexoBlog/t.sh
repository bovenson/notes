#########################################################################
# File Name: t.sh
# Author: bovenson
# Email:  szhkai@126.com
# Created Time: 2017-09-18 16:31:52
#########################################################################
#!/bin/bash

ERROR_CODE_PARAMS_ERROR=1

if [ ! $# -eq 2 ] ; then
	echo "USAGE script SourcePath DestinationPath"
	exit $ERROR_CODE_PARAMS_ERROR
elif [ ! -e $1 ] ; then 
	echo $1
	echo "ERROR: Source Dir or File does not exists"
	exit 1
elif [ -d $1 -a -f $2 ] ; then
	echo "ERROR: Source is dir.Meanwhile, destination exists and it is a file. "
	exit 1
elif [ -f $1 -a -d $2 ] ; then
	echo "ERROR: Source is file.Meanwhile, desitination exists and it is a dir. "
	exit 1
fi

echo "HEREs"

if [ -d $1 -a ! -e $2 ] ; then
    echo "Create Dir: ${2}"
	mkdir -p $2
fi



