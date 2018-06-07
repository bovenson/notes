---
title: Nginx 配置
tags:
	- Nginx
categories:
	- Nginx
---

# 配置

## upstream+server

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

# localtion

- `syntax`: `*location [=|~|~\*|^~|@] /uri/ { … }*`
- `default`: `no`
- `context`: `server`
- `/uri/` 可通过可以用正则, 也可以用普通字符串.
- `~` 表示区分大小写
- `~*` 表示不区分大小写
- 其他前缀（包括：`=`，`^~ `和`@ `）和无任何前缀的都属于普通location

# 显式文件目录列表

```shell
autoindex on;
```

**示例**

```ini
server {
	listen 80 default_server;
	listen [::]:80 default_server;

	root /var/www/html;
	
	autoindex on;	# 显式文件目录列表

	# Add index.php to the list if you are using PHPi
	index index.html index.htm index.nginx-debian.html;


	server_name _;

	location / {
		# First attempt to serve request as file, then
		# as directory, then fall back to displaying a 404.
		try_files $uri $uri/ =404;
	}
}
```



## 参考

- [参考一](https://www.2cto.com/os/201212/176520.html)