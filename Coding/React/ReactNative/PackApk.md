---
title: React Native 工程打包
tags:
	- React native
categories:
	- React native
---

# 打包Apk

## 生成签名密钥

```shell
keytool -genkey -v -keystore my-release-key.keystore -alias power-cloud -keyalg RSA -keysize 2048 -validity 10000
```

## ...

## 生成Apk

android目录下：

```shell
./gradlew assembleRelease
```

