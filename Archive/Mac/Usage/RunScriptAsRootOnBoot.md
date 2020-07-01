---
title: Mac启动时以root用户运行脚本
tags: Mac, 脚本
author: bovenson
---

[TOC]

# launchd

- 新建要运行的脚本

- 新建XML格式配置文件,后缀为plist, 格式如下:

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
  <plist version="1.0">
      <dict>
          <key>Label</key>
          <string>com.example.app</string> <!-- 程序名, 需要修改 -->
          <key>ProgramArguments</key>
          <array>
              <string>/bin/bash</string> <!-- 使用的shell, 按需修改 -->
              <string>/path/to/script</string> <!-- 脚本路径, 需要修改 -->
          </array>
          <key>RunAtLoad</key>
          <true/>
          <key>KeepAlive</key>
          <false/>
      </dict>
  </plist>
  ```
  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
  <plist version="1.0">
      <dict>
          <key>Label</key>
          <string>com.example.app</string>
          <key>ProgramArguments</key>
          <array>
              <string>/bin/sh</string>
              <string>/path/to/script</string>
          </array>
          <key>RunAtLoad</key>
          <true/>
          <key>KeepAlive</key>
          <false/>
      </dict>
  </plist>
  ```

- 把配置文件放到`/Library/LaunchDeamons/`下

- 加载配置文件

  ```shell
  #### shell
  launchctl load /Library/LaunchDeamons/com.example.app	# 需要更改com.example.app, 根据xml文件
  launchctl start com.example.app	# 这里也需要修改
  ```

- 尝试保证脚本正确运行

  ```xml
  <key>KeepAlive</key>
  <dict>
       <key>SuccessfulExit</key>
       <false/>
  </dict>
  ```

  ​



## 参考

- [参考一](https://zhuanlan.zhihu.com/p/25049770?refer=ChanTalk)
- [参考二](https://apple.stackexchange.com/questions/156288/how-to-get-shell-scripts-to-run-at-startup-on-yosemite/156312#156312)