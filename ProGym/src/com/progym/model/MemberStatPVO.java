package com.progym.model;

public class MemberStatPVO implements Comparable<MemberStatPVO>{
	
	private int id;
	private String name;
	private String daysRemaining;
	private String referPoints;
	private String image;
	private String gender;
	private String color;
	private String paymentStatus;
	private String profileActiveFlag;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDaysRemaining() {
		return daysRemaining;
	}
	public void setDaysRemaining(String daysRemaining) {
		this.daysRemaining = daysRemaining;
	}
	public String getReferPoints() {
		return referPoints;
	}
	public void setReferPoints(String referPoints) {
		this.referPoints = referPoints;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	@Override
	public int compareTo(MemberStatPVO o) {
		return this.getDaysRemaining().compareTo(o.getDaysRemaining());
	}
	public String getProfileActiveFlag() {
		return profileActiveFlag;
	}
	public void setProfileActiveFlag(String profileActiveFlag) {
		this.profileActiveFlag = profileActiveFlag;
	}
	
	
	
	
	
	
	
	
	

}
