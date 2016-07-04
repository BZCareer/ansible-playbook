package com.bzcareer.data.analytics;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.rdd.MapPartitionsRDD;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import scala.Tuple2;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public class SparkStreamingMain {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(
				"SparkStreaming Demo");
		JavaStreamingContext jstream = new JavaStreamingContext(conf,
				Durations.seconds(1));

		Map<String, Integer> topics = new HashMap<>();
		topics.put("logtest", 1);
		String zkList = "hadoopmaster:2181,hadoopdata1:2181,hadoopdata2:2181";
		String group = "sparkanalytics";
		JavaPairReceiverInputDStream<String, String> kmsgStream = KafkaUtils
				.createStream(jstream, zkList, group, topics);
		// kmsgStream.print();
		ArrayList<String> list = new ArrayList<String>();
		JavaDStream<String> lines = kmsgStream
				.map(new Function<Tuple2<String, String>, String>() {
					@Override
					public String call(Tuple2<String, String> tuple2) {
						list.add(tuple2._2().toString());
						return tuple2._2();
					}
				});
		lines.foreachRDD((rdd) -> {
			List<String> collect = rdd.collect();
			for (String str : collect) {
				JsonElement jsonElement = extractJsonElement(str, "message");
				String replacement = jsonElement.toString().replace("'", "\"");
				WebAccessRecords jsonObj = toAccessRecordObject(replacement);
				System.out.println("Items: " + jsonObj);

			}
		});
		jstream.start();
		jstream.awaitTermination();
	}

	private static WebAccessRecords toAccessRecordObject(String items) {
		WebAccessRecords logs = null;
		try {
			Gson gson = new Gson();
			String stripOutQuotes = items.substring(1,items.length()-1);
			if(stripOutQuotes.charAt(0) != '{'){
				return null;
			}
			JsonReader reader = new JsonReader(new StringReader(stripOutQuotes));
			reader.setLenient(true);
			logs = gson.fromJson(reader, WebAccessRecords.class);
		} catch (Exception e) {
			System.err.println("Invalid String:"+ items.substring(1,items.length()-2));
			System.err.println(e);
		}
		return logs;
	}


	private static JsonElement extractJsonElement(String items, String searchKey) {
		JsonParser parser = new JsonParser();
		JsonObject json = parser.parse(items).getAsJsonObject();
		JsonElement jsonElement = json.get(searchKey);
		return jsonElement;
	}
}
