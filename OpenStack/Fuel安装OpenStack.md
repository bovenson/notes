# Fuel离线安装OpenStack

## 准备

| 文件                        | 说明            | 下载地址 |
| ------------------------- | ------------- | ---- |
| MirantisOpenStack-9.0.iso | MOS           |      |
| bootstrap.zip             | MOS bootstrap |      |
| mirrors.zip               | MOS 本地源       |      |

## 安装`bootstrap`

- 将`bootstrap.zip`解压并复制到`fuel master`目录`/var/www/nailgun/`下 (覆盖)

- 进入`fuel master`目录`/var/www/nailgun/`

- 将`active_bootstrap`下所有文件移动到`bootstrap_stub`下

  `mv active_bootstrap/* ./bootstrap_stub`

- 通过命令激活

  `fuel-bootstrap activate d01c72e6-83f4-4a19-bb86-6085e40416e6`

## 上传本地源包

- 解压`mirrors.zip`到`fuel master` 目录 `/var/www/nailgun`
- 终端运行命令`fuel-createmirror`