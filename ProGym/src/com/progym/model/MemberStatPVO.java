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
	private String packageName;
	private String packageDuration;
	private String pendingFees;
	private String feesPaid;
	private String packageTotalFees;
	private String email;
	private String mobile;
	private String packagePaymentStatus;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
		return (Integer.parseInt(this.getDaysRemaining()) < Integer.parseInt(((MemberStatPVO) o).getDaysRemaining()) ? -1 : (Integer.parseInt(this.getDaysRemaining()) == Integer.parseInt(((MemberStatPVO) o).getDaysRemaining()) ? 0 : 1));
	}
	public String getProfileActiveFlag() {
		return profileActiveFlag;
	}
	public void setProfileActiveFlag(String profileActiveFlag) {
		this.profileActiveFlag = profileActiveFlag;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPackageDuration() {
		return packageDuration;
	}
	public void setPackageDuration(String packageDuration) {
		this.packageDuration = packageDuration;
	}
	public String getPendingFees() {
		return pendingFees;
	}
	public void setPendingFees(String pendingFees) {
		this.pendingFees = pendingFees;
	}
	public String getFeesPaid() {
		return feesPaid;
	}
	public void setFeesPaid(String feesPaid) {
		this.feesPaid = feesPaid;
	}
	public String getPackageTotalFees() {
		return packageTotalFees;
	}
	public void setPackageTotalFees(String packageTotalFees) {
		this.packageTotalFees = packageTotalFees;
	}
	public String getPackagePaymentStatus() {
		return packagePaymentStatus;
	}
	public void setPackagePaymentStatus(String packagePaymentStatus) {
		this.packagePaymentStatus = packagePaymentStatus;
	}
	
	
	
	
	
	
	
	
	

}
