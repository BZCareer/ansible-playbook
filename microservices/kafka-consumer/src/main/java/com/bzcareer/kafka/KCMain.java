package com.bzcareer.kafka;

public class KCMain {

	public static void main(String[] args) {
		KafkaMsgReciever krec= new KafkaMsgReciever("hadoopmaster:9092,hadoopdata1:9092,hadoopdata2:9092");
		krec.consume();
	}
}
