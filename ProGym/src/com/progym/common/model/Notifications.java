package com.progym.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "NOTIFICATIONS")
public class Notifications {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notiId;
	
	@Column
    private String trainer;
	
	@Column
    private String activity;
	
	@Column
    private String memberName;
	
	@Column
    private String amount;
	
	@Column
    private String discontinue;
	
	@Column
	String clientGender;
	
	@Column
	int clientId;
	
	@Column
	String activityDate;
	
	

	public int getNotiId() {
		return notiId;
	}

	public void setNotiId(int notiId) {
		this.notiId = notiId;
	}

	public String getTrainer() {
		return trainer;
	}

	public void setTrainer(String trainer) {
		this.trainer = trainer;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDiscontinue() {
		return discontinue;
	}

	public void setDiscontinue(String discontinue) {
		this.discontinue = discontinue;
	}

	public String getClientGender() {
		return clientGender;
	}

	public void setClientGender(String clientGender) {
		this.clientGender = clientGender;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}

	public Notifications(String trainer, String activity, String memberName, String amount, String discontinue,
			String clientGender, int clientId, String activityDate) {
		super();
		this.trainer = trainer;
		this.activity = activity;
		this.memberName = memberName;
		this.amount = amount;
		this.discontinue = discontinue;
		this.clientGender = clientGender;
		this.clientId = clientId;
		this.activityDate = activityDate;
	}

	public Notifications() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	

}
