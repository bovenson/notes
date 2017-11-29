---
title: React Native 笔记
tags: React Native
---

[TOC]

# React native

## 问题

Watchman crawl failed. Retrying once with node crawler.

```shell
echo fs.inotify.max_user_instances=524288 | sudo tee -a /etc/sysctl.conf && sudo sysctl -p
echo fs.inotify.max_user_watches=524288 | sudo tee -a /etc/sysctl.conf && sudo sysctl -p
echo fs.inotify.max_queued_events=524288 | sudo tee -a /etc/sysctl.conf && sudo sysctl -p
```

```shell
react-native run-android
react-native start
react-native log-android
```

