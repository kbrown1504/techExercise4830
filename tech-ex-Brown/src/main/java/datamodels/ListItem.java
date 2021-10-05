package datamodels;

import java.util.Date;

public class ListItem {
	private Date dueDate;
	private String title;
	private String desc;
	
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(int year, int month, int day, int hours, int mins) {
		Date newDate = new Date(year, month, day, hours, mins);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		if(title.length() > 0)
			this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}