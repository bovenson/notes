#########################################################################
# File Name: rsync-myscript.sh
# Author: bovenson
# Email:  szhkai@126.com
# Created Time: 2017-09-21 10:56:28
#########################################################################
#!/bin/bash

FULLPATH="$PWD/$(dirname $0)"
cd $FULLPATH

rsync -r /home/public/mega/myscript ./

