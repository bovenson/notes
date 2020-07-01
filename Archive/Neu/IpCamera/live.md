---
title: 网络摄像头直播
---

# 设备

| 设备名称        | 型号            | 备注   |
| ----------- | ------------- | ---- |
| 海康威视红外网络摄像头 | DS-2CD3210-l5 |      |

# RTSP

RTSP流地址:

```
rtsp://[username]:[password]@[ip]:[port]/[codec]/[channel]/[subtype]/av_stream
说明：
username: 用户名。例如admin。
password: 密码。例如12345。
ip: 为设备IP。例如 192.0.0.64。
port: 端口号默认为554，若为默认可不填写。
codec：有h264、MPEG-4、mpeg4这几种。
channel: 通道号，起始为1。例如通道1，则为ch1。
subtype: 码流类型，主码流为main，辅码流为sub。


例如，请求海康摄像机通道1的主码流，Url如下
主码流：
rtsp://admin:12345@192.0.0.64:554/h264/ch1/main/av_stream
rtsp://admin:12345@192.0.0.64:554/MPEG-4/ch1/main/av_stream


子码流：
rtsp://admin:12345@192.0.0.64/mpeg4/ch1/sub/av_stream
rtsp://admin:12345@192.0.0.64/h264/ch1/sub/av_stream
```

测试使用地址:

```
rtsp://admin:admin12345@192.168.0.104:554/h264/ch1/main/av_stream
```

可以使用vlc播放直播摄像头视频流.