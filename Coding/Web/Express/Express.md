---
title: Express
tags: Express
---

# 安装

```shell
$ npm install -g expree
#### or
$ npm install -g express-generator
```

# 使用生成器创建应用

```shell
$ express PlayCanvas

  warning: the default view engine will not be jade in future releases
  warning: use `--view=jade' or `--help' for additional options


   create : PlayCanvas
   create : PlayCanvas/package.json
   create : PlayCanvas/app.js
   create : PlayCanvas/public
   create : PlayCanvas/routes
   create : PlayCanvas/routes/index.js
   create : PlayCanvas/routes/users.js
   create : PlayCanvas/views
   create : PlayCanvas/views/index.jade
   create : PlayCanvas/views/layout.jade
   create : PlayCanvas/views/error.jade
   create : PlayCanvas/bin
   create : PlayCanvas/bin/www
   create : PlayCanvas/public/javascripts
   create : PlayCanvas/public/images
   create : PlayCanvas/public/stylesheets
   create : PlayCanvas/public/stylesheets/style.css

   install dependencies:
     $ cd PlayCanvas && npm install

   run the app:
     $ DEBUG=playcanvas:* npm start
```

配置模板引擎和sass

```shell
$ express PlayCanvas --view=jade --css=sass
```

## 安装依赖

```shell
$ cd PlayCanvas && npm install	# PlayCanvas: 工程名
```

## 启动工程

启动工程之前需要先使用命令`npm install` 安装依赖。

```shell
$ DEBUG=playcanvas:* npm start
```

## 设置模板引擎

```shell
$ express --view=jade
```

## 设置sass

```shell
$ express --css=sass
```

### 使用scss语法

更改入口文件中配置

```javascript
app.use(sassMiddleware({
  src: path.join(__dirname, 'public'),
  dest: path.join(__dirname, 'public'),
  indentedSyntax: false, // true = .sass and false = .scss	// 这里改为false
  sourceMap: true
}));
```

# 安装依赖

## 前段框架

### bootstrap

```shell
$ npm install bootstrap@4.0.0-beta.2 --save
npm WARN bootstrap@4.0.0-beta.2 requires a peer of jquery@1.9.1 - 3 but none is installed. You must install peer dependencies yourself.
npm WARN bootstrap@4.0.0-beta.2 requires a peer of popper.js@^1.12.3 but none is installed. You must install peer dependencies yourself.

+ bootstrap@4.0.0-beta.2
added 1 package in 8.659s
```

在入口js文件中添加：

```javascript
app.use(express.static(__dirname + '/node_modules/bootstrap/dist'));
```

在模板中引用(以jade模板引擎为例)：

```jade

```

