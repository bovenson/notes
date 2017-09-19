# vsftpd

## 启动和停止

```shell
# 启动
service vsftpd start
/etc/init.d/vsftpd start 

# 停止
service vsftpd stop  
/etc/init.d/vsftpd stop

# 重启
service vsftpd restart
/etc/init.d/vsftpd restart

# 查看状态
service vsftpd status  
```

## 配置文件

| 文件                          | 说明                                       |
| --------------------------- | ---------------------------------------- |
| /etc/vsftpd/vsftpd.conf     | 主配置文件                                    |
| /usr/sbin/vsftpd            | Vsftpd的主程序                               |
| /etc/rc.d/init.d/vsftpd     | 启动脚本                                     |
| /etc/pam.d/vsftpd           | PAM认证文件（此文件中file=/etc/vsftpd/ftpusers字段，指明阻止访问的用户来自/etc/vsftpd/ftpusers文件中的用户） |
| /etc/vsftpd/ftpusers        | 禁止使用vsftpd的用户列表文件。记录不允许访问FTP服务器的用户名单，管理员可以把一些对系统安全有威胁的用户账号记录在此文件中，以免用户从FTP登录后获得大于上传下载操作的权利，而对系统造成损坏。（注意：linux-4中此文件在/etc/目录下） |
| /etc/vsftpd/user_list       | 禁止或允许使用vsftpd的用户列表文件。这个文件中指定的用户缺省情况（即在/etc/vsftpd/vsftpd.conf中设置userlist_deny=YES）下也不能访问FTP服务器，在设置了userlist_deny=NO时,仅允许user_list中指定的用户访问FTP服务器。（注意：linux-4中此文件在/etc/目录下） |
| /var/ftp                    | 匿名用户主目录；本地用户主目录为：/home/用户主目录，即登录后进入自己家目录 |
| /var/ftp/pub                | 匿名用户的下载目录，此目录需赋权根chmod 1777 pub（1为特殊权限，使上载后无法删除） |
| /etc/logrotate.d/vsftpd.log | Vsftpd的日志文件                              |

### vsftpd.conf

