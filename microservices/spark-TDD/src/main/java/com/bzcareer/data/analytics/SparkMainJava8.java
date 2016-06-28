package com.bzcareer.data.analytics;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class SparkMainJava8 {
	public static void main(String[] args) {
		String logFile = "/usr/local/spark/README.md";
		SparkConf conf = new SparkConf().setAppName("Search Data Report");
		
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> logData = sc.textFile(logFile).cache();
		
		JavaRDD<String> filterS = logData.filter(line -> line.contains("s"));
		JavaRDD<String> filterD = logData.filter(line -> line.contains("d"));
		JavaRDD<String> union = filterS.union(filterD);
		JavaRDD<String> flatMap = logData.flatMap(line ->  Arrays.asList(line.split(" "))).filter(line -> line.length() != 0);
		flatMap.take(10).forEach(line -> System.out.println("flatmap:'"+ line+"'"));
		long s = filterS.count();
		long d = filterD.count();
		long count = union.count();
		
		System.out.println("searching for letter s found: " + s);
		System.out.println("searching for the letter d  found: " + d);
		System.out.println("searching for the letter d and s found: " + count);
		System.out.println("Examples of letter s found:");
		filterS.take(10).forEach(line -> System.out.println("line:"+ line));//foreach( line -> System.out.println("line: "+ line));
		System.out.println();
	}

}
