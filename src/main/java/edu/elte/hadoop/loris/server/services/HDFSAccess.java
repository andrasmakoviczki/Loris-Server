package edu.elte.hadoop.loris.server.services;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.apache.hadoop.mapreduce.Job;
import org.apache.log4j.Logger;

public class HDFSAccess {
	Logger logger = Logger.getLogger(HDFSAccess.class);

	public HDFSAccess() {
		String myHadoopIp = new String(
				new AppConfig().ReadProperty("ipaddress"));
		String myHadoopPort = new String(new AppConfig().ReadProperty("port"));

		try {
			Configuration conf = new Configuration();
			Job.getInstance(conf);
			conf.set("hadoop.job.ugi", "ubuntu");
			conf.set("fs.defaultFS", "hdfs://" + myHadoopIp + ":8020");

			FileSystem fs = FileSystem.get(conf);

			RemoteIterator<LocatedFileStatus> fileStatusListIterator = fs
					.listFiles(new Path("hdfs://" + myHadoopIp + ":8020/"),
							true);

			while (fileStatusListIterator.hasNext()) {
				LocatedFileStatus fileStatus = fileStatusListIterator.next();
				System.out.println(fileStatus.getPath());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
