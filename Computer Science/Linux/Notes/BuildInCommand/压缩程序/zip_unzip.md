[TOC]

# zip&unzip

## unzip:解压

`unzip`: 查看,检测和解压zip文件.

### 命令格式

`unzip [-Z] [-cflptTuvz[abjnoqsCDKLMUVWX$/:^]] file[.zip] [file(s) ...]  [-x xfile(s) ...] [-d exdir]`

### 示例

#### `unzip letters`

```shell
unzip letters
# 该命令: 解压letters.zip文件到当前文件夹, 如果有必要会创建子文件夹
# 示例
bovenson@vmware:~/workspace$ tree -a
.
├── git
└── tmp.zip

1 directory, 1 file
bovenson@vmware:~/workspace$ unzip tmp
Archive:  tmp.zip
   creating: tmp/
   creating: tmp/b/
 extracting: tmp/b/b
 extracting: tmp/b/.b
 extracting: tmp/.a
 extracting: tmp/a
 extracting: tmp/tmp.zip
 extracting: tmp/b.zip
 extracting: tmp/a.zip
bovenson@vmware:~/workspace$ tree -a
.
├── git
├── tmp
│   ├── a
│   ├── .a
│   ├── a.zip
│   ├── b
│   │   ├── b
│   │   └── .b
│   ├── b.zip
│   └── tmp.zip
└── tmp.zip

3 directories, 8 files

```

#### `仅解压文件`

```shell
unzip -j letters
# 该命令: 解压letters.zip中所有的文件(不包括文件夹)到当前文件夹
# 示例
bovenson@vmware:~/workspace$ tree -a
.
├── git
└── tmp.zip

1 directory, 1 file
bovenson@vmware:~/workspace$ unzip -j tmp
Archive:  tmp.zip
 extracting: b
 extracting: .b
 extracting: .a
 extracting: a
replace tmp.zip? [y]es, [n]o, [A]ll, [N]one, [r]ename: n
 extracting: b.zip
 extracting: a.zip
bovenson@vmware:~/workspace$ tree -a
.
├── a
├── .a
├── a.zip
├── b
├── .b
├── b.zip
├── git
└── tmp.zip

1 directory, 7 files

```

#### `解压指定文件到指定文件夹`

```shell
unzip tmp.zip '*.zip' -d ./git
# 该命令: 解压指定文件到指定文件夹
# 示例
bovenson@vmware:~/workspace$ tree -a
.
├── git
└── tmp.zip

1 directory, 1 file
bovenson@vmware:~/workspace$ unzip tmp.zip '*.zip' -d ./git
Archive:  tmp.zip
 extracting: ./git/tmp/tmp.zip
 extracting: ./git/tmp/b.zip
 extracting: ./git/tmp/a.zip
bovenson@vmware:~/workspace$ tree -a
.
├── git
│   └── tmp
│       ├── a.zip
│       ├── b.zip
│       └── tmp.zip
└── tmp.zip

2 directories, 4 files
```

## unzip:检测zip文件

#### `unzip -t letters`

```shell
unzip -t letters
# 该命令: 检测letters.zip文件, 并输出详细的检测报告
# 示例
bovenson@vmware:~/workspace$ unzip -t tmp.zip
Archive:  tmp.zip
    testing: tmp/                     OK
    testing: tmp/b/                   OK
    testing: tmp/b/b                  OK
    testing: tmp/b/.b                 OK
    testing: tmp/.a                   OK
    testing: tmp/a                    OK
    testing: tmp/tmp.zip              OK
    testing: tmp/b.zip                OK
    testing: tmp/a.zip                OK
No errors detected in compressed data of tmp.zip.
```

#### `unzip -tq letters`

```shell
unzip -tq letters
# 该命令: 检测letters.zip文件, 只输出检测结果概括(Ok or not OK)
# 示例
bovenson@vmware:~/workspace$ unzip -tq tmp.zip
No errors detected in compressed data of tmp.zip.
```

## unzip: 查看

### `unzip -l file.zip`

```shell
unzip -l file.zip
# 该命令: 查看压缩文件内文件详情
# 示例:
bovenson@vmware:~/workspace$ unzip -l tmp
Archive:  tmp.zip
  Length      Date    Time    Name
---------  ---------- -----   ----
        0  2017-03-17 16:22   tmp/
        0  2017-03-17 15:49   tmp/b/
        0  2017-03-17 15:49   tmp/b/b
        0  2017-03-17 15:49   tmp/b/.b
        0  2017-03-17 15:48   tmp/.a
        0  2017-03-17 15:48   tmp/a
      840  2017-03-17 15:56   tmp/tmp.zip
      970  2017-03-17 16:18   tmp/b.zip
      550  2017-03-17 15:50   tmp/a.zip
---------                     -------
     2360                     9 files
```

