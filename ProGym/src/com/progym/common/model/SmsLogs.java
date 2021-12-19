package com.progym.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(
		name = "SMS_LOGS"
)
public class SmsLogs {
	@Id
	@GeneratedValue(
			strategy = GenerationType.IDENTITY
	)
	private int id;
	@Column
	private String clientName;
	@Column
	private String mobile;
	@Column
	private String smsBody;
	@Column
	private String timestamp;
	@Column
	private String delivered;
	@Column
	private String filter;
	@Column
	private String gender;

	public SmsLogs() {
	}

	public SmsLogs(String clientName, String mobile, String smsBody, String timestamp, String delivered, String filter, String gender) {
		this.clientName = clientName;
		this.mobile = mobile;
		this.smsBody = smsBody;
		this.timestamp = timestamp;
		this.delivered = delivered;
		this.filter = filter;
		this.gender = gender;
	}

	public String getFilter() {
		return this.filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getDelivered() {
		return this.delivered;
	}

	public void setDelivered(String delivered) {
		this.delivered = delivered;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClientName() {
		return this.clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSmsBody() {
		return this.smsBody;
	}

	public void setSmsBody(String smsBody) {
		this.smsBody = smsBody;
	}

	public String getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String toString() {
		return "SmsLogs [id=" + this.id + ", clientName=" + this.clientName + ", mobile=" + this.mobile + ", smsBody=" + this.smsBody + ", timestamp=" + this.timestamp + "]";
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}

