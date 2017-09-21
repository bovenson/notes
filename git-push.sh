#!/bin/bash
# echo `pwd` > /home/bovenson/Tmp/cron.log

FULLPATH="$PWD/$(dirname $0)"
cd $FULLPATH

git add --all
git commit -m "auto commit"
git push origin master
