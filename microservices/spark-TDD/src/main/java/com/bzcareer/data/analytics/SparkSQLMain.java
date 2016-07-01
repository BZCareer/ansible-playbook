package com.bzcareer.data.analytics;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
//Import Spark SQL
import org.apache.spark.sql.hive.HiveContext;
import org.apache.spark.sql.DataFrame;
//Or if you can't have the hive dependencies
import org.apache.spark.sql.SQLContext;
//Import the JavaSchemaRDD
import org.apache.spark.sql.Row;
public class SparkSQLMain {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("SparkSQL Demo");
		JavaSparkContext sc = new JavaSparkContext(conf);
		SQLContext sqlCtx = new HiveContext(sc);
		DataFrame df = sqlCtx.read().json("file:///Users/zhassan/git/monitor.d/microservices/spark-TDD/src/main/resources/tweet1.json");
		df.show();
	}
}
