package com.bzcareer.kafka;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import kafka.utils.Json;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import scala.util.parsing.json.JSONObject;

import com.google.gson.JsonObject;

public class KafkaMsgReciever {
	Properties props = new Properties();
	KafkaConsumer<String, String> consumer;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(KafkaMsgReciever.class);

	public KafkaMsgReciever(String brokerlist) {
		props.put("bootstrap.servers", brokerlist);
		props.put("group.id", "monitordconsumer");
		props.put("key.deserializer",
				"org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer",
				"org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(
				props);

	}

	public void consume() {

		consumer.subscribe(Collections.singletonList("logtest"));
		try {
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(100);
				for (ConsumerRecord<String, String> record : records) {
					if (record == null){
						consumer.close();
						
					}
						System.out.println("record value: " + record.value());
					// System.out.printf("topic = %s, partition = %s, offset = %d, customer = %s, country = %s\n",
					// record.topic(), record.partition(), record.offset(),
					// record.key(), record.value());

					// Object updatedCount = 1;
					// Map<String,Object> custCountryMap= new HashMap<>();
					// if (custCountryMap.containsValue(record.value())) {
					// updatedCount = custCountryMap.get(record.value()) + 1;
					// }
					// custCountryMap.put(record.value(), updatedCount);
					// JsonObject json = new JSONObject(custCountryMap);
					// System.out.println(json.toString(4)) ;
				}
			}
		} catch (Exception e) {
			System.out.println("exception thrown");
		} finally {
			consumer.close();
		}
	}
}
