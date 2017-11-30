---
title: React Native 笔记
tags: React Native
---

[TOC]

# React native

## 自定义组件

```javascript
class UselessTextInput extends Component {
  render() {
    return (
      <TextInput
        {...this.props} // 将父组件传递来的所有props传递给TextInput;比如下面的multiline和numberOfLines
        editable = {true}
        maxLength = {40}
      />
    );
  }
}
```



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

