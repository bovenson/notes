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

### 传递父节点属性

```javascript
<TextInput
	{...this.props} // 将父组件传递来的所有props传递给TextInput;比如下面的multiline和		numberOfLines
	editable = {true}
	maxLength = {40}
/>
```

OR

```javascript
<TextInput
	style={this.props.style}	// 仅使用父节点定义的style属性
	editable = {true}
	maxLength = {40}
/>
```



### 调用父节点方法

```javascript
export default class Button extends Component {
  constructor(props){
    super(props)
  }
  render(){
    return(
      <TouchOpacity
      onPress={this.props.onPress}
      >
        <Text> Button </Text>
      </TouchOpacity>
    )
  }
}
```

```javascript
export default class MainPage extends Component {
  render(){
    return(
      <Button onPress={ ()=>this.doSomething }></Button>
    )
  }
}
```

### 参考

- [参考一](https://stackoverflow.com/questions/39037705/how-to-use-onpress-on-a-custom-component)

## 页面Focus/Blur事件

```JavaScript
componentDidMount() {
    let that = this;
    this.props.navigation.addListener('willFocus', function () {
        that.setState({focused: true});
    });
    this.props.navigation.addListener('willBlur', function () {
        that.setState({focused: false});
    });
}
```



# 问题

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

## IOS 允许 http 连接

允许所有http请求，`info.plist`中添加：

```xml
<key>NSAppTransportSecurity</key>
<dict>
  <key>NSAllowsArbitraryLoads</key>
  <true/>
</dict>
```

允许特定域名http请求，`info.plist`中添加：

```xml
<key>NSAppTransportSecurity</key>
<dict>
  <key>NSExceptionDomains</key>
  <dict>
    <key>域名.com</key>
    <dict>
      <!--允许子域名:subdomains-->
      <key>NSIncludesSubdomains</key>
      <true/>
      <!--允许App进行不安全的HTTP请求-->
      <key>NSTemporaryExceptionAllowsInsecureHTTPLoads</key>
      <true/>
      <!--在这里声明所支持的 TLS 最低版本-->
      <key>NSTemporaryExceptionMinimumTLSVersion</key>
      <string>TLSv1.1</string>
    </dict>
  </dict>
</dict>
```

## Android 允许Http请求

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
  package="com.loveproject">

    <uses-permission android:name="android.permission.INTERNET" />

    <application
      ...
      android:usesCleartextTraffic="true">
      ...
    </application>
</manifest>
```



## React Native version mismatch

```javascript
console.error: "React Native version mismatch.
JavaScript version: 0.54.2
Native version: 0.55.3

Make sure..."
```

