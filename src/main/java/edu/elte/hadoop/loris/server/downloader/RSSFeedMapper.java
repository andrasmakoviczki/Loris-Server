package edu.elte.hadoop.loris.server.downloader;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.avro.mapred.AvroWrapper;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class RSSFeedMapper extends MapReduceBase implements
		Mapper<LongWritable, Text, AvroWrapper, NullWritable> {
	private SimpleDateFormat dateFormatter = new SimpleDateFormat(
			"yyyy-MM-dd:HH:mm:ss");
	private AvroWrapper<RSSFeed> outpuRecord = new AvroWrapper<RSSFeed>();
	private RSSFeed rssFeed = new RSSFeed();

	@Override
	public void map(LongWritable key, Text value,
			OutputCollector<AvroWrapper, NullWritable> outputCollector,
			Reporter reporter) throws IOException {
		String[] tokens = value.toString().split(",");
		String title = tokens[0];
		String link = tokens[1];
		String publishedDateRaw = tokens[2];
		String description = tokens[3];
		Date publishedDate = null;

		try {
			publishedDate = dateFormatter.parse(publishedDateRaw);
		} catch (ParseException e) {
			return;
		}

		rssFeed.setTitle(title);
		rssFeed.setLink(link);
		rssFeed.setPublishedDate(publishedDate);
		rssFeed.setDescriptionType(description);

		outpuRecord.datum(rssFeed);
		outputCollector.collect(outpuRecord, NullWritable.get());
	}
}
