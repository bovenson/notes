# wget

## 应用

### 下载整站

```shell
有时间看到别人网站的页面比较漂亮，就想给扒皮下来，学习学习。分享一个我常用网站扒皮命令wget
这个命令可以以递归的方式下载整站，并可以将下载的页面中的链接转换为本地链接。
wget加上参数之后，即可成为相当强大的下载工具。
wget命令详解
wget -r -p -np -k http://xxx.com/xxx
-r, --recursive（递归） specify recursive download.（指定递归下载）
-k, --convert-links（转换链接） make links in downloaded HTML point to local files.（将下载的HTML页面中的链接转换为相对链接即本地链接）
-p, --page-requisites（页面必需元素） get all images, etc. needed to display HTML page.（下载所有的图片等页面显示所需的内容）
-np, --no-parent（不追溯至父级） don't ascend to the parent directory.
另外断点续传用-nc参数 日志 用-o参数
拿我自己的网站扒皮试一下吧
执行 wget -r -p -np -k https://xxx.com/ 命令
```

### 示例

```shell

```



**参考**

- [wget递归下载整个网站](http://www.cnblogs.com/freespider/p/5315729.html)