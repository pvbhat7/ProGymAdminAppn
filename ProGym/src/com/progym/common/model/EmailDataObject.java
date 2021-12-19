package com.progym.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRO_INVOICE_DATA")
public class EmailDataObject {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
	@Column
	String clientEmail;
	@Column
	String packageName;
	@Column
	String clientName;
	@Column
	String duration;
	@Column
	String paymentDate;
	@Column
	String amount;
	@Column
	String remainingAmount;
	@Column
	String isReceiptSent;
	
	@Column
	int paymentTransactionId;
	
	
	
	public int getPaymentTransactionId() {
		return paymentTransactionId;
	}
	public void setPaymentTransactionId(int paymentTransactionId) {
		this.paymentTransactionId = paymentTransactionId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getClientEmail() {
		return clientEmail;
	}
	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(String remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	public String getIsReceiptSent() {
		return isReceiptSent;
	}
	public void setIsReceiptSent(String isReceiptSent) {
		this.isReceiptSent = isReceiptSent;
	}
	


}
