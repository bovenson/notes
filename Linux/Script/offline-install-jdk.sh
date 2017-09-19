#########################################################################
# File Name: offline-install-jdk.sh
# Author: bovenson
# Email:  szhkai@126.com
# Created Time: 2017-09-19 15:52:15
#########################################################################
#!/bin/bash

copyFile() {
	# copy jdk file
	if [ -f jdk.tar ] ; then
		echo "Extracting files ..."
		tar -xf jdk.jar -C /usr/lib/jvm
		echo "Extracting files Done."
	elif [ -f jdk.tar.gz ] ; then
		echo "Extracting files ..."
		tar -xzf jdk.tar.gz -C /usr/lib/jvm
		echo "Extracting files Done."
	else
		echo "JDK file does not exsits."
		echo "Please ensure jdk.tar or jdk.tar.gz file in the same dir with this script."
	fi
}

setProfile() {
	echo "Setting profile..."
	echo "# JDK setting" >> /etc/profile
	echo "export JAVA_HOME=/usr/lib/jvm/jdk" >> /etc/profile
	echo "export CLASSPATH=\$CLASSPATH:.:\$JAVA_HOME/lib:\$JAVA_HOME/jre/lib" >> /etc/profile
	echo "export PATH=\$PATH:\$JAVA_HOME/bin:\$JAVA_HOME/jre/bin" >> /etc/profile
	echo "Setting profile done."
}

if [ -e /usr/lib/jvm/jdk ] ; then
	echo "JDK already installed."
elif [ ! -e /usr/lib/jvm ] ; then
	mkdir -p /usr/lib/jvm
	copyFile
else
	copyFile
fi

setProfile

source /etc/profile

rm /usr/bin/java
rm /usr/bin/javac
ln -s /usr/lib/jvm/jdk/bin/java /usr/bin/java
ln -s /usr/lib/jvm/jdk/bin/javac /usr/bin/javac



# update-alternatives --install /usr/lib/java java /usr/lib/jvm/jdk/bin/java 300  
# update-alternatives --install /usr/lib/javac javac /usr/lib/jvm/jdk/bin/javac 300
# update-alternatives --config java

java -version

