#!/bin/bash
# @author bovenson
# @description git repo (notes) auto commit


# echo `pwd` > /home/bovenson/Tmp/cron.log


# setting pwd
FULLPATH="$PWD/$(dirname $0)"
cd $FULLPATH

# setting comment
if [ $1 ]
then
    comment=$1
else
    comment="auto commit"
fi  

GITHUB_ADDR="git@github.com:bovenson/notes.git"
MAYUN_ADDR="git@gitee.com:bovenson/notes.git"

# remote_origin_exits=`git remote -v | grep "^origin"`
# remote_mayun_exits=`git remote -v | grep "^mayun"`
# echo $remote_origin_exits
# echo $remote_mayun_exits

if [ ! "`git remote -v | grep '^origin'`" ] ; then
	echo "remote repo origin not exits"
	eval "git remote add origin '$ORIGIN_ADDR'"
	echo "added origin $ORIGIN_ADDR"
fi

if [ ! "`git remote -v | grep '^mayun'`" ] ; then
	echo "remote repo mayun not exits"
	eval "git remote add mayun '$MAYUN_ADDR'"
	echo "added mayun $MAYUN_ADDR"
fi

# exit
git add --all
git commit -m "$comment"
git push origin master
git push mayun master
