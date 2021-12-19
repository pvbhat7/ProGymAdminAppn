package com.progym.common.model;


public class PaymentData {

    private int id;
    
    int packageId;

   String paymentDate;
   
   Double amountPaid;

   
    String clientName;

    
    String clientpackagename;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientpackagename() {
        return clientpackagename;
    }

    public void setClientpackagename(String clientpackagename) {
        this.clientpackagename = clientpackagename;
    }

    public PaymentData() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }
}
