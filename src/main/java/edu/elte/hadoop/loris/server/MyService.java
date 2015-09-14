package edu.elte.hadoop.loris.server;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import edu.elte.hadoop.loris.server.downloader.RSSChannels;

@WebService
public interface MyService {
	@WebMethod
	String GetConfigProperty(String key);

	@WebMethod
	String getRSS(String channelAddress);

	@WebMethod
	RSSChannels getRSSList();

	@WebMethod
	ArrayList<String> hadoop();

	@WebMethod
	List<String> hadoopContent();

	@WebMethod
	void LDA();

	@WebMethod
	String reverser(String value);

	@WebMethod
	void SetConfigProperty(String key, String value);

	@WebMethod
	void SparkSQL();
}
