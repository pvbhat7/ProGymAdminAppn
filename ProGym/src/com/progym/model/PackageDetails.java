package com.progym.model;

import java.util.Date;



public class PackageDetails {

    private int id;

    String startDate;

    public CPackage cPackage;

    String status;

    Double amountPaid;

    public int cliendId;





    public int getCliendId() {
        return cliendId;
    }

    public void setCliendId(int cliendId) {
        this.cliendId = cliendId;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }


    public void setcPackage(CPackage cPackage) {
        this.cPackage = cPackage;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getStartDate() {
        return startDate;
    }

    public CPackage getcPackage() {
        return cPackage;
    }

    public String getStatus() {
        return status;
    }
}
