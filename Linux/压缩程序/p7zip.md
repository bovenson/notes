[toc]
# p7zip使用手册
**p7-zip:一个高压缩率的7-zip文档存档(file archiver)**
支持如下格式:7z,LZMA2,XZ,ZIP,Zip64,CAB,BAR,ARJ,GZIP,BZIP2,TAR,CPIO,RPM,ISO,大多数文件爱你镜像,DEB格式文件.
## 命令格式
`p7zip [-d] [-h|--help] [file]`
## 参数说明
|参数|说明|
|:----|:----|
|-d|解压缩文件|
|-h,--help|打印使用手册|

# `7z`

## 安装
`sudo apt instll p7zip-full`
## 命令格式
`7z [adeltux] [-] [SWITCH] <ARCHIVE_NAME> <ARGUMENTS>`
## 参数说明
|参数|说明|
|:----|:----|
|a|添加|
|d|删除|
|e|解压|
|l|列表|
|t|测试|
|u|更新|
|x|完整路径解压|

## 示例
### 创建压缩文件
```shell
7z a -t7z archive.7z dir1
```
### 解压缩
```shell
7z x file.7z	// 7z e file.7z会将所有文件解压到当前路径,并不保存路径信息
```