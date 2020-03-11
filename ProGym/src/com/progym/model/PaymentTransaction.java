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
	private String paymentDate;
	
	@Column
	private int packageDetailsId;
	
	@Column
	private Double feesPaid;
	
	@ManyToOne
    private PackageDetails packageDetails;
	
	@Column
	private String isApproved;
	
	
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
	public Double getFeesPaid() {
		return feesPaid;
	}
	public void setFeesPaid(Double feesPaid) {
		this.feesPaid = feesPaid;
	}
	public PaymentTransaction(String paymentDate, int packageDetailsId, Double feesPaid , String isApproved) {
		super();
		this.paymentDate = paymentDate;
		this.packageDetailsId = packageDetailsId;
		this.feesPaid = feesPaid;
		this.isApproved = isApproved ;
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
	
	
	
	
	
	
	

}
