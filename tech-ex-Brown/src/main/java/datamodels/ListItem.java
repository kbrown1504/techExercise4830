package datamodels;

import java.util.Date;

public class ListItem {
	//using a string bc that is what the insert for html will expect.
	//TODO: Maybe need data validation? Date picker in html should format dates in most cases.
	private String dueDate;
	private String title;
	private String desc;
	
	public ListItem(String dateIn, String titleIn, String descIn) {
		this.setDueDate(dateIn);
		this.setTitle(titleIn);
		this.setDesc(descIn);
	}
	
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dateIn) {
		this.dueDate = dateIn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}