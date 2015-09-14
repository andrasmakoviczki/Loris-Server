package edu.elte.hadoop.loris.server.downloader;

import org.apache.avro.Schema;
import org.apache.avro.mapred.AvroJob;
import org.apache.avro.reflect.ReflectData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.RunningJob;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class AvroReader extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {

		String tmppath = "file:///home/ubuntu/workspace/loris-server/";
		tmppath = "file:///home/andris/Documents/workspace/loris-server/";
		String in = "newfile";
		String out = "outfile";
		Path inputPath = new Path(tmppath + in);
		Path outputPath = new Path(tmppath + out);

		Schema schema = ReflectData.get().getSchema(RSSFeed.class);

		Configuration conf = getConf();
		JobConf rssFeedJob = new JobConf(conf, getClass());
		rssFeedJob.setJobName("Avro Reader");
		rssFeedJob.setNumReduceTasks(0);
		AvroJob.setInputSchema(rssFeedJob, schema);
		rssFeedJob.setMapperClass(RSSFeedMapperAvro.class);
		AvroJob.setReflect(rssFeedJob);
		rssFeedJob.setOutputKeyClass(Text.class);
		rssFeedJob.setOutputValueClass(NullWritable.class);
		rssFeedJob.setOutputFormat(TextOutputFormat.class);
		FileInputFormat.setInputPaths(rssFeedJob, inputPath);
		FileOutputFormat.setOutputPath(rssFeedJob, outputPath);
		RunningJob job = JobClient.runJob(rssFeedJob);
		if (job.isSuccessful()) {
			return 0;
		}
		return 1;
	}

	public int start() throws Exception {
		return ToolRunner.run(new AvroReader(), null);
	}
}
