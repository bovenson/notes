#!/bin/bash
echo `pwd` > /home/bovenson/Tmp/cron.log
git add --all
git commit -m "auto commit"
git push origin master
