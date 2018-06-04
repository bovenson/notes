---
title: Python网络编程
tags:
	- 网络编程
categories:
	- Python
---

# 简介

使用套接字进行网络编程。

# 网络编程

使用`socket`模块的`socket()`函数，可以创建套接字。

## `socket`模块函数

要创建套接字，必须使用`socket.socket()`函数，语法如下：

`socket(socket_family, socket_type, protocol=0)`

其中，`socket_family`是`AF_UNIX`或`AF_INET`，`socket_type`是`SOCK_STREAM`或`SOCK_DGRAM`。`protocol`通常省略，默认为`0`。

**导入模块**

- 创建`TCP/IP`套接字

  `tcpSock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)`

- 创建`UDP/IP`套接字

  `udpSock = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)`

## 套接字对象方法

### 服务器套接字方法

| 名称         | 描述                                                  |
| ------------ | ----------------------------------------------------- |
| `s.bind()`   | 将地址（主机号、端口号对）绑定到套接字上              |
| `s.listen()` | 设置并启动`TCP`监听器                                 |
| `s.accept()` | 被动接受`TCP`客户端连接，一直等待知道连接到达（阻塞） |

### 客户端套接字方法

| 名称             | 描述                                                         |
| ---------------- | ------------------------------------------------------------ |
| `s.connect()`    | 主动发起`TCP`服务器连接                                      |
| `s.connect_ex()` | `connect()`的扩展版本，此时会以错误码的形式返回问题，而不是抛出一个异常 |

### 普通套接字方法

| 名称                | 描述                                                 |
| ------------------- | ---------------------------------------------------- |
| `s.recv()`          | 接收TCP消息                                          |
| `s.recv_into()`     | 接收TCP消息到指定的缓冲区                            |
| `s.send()`          | 发送TCP消息                                          |
| `s.sendall()`       | 完整地发送TCP消息                                    |
| `s.recvfrom()`      | 接受UDP消息                                          |
| `s.recvfrom_into()` | 接受UDP消息到指定的缓存区                            |
| `s.sendto()`        | 发送UDP消息                                          |
| `s.getpeername()`   | 连接到套接字（TCP）的远程地址                        |
| `s.getsockname()`   | 当前套接字的地址                                     |
| `s.getsockopt()`    | 返回给定套接字选项的指                               |
| `s.setsockopt()`    | 设置给定套接字选项的值                               |
| `s.shutdown()`      | 关闭连接                                             |
| `s.close()`         | 关闭套接字                                           |
| `s.detach()`        | 在未关闭文件描述符的情况下关闭套接字，返回文件描述符 |
| `s.ioctl()`         | 控制套接字的模式（仅支持windows）                    |

### 面向阻塞的套接字方法

| 名称              | 描述                         |
| ----------------- | ---------------------------- |
| `s.setblocking()` | 设置套接字的阻塞或非阻塞模式 |
| `s.settimeout()`  | 设置阻塞套接字操作的超时时间 |
| `s.gettimeout()`  | 获取阻塞套接字操作的超时时间 |

### 面向文件的套接字方法

| 名称           | 描述                       |
| -------------- | -------------------------- |
| `s.fileno()`   | 套接字的文件描述符         |
| `s.makefile()` | 创建与套接字关联的文件对象 |

### 数据属性

| 名称       | 描述       |
| ---------- | ---------- |
| `s.family` | 套接字家族 |
| `s.type`   | 套接字类型 |
| `s.proto`  | 套接字协议 |

## 创建TCP服务器

TCP服务器的一般伪代码。

```shell
ss = socket()					# 创建服务器套接字
ss.bind()						# 套接字与地址绑定
ss.listen()						# 监听连接
inf_loop:						# 服务器无限循环
	cs = ss.accept()			# 接收客户端连接
	comm_loop:					# 通信循环
		cs.recv() / cs.send()	# 对话（接受/发送）
	cs.close()					# 关闭客户端套接字
ss.close()						# 关闭服务器套接字（可选）
```

