#!/bin/bash

# set 
FULLPATH="$PWD/$(dirname $0)"
cd $FULLPATH

echo $PWD

./intellij-license-server-os-x 2>license-server.log 1>&2 &
