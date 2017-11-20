#!/bin/bash
ffmpeg -i rtsp://admin:admin12345@192.168.0.104:554/h264/ch1/main/av_stream -vcodec mpeg1video -s 480x270 -f hls http://localhost:8090/hls.ffm
