# OSI模型

- OSI模型，即开放式通信系统互联参考模型(Open System Interconnection,OSI/RM,Open Systems Interconnection Reference Model)

## 分层

| 名称    | 说明                                       | 比喻                   | 其他                                       |
| ----- | ---------------------------------------- | -------------------- | ---------------------------------------- |
| 物理层   | 将数据转换为可通过物理介质传送的电子信号                     | 相当于邮局中的搬运工人          | 在这一层，数据还没有被组织，仅作为原始的位流或电气电压处理，单位是比特。     |
| 数据链路层 | 决定访问网络介质的方式, 在此层将数据分帧，并处理流控制. 本层指定拓扑结构并提供硬件寻 址 | 相当于邮局中的装拆箱工人         | 该层的作用包括：物理地址寻址、数据的成帧、流量控制、数据的检错、重发等。     |
| 网络层   | 使用权数据路由经过大型网络                            | 相当于邮局中的排序工人          | 主要功能是将网络地址翻译成对应的物理地址，并决定如何将数据从发送方路由到接收方。 |
| 传输层   | 提供终端到终端的可靠连接                             | 相当于公司中跑邮局的送信职员       | O S I 模型中最重要的一层。传输协议同时进行流量控制或是基于接收方可接收数据的快慢程度规定适当的发送速率。除此之外，传输层按照网络能处理的最大尺寸将较长的数据包进行强制分割。 |
| 会话层   | 允许用户使用简单易记的名称建立连接                        | 相当于公司中收寄信、写信封与拆信封的秘书 | 负责在网络中的两节点之间建立、维持和终止通信。会话层的功能包括：建立通信链接，保持会话过程通信链接的畅通，同步两个节点之间的对 话，决定通信是否被中断以及通信中断时决定从何处重新发送。 |
| 表示层   | 协商数据交换格式                                 | 相当公司中简报老板、替老板写信的助理   | 应用程序和网络之间的翻译官，在表示层，数据将按照网络能理解的方案进行格式化；这种格式化也因所使用网络的类型不同而不同。　表示层管理数据的解密与加密，如系统口令的处理。 |
| 应用层   |                                          | 用户的应用程序和网络之间的接口老板    | 负责对软件提供接口以使程序能使用网络服务。                    |

## 协议

**应用层**

应用层协议工作在OSI模型的上层，提供应用程序间的交换和数据交换。比较常用的应用层协议有：

- **SMTP (simple Mail Transfer Protocol，简单邮件传输协议）**
- BOOTP(Boot trap．Protocol)
- **FTP (File Transfer Protocol，文件传输协议）**
- **HTTP(Hypertext Transfer Protocol，超文本传输协议）**
- AFP （Apple Talk文件协议）--Apple公司的网络协议族，用于交换文件
- **SNMP (Simple Network Management Protocol)**
- SMB (Server Message Block Protocol)
- **TFTP（简单文件传输协议）**
- NCP (NetWare Core Protocol)
- **NFS (Network File System)**
- **telnet**
- **dns**
- **WWW**

**表示层**

- JPEG
- MPEG
- ASII

**会话层**

- NFS
- SQL
- NETBIOS
- RPC

**传输层**

传输层协议提供计算机之间的通信会话，并确保数据在计算机之间可靠地传输。主要的传输层协议有：

- **TCP(Transmission Control Protocol，传输控制协议）**
- **SPX(SequenCed Packet ExChange Protocol)**
- NWL INK
- ATP(AppleTalk Transaction Protocol），NBP（名字绑定协议）
- NetBEUI(NetBIOS Extended User Internet)
- **udp（用户数据报协议）**

**网络层**

网络层协议提供所谓的链路服务，这些协议可以处理寻址和路由信息、错误检测和重传请求。

- **IP (Internet Protocol)**
- **IPX (Internet work Packet Exchange)**
- NWLINK--微软实现的 IPX/SPX
- DDP (Datagram Delivery Protocol)
- NetBEUI
- Ethernet
- **arp**
- **rarp**
- **icmp**
- **OSPF**
- **RIP**
- **IGRP**
- 路由器

**数据链路层**

数据链路层在不可靠的物理介质上提供可靠的传输。

- SDLC
- HDLC
- PPP
- STP
- 帧中继
- FR
- MAC
- VLAN
- 网桥
- 交换机

**物理层**

- RJ45
- CLOCK
- IEEE802.3 
- 中继器
- 集线器
- 网关