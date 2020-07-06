package com.progym.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRO_FLAG_DATA")
public class FlagData {
	
	@Override
	public String toString() {
		return "FlagData [id=" + id + ", flagKey=" + flagKey + ", flagValue=" + flagValue + "]";
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column
	private String flagKey;
	@Column
	private String flagValue;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFlagKey() {
		return flagKey;
	}
	public void setFlagKey(String flagKey) {
		this.flagKey = flagKey;
	}
	public String getFlagValue() {
		return flagValue;
	}
	public void setFlagValue(String flagValue) {
		this.flagValue = flagValue;
	}
	
	

}
