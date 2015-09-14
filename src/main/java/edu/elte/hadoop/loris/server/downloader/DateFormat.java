package edu.elte.hadoop.loris.server.downloader;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {

	private Date formattedDate;

	private SimpleDateFormat formatter = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	DateFormat(Date date) {
		try {
			this.formattedDate = formatter.parse(formatter.format(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	DateFormat(String date) {
		try {
			this.formattedDate = formatter.parse(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getFormatDate() {
		return formatter.format(formattedDate);
	}

	public Date getFormattedDate() {
		return formattedDate;
	}

	public void setFormattedDate(Date formattedDate) {
		this.formattedDate = formattedDate;
	}

	@Override
	public String toString() {
		return formatter.format(formattedDate);
	}
}
