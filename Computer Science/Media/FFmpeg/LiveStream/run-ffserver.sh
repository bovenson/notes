#!/bin/bash
ffserver -f ffserver.conf 2>1.log 1>&2 & 
ffserver -f ffserver-hls.conf 2>2.log 1>&2 &

ffmpeg -i rtsp://admin:admin12345@192.168.0.104:554/h264/ch1/main/av_stream -map 0 http://localhost:8090/feed.ffm 2>3.log 1>&2 &
ffmpeg -i rtsp://admin:admin12345@192.168.0.104:554/h264/ch1/main/av_stream -map 0 http://localhost:8090/feedm3u8.ffm 2>4.log 1>&2 &

