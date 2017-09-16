# 虚拟环境

**新建**

```shell
conda create --name envname python=3.6	# 指定环境名及python版本
```

**激活**

```shell
source activate envname
```

**取消激活**

```shell
source deactivate envname
```

**删除环境**

```shell
conda remove --name envname --all
```

# 设置国内源

```shell
# 添加Anaconda的TUNA镜像
conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free/
# TUNA的help中镜像地址加有引号，需要去掉
 
# 设置搜索时显示通道地址
conda config --set show_channel_urls yes
```