```shell
# 是否允许匿名登录FTP服务器，默认设置为YES允许
# 用户可使用用户名ftp或anonymous进行ftp登录，口令为用户的E-mail地址。
# 如不允许匿名访问则设置为NO
anonymous_enable=YES
# 是否允许本地用户(即linux系统中的用户帐号)登录FTP服务器，默认设置为YES允许
# 本地用户登录后会进入用户主目录，而匿名用户登录后进入匿名用户的下载目录/var/ftp/pub
# 若只允许匿名用户访问，前面加上#注释掉即可阻止本地用户访问FTP服务器
local_enable=YES
# 是否允许本地用户对FTP服务器文件具有写权限，默认设置为YES允许
write_enable=YES 
# 掩码，本地用户默认掩码为077
# 你可以设置本地用户的文件掩码为缺省022，也可根据个人喜好将其设置为其他值
#local_umask=022
# 是否允许匿名用户上传文件，须将全局的write_enable=YES。默认为YES
#anon_upload_enable=YES
# 是否允许匿名用户创建新文件夹
#anon_mkdir_write_enable=YES 
# 是否激活目录欢迎信息功能
# 当用户用CMD模式首次访问服务器上某个目录时，FTP服务器将显示欢迎信息
# 默认情况下，欢迎信息是通过该目录下的.message文件获得的
# 此文件保存自定义的欢迎信息，由用户自己建立
#dirmessage_enable=YES
# 是否让系统自动维护上传和下载的日志文件
# 默认情况该日志文件为/var/log/vsftpd.log,也可以通过下面的xferlog_file选项对其进行设定
# 默认值为NO
xferlog_enable=YES
# Make sure PORT transfer connections originate from port 20 (ftp-data).
# 是否设定FTP服务器将启用FTP数据端口的连接请求
# ftp-data数据传输，21为连接控制端口
connect_from_port_20=YES
# 设定是否允许改变上传文件的属主，与下面一个设定项配合使用
# 注意，不推荐使用root用户上传文件
#chown_uploads=YES
# 设置想要改变的上传文件的属主，如果需要，则输入一个系统用户名
# 可以把上传的文件都改成root属主。whoever：任何人
#chown_username=whoever
# 设定系统维护记录FTP服务器上传和下载情况的日志文件
# /var/log/vsftpd.log是默认的，也可以另设其它
#xferlog_file=/var/log/vsftpd.log
# 是否以标准xferlog的格式书写传输日志文件
# 默认为/var/log/xferlog，也可以通过xferlog_file选项对其进行设定
# 默认值为NO
#xferlog_std_format=YES
# 以下是附加配置，添加相应的选项将启用相应的设置
# 是否生成两个相似的日志文件
# 默认在/var/log/xferlog和/var/log/vsftpd.log目录下
# 前者是wu_ftpd类型的传输日志，可以利用标准日志工具对其进行分析；后者是vsftpd类型的日志
#dual_log_enable
# 是否将原本输出到/var/log/vsftpd.log中的日志，输出到系统日志
#syslog_enable
# 设置数据传输中断间隔时间，此语句表示空闲的用户会话中断时间为600秒
# 即当数据传输结束后，用户连接FTP服务器的时间不应超过600秒。可以根据实际情况对该值进行修改
#idle_session_timeout=600
# 设置数据连接超时时间，该语句表示数据连接超时时间为120秒，可根据实际情况对其个修改
#data_connection_timeout=120
# 运行vsftpd需要的非特权系统用户，缺省是nobody
#nopriv_user=ftpsecure
# 是否识别异步ABOR请求。
# 如果FTP client会下达“async ABOR”这个指令时，这个设定才需要启用
# 而一般此设定并不安全，所以通常将其取消
#async_abor_enable=YES
# 是否以ASCII方式传输数据。默认情况下，服务器会忽略ASCII方式的请求。
# 启用此选项将允许服务器以ASCII方式传输数据
# 不过，这样可能会导致由"SIZE /big/file"方式引起的DoS攻击
#ascii_upload_enable=YES
#ascii_download_enable=YES
# 登录FTP服务器时显示的欢迎信息
# 如有需要，可在更改目录欢迎信息的目录下创建名为.message的文件，并写入欢迎信息保存后
#ftpd_banner=Welcome to blah FTP service.
# 黑名单设置。如果很讨厌某些email address，就可以使用此设定来取消他的登录权限
# 可以将某些特殊的email address抵挡住。
#deny_email_enable=YES
# 当上面的deny_email_enable=YES时，可以利用这个设定项来规定哪些邮件地址不可登录vsftpd服务器
# 此文件需用户自己创建，一行一个email address即可
#banned_email_file=/etc/vsftpd/banned_emails
# 用户登录FTP服务器后是否具有访问自己目录以外的其他文件的权限
# 设置为YES时，用户被锁定在自己的home目录中，vsftpd将在下面chroot_list_file选项值的位置寻找chroot_list文件
# 必须与下面的设置项配合
#chroot_list_enable=YES
# 被列入此文件的用户，在登录后将不能切换到自己目录以外的其他目录
# 从而有利于FTP服务器的安全管理和隐私保护。此文件需自己建立
#chroot_list_file=/etc/vsftpd/chroot_list
# 是否允许递归查询。默认为关闭，以防止远程用户造成过量的I/O
#ls_recurse_enable=YES
# 是否允许监听。
# 如果设置为YES，则vsftpd将以独立模式运行，由vsftpd自己监听和处理IPv4端口的连接请求
listen=YES
# 设定是否支持IPV6。如要同时监听IPv4和IPv6端口，
# 则必须运行两套vsftpd，采用两套配置文件
# 同时确保其中有一个监听选项是被注释掉的
#listen_ipv6=YES
# 设置PAM外挂模块提供的认证服务所使用的配置文件名，即/etc/pam.d/vsftpd文件
# 此文件中file=/etc/vsftpd/ftpusers字段，说明了PAM模块能抵挡的帐号内容来自文件/etc/vsftpd/ftpusers中
#pam_service_name=vsftpd
# 是否允许ftpusers文件中的用户登录FTP服务器，默认为NO
# 若此项设为YES，则user_list文件中的用户允许登录FTP服务器
# 而如果同时设置了userlist_deny=YES，则user_list文件中的用户将不允许登录FTP服务器，甚至连输入密码提示信息都没有
#userlist_enable=YES/NO
# 设置是否阻扯user_list文件中的用户登录FTP服务器，默认为YES
#userlist_deny=YES/NO
# 是否使用tcp_wrappers作为主机访问控制方式。
# tcp_wrappers可以实现linux系统中网络服务的基于主机地址的访问控制
# 在/etc目录中的hosts.allow和hosts.deny两个文件用于设置tcp_wrappers的访问控制
# 前者设置允许访问记录，后者设置拒绝访问记录。
# 如想限制某些主机对FTP服务器192.168.57.2的匿名访问，编缉/etc/hosts.allow文件，如在下面增加两行命令：
# vsftpd:192.168.57.1:DENY 和vsftpd:192.168.57.9:DENY
# 表明限制IP为192.168.57.1/192.168.57.9主机访问IP为192.168.57.2的FTP服务器
# 此时FTP服务器虽可以PING通，但无法连接
tcp_wrappers=YES
```

