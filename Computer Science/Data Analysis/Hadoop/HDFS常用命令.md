[TOC]

# HDFS 常用命令

## ls

`hdfs dfs ls`

## cat 

`hdfs dfs -cat /filepath/filename`

## put: 上传本地文件到HDFS

`hdfs dfs -put /localfilepath/filename /hdfspath/`

例如复制当前文件夹下的`file.txt`文件到`hdfs`中的`/user/bovenson/input`命令为:

```shell
hdfs dfs -put ./file.txt /user/bovenson/input/
```

## get: 从HDFS获取文件到本地

` hdfs dfs -get /hdfsfilepath/filename /localpath/`

例如复制`hdfs`中`/user/bovenson/input`目录下所有文件, 到当前目录下:

` hdfs dfs -get /user/bovenson/input/* ./`  

## mkdir

`hdfs dfs -mkdir input/countword`

## mv

`hdfs dfs -mv input/file* input/countword`

## rm

`hdfs dfs -rm /hdfs/path/to/filename`

** 未完待续... **