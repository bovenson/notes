# 写磁盘空文件占用磁盘

```shell
for i in {1..16000}; do dd if=/dev/zero of=data_bak_part_${i} bs=50M count=1 ; done
```



