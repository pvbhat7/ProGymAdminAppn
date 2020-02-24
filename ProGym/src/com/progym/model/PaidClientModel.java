package com.progym.model;

public class PaidClientModel {

    String name;
    String paymentDate;
    String cpackage;
    Double paymentAmount;

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public PaidClientModel(String name, String paymentDate, String cpackage, Double paymentAmount) {
        this.name = name;
        this.paymentDate = paymentDate;
        this.cpackage = cpackage;
        this.paymentAmount = paymentAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getCpackage() {
        return cpackage;
    }

    public void setCpackage(String cpackage) {
        this.cpackage = cpackage;
    }
}
