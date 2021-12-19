package com.progym.common.model;

public class AddPackageObject {

	private String packageName;
	private Integer days;
	private String gender;
	private Double fees;
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Double getFees() {
		return fees;
	}
	public void setFees(Double fees) {
		this.fees = fees;
	}
	public AddPackageObject(String packageName, Integer days, String gender, Double fees) {
		super();
		this.packageName = packageName;
		this.days = days;
		this.gender = gender;
		this.fees = fees;
	}
	public AddPackageObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
