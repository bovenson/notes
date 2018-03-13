---
title: Git 知识
tags:
	- Git
categories:
	- Git
---

# fetch

-   `git fetch`命令用于从另一个存储库下载对象和引用。


-   从一个或多个其他存储库中获取分支和/或标签(统称为“引用”)以及完成其历史所必需的对象。
-   远程跟踪分支已更新(Git术语叫做`commit`)，需要将这些更新取回本地，这时就要用到`git fetch`命令。
-   默认情况下，还会获取指向正在获取的历史记录的任何标签; 效果是获取指向您感兴趣的分支的标签。可以使用`--tags`或`--no-tags`选项或通过配置远程`.<name>.tagOpt` 来更改此默认行为。 通过使用显式提取标签的`refspec`，可以获取不指向您感兴趣的分支的标签。
-   `git fetch`可以从单个命名的存储库或URL中获取，也可以从多个存储库中获取，如果给定了`<group>`，并且配置文件中有一个远程`<group>`条目。
-   获取的参考名称以及它们所指向的对象名称被写入到`.git/FETCH_HEAD`中。 此信息可能由脚本或其他git命令使用，如`git-pull`。

## 使用语法

```shell
git fetch [<options>] [<repository> [<refspec>…]]
git fetch [<options>] <group>
git fetch --multiple [<options>] [(<repository> | <group>)…]
git fetch --all [<options>]
```

## 示例

### 更新远程跟踪分支

```shell
git fetch origin
```

上述命令从远程`refs/heads/`命名空间复制所有分支，并将它们存储到本地的`refs/remotes/ origin/`命名空间中，除非使用分支`.<name>.fetch`选项来指定非默认的`refspec`。

### 明确使用refspec

```shell
$ git fetch origin +pu:pu maint:tmp
```

此更新(或根据需要创建)通过从远程存储库的分支(分别)`pu`和`maint`提取来分支本地存储库中的`pu`和`tmp`。

即使没有快进，`pu`分支将被更新，因为它的前缀是加号; `tmp`不会。

### **在远程分支上窥视，无需在本地存储库中配置远程**

```shell
$ git fetch git://git.kernel.org/pub/scm/git/git.git maint
$ git log FETCH_HEAD
```

Shell

第一个命令从 `git://git.kernel.org/pub/scm/git/git.git` 从存储库中获取`maint`分支，第二个命令使用`FETCH_HEAD`来检查具有`git-log`的分支。

### 将某个远程主机的更新

```shell
$ git fetch <远程主机名>
```

要更新所有分支，命令可以简写为：

```shell
$ git fetch
```

上面命令将某个远程主机的更新，全部取回本地。默认情况下，`git fetch`取回所有分支的更新。如果只想取回特定分支的更新，可以指定分支名,如下所示：

```shell
$ git fetch <远程主机名> <分支名>
```

比如，取回`origin`主机的`master`分支。

```shell
$ git fetch origin master
```

所取回的更新，在本地主机上要用”远程主机名/分支名”的形式读取。比如`origin`主机的`master`分支，就可以用`origin/master`读取。

`git branch`命令的`-r`选项，可以用来查看远程分支，`-a`选项查看所有分支。

```shell
$ git branch -r
origin/master

$ git branch -a
* master
  remotes/origin/master
```

上面命令表示，本地主机的当前分支是`master`，远程分支是`origin/master`。

取回远程主机的更新以后，可以在它的基础上，使用git checkout命令创建一个新的分支。

```shell
$ git checkout -b newBrach origin/master
```

上面命令表示，在`origin/master`的基础上，创建一个新分支:*newBrach*。

此外，也可以使用`git merge`命令或者`git rebase`命令，在本地分支上合并远程分支。

```shell
$ git merge origin/master
# 或者
$ git rebase origin/master
```

上面命令表示在当前分支上，合并`origin/master`。

# checkout

`git checkout`命令用于切换分支或恢复工作树文件。

`git checkout`是git最常用的命令之一，同时也是一个很危险的命令，因为这条命令会重写工作区。

## 使用语法

```shell
git checkout [-q] [-f] [-m] [<branch>]
git checkout [-q] [-f] [-m] --detach [<branch>]
git checkout [-q] [-f] [-m] [--detach] <commit>
git checkout [-q] [-f] [-m] [[-b|-B|--orphan] <new_branch>] [<start_point>]
git checkout [-f|--ours|--theirs|-m|--conflict=<style>] [<tree-ish>] [--] <paths>…
git checkout [-p|--patch] [<tree-ish>] [--] [<paths>…]
```

## 描述

更新工作树中的文件以匹配索引或指定树中的版本。如果没有给出路径 - `git checkout`还会更新`HEAD`，将指定的分支设置为当前分支。

## 示例

```shell
$ git checkout master             # 切换分支
$ git checkout master~2 Makefile  # 从另一个提交中取出文件
$ rm -f hello.c
$ git checkout hello.c            # 从索引中恢复hello.c
```

如果想要检出索引中的所有`C`源文件，可以使用以下命令 

```shell
$ git checkout -- '*.c'
```

注意:`*.c`是使用引号的。 文件`hello.c`也将被检出，即使它不再在工作树中，因为文件`globbing`用于匹配索引中的条目(而不是在shell的工作树中)。

如果有一个分支也命名为`hello.c`，这一步将被混淆为一个指令切换到该分支。应该写：

```shell
$ git checkout -- hello.c
```

在错误的分支工作后，想切换到正确的分支，则使用：

```shell
$ git checkout mytopic
```

但是，您的“错误”分支和正确的“`mytopic`”分支可能会在在本地修改的文件中有所不同，在这种情况下，上述检出将会失败：

```shell
$ git checkout mytopic
error: You have local changes to 'frotz'; not switching branches.
```

可以将`-m`标志赋给命令，这将尝试三路合并：

```shell
$ git checkout -m mytopic
Auto-merging frotz
```

在这种三路合并之后，本地的修改没有在索引文件中注册，所以`git diff`会显示从新分支的提示之后所做的更改。

当使用`-m`选项切换分支时发生合并冲突时，会看到如下所示：

```shell
$ git checkout -m mytopic
Auto-merging frotz
ERROR: Merge conflict in frotz
fatal: merge program failed
```

此时，`git diff`会显示上一个示例中干净合并的更改以及冲突文件中的更改。 编辑并解决冲突，并用常规方式用`git add`来标记它：

```shell
$ edit frotz # 编辑 frotz 文件中内容，然后重新添加
$ git add frotz
```

**其它示例**

`git checkout`的主要功能就是迁出一个分支的特定版本。默认是迁出分支的HEAD版本
一此用法示例：

```shell
$ git checkout master     #//取出master版本的head。
$ git checkout tag_name    #//在当前分支上 取出 tag_name 的版本
$ git checkout  master file_name  #//放弃当前对文件file_name的修改
$ git checkout  commit_id file_name  #//取文件file_name的 在commit_id是的版本。commit_id为 git commit 时的sha值。

$ git checkout -b dev/1.5.4 origin/dev/1.5.4

# 从远程dev/1.5.4分支取得到本地分支/dev/1.5.4
$ git checkout -- hello.rb
#这条命令把hello.rb从HEAD中签出.
$ git checkout .
#这条命令把 当前目录所有修改的文件 从HEAD中签出并且把它恢复成未修改时的样子.
#注意：在使用 git checkout 时，如果其对应的文件被修改过，那么该修改会被覆盖掉。
```

# 参考

-   [易百教程](https://www.yiibai.com/git/git_checkout.html)

