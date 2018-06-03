#!/bin/bash
#########################################################################
# File Name: InstallJDK.sh
# Author: bovenson
# Email:  szhkai@126.com
# Created Time: 2017-09-19 15:52:15
#########################################################################

# 使用: bash InstallJDK.sh jdk-file.tar.gz

if [[ $EUID -ne 0 ]]; then
   echo "This script must be run as root" 
   exit 1
fi

# 解压jdk文件
extractFile() {
	destPath="/usr/lib/jvm/${dirName}"	# 解压目标目录
	if [ -e $destPath ] ; then
		echo "=============== $destPath exists ==============="
	else
		if [ ! -e /usr/lib/jvm ] ; then
			mkdir -p /usr/lib/jvm
		fi
		echo "=============== Extracting files ==============="
		tar -xzf $filePath -C /usr/lib/jvm/
		echo "=============== Extracting files Done ==============="
	fi

}

# 在Profile中设置PATH
setProfile() {
	if [ -v JAVA_HOME ]; then
		echo "=============== JAVA_HOME setted ==============="
		exit 1
	else
		echo "=============== Setting profile ==============="
		echo "# JDK setting" >> /etc/profile
		echo "export JAVA_HOME=${destPath}" >> /etc/profile
		echo "export CLASSPATH=\$CLASSPATH:.:\$JAVA_HOME/lib:\$JAVA_HOME/jre/lib" >> /etc/profile
		echo "export PATH=\$PATH:\$JAVA_HOME/bin:\$JAVA_HOME/jre/bin" >> /etc/profile
		echo "=============== Setting profile done ==============="
	fi
}

main() {
	source /etc/profile
	extractFile
	setProfile
	echo "=============== ALL DONE ==============="

	# source /etc/profile

	# rm /usr/bin/java
	# rm /usr/bin/javac
	# ln -s /usr/lib/jvm/jdk/bin/java /usr/bin/java
	# ln -s /usr/lib/jvm/jdk/bin/javac /usr/bin/javac



	# # update-alternatives --install /usr/lib/java java /usr/lib/jvm/jdk/bin/java 300  
	# # update-alternatives --install /usr/lib/javac javac /usr/lib/jvm/jdk/bin/javac 300
	# # update-alternatives --config java

	java -version
}

# 检查输入文件
if [ ! $# -eq 1 ] ; then	# 参数不正确
	echo "=============================="
	echo "Wrong Parameters."
	echo "Usage: Command JDK-File-Path"
	echo "Example: bash InstallJDK.sh jdk-1.8.11.tar.gz"
	echo "=============================="
elif [ ! -f $1 ] ; then		# 文件不存在
		echo "=============== JDK file doesn't exists ==============="
		exit -1
else						# 开始安装
	echo "=============== Begin install JDK ==============="
	filePath=$1				# 文件路径

	# 获取解压后文件夹名称
	fileNames=($(tar -tf $1))
	dirName=${fileNames%/*}
	main
fi
