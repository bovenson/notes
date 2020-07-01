# Anaconda

## 环境

### 新建

```shell
conda create -n tensorflow
# Here, the ‘py36’ is the name of the environment you want to create, and ‘anaconda’ is the meta-package that includes all of 
# the actual Python packages comprising the Anaconda distribution. 
conda create -n py36 python=3.6 anaconda
```

### 删除

```shell
conda env remove -n tensorflow
```

