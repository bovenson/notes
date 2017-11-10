---
title: ffmpeg使用
tags: ffmpeg
---

# 参数

**主要参数：**

- `-i` 设定输入流
- `-f` 设定输出格式
- `-ss` 开始时间

**视频参数：**

- `-b` 设定视频流量，默认为200Kbit/s
- `-r` 设定帧速率，默认为25
- `-s` 设定画面的宽与高
- `-aspect` 设定画面的比例
- `-vn` 不处理视频
- `-vcodec` 设定视频编解码器，未设定时则使用与输入流相同的编解码器

**音频参数：**

- `-ar` 设定采样率
- `-ac` 设定声音的Channel数
- `-acodec` 设定声音编解码器，未设定时则使用与输入流相同的编解码器
- `-an` 不处理音频

# 示例

```shell
#### 分离音视频流
ffmpeg -i input_file -vcodec copy -an output_file_video　　//分离视频流
ffmpeg -i input_file -acodec copy -vn output_file_audio　　//分离音频流

#### 视频解复用
ffmpeg –i test.mp4 –vcodec copy –an –f m4v test.264
ffmpeg –i test.avi –vcodec copy –an –f m4v test.264

#### 视频转码
# 转码为码流原始文件
ffmpeg –i test.mp4 –vcodec h264 –s 352*278 –an –f m4v test.264          
# 转码为码流原始文件
ffmpeg –i test.mp4 –vcodec h264 –bf 0 –g 25 –s 352*278 –an –f m4v test.264 
# 转码为封装文件 
ffmpeg –i test.avi -vcodec mpeg4 –vtag xvid –qsame test_xvid.avi    
# -bf B帧数目控制，-g 关键帧间隔控制，-s 分辨率控制

#### 视频封装
ffmpeg –i video_file –i audio_file –vcodec copy –acodec copy output_file

#### 视频裁剪
# 提取图片
ffmpeg –i test.avi –r 1 –f image2 image-%3d.jpeg        
# 剪切视频
ffmpeg -ss 0:1:30 -t 0:0:20 -i input.avi -vcodec copy -acodec copy output.avi  
# -r 提取图像的频率，-ss 开始时间，-t 持续时间

#### 视频录制
ffmpeg –i rtsp://192.168.3.205:5555/test –vcodec copy out.avi

#### YUV序列播放
ffplay -f rawvideo -video_size 1920x1080 input.yuv

#### YUV转AVI
ffmpeg –s w*h –pix_fmt yuv420p –i input.yuv –vcodec mpeg4 output.avi
```



# 参考

- [参考一](https://www.cnblogs.com/dwdxdy/p/3240167.html)