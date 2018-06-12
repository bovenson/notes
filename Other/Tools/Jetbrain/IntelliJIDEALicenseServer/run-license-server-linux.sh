#!/bin/bash
# Run IntelliJ License Server
# Note: run as root
# @author bovenson

# set path of script as pwd
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
cd "$DIR"

date > ./debug.log
echo "run script" >> ./debug.log
echo "pwd: $PWD" >> ./debug.log

# run license server
./IntelliJIDEALicenseServer_linux_amd64 2>license-server.log 1>&2 &
