# nginx

## 配置

### upstream+server

```ini
upstream backend {
    server backend1.example.com       weight=5;
    server backend2.example.com:8080;
    server unix:/tmp/backend3;

    server backup1.example.com:8080   backup;
    server backup2.example.com:8080   backup;
}

server {
    location / {
        proxy_pass http://backend;
    }
}
```

## localtion

- `syntax`: `*location [=|~|~\*|^~|@] /uri/ { … }*`
- `default`: `no`
- `context`: `server`
- `/uri/` 可通过可以用正则, 也可以用普通字符串.
- `~` 表示区分大小写
- `~*` 表示不区分大小写
- 其他前缀（包括：`=`，`^~ `和`@ `）和无任何前缀的都属于普通location
- ​