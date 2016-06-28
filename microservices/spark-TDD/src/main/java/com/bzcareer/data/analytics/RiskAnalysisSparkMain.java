package com.bzcareer.data.analytics;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class RiskAnalysisSparkMain {

	public static void main(String[] args) {
 	SparkConf conf = new SparkConf().setAppName("Delinquent Risk Data Report");
		
		//		SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("Delinquent Risk Data Report");
	
		// Using kryo serialization for improved performance
		conf.set("spark.serializer",
				"org.apache.spark.serializer.KryoSerializer");
		conf.set("spark.kryo.registrator",
				AccountKyroRegistrator.class.getName());

		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<Account> accountReview = sc.parallelize(Arrays
				.asList(new Account.Builder().withAccountNum(12345)
						.withFirstName("Jack").withLastName("Welch")
						.withTotalWealth(100.11).withDebt(72727.11).build(),
						new Account.Builder().withAccountNum(552346)
								.withFirstName("George").withLastName("Cotton")
								.withTotalWealth(3838333.11)
								.withDebt(272772.11).build(),
						new Account.Builder().withAccountNum(523435)
								.withFirstName("Steward")
								.withLastName("Rattler")
								.withTotalWealth(399372.11).withDebt(287272.11)
								.build(),
						new Account.Builder().withAccountNum(123555)
								.withFirstName("Jessy").withLastName("Jackson")
								.withTotalWealth(288272.11).withDebt(2828.11)
								.build()));

		// Find out who has more debt then their totalWealth?
		JavaRDD<List<? extends Object>> map = accountReview.filter(
				(account) -> account.netWorth() < 0).map(
				(account) -> Arrays.asList(account.fname,
						account.account_number, account.debt,
						account.totalWealth, account.netWorth()));
		long numOfDeliqRisk = map.count();

		StringBuilder sb = new StringBuilder();
		sb.append(System.lineSeparator()).append("Report Results:")
				.append(System.lineSeparator()).append("--------------")
				.append(System.lineSeparator())
				.append("Number of accounts at risk of delinquent status: ")
				.append(numOfDeliqRisk);

		map.collect().forEach(
				(items) -> {
					sb.append(System.lineSeparator()).append("Client ")
							.append(items.get(0)).append(" with accountNum: ")
							.append(items.get(1)).append(" owes ")
							.append(items.get(2))
							.append(" has a total funds: ")
							.append(items.get(3))
							.append(" total networth of: ")
							.append(items.get(4));
				});

		System.out.println(sb.toString());

	}
}
