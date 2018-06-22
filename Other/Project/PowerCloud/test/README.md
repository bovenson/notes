# 启动
## Kafka
```shell
cd ~/Public/installed/kafka/
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties
```

## emq

```shell
docker start emq20
```

进入控制台

```shell
docker exec -it emq20 /bin/sh
```

## 提交spark任务

```shell
spark-submit --packages org.apache.spark:spark-streaming-kafka-0-8_2.11:2.2.0 ./spark-streaming-recv.py
```

## paho
```shell
sudo pip3 install paho-mqtt
```