#### 限制最大连接数和传输速率

在FTP服务器的管理中，无论对本地用户还是匿名用户，对于FTP服务器资源的使用都需要进行控控制，避免由于负担过大造成FTP服务器运行异常，可以添加以下配置项对FTP客户机使用FTP服务器资源进行控制：

-   max_client设置项 用于设置FTP服务器所允许的最大客户端连接数，值为0时表示不限制。例如max_client=100表示FTP服务器的所有客户端最大连接数不超过100个。
-   max_per_ip设置项 用于设置对于同一IP地址允许的最大客户端连接数，值为0时表示不限制。例如max_per_ip=5表示同一IP地址的FTP客户机与FTP服务器建立的最大连接数不超过5个。
-   local_max_rate设置项 用于设置本地用户的最大传输速率，单位为B/s，值为0时表示不限制。例如local_max_rate=500000表示FTP服务器的本地用户最大传输速率设置为500KB/s.
-   anon_max_rate设置项 用于设置匿名用户的最大传输速率，单位为B/s,值为0表示不限制。例如ano_max_rate=200000，表示FTP服务器的匿名用户最大传输速率设置为200KB/s.

#### **指定用户的权限设置**

vsftpd.user_list文件需要与vsftpd.conf文件中的配置项结合来实现对于vsftpd.user_list文件中指定用户账号的访问控制：

#### **设置禁止登录的用户账号**

当vsftpd.conf配置文件中包括以下设置时，vsftpd.user_list文件中的用户账号被禁止进行FTP登录：

```shell
userlist_enable=YES
userlist_deny=YES
```

userlist_enable设置项设置使用vsftpd.user_list文件，userlist_deny设置为YES表示vsftpd.user_list文件用于设置禁止的用户账号。

#### **设置只允许登录的用户账号**

当vsftpd.conf配置文件中包括以下设置时，只有vsftpd.user_list文件中的用户账号能够进行FTP登录：

```shell
userlist_enable=YES
userlist_deny=NO 
```

userlist_enable设置项设置使用vsftpd.user_list文件，userlist _deny设置为NO表示vsftpd.usre_list文件用于设置只允许登录的用户账号，文件中未包括的用户账号被禁止FTP登录。

userlist_deny和userlist_enable选项限制用户登录FTP服务器（使用userlist_deny选项和user_list文件一起能有效阻止root,apache,www等系统用户登录FTP服务器，从而保证FTP服务器的分级安全性）。以下是两个选项的具体表现形式和两种搭配使用方式的效果：

| 设置项                                     | 说明                                       |
| --------------------------------------- | ---------------------------------------- |
| Userlist_enable=YES                     | Ftpusers中用户允许访问User_list中用户允许访问          |
| Userlist_enable=NO                      | Ftpusers中用户禁止访问User_list中用户允许访问          |
| Userlist_deny=YES                       | Ftpusers中用户禁止访问（登录时可以看到密码输入提示，但仍无法访问）user_list 中用户禁止访问 |
| Userlist_deny=NO                        | ftpusers中用户禁止访问user_list中用户允许访问          |
| Userlist_enable=YES 并且Userlist_deny=YES | Ftpusers中用户禁止访问User_list中用户禁止访问（登录时不会出现密码提示，直接被服务器拒绝） |
| Userlist_enable=YES 并且Userlist_deny=NO  | Ftpusers中用户禁止访问User_list中用户允许访问          |

