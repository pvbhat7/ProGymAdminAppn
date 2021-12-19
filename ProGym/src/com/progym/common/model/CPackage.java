package com.progym.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PACKAGES")
public class CPackage {

	public CPackage() {		
	}

	
	public CPackage(Double fees, String packageName, Integer days, String gender,String discontinue) {
		super();
		this.fees = fees;
		this.packageName = packageName;
		this.days = days;
		this.gender = gender;
		this.discontinue = discontinue;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

	@Column
    private Double fees;

	@Column
    private String packageName;

	@Column
    private Integer days;

	@Column
    private String gender;

	
	@Column
	private String discontinue;
	
	@Column
	private int packageVersion;
	
	public Double getFees() {
		return fees;
	}


	public void setFees(Double fees) {
		this.fees = fees;
	}


	public String getPackageName() {
		return packageName;
	}


	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}


	public Integer getDays() {
		return days;
	}


	public void setDays(Integer days) {
		this.days = days;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	
	public String getDiscontinue() {
		return discontinue;
	}

	public void setDiscontinue(String discontinue) {
		this.discontinue = discontinue;
	}


	public int getPackageVersion() {
		return packageVersion;
	}


	public void setPackageVersion(int packageVersion) {
		this.packageVersion = packageVersion;
	}


	public int getId() {
		return Id;
	}


	public void setId(int id) {
		Id = id;
	}


	
	
	
	
	
	
	
	
	
	
    
    
    
}
