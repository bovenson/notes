#!/bin/bash

# 1 安装Stardict
# 2 下载字典

if [[ $EUID -ne 0 ]]; then
    echo "This script must be run as root" 
    exit 1
fi

apt install stardict

mkdir .dict
cd .dict

# 下载字典
echo "Downloading..."
wget http://download.huzheng.org/zh_CN/stardict-oald-cn-2.4.2.tar.bz2
wget http://download.huzheng.org/zh_CN/stardict-oxford-gb-2.4.2.tar.bz2

wget http://download.huzheng.org/zh_CN/stardict-langdao-ec-gb-2.4.2.tar.bz2
wget http://download.huzheng.org/zh_CN/stardict-langdao-ce-gb-2.4.2.tar.bz2

wget http://download.huzheng.org/zh_CN/stardict-lazyworm-ec-2.4.2.tar.bz2
wget http://download.huzheng.org/zh_CN/stardict-lazyworm-ce-2.4.2.tar.bz2

wget http://download.huzheng.org/zh_CN/stardict-xhzd-2.4.2.tar.bz2
wget http://download.huzheng.org/zh_CN/stardict-HanYuChengYuCiDian-new_colors-2.4.2.tar.bz2


# 解压
echo "Extracting..."
for file in `ls`; do
    if [ "${file##*.}" = "tar" ]; then
        tar -xf ${file}
        rm ${file}
    elif [ "${file##*.}" = "bz2" ]; then
        tar -jxf ${file}
        rm ${file}
    elif [ "${file##*.}" = "gz" ]; then
        tar -zxf ${file}
        rm ${file}
    fi
done;

cd ..
mv .dict/* /usr/share/stardict/dic/
rmdir ./.dict
echo "Done"