`unzip -v file.zip`

```shell
unzip -v file.zip
# 该命令: 查看压缩文件内文件详情(更详细)
# 示例:
bovenson@vmware:~/workspace$ unzip -v tmp.zip
Archive:  tmp.zip
 Length   Method    Size  Cmpr    Date    Time   CRC-32   Name
--------  ------  ------- ---- ---------- ----- --------  ----
       0  Stored        0   0% 2017-03-17 16:22 00000000  tmp/
       0  Stored        0   0% 2017-03-17 15:49 00000000  tmp/b/
       0  Stored        0   0% 2017-03-17 15:49 00000000  tmp/b/b
       0  Stored        0   0% 2017-03-17 15:49 00000000  tmp/b/.b
       0  Stored        0   0% 2017-03-17 15:48 00000000  tmp/.a
       0  Stored        0   0% 2017-03-17 15:48 00000000  tmp/a
     840  Stored      840   0% 2017-03-17 15:56 c53171a8  tmp/tmp.zip
     970  Stored      970   0% 2017-03-17 16:18 b29e9c36  tmp/b.zip
     550  Stored      550   0% 2017-03-17 15:50 ab81376e  tmp/a.zip
--------          -------  ---                            -------
    2360             2360   0%                            9 files
```



## zip:创建

```shell
zip stuff *
# 把当前目录下所有非'.'开头的文件打包为 stuff.zip 文件, .zip自动追加, 除非名称包含.字符
# 想要打包以'.'开头的所有文件可以使用:
# 不能打包子文件夹下内容
zip stuff * .*

# 使用选项参数[r]打包一个完整的文件夹(包括'.'开头文件和子文件夹内容)
zip -r foo foo
# 第一个foo为生成的zip文件名, 如果名称中包含'.', 则不会追加'.zip'后缀, 否则会自动追加'.zip'后缀

# 只打包文件, 而不打包目录
zip -j foo foo/*

# 如果磁盘空间不够用, 可以先打包一个然后使用[m]选项一个个得添加到压缩文件并将该文件删除
# 比如有三个文件(夹)tom, dick, harry, 可以这样:
zip -rm foo foo/tom
zip -rm foo foo/dick
zip -rm foo foo/harry
# [rm]选项, 在递归添加后将该文件删除

# 使用[s]选项来创建分卷压缩文件
# 单位有k(KB),m(MB),g(GB),t(TB)
zip -s 2g -r split.zip foo
# 如果一个文件大小为5G, 且压缩后总大小也是5G(仅示例), 则会生成3个文件:2g的split.z01, 2g的split.z02 和 1g的split.zip

zip foo.zip c --out bar.zip
# 该命令: 读取foo.zip(分卷压缩文件也可以)并添加文件c, 生成新的压缩文件 bar.zip
```

```shell
# [x] 选项: 排除一些文件
zip -r tmp.zip tmp -x *.zip
# 该命令: 打包当前路径下文件/文件夹tmp, 但会跳过所有以'.zip'结尾的文件
# 示例:
bovenson@vmware:~/workspace$ tree
.
├── c
├── git
└── tmp
    ├── a
    ├── a.zip
    ├── b
    │   └── b
    ├── b.zip
    └── tmp.zip

3 directories, 6 files
bovenson@vmware:~/workspace$ zip -r tmp tmp -x *.zip
  adding: tmp/ (stored 0%)
  adding: tmp/b/ (stored 0%)
  adding: tmp/b/b (stored 0%)
  adding: tmp/b/.b (stored 0%)
  adding: tmp/.a (stored 0%)
  adding: tmp/a (stored 0%)
bovenson@vmware:~/workspace$ zip -r tmp tmp
updating: tmp/ (stored 0%)
updating: tmp/b/ (stored 0%)
updating: tmp/b/b (stored 0%)
updating: tmp/b/.b (stored 0%)
updating: tmp/.a (stored 0%)
updating: tmp/a (stored 0%)
  adding: tmp/tmp.zip (stored 0%)
  adding: tmp/b.zip (stored 0%)
  adding: tmp/a.zip (stored 0%)
```



