package edu.elte.hadoop.loris.server.downloader;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

//@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class RSSChannel {
	private String address;
	private String copyright;
	private String description;
	private String feedType;
	private String language;
	private Date lastPublished;
	private Integer size;
	private String title;

	public RSSChannel() {
		// TODO Auto-generated constructor stub
	}

	public RSSChannel(RSSChannel rssChannel) {
		this.title = rssChannel.title;
		this.address = rssChannel.address;
		this.language = rssChannel.language;
		this.lastPublished = rssChannel.lastPublished;
		this.size = rssChannel.size;
		this.description = rssChannel.description;
		this.feedType = rssChannel.feedType;
		this.copyright = rssChannel.copyright;
	}

	public RSSChannel(String title, String address, String language, Date date,
			Integer channelSize, String description, String feedType,
			String copyright) {
		super();

		this.title = "".equals(title) ? "null" : title;
		this.address = "".equals(address) ? "null" : address;
		this.language = "".equals(language) ? "null" : language;
		this.lastPublished = "".equals(date) ? new Date() : date;
		this.size = "".equals(channelSize) ? 0 : channelSize;
		this.description = "".equals(description) ? "null" : description;
		this.feedType = "".equals(feedType) ? "null" : feedType;
		this.copyright = "".equals(copyright) ? "null" : copyright;
	}

	public String getAddress() {
		return address;
	}

	public String getCopyright() {
		return copyright;
	}

	public String getDescription() {
		return description;
	}

	public String getFeedType() {
		return feedType;
	}

	public String getLanguage() {
		return language;
	}

	public Date getLastPublished() {
		return lastPublished;
	}

	public Integer getSize() {
		return size;
	}

	public String getTitle() {
		return title;
	}

	// @XmlAttribute
	public void setAddress(String address) {
		this.address = address;
	}

	// @XmlElement
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	// @XmlElement
	public void setDescription(String description) {
		this.description = description;
	}

	// @XmlElement
	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}

	// @XmlElement
	public void setLanguage(String language) {
		this.language = language;
	}

	// @XmlElement
	public void setLastPublished(Date lastPublished) {
		this.lastPublished = lastPublished;
	}

	public void setRSSChannel(RSSChannel rssChannel) {
		this.title = rssChannel.title;
		this.address = rssChannel.address;
		this.language = rssChannel.language;
		this.lastPublished = rssChannel.lastPublished;
		this.size = rssChannel.size;
		this.description = rssChannel.description;
		this.feedType = rssChannel.feedType;
		this.copyright = rssChannel.copyright;
	}

	// @XmlElement
	public void setSize(Integer size) {
		this.size = size;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(title);
		builder.append(",");
		builder.append(address);
		builder.append(",");
		builder.append(language);
		builder.append(",");
		builder.append(new DateFormat(lastPublished).toString());
		builder.append(",");
		builder.append(size);
		builder.append(",");
		builder.append(description);
		builder.append(",");
		builder.append(feedType);
		builder.append(",");
		builder.append(copyright);
		return builder.toString();
	}
}