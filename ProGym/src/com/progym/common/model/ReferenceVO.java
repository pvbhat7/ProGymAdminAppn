package com.progym.common.model;

public class ReferenceVO {

	private int clientId;
	private String name;
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ReferenceVO(int clientId, String name) {
		super();
		this.clientId = clientId;
		this.name = name;
	}
	
	
	
	
}
