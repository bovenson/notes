- reset offset

  ```c++
  class ExampleRebalanceCb : public RdKafka::RebalanceCb {
  public:
    void rebalance_cb (RdKafka::KafkaConsumer *consumer,
  		     RdKafka::ErrorCode err,
                       std::vector<RdKafka::TopicPartition*> &partitions) {
      if (err == RdKafka::ERR__ASSIGN_PARTITIONS) {
        RdKafka::TopicPartition *part;
        // find the partition, through std::find() or other means
        ...
        if (part)
           part->set_offset(1234);
        consumer->assign(partitions);
      } else {
        consumer->unassign();
      }
    }
  };
  ```

  - [Ref 1](https://github.com/edenhill/librdkafka/wiki/Manually-setting-the-consumer-start-offset)

