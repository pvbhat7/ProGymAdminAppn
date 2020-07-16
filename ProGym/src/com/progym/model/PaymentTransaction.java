package com.progym.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "paymentTransaction")
public class PaymentTransaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	public String paymentDate;
	
	@Column
	public int packageDetailsId;
	
	@Column
	public double feesPaid;
	
	@ManyToOne
	public PackageDetails packageDetails;
	
	@Column
	public String isApproved;
	
	@Column
	private String discontinue;
	
	@Column
	private String clientId;
	
	@Column
	private String clientGender;
	
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientGender() {
		return clientGender;
	}
	public void setClientGender(String clientGender) {
		this.clientGender = clientGender;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public int getPackageDetailsId() {
		return packageDetailsId;
	}
	public void setPackageDetailsId(int packageDetailsId) {
		this.packageDetailsId = packageDetailsId;
	}
	public double getFeesPaid() {
		return feesPaid;
	}
	public void setFeesPaid(double feesPaid) {
		this.feesPaid = feesPaid;
	}
	public PaymentTransaction(String paymentDate, int packageDetailsId, double feesPaid , String isApproved , String discontinue) {
		super();
		this.paymentDate = paymentDate;
		this.packageDetailsId = packageDetailsId;
		this.feesPaid = feesPaid;
		this.isApproved = isApproved ;
		this.discontinue = discontinue;
	}
	public PaymentTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public PackageDetails getPackageDetails() {
		return packageDetails;
	}
	public void setPackageDetails(PackageDetails packageDetails) {
		this.packageDetails = packageDetails;
	}
	public String getIsApproved() {
		return isApproved;
	}
	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}
	public String getDiscontinue() {
		return discontinue;
	}
	public void setDiscontinue(String discontinue) {
		this.discontinue = discontinue;
	}
	
	
	
	
	
	
	

}
