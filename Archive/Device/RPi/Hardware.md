# Speaker

```shell
# play audio
$ sudo apt-get install sox libsox-fmt-all
$ play file-name.mp3

# turn volume
$ amixer set Master 50%
$ amixer set Master 10%+

# slelect speaker
$ alsamixer
## F6 to select device

# set volume over 100%
$ sudo apt install pulseaudio
$ pulseaudio --start -D
$ pulseaudio --check -v # check
$ sudo apt-get install pulseaudio-utils
$ pactl set-sink-volume 0 300%
```

# Camera

## Web Stream

```shell
# install
$ sudo apt-get install libjpeg8-dev imagemagick
```

**Reference**

```shell
# setup and test
https://www.raspberrypi.org/documentation/configuration/camera.md

# official documents
https://www.raspberrypi.org/documentation/raspbian/applications/camera.md

# web stream
https://blog.miguelgrinberg.com/post/stream-video-from-the-raspberry-pi-camera-to-web-browsers-even-on-ios-and-android
https://blog.miguelgrinberg.com/post/how-to-build-and-run-mjpg-streamer-on-the-raspberry-pi
## 注释 mjpg-streamer/util.c stats.h
# // #include <linux/stat.h>
# // #include <sys/stat.h>

# generate stream
$ raspistill --nopreview -w 640 -h 480 -q 5 -o /home/pi/test.jpg -tl 100 -t 9999999 -th 0:0:0

# serve stream
$ LD_LIBRARY_PATH=/usr/local/lib mjpg_streamer -i "input_file.so -f /home/pi -n test.jpg" -o "output_http.so -w /usr/local/www"

# watch
http://rpi-ip:8080
```

