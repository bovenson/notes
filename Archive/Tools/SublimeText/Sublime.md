---
title: Sublime Text
tags:
	- Sublime Text
categories:
	- Tools
---

# 格式化代码

## 安装插件

- 打开`Sublime Text 3`

- `Ctrl + Shift + P`

- 输入`install`，选（点击）`Package Control: Install Package`

  ![](./img/001.jpg)

- 输入`prettify`，选（点击）`HTML-CSS-JS Prettify`

  ![](./img/002.jpg)

- 格式化代码快捷键`Ctrl + Shift + H`

**示例**

Format前

![](./img/003.jpg)

Format后

![](./img/004.jpg)

## 不安装插件

仅支持自动缩进。

- 打开`Sublime Text 3`

- 菜单栏 `Preferences -> Key Bindings`

- 右侧用户配置文件添加下面代码

  ```javascript
  [
  	{ "keys": ["f12"], "command": "reindent", "args": {"single_line": false} } 
  ]
  ```

  ![](./img/005.jpg)

- 自动缩进快捷键`F12`

**示例**

缩进前

![](./img/003.jpg)

缩进后

![](./img/006.jpg)

**注** 仅缩进代码

## 使用其他编辑器

### Visual Studio Code

- 快捷键`Ctrl+K Ctrl+F`

### 示例

格式化前

![](./img/007.jpg)

格式化后

![](./img/008.jpg)