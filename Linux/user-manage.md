# 用户管理

## 添加用户

`useradd [-u UID] [-g 初始用户组] [-mM] [-c 说明栏] [-d 主文件夹绝对路径] [-s shell] 用户账户名`

### 参数

| 参数   | 说明                                       |
| ---- | ---------------------------------------- |
| `-u` | UID, 一组数字. 为账户指定特定的UID                   |
| `-g` | 为用户指定用户组名, 该用户组的GID会被放置到`/etc/passwd`的第四个字段内 |
| `-G` | 为用户指定还可以加入的用户组名, 这个参数会修改`/etc/group`内的相关数据 |
| `-M` | 强制不创建用户主文件夹(系统账号默认值)                     |
| `-m` | 强制创建用户主文件夹(普通账号默认值)                      |
| `-c` | `/etc/passwd`第五列的说明内容, 可随意设置             |
| `-d` | 指定某个目录为该用户主文件夹, 而不是使用默认值.                |
| `-r` | 创建一个系统账号, 账号的UID会有限制                     |
| `-s` | 后面接一个shell, 若没有指定则默认是`/bin/bash`         |
| `-e` | 后面接一个日期, 格式为`YYYY-MM-DD`, 此选项可写入shadow第八个字段, 即账号失效日的设置选项 |
| `-f` | 后面shadow的第七个字段选项, 指定密码是否会失效. 0为立刻失效 , -1为永远不失效. |

### 示例

#### 创建sudo用户

```shell
# 示例一
# -G:sudo group / -s:指定shell / -m:创建主目录 / 用户名
sudo useradd -G sudo -s /bin/bash -m bovenson
```

## 为已有用户添加sudo权限

```shell
# 示例一
sudo vim /etc/sudoers
# 添加
yourusername    ALL=(ALL:ALL) ALL

# 示例二
sudo adduser yourusername sudo

# 示例三
sudo usermod -a -G sudo yourusername
```

