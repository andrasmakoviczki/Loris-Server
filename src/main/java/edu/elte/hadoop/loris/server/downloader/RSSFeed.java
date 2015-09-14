package edu.elte.hadoop.loris.server.downloader;

import java.util.Date;

public class RSSFeed {

	private String categoryName;

	private String descriptionType;
	private String descriptionValue;
	private String link;
	private Date publishedDate;
	private String title;
	public RSSFeed() {
	}

	public RSSFeed(String title, String link, Date publishedDate,
			String descriptionValue, String descriptionType, String categoryName) {
		super();
		this.title = "".equals(title) ? "null" : title;
		this.link = "".equals(link) ? "null" : link;
		this.publishedDate = "".equals(publishedDate) ? new Date()
				: publishedDate;
		this.descriptionValue = "".equals(descriptionValue) ? "null"
				: descriptionValue;
		this.descriptionType = "".equals(descriptionType) ? "null"
				: descriptionType;
		this.categoryName = "".equals(categoryName) ? "null" : categoryName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public String getDescriptionType() {
		return descriptionType;
	}

	public String getDescriptionValue() {
		return descriptionValue;
	}

	public String getLink() {
		return link;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public String getTitle() {
		return title;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setDescriptionType(String descriptionType) {
		this.descriptionType = descriptionType;
	}

	public void setDescriptionValue(String descriptionValue) {
		this.descriptionValue = descriptionValue;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(title);
		builder.append(",");
		builder.append(link);
		builder.append(",");
		builder.append(new DateFormat(publishedDate).toString());
		builder.append(",");
		builder.append(descriptionValue);
		builder.append(",");
		builder.append(descriptionType);
		builder.append(",");
		builder.append(categoryName);
		return builder.toString();
	}
}
