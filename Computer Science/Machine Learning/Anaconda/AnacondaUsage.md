# 虚拟环境

## 新建

```shell
conda create --name envname python=3.6	# 指定环境名及python版本
```

从文件新建环境

```shell
conda env create -f environment.yml
```

配置文件示例

```yaml
name: stats
dependencies:
  - numpy
  - pandas
```

```yaml
name: stats2
channels:
  - javascript
dependencies:
  - python=3.4   # or 2.7
  - bokeh=0.9.2
  - numpy=1.9.*
  - nodejs=0.10.*
  - flask
  - pip:
    - Flask-Testing
```

## 激活

```shell
source activate envname
```

## 取消激活

```shell
source deactivate envname
```

## 删除环境

```shell
conda remove --name envname --all
```

## 查看已有环境

```shell
### 1
# conda env list
$ conda env list
# conda environments:
#
dataanalysis             /home/public/installed/anaconda3/envs/dataanalysis
py2env                   /home/public/installed/anaconda3/envs/py2env
root                  *  /home/public/installed/anaconda3

### 2
# conda info --envs
$ conda info --envs
# conda environments:
#
dataanalysis             /home/public/installed/anaconda3/envs/dataanalysis
py2env                   /home/public/installed/anaconda3/envs/py2env
root                  *  /home/public/installed/anaconda3
```

## 查看环境已有包

```shell
### 1
# 查看当前激活的环境安装的包
# conda list
bovenson@ThinkCentre:/home/public/Git/notes$ conda list
# packages in environment at /home/public/installed/anaconda3:
#
_license                  1.1                      py36_1    defaults
alabaster                 0.7.10                   py36_0    defaults
anaconda                  custom                   py36_0    defaults
anaconda-client           1.6.3                    py36_0    defaults
...

### 2
# 查看指定名称环境安装的包
bovenson@ThinkCentre:/home/public/Git/notes$ conda list -n py2env
# packages in environment at /home/public/installed/anaconda3/envs/py2env:
#
cairo                     1.14.8                        0    https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free
certifi                   2016.2.28                py27_0    https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free
cycler                    0.10.0                   py27_0    https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free
dbus                      1.10.20                       0    https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free
expat                     2.1.0                         0    https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free
fontconfig                2.12.1                        3    https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free
freetype                  2.5.5                         2    https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free
...
```

## 安装包

```shell
### 1
# 向当前激活的环境安装包
conda install scipy

### 2
# 向指定环境安装包
conda install -n myenv scipy
```



# 设置国内源

```shell
# 添加Anaconda的TUNA镜像
conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free/
# TUNA的help中镜像地址加有引号，需要去掉
 
# 设置搜索时显示通道地址
conda config --set show_channel_urls yes
```

