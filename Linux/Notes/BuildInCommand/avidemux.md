# avidemux

## 截取

```shell
ffmpeg -ss 00:05:17.18 -i in.mp4 -to 00:06:29.10 -acodec copy -vcodec copy out.mp4
mencoder -ss 01:06:38 -endpos 00:10:00 -oac copy -ovc copy 2003告别经典演唱会CD2\]演唱会\(DVDRip\).avi -o out.mp4
```

