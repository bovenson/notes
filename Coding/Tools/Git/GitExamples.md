---
title: Git常用操作
tags: Git
---

[TOC]

# 移除某文件的版本控制

还没有加到版本控制中：

- 还没有`git  add`
  - 在`.gitignore`中添加
- 已经git add
  - 先`git  rm  -r  --cached`文件
  - 在`.gitignore`中添加

已经加到版本控制中

- 先`git  rm  -r  --cached`文件  
- 在`.gitignore`中添加
- 最后`git commit -m`提交`.gitignore`


# 查看历史提交

```shell
bovenson@MBP:~/Git/notes$ git log
commit 27a5edf3be21260ae58db052daece60c43f2fd03 (HEAD -> master, origin/master, origin/HEAD, mayun/master)
Author: bovenson <szhkai@qq.com>
Date:   Fri Dec 1 21:58:06 2017 +0800

    auto commit

commit 95bd6dd8068b02cb64fe0692630b79f21cd35ee6
Author: bovenson <szhkai@qq.com>
Date:   Fri Dec 1 09:05:45 2017 +0800

    auto commit

commit f4d00e5f9f56064422976f3bad3ce5c72d4f3e6d
Author: bovenson <szhkai@qq.com>
Date:   Thu Nov 30 18:19:21 2017 +0800

    auto commit

commit deebf122267a72ad889cedbacb8dee5efba15a2e
Author: bovenson <szhkai@qq.com>
Date:   Wed Nov 29 20:04:40 2017 +0800

    auto commit
...
```

# 从历史版本检出某个文件

```shell
bovenson@MBP:~/Git/notes$ git checkout d93d872 Git/git-common-operations.md ./Git/git-common-operations.md	# 指定目标目录
bovenson@MBP:~/Git/notes$ git checkout d93d872 Git/git-common-operations.md	# 不指定目标目录
```



```shell
bovenson@MBP:~/Tmp$ mkdir git
bovenson@MBP:~/Tmp$ cd git/
bovenson@MBP:~/Tmp/git$ ls
bovenson@MBP:~/Tmp/git$ touch a
bovenson@MBP:~/Tmp/git$ touch b
bovenson@MBP:~/Tmp/git$ git init
Initialized empty Git repository in /Users/bovenson/Tmp/git/.git/
bovenson@MBP:~/Tmp/git$ git add * && git commit -m "first commit"
[master (root-commit) 74e2a0f] first commit
 2 files changed, 0 insertions(+), 0 deletions(-)
 create mode 100644 a
 create mode 100644 b
bovenson@MBP:~/Tmp/git$ git status
On branch master
nothing to commit, working tree clean
bovenson@MBP:~/Tmp/git$ rm a
bovenson@MBP:~/Tmp/git$ ls
b
bovenson@MBP:~/Tmp/git$ git add * && git commit -m "first commit"
On branch master
Changes not staged for commit:
	deleted:    a

no changes added to commit
bovenson@MBP:~/Tmp/git$ ls
b
bovenson@MBP:~/Tmp/git$ git log
commit 74e2a0fbd6a91ebeee79cbc5e286c9591a478e25 (HEAD -> master)
Author: bovenson <szhkai@qq.com>
Date:   Sun Dec 3 18:40:46 2017 +0800

    first commit
bovenson@MBP:~/Tmp/git$ git checkout 74e2a0fbd6a91ebeee79cbc5e286c9591a478e25 a ./
bovenson@MBP:~/Tmp/git$ ls
a	b
bovenson@MBP:~/Tmp/git$ 
```

# 从历史版本检出某个文件夹

```shell
bovenson@MBP:~/Git/notes$ git checkout 95bd6dd8068b02cb64fe0692630b79f21cd35ee6 -- Other/MF/
```

如果提示找不到路径, 注意执行命令的当前路径,  如果给出的相对于相对于仓库根目录的相对路径, 要在仓库根目录下执行命令.

# 查看两个版本之间的差别

```shell
bovenson@MBP:~/Tmp/git$ git diff 3928026c7 63c2b556
diff --git a/d b/d
deleted file mode 100644
index e69de29..0000000
```

# 查看两个版本某个文件/文件夹的差异

```shell
bovenson@MBP:~/Tmp/git$ git diff e56af9ffa52a67b754e5c5759afd6972e1cb0f69 daa020c8b813097305efd476d72d3e377fe7b18a
diff --git a/a b/a
index ce01362..e69de29 100644
--- a/a
+++ b/a
@@ -1 +0,0 @@
-hello
```

# 回退到某一版本

```shell
$ git reset --hard HEAD^         回退到上个版本
$ git reset --hard HEAD~3        回退到前3次提交之前，以此类推，回退到n次提交之前
$ git reset --hard commit_id     退到/进到 指定commit的sha码
```

# 删除某一commit

```shell
git log	# 获取commit信息 
git rebase -i commit_id	# commit-id 为要删除的commit的下一个commit号 
# 编辑文件，将要删除的commit之前的单词改为drop 
# 保存文件退出大功告成 
git log	# 查看
```

# 检出远程分支为本地分支

```shell
$ git chekcout -b local_branch_name remotes/origin_or_something/branch_name
```

# Unstage all staged file

```shell
## unstage all file
$ git reset
```

# Remove file or directory from repository

```shell
$ git rm --cached related-path/to/file
$ git rm --cached -r related-path/to/directory
```

# 删除文件

```shell
# tracked file: reset; untracked file: clean.

# 删除更改
$ git reset --hard

# [当前目录] 删除所有没有跟踪的文件(新文件且未被ignored) 
$ git clean -f -d
$ git clean -fd

# [当前目录] 删除所有没有跟踪的文件, 包括被ignored文件
$ git clean -f -x -d
$ git clean -fxd

# [当前仓库] 删除所有没有跟踪的文件, 包括被ignored文件
$ git clean -fxd :/
```

# 配置当前工程用户信息

```shell
$ git config user.name "*" 
$ git config user.email "*" 
$ cat .git/config
```

