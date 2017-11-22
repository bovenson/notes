#!/bin/bash
ffmpeg -i rtsp://admin:admin12345@192.168.0.104:554/h264/ch1/main/av_stream -map 0 http://localhost:8090/feedmp4.ffm
