package com.progym.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SMS_LOGS")
public class SmsLogs {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column
    private String clientName;
    
    @Column
    private String mobile;
    
    @Column
    private String smsBody;
    
    @Column
    private String timestamp;

	public SmsLogs() {
		
	}

	public SmsLogs(String clientName, String mobile, String smsBody, String timestamp) {
		this.clientName = clientName;
		this.mobile = mobile;
		this.smsBody = smsBody;
		this.timestamp = timestamp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSmsBody() {
		return smsBody;
	}

	public void setSmsBody(String smsBody) {
		this.smsBody = smsBody;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "SmsLogs [id=" + id + ", clientName=" + clientName + ", mobile=" + mobile + ", smsBody=" + smsBody
				+ ", timestamp=" + timestamp + "]";
	}
    
    
}
