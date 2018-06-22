package com.bovenson.spark.streaming;

/**
 * Deploy: spark-submit --packages org.apache.spark:spark-streaming-kafka-0-8_2.11:2.2.0 SparkStreamingRecv.java
 */

import kafka.serializer.StringDecoder;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.*;
import org.apache.spark.streaming.Durations;
import scala.Tuple2;

import java.util.*;

public class SparkStreamingRecv {

    public static void main(String[] args) throws InterruptedException {
        // 初始化 Spark
        SparkConf conf = new SparkConf().setAppName("SparkStreamRecvExample");
        JavaStreamingContext sc = new JavaStreamingContext(conf, Durations.seconds(3));

        // kafka params
        Map<String, String> kafkaParams = new HashMap<String, String>();
        kafkaParams.put("metadata.broker.list", "localhost:9092");

        Set<String> topicsSet = new HashSet<String>();
        topicsSet.add("test");

        // Kafka
        JavaPairInputDStream<String, String> directKafkaStream = KafkaUtils.createDirectStream(
                sc,
                String.class,
                String.class,
                StringDecoder.class,
                StringDecoder.class,
                kafkaParams,
                topicsSet);

        // 处理数据
        directKafkaStream.foreachRDD(new VoidFunction<JavaPairRDD<String, String>>() {
            public void call(JavaPairRDD<String, String> stringStringJavaPairRDD) throws Exception {
                List<Tuple2<String, String>> list = stringStringJavaPairRDD.collect();
                for (Tuple2<String, String> tuple : list) {
                    System.out.println(tuple._2);
                }
            }
        });

        // directKafkaStream.print();   // 打印
        sc.start();
        sc.awaitTermination();
    }
}
