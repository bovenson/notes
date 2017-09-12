# 打印当前目录下所有文件

```shell
###### 1
for file in *; do echo ${file}; done;
# 示例输出
test-01.sh
test-02.sh
test-03.sh

###### 2
for file in `ls`; do echo ${file}; done;
# 示例输出
test-01.sh
test-02.sh
test-03.sh
```

