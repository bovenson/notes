---
title: Redis Usage
---

```shell
# 远程连接
$ redis-cli -h XXX.XXX.XXX.XXX -p YYYY

# 查看集群master节点
$ redis-cli -h xx -p xx cluster nodes | grep master | cut -d ' ' -f2 | paste -sd ',' -

$ redis-cli -h $rhost -p $rport cluster nodes | grep master | cut -d ' ' -f2 | paste -sd ',' -
```


