---
title: Kafka Command Line Tools
tags:
	- Kafka
categories:
	- Architecture
---

# list topic

```shell
$ ./bin/kafka-topics.sh --list --zookeeper server.zk
__consumer_offsets
demo_kafka_topic_1
model_diff_update_111
model_diff_update_156
model_diff_update_785
model_diff_update_802
```

# 清空topic

```shell
kafka-configs.sh --zookeeper <zkhost>:2181 --alter --entity-type topics --entity-name <topic name> --add-config retention.ms=1000
```

# 查看topic的partition信息

```shell
$ ./bin/kafka-topics.sh --zookeeper c3cloudsrv.zk.hadoop.srv:11000/kafka/c3cloudsrv-feeds --describe --topic model_diff_update_111
Topic:model_diff_update_111	PartitionCount:1	ReplicationFactor:1	Configs:retention.ms=300000
	Topic: model_diff_update_111	Partition: 0	Leader: 1	Replicas: 1	Isr: 1
```

