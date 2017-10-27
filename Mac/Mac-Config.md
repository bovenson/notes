---
title: mac配置
tags: mac, 配置
---

# PATH

Mac系统的环境变量，加载顺序为：
`/etc/profile /etc/paths ~/.bash_profile ~/.bash_login ~/.profile ~/.bashrc····`

# 应用

## Vim

# 重置Launchpad

如果Launchpad出现问题或者想重新排列可以:

```shell
defaults write com.apple.dock ResetLaunchPad -bool true; killall Dock
```

**注意:** 会把原来的排列打乱