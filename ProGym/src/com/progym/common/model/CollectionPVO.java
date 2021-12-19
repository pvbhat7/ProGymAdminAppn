package com.progym.common.model;

public class CollectionPVO {

	private String name;
	private Double feesPaid;
	private String packageName;
	private String paymentStatus;
	private String lastPaymentDate;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getFeesPaid() {
		return feesPaid;
	}
	public void setFeesPaid(Double feesPaid) {
		this.feesPaid = feesPaid;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getLastPaymentDate() {
		return lastPaymentDate;
	}
	public void setLastPaymentDate(String lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}
	public CollectionPVO(String name, Double feesPaid, String packageName, String paymentStatus,
			String lastPaymentDate) {
		super();
		this.name = name;
		this.feesPaid = feesPaid;
		this.packageName = packageName;
		this.paymentStatus = paymentStatus;
		this.lastPaymentDate = lastPaymentDate;
	}
	
	
	
	
	
}
