package com.progym.common.model;

public class BloodGroupDetails {

    private String name;
    private String bloodGroup;
    private String mobile;
    private String email;
    private String address;

    public BloodGroupDetails() {

    }

    public BloodGroupDetails(String name, String bloodGroup, String mobile, String email, String address) {
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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
}