- 所有套接字都是通过使用`socket.socket()`函数来创建
- 服务器需要占用一个端口并等待客户端的请求，所以它们必须绑定到一个本地地址
- 因为TCP是一种面向连接的通信系统，所以TCP服务器开始操作之前，必须安装一些基础设施
- TCP服务器必须监听（传入） 的连接，一旦这个安装过程完成后，服务器可以开始它的无限循环
- 调用`accept()`函数之后，就开启了一个简单的服务器，它会等待客户端的连接，默认情况下，`accept()`是阻塞的，这意味着执行将被暂停，直到一个连接到达
- 一旦服务器接受一个连接，就会返回（通过`accept()`）一个独立的客户端套接字，用来与即将到来的消息进行交换
- 一旦创建了临时套接字，通信就可以开始，通过使用这个新的套接字，客户端与服务器就可以开始参与发送和接受的对话中，直到连接终止
- 当一方关闭连接或者向对方发送一个空字符串时，通常就会关闭连接
- 在代码中，一个客户端连接关闭之后，服务器就会等待另一个客户端连接

下面是一个TCP服务器程序，它接受客户端发送的数据字符串，并将其打上时间戳，并返回给客户端。

```python
# coding: utf-8

from socket import *
from time import ctime


HOST = ''		# HOST变量是空白，这是对bind()方法的标识，表示它可以使用任何可用的地址
PORT = 21567	
BUFSIZ = 1024	# 缓冲区大小设置为1KB
ADDR = (HOST, PORT)	

tcpSerSock = socket(AF_INET, SOCK_STREAM)	# 分配TCP服务器套接字
tcpSerSock.bind(ADDR)						# 将套接字绑定到服务器地址
tcpSerSock.listen(5)	# 开启TCP监听器的调用，参数是在连接被转接或拒绝之前，传入连接请求的最大数

while True:
    print('waiting for connection...')
    tcpCliSock, addr = tcpSerSock.accept()	# 被动等待客户端的连接
    print('...connected from:', addr)

    while True:
        data = tcpCliSock.recv(BUFSIZ)		# 等待客户端发送的消息
        if not data:						# 如果消息是空白的，意味着客户端已经退出
            break							# 跳出对话循环
        tcpCliSock.send(bytes('[%s] %s' % (ctime(), data), 'utf-8'))

    tcpCliSock.close()						# 关闭当前客户端连接

# tcpSerSock.close()
```

## 创建TCP客户端

TCP客户端的一般伪代码。

```shell
cs = socket()				# 创建客户端套接字
cs.connect()				# 尝试连接服务器
comm_loop:					# 通信循环
	cs.send() / cs.recv()	# 对话（发送 / 接受）
cs.close()					# 关闭客户端套接字
```

Python代码实现的TCP客户端。

```python
# coding: utf-8

from socket import *

HOST = '127.0.0.1'
PORT = 21567
BUFSIZ = 1024
ADDR = (HOST, PORT)

tcpCliSock = socket(AF_INET, SOCK_STREAM)	# 分配TCP客户端套接字
tcpCliSock.connect(ADDR)					# 主动调用并连接到服务器

while True:
    data = input('> ')
    if not data:
        break
    tcpCliSock.send(bytes(data, 'utf-8'))
    data = tcpCliSock.recv(BUFSIZ)
    if not data:
        break
    print(data.decode('utf-8'))

tcpCliSock.close()
```

## 执行TCP服务器和客户端

**服务器端**

```shell
bovenson@ThinkCentre:~/Git/notes/Python/Code/LearnPythonCode/network_programming$ python3 tsTserv.py 
waiting for connection...
...connected from: ('127.0.0.1', 35550)
```

**客户端**

```shell
bovenson@ThinkCentre:~/Git/notes/Python/Code/LearnPythonCode/network_programming$ python3 tsTclnt.py 
> Hello
[Mon Jun  4 11:20:46 2018] b'Hello'
> World
[Mon Jun  4 11:20:48 2018] b'World'
> 
```

## 创建UDP服务器

UDP服务器不需要TCP服务器那么多的设置，因为它们不是面向连接的，除了等待传入的连接之外，几乎不需要做其他工作。

