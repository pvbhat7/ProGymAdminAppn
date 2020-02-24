package com.progym.model;




public class CPackage {

    private int packageId;

    Double fees;

    String packageName;

    Integer days;

    String sex;

    String packageCategory;
    
    public CPackage(){

    }



    public CPackage(String packageCategory,String packageName, Integer days,Double fees ) {
        this.fees = fees;
        this.packageName = packageName;
        this.days = days;
        this.packageCategory = packageCategory;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setPackageCategory(String packageCategory) {
        this.packageCategory = packageCategory;
    }

    public int getPackageId() {
        return packageId;
    }

    public Double getFees() {
        return fees;
    }

    public String getPackageName() {
        return packageName;
    }

    public Integer getDays() {
        return days;
    }

    public String getSex() {
        return sex;
    }

    public String getPackageCategory() {
        return packageCategory;
    }
}
