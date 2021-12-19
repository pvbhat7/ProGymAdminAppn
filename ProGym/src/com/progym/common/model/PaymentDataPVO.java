package com.progym.common.model;

public class PaymentDataPVO {

	String clientName;
	String packageName;
	Double feesPaid;
	String packageStartDate;
	String packageEndDate;
	String packagePaymentEndDate;
	String gender;
	String clientPackageStatus;
	int clientId;
	
	
	
	
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getClientPackageStatus() {
		return clientPackageStatus;
	}
	public void setClientPackageStatus(String clientPackageStatus) {
		this.clientPackageStatus = clientPackageStatus;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public Double getFeesPaid() {
		return feesPaid;
	}
	public void setFeesPaid(Double feesPaid) {
		this.feesPaid = feesPaid;
	}
	public String getPackageStartDate() {
		return packageStartDate;
	}
	public void setPackageStartDate(String packageStartDate) {
		this.packageStartDate = packageStartDate;
	}
	public String getPackageEndDate() {
		return packageEndDate;
	}
	public void setPackageEndDate(String packageEndDate) {
		this.packageEndDate = packageEndDate;
	}
	public String getPackagePaymentEndDate() {
		return packagePaymentEndDate;
	}
	public void setPackagePaymentEndDate(String packagePaymentEndDate) {
		this.packagePaymentEndDate = packagePaymentEndDate;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "PaymentDataPVO [clientName=" + clientName + ", packageName=" + packageName + ", feesPaid=" + feesPaid
				+ ", packageStartDate=" + packageStartDate + ", packageEndDate=" + packageEndDate
				+ ", packagePaymentEndDate=" + packagePaymentEndDate + ", gender=" + gender + "]";
	}
	public PaymentDataPVO(String clientName, String packageName, Double feesPaid, String packageStartDate,
			String packageEndDate, String packagePaymentEndDate, String gender,String paymentStatus
			,int clientId) {
		super();
		this.clientName = clientName;
		this.packageName = packageName;
		this.feesPaid = feesPaid;
		this.packageStartDate = packageStartDate;
		this.packageEndDate = packageEndDate;
		this.packagePaymentEndDate = packagePaymentEndDate;
		this.gender = gender;
		this.clientPackageStatus = paymentStatus;
		this.clientId = clientId;
	}
	
	
	
	
}
