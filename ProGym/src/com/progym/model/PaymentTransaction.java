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
	private String packageDetailsId;
	
	@Column
	private Double feesPaid;
	
	@ManyToOne
    private PackageDetails packageDetails;
	
	
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPackageDetailsId() {
		return packageDetailsId;
	}
	public void setPackageDetailsId(String packageDetailsId) {
		this.packageDetailsId = packageDetailsId;
	}
	public Double getFeesPaid() {
		return feesPaid;
	}
	public void setFeesPaid(Double feesPaid) {
		this.feesPaid = feesPaid;
	}
	public PaymentTransaction(String paymentDate, String packageDetailsId, Double feesPaid) {
		super();
		this.paymentDate = paymentDate;
		this.packageDetailsId = packageDetailsId;
		this.feesPaid = feesPaid;
	}
	public PaymentTransaction() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

}
