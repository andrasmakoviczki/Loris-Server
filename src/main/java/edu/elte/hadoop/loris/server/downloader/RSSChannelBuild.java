package edu.elte.hadoop.loris.server.downloader;

import java.net.URL;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.fetcher.FeedFetcher;
import com.sun.syndication.fetcher.impl.FeedFetcherCache;
import com.sun.syndication.fetcher.impl.HashMapFeedInfoCache;
import com.sun.syndication.fetcher.impl.HttpURLFeedFetcher;

public class RSSChannelBuild {

	private RSSChannel channel;
	private String channelName;

	public RSSChannelBuild(String channelName) {
		super();
		this.channelName = channelName;

		try {
			FeedFetcherCache feedInfoCache = HashMapFeedInfoCache.getInstance();
			FeedFetcher fetcher = new HttpURLFeedFetcher(feedInfoCache);

			URL url = new URL(channelName);
			SyndFeed feed = fetcher.retrieveFeed(url);

			List<SyndEntry> entries = feed.getEntries();
			int channelSize = entries.size();

			channel = new RSSChannel(feed.getTitle(), feed.getLink(),
					feed.getLanguage(), feed.getPublishedDate(), channelSize,
					feed.getDescription(), feed.getFeedType(),
					feed.getCopyright());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public RSSChannel getChannel() {
		return channel;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannel(RSSChannel channel) {
		this.channel = channel;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

}
