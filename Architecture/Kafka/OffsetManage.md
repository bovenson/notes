---
title: Kafka Offset管理
---

# 定义

偏移量是，在一个分区内下一条需要发送给消费者的消息位置。Kafka包括两种类型的offset：

- Current offset
  - 当前偏移量是指向Kafka在最近一次轮询中已发送给消费者的最后一条记录的指针。所以Current Offset消费者不会获取到相同的记录
- Committed offset
  - Commited Offset 是消费者已确认处理的位置

**总结**

- Current offset：Send Records：用来避免向同一个消费者发送相同的数据
- Committed offset：Processed Records：避免在分区平衡的状况下，向新的消费者发送相同的记录

Commited Offset在分区平衡的情况下至关重要。分区平衡的情况下，新的消费者被分配到新的分区时，Committed Offset可以解决从哪里开始、哪些记录已经被消费的问题。

# Commit an offset

Current offset 和 committed offset由Kafka管理。提交一个offset的方式有两种：

- Auto commit
- Manual-commit

## Auto commit

通过两个属性来控制Auto-Commit：

- enable.auto.commit
  - 默认为true，所有 auto-commit 默认开启
- auto.commit.interval.ms
  - 定义auto-commit时间间隔

auto-commit的一个问题是，在提交之前，数据可能会被其他消费者处理。这种情况下，没办法彻底避免消息被重复处理。

## Manual commit

可以使用manual-commit的方式解决auto-commit的问题。manual-commit由两种方式：

- Commit Sync
- Commit Async

一个简单示例。

```java
import java.util.*;
import java.io.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class ManualConsumer{

    public static void main(String[] args) throws Exception{

        String topicName = "SupplierTopic";
        String groupName = "SupplierTopicGroup";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9093");
        props.put("group.id", groupName);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "SupplierDeserializer");
        props.put("enable.auto.commit", "false");

        KafkaConsumer<String, Supplier> consumer = null;

        try {
            consumer = new KafkaConsumer<>(props);
            consumer.subscribe(Arrays.asList(topicName));

            while (true){
                ConsumerRecords<String, Supplier> records = consumer.poll(100);
                for (ConsumerRecord<String, Supplier>record : records){
                    System.out.println("Supplier id= " + String.valueOf(record.value().getID()) + 
                                       " Supplier  Name = " + record.value().getName() + 
                                       " Supplier Start Date = " + record.value().getStartDate().toString());
                }
                consumer.commitAsync();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally{
            consumer.commitSync();
            consumer.close();
        }
    }
}                           
```

这里：

- 使用异步提交
- 当出现异常或退出时，使用同步提交

# 参考

- [Kafka Foundation](https://www.learningjournal.guru/courses/kafka/kafka-foundation-training/offset-management/)