[TOC]

# scp命令使用

**scp - 在网络上不同主机之间拷贝文件** 使用ssh来进行数据传输,使用和ssh一样的验证方式,提供和ssh一样的安全保证.
## 命令格式
```shell
scp [-12346Cpqrv] [-C cipher] [-F ssh_config] [-i identity_file]
	[-l limit] [-o ssh_option] [-P port] [-S program] [[user@]host1:]file1 ... 
    [[user@]host2:]file2
```

## 参数说明

| 参数               | 说明                                       |
| :--------------- | :--------------------------------------- |
| -1               | 强制使用协议(protocol)1                        |
| -2               | 强制使用协议(protocol)2                        |
| -3               | 通过本机在两个远程主机间拷贝文件                         |
| -4               | 强制使用IPv4地址                               |
| -6               | 强制使用IPv6                                 |
| -B               | 选择批量拷贝模式(避免多次询问密码)                       |
| -C               | 使用压缩                                     |
| -c cipher        | 在数据传输过程中,使用指定的cipher加密数据                 |
| -F ssh_config    | 为每个用户指明特定的ssh配置文件                        |
| -i identity_file | 选择可以从中读取公钥标志(密钥)的文件                      |
| -l limit         | 限制带宽,单位是`Kbit/s`                         |
| -o ssh_option    | 用来向ssh程序传递参数                             |
| -P port          | 指定连接远程主机的端口                              |
| -p               | 保留原文件的修改时间(modification time),访问时间(access time)以及模式(mode) |
| -q               | 静默模式:关闭进度提示信息,警告信息,及ssh的输出信息             |
| -S program       | 使用加密连接的应用程序名称,该程序必须支持ssh选项               |
| -V               | 显示scp及ssh调试信息                            |

## 示例

### 复制本地文件至远程主机

```shell
scp ./file.zip bvz@192.168.31.8:	// 将本机当前目录下的file.zip拷贝到远程主机
									//192.168.31.8用户bvz的主目录下
```

### 从远程主机拷贝文件至本地

```shell
scp bovenson@202.118.26.152:/etc/vim/vimrc ./
```



## 手册原文

使用`man scp`查看
```shell
SCP(1)                                       BSD General Commands Manual                                       SCP(1)

NAME
     scp — secure copy (remote file copy program)

SYNOPSIS
     scp [-12346BCpqrv] [-c cipher] [-F ssh_config] [-i identity_file] [-l limit] [-o ssh_option] [-P port]
         [-S program] [[user@]host1:]file1 ... [[user@]host2:]file2

DESCRIPTION
     scp copies files between hosts on a network.  It uses ssh(1) for data transfer, and uses the same authentication
     and provides the same security as ssh(1).  scp will ask for passwords or passphrases if they are needed for
     authentication.

     File names may contain a user and host specification to indicate that the file is to be copied to/from that
     host.  Local file names can be made explicit using absolute or relative pathnames to avoid scp treating file
     names containing ‘:’ as host specifiers.  Copies between two remote hosts are also permitted.

     The options are as follows:

     -1      Forces scp to use protocol 1.

     -2      Forces scp to use protocol 2.

     -3      Copies between two remote hosts are transferred through the local host.  Without this option the data is
             copied directly between the two remote hosts.  Note that this option disables the progress meter.

     -4      Forces scp to use IPv4 addresses only.

     -6      Forces scp to use IPv6 addresses only.

     -B      Selects batch mode (prevents asking for passwords or passphrases).

     -C      Compression enable.  Passes the -C flag to ssh(1) to enable compression.

     -c cipher
             Selects the cipher to use for encrypting the data transfer.  This option is directly passed to ssh(1).

     -F ssh_config
             Specifies an alternative per-user configuration file for ssh.  This option is directly passed to ssh(1).

     -i identity_file
             Selects the file from which the identity (private key) for public key authentication is read.  This
             option is directly passed to ssh(1).

     -l limit
             Limits the used bandwidth, specified in Kbit/s.

     -o ssh_option
             Can be used to pass options to ssh in the format used in ssh_config(5).  This is useful for specifying
             options for which there is no separate scp command-line flag.  For full details of the options listed
             below, and their possible values, see ssh_config(5).

                   AddressFamily
                   BatchMode
                   BindAddress
                   CanonicalDomains
                   CanonicalizeFallbackLocal
                   CanonicalizeHostname
                   CanonicalizeMaxDots
                   CanonicalizePermittedCNAMEs
                   CertificateFile
                   ChallengeResponseAuthentication
                   CheckHostIP
                   Cipher
                   Ciphers
                   Compression
                   CompressionLevel
                   ConnectionAttempts
                   ConnectTimeout
                   ControlMaster
                   ControlPath
                   ControlPersist
                   GlobalKnownHostsFile
                   GSSAPIAuthentication
                   GSSAPIDelegateCredentials
                   HashKnownHosts
                   Host
                   HostbasedAuthentication
                   HostbasedKeyTypes
                   HostKeyAlgorithms
                   HostKeyAlias
                   HostName
                   IdentityFile
                   IdentitiesOnly
                   IPQoS
                   KbdInteractiveAuthentication
                   KbdInteractiveDevices
                   KexAlgorithms
                   LogLevel
                   MACs
                   NoHostAuthenticationForLocalhost
                   NumberOfPasswordPrompts
                   PasswordAuthentication
                   PKCS11Provider
                   Port
                   PreferredAuthentications
                   Protocol
                   ProxyCommand
                   PubkeyAcceptedKeyTypes
                   PubkeyAuthentication
                   RekeyLimit
                   RhostsRSAAuthentication
                   RSAAuthentication
                   SendEnv
                   ServerAliveInterval
                   ServerAliveCountMax
                   StrictHostKeyChecking
                   TCPKeepAlive
                   UpdateHostKeys
                   UsePrivilegedPort
                   User
                   UserKnownHostsFile
                   VerifyHostKeyDNS

     -P port
             Specifies the port to connect to on the remote host.  Note that this option is written with a capital
             ‘P’, because -p is already reserved for preserving the times and modes of the file.

     -p      Preserves modification times, access times, and modes from the original file.

     -q      Quiet mode: disables the progress meter as well as warning and diagnostic messages from ssh(1).

     -r      Recursively copy entire directories.  Note that scp follows symbolic links encountered in the tree tra‐
             versal.

     -S program
             Name of program to use for the encrypted connection.  The program must understand ssh(1) options.

     -v      Verbose mode.  Causes scp and ssh(1) to print debugging messages about their progress.  This is helpful
             in debugging connection, authentication, and configuration problems.

EXIT STATUS
     The scp utility exits 0 on success, and >0 if an error occurs.

SEE ALSO
     sftp(1), ssh(1), ssh-add(1), ssh-agent(1), ssh-keygen(1), ssh_config(5), sshd(8)

HISTORY
     scp is based on the rcp program in BSD source code from the Regents of the University of California.

AUTHORS
     Timo Rinne <tri@iki.fi>
     Tatu Ylonen <ylo@cs.hut.fi>

BSD                                               September 25, 2015                                              BSD
```