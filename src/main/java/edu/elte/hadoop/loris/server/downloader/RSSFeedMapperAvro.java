package edu.elte.hadoop.loris.server.downloader;

import java.io.IOException;

import org.apache.avro.mapred.AvroWrapper;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class RSSFeedMapperAvro extends MapReduceBase implements
		Mapper<AvroWrapper<RSSFeed>, NullWritable, Text, NullWritable> {
	private Text text = new Text();

	@Override
	public void map(AvroWrapper<RSSFeed> key, NullWritable value,
			OutputCollector<Text, NullWritable> outputcollector,
			Reporter reporter) throws IOException {
		text.set(key.datum().toString());
		outputcollector.collect(text, NullWritable.get());
	}
}
