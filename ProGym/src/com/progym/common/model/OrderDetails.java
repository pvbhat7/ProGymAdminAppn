package com.progym.common.model;

public class OrderDetails {

    private String order_id;
    private String img;
    private String name;
    private String date;
    private String status;
    private String clientId;
    private String amount;
    private String txnId;

    public OrderDetails(String order_id, String img, String name, String date, String status, String clientId, String amount, String txnId) {
        this.order_id = order_id;
        this.img = img;
        this.name = name;
        this.date = date;
        this.status = status;
        this.clientId = clientId;
        this.amount = amount;
        this.txnId = txnId;
    }

    public OrderDetails() {

    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
