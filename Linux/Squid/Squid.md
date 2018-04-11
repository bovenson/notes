# 安装

```shell
# docker
$ docker pull sameersbn/squid

# 运行
docker run --name squid -d --restart=always \
  --publish 3128:3128 \
  --volume /srv/docker/squid/cache:/var/spool/squid3 \
  sameersbn/squid
```

