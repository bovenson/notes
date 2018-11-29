---
title: 系统数据流
tags:
	- 系统数据流
categories:
	- 推荐系统
---

# 系统

## 架构

- Online Domain
  - User
  - Feed Api
  - Reanking
    - Multi Queue Merge
  - Matching
    - candidate & recall
  - KV Store
    - HBase
    - Redis
  - Content Pool
    - news
    - video
    - pic
- Offline Domain
  - Data Bus
  - Processor
    - Streaming Process
    - Batch Process
    - Model Training
    - UserProfile & Indexing & ItemProfile

## 数据流

- User Action Logs
- Data Bus
  - scribe
  - kafka
  - hdfs
- Processor
  - profile & index & etc
    - KV Store
  - Models
    - Multi Target Prediction Model Scoring
    - Ranking & Matching