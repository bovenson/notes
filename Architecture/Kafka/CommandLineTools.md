---
title: Kafka Command Line Tools
tags:
	- Kafka
categories:
	- Architecture
---

# kafka-topics.sh

## 清空topic

```shell
kafka-configs.sh --zookeeper <zkhost>:2181 --alter --entity-type topics --entity-name <topic name> --add-config retention.ms=1000
```

