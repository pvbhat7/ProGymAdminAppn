package com.progym.model;

import com.progym.utils.FeesReminderEmail;

public class EmailPVO {
	
	String email;
	String subject;
	String name;
	String packageName;
	String packageDuration;
	String daysRemaining;
	String feesPaid;
	String pendingFees;
	String packageTotalFees;
	String messageLine;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getDaysRemaining() {
		return daysRemaining;
	}
	public void setDaysRemaining(String daysRemaining) {
		this.daysRemaining = daysRemaining;
	}
	public String getFeesPaid() {
		return feesPaid;
	}
	public void setFeesPaid(String feesPaid) {
		this.feesPaid = feesPaid;
	}
	public String getPendingFees() {
		return pendingFees;
	}
	public void setPendingFees(String pendingFees) {
		this.pendingFees = pendingFees;
	}
	public String getPackageTotalFees() {
		return packageTotalFees;
	}
	public void setPackageTotalFees(String packageTotalFees) {
		this.packageTotalFees = packageTotalFees;
	}
	public String getMessageLine() {
		return messageLine;
	}
	public void setMessageLine(String messageLine) {
		this.messageLine = messageLine;
	}
	public EmailPVO(String email, String subject, String name, String packageName, String packageDuration,
			String daysRemaining, String feesPaid, String pendingFees, String packageTotalFees, String messageLine) {
		super();
		this.email = email;
		this.subject = subject;
		this.name = name;
		this.packageName = packageName;
		this.packageDuration = packageDuration;
		this.daysRemaining = daysRemaining;
		this.feesPaid = feesPaid;
		this.pendingFees = pendingFees;
		this.packageTotalFees = packageTotalFees;
		this.messageLine = messageLine;
	}
	
	
}
