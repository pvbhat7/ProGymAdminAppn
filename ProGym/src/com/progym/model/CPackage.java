package com.progym.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CPackage {

	public CPackage() {		
	}


	public CPackage(Double fees, String packageName, Integer days, String gender, String packageCategory) {
		super();
		this.fees = fees;
		this.packageName = packageName;
		this.days = days;
		this.gender = gender;
		this.packageCategory = packageCategory;
		this.packageDetails = packageDetails;
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
    private String packageCategory;
    
    @OneToOne(mappedBy = "cPackage")
    private PackageDetails packageDetails;

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


	public String getPackageCategory() {
		return packageCategory;
	}


	public void setPackageCategory(String packageCategory) {
		this.packageCategory = packageCategory;
	}


	public PackageDetails getPackageDetails() {
		return packageDetails;
	}


	public void setPackageDetails(PackageDetails packageDetails) {
		this.packageDetails = packageDetails;
	}
    
    
    
}
