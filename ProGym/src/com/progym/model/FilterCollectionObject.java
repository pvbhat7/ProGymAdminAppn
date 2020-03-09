package com.progym.model;

public class FilterCollectionObject {

	private String filter;
	private String gender;
	private String month;
	private String year;
	private String date;
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public FilterCollectionObject(String filter, String gender, String month, String year, String date) {
		super();
		this.filter = filter;
		this.gender = gender;
		this.month = month;
		this.year = year;
		this.date = date;
	}
	
	
	
	
}
