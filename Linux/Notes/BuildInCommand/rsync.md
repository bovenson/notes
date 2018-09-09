# 简介

- rsync命令是一个远程数据同步工具，可通过LAN/WAN快速同步多台主机间的文件
- rsync使用所谓的“rsync算法”来使本地和远程两个主机之间的文件达到同步，这个算法只传送两个文件的不同部分，而不是每次都整份传送

# 使用

## 本机同步

```shell
### 示例一
bovenson@ThinkCentre:~/Tmp/RsyncTest$ tree .
.
└── a
    └── b
        └── c
            └── test.sh

3 directories, 1 file
bovenson@ThinkCentre:~/Tmp/RsyncTest$ rsync -r a aa
bovenson@ThinkCentre:~/Tmp/RsyncTest$ tree .
.
├── a
│   └── b
│       └── c
│           └── test.sh
└── aa
    └── a
        └── b
            └── c
                └── test.sh

7 directories, 2 files
bovenson@ThinkCentre:~/Tmp/RsyncTest$ rsync -r a/* ab
bovenson@ThinkCentre:~/Tmp/RsyncTest$ tree .
.
├── a
│   └── b
│       └── c
│           └── test.sh
├── aa
│   └── a
│       └── b
│           └── c
│               └── test.sh
└── ab
    └── b
        └── c
            └── test.sh

10 directories, 3 files
bovenson@ThinkCentre:~/Tmp/RsyncTest$ echo "a" > a/b/c/test.sh 
bovenson@ThinkCentre:~/Tmp/RsyncTest$ rsync -r a/* ab
bovenson@ThinkCentre:~/Tmp/RsyncTest$ cat ab/b/c/test.sh 
a
bovenson@ThinkCentre:~/Tmp/RsyncTest$ echo "append info" > a/b/c/test.sh
bovenson@ThinkCentre:~/Tmp/RsyncTest$ rsync -r a/* ab
bovenson@ThinkCentre:~/Tmp/RsyncTest$ cat ab/b/c/test.sh 
append info
bovenson@ThinkCentre:~/Tmp/RsyncTest$ echo "eof" >> a/b/c/test.sh
bovenson@ThinkCentre:~/Tmp/RsyncTest$ rsync -r a/* ab
bovenson@ThinkCentre:~/Tmp/RsyncTest$ cat ab/b/c/test.sh 
append info
eof
bovenson@ThinkCentre:~/Tmp/RsyncTest$ cat a/b/c/test.sh 
append info
eof
```



## 远程同步

### 参考

- [参考一](http://www.cnblogs.com/ggjucheng/p/5474038.html)