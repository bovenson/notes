#!/usr/bin python3
# coding: utf-8

"""
AUTHOR: bovenson
EMAIL: szhkai@qq.com
FILE: spark-streaming-recv.py
DATE: 17-8-28 上午9:53
DESC:
RUN: spark-submit --packages org.apache.spark:spark-streaming-kafka-0-8_2.11:2.2.0 ./spark-streaming-recv.py
"""
from pyspark import SparkContext
from pyspark.streaming import StreamingContext
from pyspark.streaming.kafka import KafkaUtils

kafka_params = {
    'metadata.broker.list': 'localhost:9092'
}

if __name__ == '__main__':
    sc = SparkContext(appName='NetworkWordCount')
    ssc = StreamingContext(sc, 5)

    kafka_streams = KafkaUtils.createDirectStream(ssc=ssc, topics=['test'], kafkaParams=kafka_params)

    kafka_streams.pprint()

    ssc.start()
    ssc.awaitTermination()
