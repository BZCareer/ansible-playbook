package com.bzcareer.data.analytics;

import java.time.Duration;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

//nc -l 0.0.0.0 7766
public class SparkStreamingMain {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("SparkStreaming Demo");
 		JavaStreamingContext jstream = new JavaStreamingContext(conf, Durations.seconds(1));
		JavaReceiverInputDStream<String> data = jstream.socketTextStream("0.0.0.0", 7766);
		JavaDStream<String> errList = data.filter(new Function<String, Boolean>() {
			@Override
			public Boolean call(String str) throws Exception {
				return str.contains("error");
			}
		});
		errList.print();
		jstream.start();
		jstream.awaitTermination();
	}
}
