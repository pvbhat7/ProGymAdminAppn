package com.progym.model;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CLIENT")
public class Client {
	
	
	public Client() {		
	}

	
	

	public Client(String name, String mobile, String gender, String birthDate, String remarks, String discontinue,
			List<PackageDetails> packageDetails,String referPoints,
			String email , String address , String bloodGroup , String reference , String previousGym,
			double height,double weight) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.gender = gender;
		this.birthDate = birthDate;
		this.remarks = remarks;
		this.discontinue = discontinue;
		this.packageDetails = packageDetails;
		this.referPoints = referPoints;
		this.email = email;
		this.address = address;	
		this.bloodGroup = bloodGroup;	
		this.reference = reference;	
		this.previousGym = previousGym;
		this.height = height;
		this.weight = weight;
	}




	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column
    private String name;
	
	@Column
    private String mobile;
	
	@Column
    private String gender;
   
	@Column
	private String birthDate;
	
	@Column
	private String remarks;
	
	@Column
	private String discontinue;
	
	@Column
	private String referPoints;
	
	@Column
	private String email;
	
	@Column
	private String address;
	
	@Column
	private String bloodGroup;
	
	public double getHeight() {
		return height;
	}




	public void setHeight(double height) {
		this.height = height;
	}




	public double getWeight() {
		return weight;
	}




	public void setWeight(double weight) {
		this.weight = weight;
	}




	@Column
	private String reference;
	
	@Column
	private String previousGym;
	
	@Column
	private double height;
	
	@Column
	private double weight;
	
	
	
	//private byte[] photo;
	
	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="CLIENT_ID")
	private List<PackageDetails> packageDetails;

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getRemarks() {
		return remarks;
	}


	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	public List<PackageDetails> getPackageDetails() {
		return packageDetails;
	}


	public void setPackageDetails(List<PackageDetails> packageDetails) {
		this.packageDetails = packageDetails;
	}


	public String getBirthDate() {
		return birthDate;
	}


	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}




	public String getDiscontinue() {
		return discontinue;
	}




	public void setDiscontinue(String discontinue) {
		this.discontinue = discontinue;
	}
	
	public String getReferPoints() {
		return referPoints;
	}


	public void setReferPoints(String referPoints) {
		this.referPoints = referPoints;
	}
	
	


	public String getEmail() {
		return email;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public String getAddress() {
		return address;
	}




	public void setAddress(String address) {
		this.address = address;
	}




	public String getBloodGroup() {
		return bloodGroup;
	}




	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}




	public String getReference() {
		return reference;
	}




	public void setReference(String reference) {
		this.reference = reference;
	}




	public String getPreviousGym() {
		return previousGym;
	}




	public void setPreviousGym(String previousGym) {
		this.previousGym = previousGym;
	}


	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", mobile=" + mobile + ", gender=" + gender + ", birthDate="
				+ birthDate + ", remarks=" + remarks + ", discontinue=" + discontinue + ", packageDetails="
				+ packageDetails + "]";
	}
	
	
	
	



	
	
	
	
		
    
}
