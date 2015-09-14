package edu.elte.hadoop.loris.server.downloader;

import org.apache.avro.Schema;
import org.apache.avro.mapred.AvroJob;
import org.apache.avro.mapred.AvroWrapper;
import org.apache.avro.reflect.ReflectData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RunningJob;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import edu.elte.hadoop.loris.server.services.AppConfig;

public class AvroWriter extends Configured implements Tool {

	String myHadoopIp = new String(new AppConfig().ReadProperty("ipaddress"));
	String myHadoopPort = new String(new AppConfig().ReadProperty("port"));

	@Override
	public int run(String[] arg0) throws Exception {
		// TODO: Path!!!
		String tmppath = "file:///home/ubuntu/workspace/loris-server/";
		tmppath = "file:///home/andris/Documents/workspace/loris-server/";
		String in = "testfile";
		String out = "newfile";
		Path inputPath = new Path(tmppath + in);
		Path outputPath = new Path(tmppath + out);

		Schema schema = ReflectData.get().getSchema(RSSFeed.class);

		Configuration conf = getConf();
		JobConf rssFeedJob = new JobConf(conf, getClass());
		rssFeedJob.setJobName("Avro HelloWorld");
		rssFeedJob.setNumReduceTasks(0);
		rssFeedJob.setMapperClass(RSSFeedMapper.class);
		rssFeedJob.setMapOutputKeyClass(AvroWrapper.class);
		rssFeedJob.setMapOutputValueClass(NullWritable.class);
		rssFeedJob.setInputFormat(TextInputFormat.class);
		AvroJob.setOutputSchema(rssFeedJob, schema);

		FileInputFormat.setInputPaths(rssFeedJob, inputPath);
		FileOutputFormat.setOutputPath(rssFeedJob, outputPath);

		RunningJob job = JobClient.runJob(rssFeedJob);

		if (job.isSuccessful()) {
			return 0;
		}

		return 1;
	}

	public int Start() throws Exception {
		return ToolRunner.run(new AvroWriter(), null);
	}
}
