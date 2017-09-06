# 示例

```shell
# 解压当前所有rar文件到指定目录
for file in *.rar; do unrar x ${file} ./ex; done;

# bash script file
#!/bin/bash
for file in *.rar
do
    mkdir -p ./ex/${file}
    unrar x ${file} ./ex/${file}
done
```

