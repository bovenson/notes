# tar

## 新建一个tar文档
##
```bash
touch file1
touch file2
mkdir dir1
touch dir1/file3

# 普通tar文档
tar -cf tar-file.tar file1 file2 dir1
# 压缩的tar文档(使用gnuzip算法)
tar -czf tgz-file.tgz file1 file2 dir1
```
** tgz格式通常也写作tar.gz **

## 查看tar文档内容
```bash
# 对于压缩的tar文档,也使用相同的命令
mint@lenovo ~/temp $ tar -tf tar-file.tar
file1
file2
dir1/
dir1/file3/
mint@lenovo ~/temp $ tar -tf tgz-file.tgz 
file1
file2
dir1/
dir1/file3
```

## 解压tar文档
### 解压到当前工作路径
```bash
# 未压缩
tar -xf tar-file.tar
# 使用gnuzip算法进行压缩的tar文档
tar -xzf tgz-file.tar
```

### 解压到指定目录
```bash
# 未进行压缩的tar文档
tar -xf tar-file.tar -C /path/to/destination
# 使用gunzip算法压缩的tar文档
tar -xzf tar-file.tar -C /path/to/destination
```

### 示例
```bash
mint@lenovo ~/temp $ mkdir ex
mint@lenovo ~/temp $ tar -xf tar-file.tar -C ex/
mint@lenovo ~/temp $ cd ex/
mint@lenovo ~/temp/ex $ ls
dir1  file1  file2
```
