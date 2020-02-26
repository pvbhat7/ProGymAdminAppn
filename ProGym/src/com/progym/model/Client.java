package com.progym.model;


import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "t_client")
public class Client {
	
	
	public Client() {		
	}

	
	

	public Client(String name, String mobile, String gender, String birthDate, String remarks, String discountinue,
			List<PackageDetails> packageDetails) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.gender = gender;
		this.birthDate = birthDate;
		this.remarks = remarks;
		this.discountinue = discountinue;
		this.packageDetails = packageDetails;
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
	private String discountinue;
	
	//private byte[] photo;
	
	@OneToMany(cascade=CascadeType.ALL)
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


	public String getDiscountinue() {
		return discountinue;
	}


	public void setDiscountinue(String discountinue) {
		this.discountinue = discountinue;
	}
	
	
	
	
		
    
}
