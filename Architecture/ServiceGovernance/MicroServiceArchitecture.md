---
title: 微服务架构
tags:
    - 微服务
    - 架构
categories:
    - 架构
---

# 简介

微服务化是一个比较宽泛的概念，涉及到一个产品生命周期的多个方面，首先它作为一个指导原则指引业务划分、架构解耦等；技术层面实施微服务需要开发测试阶段、运行阶段、发布阶段、部署阶段等一系列基础框架的支撑。我们在享受服务化易扩展易部署等便利性的同时，也面临新的问题，如数据一致性、分布式调用链路追踪、异常定位、日志采集等。<sup>[1]</sup>

# 服务治理框架对比

|            | Dubbo                                                        | Spring Cloud                                             |
| ---------- | ------------------------------------------------------------ | -------------------------------------------------------- |
| 特点       | 性能更好；文档质量更高；配置复杂度稍高；过多依赖第三方组件； | 开箱即用、可扩展性更好；大而全，不够精致；社区活跃度高； |
| 注册中心   | Zookeeper、Nacos、etcd、Consul                               | Eureka、Consul、Zookeeper、etcd                          |
| 调用方式   | RPC                                                          | REST API                                                 |
| Protocol   | Dubbo、JSON、HTTP、REST、Thrift、Redis ...                   | REST，可以整合Thrift等                                   |
| 分布式配置 | Zookeeper、Nacos、etcd、Apollo                               | Spring Cloud Config、Zookeeper、Consu                    |

# 参考

# Refer

- [网易考拉海购Dubbok框架优化详解](https://blog.csdn.net/karamos/article/details/80127976)