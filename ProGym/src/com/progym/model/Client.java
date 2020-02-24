package com.progym.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column
    private String name;
	
	@Column
    private String mobile;
	
	@Column
    private String sex;
   

    public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(String name, String mobile, String sex) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.sex = sex;
	}

	public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }


    public String getSex() {
        return sex;
    }
    
}
