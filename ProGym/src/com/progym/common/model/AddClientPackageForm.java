package com.progym.common.model;

public class AddClientPackageForm {

	private String cpackageId;
	private String startDate;
	private int clientId;
	private String gender;
	private String discountPercentage;
	
	
	
	
	public AddClientPackageForm() {
	}
	public AddClientPackageForm(String gender) {
		this.gender = gender;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCpackageId() {
		return cpackageId;
	}
	public void setCpackageId(String cpackageId) {
		this.cpackageId = cpackageId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	
	
	public String getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(String discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	@Override
	public String toString() {
		return "AddClientPackageForm [cpackageId=" + cpackageId + ", startDate=" + startDate + ", clientId=" + clientId
				+ "]";
	}
	
	
	
	
	
}
