package edu.elte.hadoop.loris.server.downloader;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RSSChannels {
	@XmlElement(name = "rsschannel", required = true)
	private List<RSSChannel> rssChannels;

	public RSSChannels() {
		rssChannels = new ArrayList<RSSChannel>();
	}

	public List<RSSChannel> getRssChannels() {
		return rssChannels;
	}

	public void setRSSChannels(List<RSSChannel> rssChannels) {
		this.rssChannels = rssChannels;
	}

}