```shell
ss = socket()							# 创建服务器套接字
ss.bind()								# 绑定服务器套接字
inf_loop():								# 服务器无限循环
	cs = ss.recvfrom() / ss.sendto()	# 关闭（接受 / 发送）
ss.close()								# 关闭服务器套接字
```

UDP服务器和TCP服务器之间的另一个显著差异是，以为数据报套接字是无连接的，所以就没有为了成功通信而使一个客户端连接到一个独立的套接字转换操作。这些服务器仅仅接受消息并有可能回复数据。

```python
# coding: utf-8

from socket import *
from time import ctime

HOST = ''
PORT = 21567
BUFSIZ = 1024
ADDR = (HOST, PORT)

udpSerSock = socket(AF_INET, SOCK_DGRAM)
udpSerSock.bind(ADDR)

while True:
    print('waiting for message...')
    data, addr = udpSerSock.recvfrom(BUFSIZ)
    udpSerSock.sendto('[%s] %s' % (ctime(), data), addr)
    print('...received from and returned to:', addr)

# udpSerSock.close()
```

## 创建UDP客户端

客户端伪代码如下。

```python
ss = socket()					# 创建客户端套接字
comm_loop:						# 通信循环
    cs.sendto() / cs.recvfrom()	# 对话（发送 / 接收）
cs.close()						# 关闭客户端套接字
```

Python实现的客户端代码。

```python
# coding: utf-8

from socket import *
from time import ctime

HOST = ''
PORT = 21567
BUFSIZ = 1024
ADDR = (HOST, PORT)

udpSerSock = socket(AF_INET, SOCK_DGRAM)
udpSerSock.bind(ADDR)

while True:
    print('waiting for message...')
    data, addr = udpSerSock.recvfrom(BUFSIZ)
    udpSerSock.sendto(bytes('[%s] %s' % (ctime(), data), 'utf-8'), addr)
    print('...received from and returned to:', addr)

# udpSerSock.close()
```

## 执行UDP服务器和客户端

**客户端**

```shell
/usr/bin/python3.5 /home/bovenson/Git/notes/Python/Code/LearnPythonCode/network_programming/tsUclnt.py
> Hello
b"[Mon Jun  4 12:42:07 2018] b'Hello'"
> World
b"[Mon Jun  4 12:42:08 2018] b'World'"
> 
```

**服务端**

```shell
/usr/bin/python3.5 /home/bovenson/Git/notes/Python/Code/LearnPythonCode/network_programming/tsUserv.py
waiting for message...
...received from and returned to: ('127.0.0.1', 59736)
waiting for message...
...received from and returned to: ('127.0.0.1', 59736)
waiting for message...
```

**注** 和TCP不同的是，UDP客户端可以先于UDP服务器运行。但是TCP服务器必须先于TCP客户端运行。

## `socket`模块属性

### 数据属性

| 属性名称                                      | 描述                             |
| --------------------------------------------- | -------------------------------- |
| `AF_UNIX,AF_INET,AF_INET6,AF_NETLINK,AF_TIPC` | `Python`中支持的套接字地址家族   |
| `SO_STREAM,SO_DGRAM`                          | 套接字类型（TCP=流，UDP=数据报） |
| `has_ipv6`                                    | 指示是否支持`IPv6`的布尔标记     |

### 异常

| 名称       | 描述               |
| ---------- | ------------------ |
| `error`    | 套接字相关错误     |
| `herror`   | 主机和地址相关错误 |
| `gaierror` | 地址相关错误       |
| `timeout`  | 超时时间           |

### 函数