#### **修改默认端口**

默认FTP服务器端口号是21，出于安全目的，有时需修改默认端口号，修改/etc/vsftpd/vsftpd.conf，添加语句(例)：

```shell
listen_port=4449 
```

语句指定了修改后FTP服务器的端口号，应尽量大于4000。修改后访问:

`#ftp 192.168.57.2 4449`

注意这里需加上正确的端口号了，否则不能正常连接。

#### 设置用户组

有关FTP用户和用户组的重要性，我们在之前[介绍vsftpd的时候](http://os.51cto.com/art/201008/221714_1.htm)便已经提到过。这里主要是简单的说明用户组的技术实现，至于具体如何应用，还是具体需求具体对待

```shell
#mkdir -p /home/try  递归创建新目录
#groupadd try        新建组
#useradd -g try -d /home/try try1 新建用户try1并指定家目录和属组
#useradd -g try -d /home/try try2 新建用户try2并指定家目录和属组
#useradd -g try -d /home/try try3 新建用户try3并指定家目录和属组
#passwd try1  为新用户设密码
#passwd try2  为新用户设密码
#passwd try3  为新用户设密码
#chown try1 /home/try 设置目录属主为用户try1
#chown .try /home/try 设置目录属组为组try
#chmod 750 /home/try  设置目录访问权限try1为读，写，执行；try2，try3为读，执行
```

由于本地用户登录FTP服务器后进入自己主目录，而try1,try2 try3对主目录/home/try分配的权限不同，所以通过FTP访问的权限也不同，try1访问权限为：上传，下载，建目录；try2，try3访问权限为下载，浏览，不能建目录和上传。实现了群组中用户不同访问级别，加强了对FTP服务器的分级安全管理。

#### **连接超时**

配置空闲的用户会话的中断时间：如下配置将在用户会话空闲5分钟后被中断，以释放服务器的资源

```shell
Idle_session_timeout=300
```

配置空闲的数据连接的中断时间：如下配置将在数据空闲连接1分钟后被中断，同样也是为了释放服务器的资源

```shell
Data_connection_timeout=60
```

配置客户端空闲时的自动中断和激活连接的时间：如下配置将使客户端空闲1分钟后自动中断连接，并在30秒后自动激活连接

```shell
Accept_timeout=60
Connect_timeout=30
```

##  虚拟用户使用vsftpd服务器

虚拟用户是指在FTP服务器上拥有账号，并且该账号只能用于文件传输服务的用户，也称作Guest用户。该类用户可以通过输入账号以及口令来进行授权登录。登录入系统后，其登录目录为指定的目录。一般情况下，该类用户既可以下载也可以上传文件。

vsftpd的虚拟用户采用单独的用户名/口令保存方式，与系统账号（passwd/shadow）分离，这大大增强了系统的安全性。vsftpd可以采用数据库文件来保存用户/口令，如hash;也可以将用户/口令保存在数据库服务器中，如MySQL等。vsftpd验证虚拟用户，则采用PAM方式。由于虚拟用户的用户名/口令被单独保存，因此在验证时，vsftpd须要用一个系统用户的身份来读取数据库文件或数据库服务器以完成验证，这就是guest用户，这正如同匿名用户也需要有一个系统用户ftp一样。当然，guest用户也可以被认为是用于映射虚拟用户。

在虚拟用户使用vsftpd服务器之前，要对服务器进行配置，主要包括如下几个步骤：

-   生成虚拟用户口令库文件。
-   配置生成vsftpd的认证文件。
-   建立虚拟用户访问所需要的目录并且设定相应的访问权限。
-   建立配置文件。
-   重新启动vsftpd服务器。

下面是完成上述五个步骤的基本命令和过程，如下所示：

### 生成虚拟用户口令库文件

```shell
//生成虚拟用户口令库文件，按照格式编辑口令文件。单数行为用户名，偶数行为用户口令
#vi login.txt
liyangsuper//用户名
real//口令
patterson//用户名
jef//口令
guest//用户名
guest//口令
//存盘退出
```

### 配置生成vsftpd的认证文件

```shell
# 使用db_load命令生成口令库文件
# 安装 db-util
#db_load –T –t hash –f login.txt /etc/vsftpd/vsftpd_login.db
//修改该口令库文件的权限
#chmod 600 /etc/vsftpd/vsftpd_login.db
//编辑虚拟用户所需的PAM配置文件
#vi /etc/pam.d/vsftpd
//在该文件中加入如下两行，并且保存后退出
auth required /lib/security/pam_userdb.so db=/etc/vsftpd/vsftpd_login.db
account required /lib/security/pam_userdb.so db=/etc/vsftpd/vsftpd_login.db
```

### 建立虚拟用户访问所需要的目录并且设定相应的访问权限

```shell
#useradd –d /home/ftp virtual
#chmod 700 /home/ftp
```

### 对vsftpd的主配置文件进行配置

```shell
//为了保证安全，首先生成该文件的一个备份，然后进行修改
#cp /etc/vsftpd/vsftpd.conf /etc/vsftpd/vsftpd.conf.bak
#vi /etc/vsftpd/vsftpd.conf
//配置相关选项如下所示
listen=YES
tcp_wrappers=YES //支持tcp_wrappers,限制访问
(/etc/hosts.allow,/etc/hosts.deny)
listen=YES的意思是使用standalone启动vsftpd，而不是super daemon(xinetd)控制它
(vsftpd推荐使用standalone方式)
anonymous_enable=NO
local_enable=YES //PAM方式此处必须为YES
write_enable=NO
anon_upload_enable=NO
anon_mkdir_write_enable=NO
anon_other_write_enable=NO
chroot_local_user=YES
guest_enable=YES
guest_username=vsftpd //采用虚拟用户形式
```

###  重新启动vsftpd服务器

```shell
#service vsftpd restart
```

经过上面几个步骤的配置后，就可以使用虚拟用户登录vsftpd服务器了，如下所示：

使用创建的虚拟用户liyangsuper，登录成功：

```shell
#ftp 127.0.0.1
Connected to 127.0.0.1 (127.0.0.1).
220 Welcome to virtual FTP service.
Name (127.0.0.1:root): liyangsuper
331 Please specify the password.
Password:
230 Login successful. Have fun.
Remote system type is UNIX.
Using binary mode to transfer files.
ftp> ls
227 Entering Passive Mode (127,0,0,1,119,210)
150 Here comes the directory listing.
drwxr-xr-x    2 0        0            4096 Jul 09 15:26 ftp
226 Directory send OK.
```

能够浏览虚拟目录/home/ftp里的文件和目录

```shell
ftp> cd ftp
250 Directory successfully changed.
ftp> ls
227 Entering Passive Mode (127,0,0,1,149,3)
150 Here comes the directory listing.
-rw-r--r--    1 0        0              10 Jul 09 15:26 test.c
226 Directory send OK.
```

测试是否能够创建目录

```shell
ftp> mkdir super
550 Permission denied.//操作被禁止
ftp> bye
221 Goodbye.
```

## 配置vsftpd服务器中chroot

在vsftpd服务器的默认设置中，本地用户可以切换到主目录以外的目录进行浏览访问，这样对于服务器来说是不太安全的，因为任何用户可以随时浏览到别的用户的私有信息，下面介绍如何使用chroot选项来防止这种情况的发生。

与该功能相关的选项主要包括：

-   chroot_local_user
-   chroot_list_enable
-   chroot_list_file

可以通过如下两种方法来设置chroot，从而杜绝上述不安全的情况发生：

-   设置所有的本地用户执行chroot，只要将/etc/vsftpd/vsftpd.con文件中的chroot_local_ user值置为YES，即chroot_local_user=YES。

-   设置指定的用户执行chroot，按照如下方法进行设置：

    ```shell
    chroot_local_user=NO
    chroot_list_enable=YES
    chroot_list_file=/etc/vsftpd.chroot_list
    ```

    设置后，只有/etc/vsftpd.chroot_list文件中指定的用户才能够执行chroot命令。

## 配置vsftpd服务器在非标准端口工作

在使用FTP服务的过程中，可以使该服务在非标准端口（非21端口）工作，不过要完成这项工作，须要使vsftpd服务器运行在独立启动方式下，而且要配置vsftpd的主配置文件/etc/vsftpd/vsftpd.conf，将listen_port=10003或者是其他端口号的选项加入该文件即可，然后要重新启动vsftpd守护进程：

`#service vsftpd restart`

下面是对该功能进行实例验证, 使用匿名用户登录到服务器的10003端口，成功：

```shell
#ftp 127.0.0.1 10003
Connected to 127.0.0.1 (127.0.0.1).
220 (vsFTPd 1.1.3)
Name (127.0.0.1:root): anonymous
331 Please specify the password.
Password:
230 Login successful. Have fun.
Remote system type is UNIX.
Using binary mode to transfer files.
ftp> bye
221 Goodbye.
```

## 配置虚拟FTP服务器

所谓虚拟FTP服务器，是指一台机器上有多个IP地址，并且可以向外提供多FTP服务，这些服务器在逻辑上是独立的，有不同的访问控制表和不同的下载内容。

配置虚拟FTP服务器的步骤如下所示：

-   为一个服务器配置多个IP地址。假设原来的主机内部地址为210.77.27.222，可再绑定一个IP地址如下：

    向接口添加一个新的IP210.77.27.223

    `#/sbin/ifconfig eth0:0 210.77.27.223 up `

-   创建虚拟FTP服务器的根目录，并确保/var/newftp和/var/newftp/pub目录的拥有者和组均为root，掩码为755。

    ```shell
    #mkdir -p /var/newftp/pub
    #chmod 755 /var/newftp
    #chmod 755 /var/new/ftp/pub
    ```

-   增加虚拟FTP服务器的匿名用户账号。原先的FTP服务器使用系统用户ftp作为其匿名用户账号。需要增加一个newftp用于虚拟FTP服务器。

    `useradd -d /var/newftp -M newftp`

-   创建虚拟FTP服务器的配置文件。复制原来的vsftpd.conf作为虚拟FTP服务器的配置文件，并修改相关参数。

    `#cp /etc/vsftpd/vsftpd.conf /etc/vsftpd/vsftpd2.conf`

    新添或修改以下参数：

    ```shell
    listen=YES
    listen_address=210.77.27.223
    ftp_username=newftp
    ```

    此处需要特别注意：由于vsftpd默认是监听所有的IP地址，当设定基于IP的虚拟FTP服务器时，为防止原来的FTP服务器与虚拟FTP服务器发生监听上的冲突，原FTP服务器须要指定监听的IP地址。在这里，原来的配置文件中就要设置listen_address=//向接口添加一个新的IP210.77.27.222。

-   启动和测试虚拟FTP服务器

    可以使用命令同时启动或关闭原FTP服务器和新加的虚拟FTP服务器，如下：

    ```shell
    #service vsftpd restart
    Shutting down vsftpd:                                    [  OK  ]
    Starting vsftpd for vsftpd2:                             [  OK  ]
    Starting vsftpd for vsftpd:                              [  OK  ]
    ```

    启动成功后，就可以对该虚拟服务器登录进行测试，如下面所示：

    测试以127.0.0.1登录：

    ```shell
    # ftp 127.0.0.1
    ftp: connect: Connection refused
    ftp> bye
    //测试失败，因为该服务器具有两个不同的IP
    ```

    连接原来的FTP服务器，其IP地址为210.77.27.222，端口号为10003：

    ```shell
    # ftp 210.77.27.222 10003
    Connected to 210.77.27.222 (210.77.27.222).
    220 Welcome to virtual FTP service.
    ```

    使用匿名用户登录，成功：

    ```shell
    Name (210.77.27.222:root): anonymous
    331 Please specify the password.
    Password:
    230 Login successful. Have fun.
    Remote system type is UNIX.
    Using binary mode to transfer files.
    ftp> ls
    227 Entering Passive Mode (210,77,27,222,51,224)
    150 Here comes the directory listing.
    drwxr-xr-x    2 0        0            4096 Jul 09 15:26 pub
    226 Directory send OK.
    ftp> cd pub
    250 Directory successfully changed.
    ftp> ls
    227 Entering Passive Mode (210,77,27,222,223,123)
    150 Here comes the directory listing.
    -rw-r--r--    1 0        0              10 Jul 09 15:26 test.c
    226 Directory send OK.
    ftp> bye
    221 Goodbye.
    ```

    连接虚拟FTP服务器，其IP地址为210.77.27.223，端口号为10004：

    ```shell
    # ftp 210.77.27.223 10004
    Connected to 210.77.27.223 (210.77.27.223).
    220 Welcome to virtual FTP service.
    ```

    使用匿名用户登录，成功：

    ```shell
    Name (210.77.27.223:root): anonymous
    331 Please specify the password.
    Password:
    230 Login successful. Have fun.
    Remote system type is UNIX.
    Using binary mode to transfer files.
    ftp> ls
    227 Entering Passive Mode (210,77,27,223,102,119)
    150 Here comes the directory listing.
    drwxr-xr-x    2 0        0            4096 Jul 10 13:23 pub
    226 Directory send OK.
    ```

    进入该登录文件夹：

    ```shell
    ftp> cd pub
    250 Directory successfully changed.
    ftp> ls //由于该虚拟服务器没有加入任何文件，所以ls结果为空
    227 Entering Passive Mode (210,77,27,223,170,40)
    150 Here comes the directory listing.
    226 Directory send OK.
    ftp> bye
    221 Goodbye.
    ```

    ​


## 示例配置

```shell
# tree /etc/vsftpd/
/etc/vsftpd/
├── ftpusers
├── user_list
├── vsftpd.conf
└── vsftpd_conf_migrate.sh

0 directories, 4 files

$ cat /etc/vsftpd/ftpusers 
# Users that are not allowed to login via ftp
root
bin
daemon
adm
lp
sync
shutdown
halt
mail
news
uucp
operator
games
nobody


$ cat /etc/vsftpd/user_list 
# vsftpd userlist
# If userlist_deny=NO, only allow users in this file
# If userlist_deny=YES (default), never allow users in this file, and
# do not even prompt for a password.
# Note that the default vsftpd pam config also checks /etc/vsftpd/ftpusers
# for users that are denied.
root
bin
daemon
adm
lp
sync
shutdown
halt
mail
news
uucp
operator
games
nobody
ftp

$ cat /etc/vsftpd/vsftpd.conf 
# Example config file /etc/vsftpd/vsftpd.conf
#
# The default compiled in settings are fairly paranoid. This sample file
# loosens things up a bit, to make the ftp daemon more usable.
# Please see vsftpd.conf.5 for all compiled in defaults.
#
# READ THIS: This example file is NOT an exhaustive list of vsftpd options.
# Please read the vsftpd.conf.5 manual page to get a full idea of vsftpd's
# capabilities.
#
# Allow anonymous FTP? (Beware - allowed by default if you comment this out).
anonymous_enable=YES
#
# Uncomment this to allow local users to log in.
# When SELinux is enforcing check for SE bool ftp_home_dir
local_enable=YES
#
# Uncomment this to enable any form of FTP write command.
write_enable=YES
#
# Default umask for local users is 077. You may wish to change this to 022,
# if your users expect that (022 is used by most other ftpd's)
local_umask=022
#
# Uncomment this to allow the anonymous FTP user to upload files. This only
# has an effect if the above global write enable is activated. Also, you will
# obviously need to create a directory writable by the FTP user.
# When SELinux is enforcing check for SE bool allow_ftpd_anon_write, allow_ftpd_full_access
#anon_upload_enable=YES
#
# Uncomment this if you want the anonymous FTP user to be able to create
# new directories.
#anon_mkdir_write_enable=YES
#
# Activate directory messages - messages given to remote users when they
# go into a certain directory.
dirmessage_enable=YES
#
# Activate logging of uploads/downloads.
xferlog_enable=YES
#
# Make sure PORT transfer connections originate from port 20 (ftp-data).
connect_from_port_20=YES
#
# If you want, you can arrange for uploaded anonymous files to be owned by
# a different user. Note! Using "root" for uploaded files is not
# recommended!
#chown_uploads=YES
#chown_username=whoever
#
# You may override where the log file goes if you like. The default is shown
# below.
#xferlog_file=/var/log/xferlog
#
# If you want, you can have your log file in standard ftpd xferlog format.
# Note that the default log file location is /var/log/xferlog in this case.
xferlog_std_format=YES
#
# You may change the default value for timing out an idle session.
#idle_session_timeout=600
#
# You may change the default value for timing out a data connection.
#data_connection_timeout=120
#
# It is recommended that you define on your system a unique user which the
# ftp server can use as a totally isolated and unprivileged user.
#nopriv_user=ftpsecure
#
# Enable this and the server will recognise asynchronous ABOR requests. Not
# recommended for security (the code is non-trivial). Not enabling it,
# however, may confuse older FTP clients.
#async_abor_enable=YES
#
# By default the server will pretend to allow ASCII mode but in fact ignore
# the request. Turn on the below options to have the server actually do ASCII
# mangling on files when in ASCII mode.
# Beware that on some FTP servers, ASCII support allows a denial of service
# attack (DoS) via the command "SIZE /big/file" in ASCII mode. vsftpd
# predicted this attack and has always been safe, reporting the size of the
# raw file.
# ASCII mangling is a horrible feature of the protocol.
#ascii_upload_enable=YES
#ascii_download_enable=YES
#
# You may fully customise the login banner string:
#ftpd_banner=Welcome to blah FTP service.
#
# You may specify a file of disallowed anonymous e-mail addresses. Apparently
# useful for combatting certain DoS attacks.
#deny_email_enable=YES
# (default follows)
#banned_email_file=/etc/vsftpd/banned_emails
#
# You may specify an explicit list of local users to chroot() to their home
# directory. If chroot_local_user is YES, then this list becomes a list of
# users to NOT chroot().
# (Warning! chroot'ing can be very dangerous. If using chroot, make sure that
# the user does not have write access to the top level directory within the
# chroot)
chroot_local_user=YES
chroot_list_enable=YES
# (default follows)
chroot_list_file=/etc/vsftpd/chroot_list
#
# You may activate the "-R" option to the builtin ls. This is disabled by
# default to avoid remote users being able to cause excessive I/O on large
# sites. However, some broken FTP clients such as "ncftp" and "mirror" assume
# the presence of the "-R" option, so there is a strong case for enabling it.
#ls_recurse_enable=YES
#
# When "listen" directive is enabled, vsftpd runs in standalone mode and
# listens on IPv4 sockets. This directive cannot be used in conjunction
# with the listen_ipv6 directive.
listen=YES
#
# This directive enables listening on IPv6 sockets. By default, listening
# on the IPv6 "any" address (::) will accept connections from both IPv6
# and IPv4 clients. It is not necessary to listen on *both* IPv4 and IPv6
# sockets. If you want that (perhaps because you want to listen on specific
# addresses) then you must run two copies of vsftpd with two configuration
# files.
# Make sure, that one of the listen options is commented !!
listen_ipv6=NO

pam_service_name=vsftpd
userlist_enable=YES
tcp_wrappers=YES
userlist_deny=NO


$
```





# 问题

## 卸载vsftpd出错

```shell
$ sudo apt remove vsftpd
Reading package lists... Done
Building dependency tree       
Reading state information... Done
The following packages will be REMOVED:
  vsftpd
0 upgraded, 0 newly installed, 1 to remove and 0 not upgraded.
1 not fully installed or removed.
After this operation, 352 kB disk space will be freed.
Do you want to continue? [Y/n] y
(Reading database ... 215847 files and directories currently installed.)
Removing vsftpd (3.0.3-8) ...
dpkg: error processing package vsftpd (--remove):
 subprocess installed post-removal script returned error exit status 1
Errors were encountered while processing:
E: Sub-process /usr/bin/dpkg received a segmentation fault.
```

### 解决

```shell
# 手动删除用户ftp
sudo deluser --system ftp
# 删除vsftpd
sudo apt remove vsftpd
```

## refusing to run with writable root inside chroot()

```shell
"如果启用chroot,必须保证ftp根目录不可写,这样对于ftp根直接为网站根目录的用户不方便,所以建议假如ftp根目录是/home/ftp,则将访问权限改写如下
chmod a-w /home/ftp
```

