# 安装

```shell
# docker
$ docker pull sameersbn/squid

# 运行
docker run --name sqd -d --restart=always \
  --publish 3128:3128 \
  --volume /srv/docker/squid/cache:/var/spool/squid3 \
  sameersbn/squid
  
# exec
docker exec -it squid bash

# conf
acl all src 0.0.0.0/0.0.0.0 acl SSL_ports port 443
acl Safe_ports port 80 # http
acl Safe_ports port 443 # https
acl CONNECT method CONNECT
http_access allow all
http_port 3128
visible_hostname proxy
```

# 配置

## 认证

- [参考一](https://blog.csdn.net/skylinethj/article/details/43837277)