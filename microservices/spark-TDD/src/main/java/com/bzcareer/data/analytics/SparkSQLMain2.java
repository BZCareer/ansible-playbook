package com.bzcareer.data.analytics;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.api.java.UDF1;
//Import Spark SQL
import org.apache.spark.sql.hive.HiveContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.Column;
import org.apache.spark.sql.DataFrame;
//Or if you can't have the hive dependencies
import org.apache.spark.sql.SQLContext;
//Import the JavaSchemaRDD
import org.apache.spark.sql.Row;

public class SparkSQLMain2 {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName(
				"SparkSQL Demo");
		JavaSparkContext sc = new JavaSparkContext(conf);
		SQLContext sqlCtx = new HiveContext(sc);
		DataFrame df = sqlCtx
				.read()
				.json(args[0]).cache();
		System.out.println("~~~~~~    PRINT SCHEMA    ~~~~~~");
		df.printSchema();
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		sqlCtx.udf().register
		("test",new UDF1<String,Integer>(){

			@Override
			public Integer call(String t1) throws Exception {
				
				return t1.length();
			}
			
		},DataTypes.IntegerType);
		df.registerTempTable("twitter");
		JavaRDD<Row> javaRDD = df.select(new Column("createdAt")).toJavaRDD();
		System.out.println("Count: "+javaRDD.count());
		for (String table :  sqlCtx.tableNames()) {
			System.out.println(table);
		}
		
		System.out.println("UDFS:");
		sqlCtx.sql("select test('createdAt') FROM twitter").show();;
		//.parquet("file:///Users/zhassan/git/monitor.d/microservices/spark-TDD/src/main/resources/tweet1.parquet");
 		//df.write().orc("file:///Users/zhassan/git/monitor.d/microservices/spark-TDD/src/main/resources/tweet1.orc");
	}
}
