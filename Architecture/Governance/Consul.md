---
title: Consul
tags:
    - Consul
    - 服务治理
    - 服务发现
category:
    - Architecture
---

# Descrption

Consul是一种服务网络解决方案，提供跨任何运行时平台、公共云或私有云的连接和保护服务。

# Glossary

## Agent

- Agent是Consul集群每个成员都存在的、持续运行的守护程序
- 通过执行 `consul agent` 启动
- agent 可以运行在 client 活 server 模式
- 所有的agent都可以运行DNS或HTTP界面
- 负责同步运行服务存活检测

## Client

- Client是将所有RPC转发到服务器的代理

## Server

- Server是承担更多责任的代理：Raft仲裁、维护集群状态、响应RPC请求、和其他datacenter交换WAN gossip、将请求转发给leaders或其他远程datacenter

## Datacenter

- 定义datacenter为一个具有专用的、低延迟、高带宽的网络环境

## Gossip

- Consul建立在Serf之上，提供完整 gossip 协议
- Serf提供成员认证、故障检测、事件广播
- gossip 涉及节点间随机通讯，主要通过UDP协议

## LAN Gossip

- 指本地 gossip 池，所有节点在相同的本地网络或datacenter

## WAN Gossip

- 指广域网 gossip 池，只包括servers. 这些server主要在不同的datacenter，通常通过互联网或广域网进行通讯

## RPC

- Remote Procedure Call

# Internals

## Architecture

![](imgs/001.png)

- Consul支持多datacenter
- 每个datacenter包含 clients  和 servers
- server的数量最好在3-5台
- client数量没有限制，可以轻松扩展至数千甚至数万
- datacenter中每个agent使用gossip 协议，这意味着对于给定的datacenter，存在一个gossip pool包含该datacenter中所有的节点，这样可以：
  - 不需要为clients配置server地址，服务发现自动完成
  - 检测代理程序故障的工作不是放在server上，而是分布式的

## Consensus Protocol

- Consul 使用基于Raft算法的一致性协议

**Raft**

- 关键词
  - Log
    - Raft系统中主要工作单元是 log entry
    - 一致性问题可以分解为日志复制
  - FSM
  - Peer set
  - Quorum
  - Commited Entry
  - Leader