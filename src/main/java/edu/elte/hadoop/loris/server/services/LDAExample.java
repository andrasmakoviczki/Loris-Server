package edu.elte.hadoop.loris.server.services;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LDAExample {
	public LDAExample() {

		try {
			System.out.println("Start...");
			Process process = new ProcessBuilder(
					"/usr/bin/spark-submit",
					"--master",
					"yarn-cluster",
					"--class",
					"org.apache.spark.examples.SparkPi",
					"/opt/cloudera/parcels/CDH-5.3.3-1.cdh5.3.3.p0.5/jars/spark-examples-1.2.0-cdh5.3.3-hadoop2.5.0-cdh5.3.3.jar")
					.start();
			System.out.println("Finished...");
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;

			// System.out.printf("Output of running %s is:",
			// Arrays.toString(args));

			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Out...");

	}
}