#!/bin/bash
# echo `pwd` > /home/bovenson/Tmp/cron.log

FULLPATH="$PWD/$(dirname $0)"
cd $FULLPATH

if [ $1 ]
then
    comment=$1
else
    comment="auto commit"
fi  

git add --all
git commit -m "$comment"
git push origin master
git push mayun master
