package com.progym.model;

public class AddClientPackageForm {

	private String cpackageId;
	private String startDate;
	private int clientId;
	private String gender;
	
	
	
	
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
	@Override
	public String toString() {
		return "AddClientPackageForm [cpackageId=" + cpackageId + ", startDate=" + startDate + ", clientId=" + clientId
				+ "]";
	}
	
	
	
	
	
}
