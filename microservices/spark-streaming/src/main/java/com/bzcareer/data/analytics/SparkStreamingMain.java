package com.bzcareer.data.analytics;

import java.util.HashMap;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

public class SparkStreamingMain {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("SparkStreaming Demo");
 		JavaStreamingContext jstream = new JavaStreamingContext(conf, Durations.seconds(1));

		Map<String, Integer> topics= new HashMap<>();
		topics.put("logtest",1);
		String zkList="localhost:2181";
		String group="sparkanalytics";
		JavaPairReceiverInputDStream<String, String> kmsgStream = KafkaUtils.createStream(jstream, zkList, group, topics);
		kmsgStream.print();
		jstream.start();
		jstream.awaitTermination();
	}
}
