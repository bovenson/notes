#!/bin/bash

echo "To install Docker CE, you need the 64-bit version of one of these Ubuntu versions:"
echo "	Yakkety 16.10"
echo "	Xenial 16.04"
echo "	Trusty 14.04"


# Got root?
myWHOAMI=$(whoami)
if [ "$myWHOAMI" != "root" ]
  then
    echo "Need to run as root ..."
    sudo ./$0
    exit
fi


# Set up the Docker CE repository on Ubuntu. 
# The lsb_release -cs sub-command prints the name of your Ubuntu version, like xenial or trusty.
sudo apt-get -y install apt-transport-https ca-certificates curl
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

sudo apt-get update
sudo apt-get -y install docker-ce