| 名称                                      | 描述                                                         |
| ----------------------------------------- | ------------------------------------------------------------ |
| `socket()`                                | 以给定的地址家族、套接字类型和协议类型（可选）创建一个套接字对象 |
| `socketpair()`                            | 以给定的地址家族、套接字类型和协议类型（可选）创建一对套接字对象 |
| `create_connection()`                     | 常规函数，接受一个地址（主机号，端口号）对，返回套接字对象   |
| `fromfd()`                                | 以一个打开的文件描述符创建一个套接字对象                     |
| `ssl()`                                   | 通过套接字启动一个安全套接字层连接；不执行证书验证           |
| `getaddrinfo()`                           | 获取一个五元组序列形式的地址信息                             |
| `getnameinfo()`                           | 给定一个套接字地址，返回（主机号，端口号）二元组             |
| `getfqn()`                                | 返回完整的域名                                               |
| `gethostname()`                           | 返回当前主机名                                               |
| `gethostbyname()`                         | 将一个主机名映射到它的IP地址                                 |
| `gethostbyname_ex()`                      | `gethostname()`的扩展版本，它返回主机名、别名主机集合和IP地址列表 |
| `gethostbyaddr()`                         | 将一个IP地址映射到DNS信息：返回与`gethostbyname_ex()`相同的3元组 |
| `getprotobyname()`                        | 将一个协议名（如`tcp`）映射到一个数字                        |
| `getservbyname()/getservbyport()`         | 将一个服务名映射到一个端口号，或者反过来；对于任何一个函数来说，协议名都是可选的 |
| `ntohl()/ntohs()`                         | 将来自网络的整数转换为主机字节顺序                           |
| `htonl()/htons()`                         | 将来自主机的整数转换为网络字节顺序                           |
| `inet_aton()/inet_ntoa()`                 | 将IP地址八进制字符串转换为32位的包格式，或者反过来（仅用于IPv4地址） |
| `inet_pton()/inet_ntop()`                 | 将IP地址字符串转换成打包的二进制格式，或者反过来（同时适用于IPv4和IPv6地址） |
| `getdefaulttimeout()/setdefaulttimeout()` | 以秒（浮点数）为单位返回默认套接字超时时间；以秒（浮点数）为单位设置默认套接字超时时间 |

# `SocketServer`模块

`SocketServer`是标准库中的一个高级模块，它的目标是简化很多样板代码，它们是创建网络客户端和服务器所需必须的代码。

| 类                                            | 描述                                                         |
| --------------------------------------------- | ------------------------------------------------------------ |
| `BaseServer`                                  | 包含核心服务器功能和`mix-in`类的钩子；仅用于推导，这样不会创建这个类的实例；可以用`TCPServer`和`UDPServer`创建类的实例 |
| `TCPServer/UDPServer`                         | 基础的网络同步`TCP/UDP`服务器                                |
| `UnixStreamServer/UnixDatagramServer`         | 基于文件的基础同步`TCP/UDP`服务器                            |
| `ForkingMixIn/ThreadingMixIn`                 | 核心派出或线程功能；只用作`mix-in`类与一个服务器类配合实现一些异步性；不能直接实例化这个类 |
| `ForkingTCPServer/ForkingUDPServer`           | `ForkingMixIn`和`TCPServer/UDPServer`的组合                  |
| `ThreadingTCPServer/ThreadingUDPServer`       | `ThreadingMixIn`和`TCPServer/UDPServer`的组合                |
| `BaseRequestHandler`                          | 包含处理服务请求的核心功能；仅仅用于推导，这样无法创建这个类的实例；可以使用`StreamRequestHandler`或`DatagramRequestHandler`创建类的实例 |
| `StreamRequestHandler/DatagramRequestHandler` | 实现`TCP/UDP`服务器的服务处理器                              |

事件包括消息的发送和接受。

类定义只包括一个用来接收客户端消息的事件处理程序，所有其他的功能都来自使用的`SocketServer`类。

## 创建`SocketServer` TCP服务器

```python
# coding: utf-8

from socketserver import (TCPServer as TCP, StreamRequestHandler as SRH)

from time import ctime

HOST = ''
PORT = 21567
ADDR = (HOST, PORT)


class MyRequestHandler(SRH):
    def handle(self):
        print('...connected from:', self.client_address)
        self.wfile.write('[%s] %s' % (ctime(), self.rfile.readline()))

tcpServ = TCP(ADDR, MyRequestHandler)
print('waiting for connection...')
tcpServ.serve_forever()
```

