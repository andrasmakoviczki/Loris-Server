package edu.elte.hadoop.loris.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.jws.WebService;

import edu.elte.hadoop.loris.server.downloader.ChannelConfig;
import edu.elte.hadoop.loris.server.downloader.RSSChannel;
import edu.elte.hadoop.loris.server.downloader.RSSChannels;
import edu.elte.hadoop.loris.server.services.AppConfig;
import edu.elte.hadoop.loris.server.services.HadoopFile;
import edu.elte.hadoop.loris.server.services.HiveAccess;
import edu.elte.hadoop.loris.server.services.LDAExample;
import edu.elte.hadoop.loris.server.services.WordCount2;

@WebService(endpointInterface = "edu.elte.hadoop.loris.server.MyService")
public class MyServiceImpl implements MyService {

	@Override
	public String GetConfigProperty(String key) {
		return new AppConfig().ReadProperty(key);
	}

	@Override
	public String getRSS(String channelAddress) {
		return new ChannelConfig().ReadChannelFile(channelAddress).getTitle();
	}

	@Override
	public RSSChannels getRSSList() {
		return new ChannelConfig().ListChannels();
	}

	@Override
	public ArrayList<String> hadoop() {
		ArrayList<String> al = new ArrayList<String>();
		String s = null;
		List<String> cont = null;
		HadoopFile h = new HadoopFile();

		h.hdfsls(al, s, cont);

		// al.add("Hello");

		return al;
	}

	// WordCount
	@Override
	public List<String> hadoopContent() {
		ArrayList<String> al = new ArrayList<String>();

		String ip = new String("172.31.14.253");
		String port = new String("8020");

		String filename = new String("book");
		String appender = new String("/part-r-00000");

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
				.format(Calendar.getInstance().getTime());

		String in = new String("hdfs://" + ip + ":" + port + "/user/ubuntu/"
				+ filename);
		String out = new String("hdfs://" + ip + ":" + port + "/user/ubuntu/"
				+ filename + "_out" + timeStamp);

		List<String> cont = new ArrayList<String>();
		HadoopFile h = new HadoopFile();
		WordCount2 wc = new WordCount2();

		try {
			wc.WordCount(in, out);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
		}

		out = out + appender;

		h.hdfsls(al, out, cont);

		return cont;
	}

	@Override
	public void LDA() {
		LDAExample lda = new LDAExample();
	}

	@Override
	public String reverser(String value) {
		if (value == null)
			return null;
		return new StringBuffer(value).reverse().toString();
	}

	@Override
	public void SetConfigProperty(String key, String value) {
		new AppConfig().ChangeProperty(key, value);
	}

	public void setRSS(RSSChannel channel) {
		/*
		 * try{ new ChannelConfig().UpdateChannelFile(channel); } catch
		 * (NullPointerException e) { new
		 * ChannelConfig().WriteChannelFile(channel.getAddress()); }
		 */
	}

	@Override
	public void SparkSQL() {
		HiveAccess hive = new HiveAccess();
	}

}
