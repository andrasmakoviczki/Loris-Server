package edu.elte.hadoop.loris.server.downloader;

import java.net.URL;
import java.util.List;

import com.google.common.collect.Lists;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.fetcher.FeedFetcher;
import com.sun.syndication.fetcher.impl.FeedFetcherCache;
import com.sun.syndication.fetcher.impl.HashMapFeedInfoCache;
import com.sun.syndication.fetcher.impl.HttpURLFeedFetcher;

public class RSSFeedDownload {
	public RSSFeedDownload() {
		try {
			FeedFetcherCache feedInfoCache = HashMapFeedInfoCache.getInstance();
			FeedFetcher fetcher = new HttpURLFeedFetcher(feedInfoCache);

			List<RSSFeed> result = Lists.newLinkedList();
			URL url = new URL("http://index.hu/24ora/rss");
			SyndFeed feed = fetcher.retrieveFeed(url);

			List<SyndEntry> entries = feed.getEntries();
			int channelSize = entries.size();

			/*
			 * RSSChannel channel = new RSSChannel(feed.getTitle(),
			 * feed.getLink(), feed.getLanguage(), feed.getPublishedDate(),
			 * channelSize, feed.getDescription(), feed.getFeedType(),
			 * feed.getCopyright());
			 */

			for (SyndEntry entry : entries) {
				String category = entry.getCategories().get(0).toString();
				result.add(new RSSFeed(entry.getTitle(), entry.getLink(), entry
						.getPublishedDate(), entry.getDescription().getValue(),
						entry.getDescription().getType(), category.substring(
								category.lastIndexOf('=') + 1,
								category.length() - 1)));
			}

			for (RSSFeed item : result) {
				System.out.println(item);
			}

			/*
			 * Writer w = null; try { w = new BufferedWriter(new
			 * OutputStreamWriter(new FileOutputStream("testfile"))); for
			 * (RSSFeed item : result) { w.write(item.toString()); } } catch
			 * (Exception e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); } finally { w.close(); }
			 */

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("ERROR: " + ex.getMessage());
		}
	}
}
