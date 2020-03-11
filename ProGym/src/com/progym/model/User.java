package com.progym.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {
	
		@Column
	  private String username;
		
		@Column
	  private String password;
		
		@Column
	  private String authorizedToApprovePayment;
		@Column
		  private String name;
	  
	  
	  public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	public User(String username, String password ,String name, String authorizedToApprovePayment) {
		super();
		this.username = username;
		this.password = password;
		this.authorizedToApprovePayment = authorizedToApprovePayment;
		this.name = name;
	}
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	public String getUsername() {
	  return username;
	  }
	  public void setUsername(String username) {
	  this.username = username;
	  }
	  public String getPassword() {
	  return password;
	  }
	  public void setPassword(String password) {
	  this.password = password;
	  }
	public String getAuthorizedToApprovePayment() {
		return authorizedToApprovePayment;
	}
	public void setAuthorizedToApprovePayment(String authorizedToApprovePayment) {
		this.authorizedToApprovePayment = authorizedToApprovePayment;
	}
	
	public boolean isAuthorized() {
		return authorizedToApprovePayment.equals("YES") ? true : false;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", authorizedToApprovePayment="
				+ authorizedToApprovePayment + ", name=" + name + ", id=" + id + "]";
	}
	
	
	  
	  
	}