`MyRequestHandler`作为`SocketServer`中`StreamRequestHandler`的一个子类，并重写了它的`handler()`方法。当接收到一个来自客户端的消息时，就会调用`handler()`方法。而`StreamRequestHandler`类将输入和输出套接字看作类似文件的对象，因此我们将使用`readline()`来获取客户端消息，并利用`write()`将字符串发送回客户端。

## 创建`SocketServer` TCP客户端

```python
# coding: utf-8

from socket import *

HOST = 'localhost'
PORT = 21567
BUFSIZ = 1024
ADDR = (HOST, PORT)

while True:
    tcpCliSock = socket(AF_INET, SOCK_STREAM)
    tcpCliSock.connect(ADDR)
    data = input('> ')
    if not data:
        break
    tcpCliSock.send(bytes('%s\r\n' % data, 'utf-8'))
    data = tcpCliSock.recv(BUFSIZ)
    if not data:
        break
    print(data.strip())
    tcpCliSock.close()
```

## 执行TCP服务器和客户端

**服务端**

```shell
/usr/bin/python3.5 /home/bovenson/Git/notes/Python/Code/LearnPythonCode/network_programming/tsTservSS.py
waiting for connection...
...connected from: ('127.0.0.1', 36118)
...connected from: ('127.0.0.1', 36120)
...connected from: ('127.0.0.1', 36122)
```

**客户端**

```shell
/usr/bin/python3.5 /home/bovenson/Git/notes/Python/Code/LearnPythonCode/network_programming/tsTclntSS.py
> a
b"[Mon Jun  4 15:34:12 2018] b'a\\r\\n'"
> c
b"[Mon Jun  4 15:34:13 2018] b'c\\r\\n'"
> 
```

## Twisted框架

`Twisted`是一个完整的事件驱动的网络框架，既可以使用，也能开发完整的异步网络应用程序和协议。

### 创建`Twisted Reactor` TCP服务器

```python
# coding: utf-8

from twisted.internet import protocol, reactor
from time import ctime

PORT = 21567

class TSServProtocol(protocol.Protocol):
    def connectionMade(self):   # 客户端连接到服务器时就会执行
        clnt = self.clnt = self.transport.getPeer().host
        print('...connected from:', clnt)

    def dataReceived(self, data):   # 当服务器收到客户端通过网络发送的一些数据时就会调用 dataReceived() 方法
        self.transport.write(bytes('[%s] %s' % (ctime(), data), 'utf-8'))

factory = protocol.Factory()
factory.protocol = TSServProtocol
print('waiting for connection...')
reactor.listenTCP(PORT, factory)
reactor.run()
```

### 创建`Twisted Reactor` TCP客户端

```python
# coding: utf-8

from twisted.internet import protocol, reactor

HOST = 'localhost'
PORT = 21567

class TSClntProtocol(protocol.Protocol):
    def sendData(self):
        data = input('> ')
        if data:
            print('...sending %s...' % data)
            self.transport.write(bytes(data, 'utf-8'))
        else:
            self.transport.loseConnection()

    def connectionMade(self):
        self.sendData()

    def dataReceived(self, data):
        print(data)
        self.sendData()


class TSClntFactory(protocol.ClientFactory):
    protocol = TSClntProtocol
    clientConnectionLost = clientConnectionFailed = lambda self, connector, reason: reactor.stop()

reactor.connectTCP(HOST, PORT, TSClntFactory())
reactor.run()
```

## 执行TCP服务器和客户端

**服务器**

```shell
/usr/bin/python3.5 /home/bovenson/Git/notes/Python/Code/LearnPythonCode/network_programming/tsTservTW.py
waiting for connection...
...connected from: 127.0.0.1
```

**客户端**

```shell
/usr/bin/python3.5 /home/bovenson/Git/notes/Python/Code/LearnPythonCode/network_programming/tsTclntTW.py
> a
...sending a...
b"[Mon Jun  4 16:40:40 2018] b'a'"
> b
...sending b...
b"[Mon Jun  4 16:40:42 2018] b'b'"
> 
```

# 参考

- 《Python核心编程》