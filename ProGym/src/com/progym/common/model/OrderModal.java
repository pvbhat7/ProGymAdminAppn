package com.progym.common.model;

import java.util.Date;

public class OrderModal {

    private String order_id;
    private String clientServerId;
    private String clientDesktopId;
    private String productImg;
    private String clientImg;
    private String productName;
    private String amount;
    private String orderStatus;
    private String orderDate;
    private String orderPaymentStatus;
    private String trackingDetails;
    private String clientName;
    private String clientMobile;
    private String clientEmail;


    public OrderModal(String order_id, String clientServerId, String clientDesktopId, String productImg, String clientImg, String productName, String amount, String orderStatus, String orderDate, String orderPaymentStatus, String trackingDetails, String clientName, String clientMobile, String clientEmail) {
        this.order_id = order_id;
        this.clientServerId = clientServerId;
        this.clientDesktopId = clientDesktopId;
        this.productImg = productImg;
        this.clientImg = clientImg;
        this.productName = productName;
        this.amount = amount;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.orderPaymentStatus = orderPaymentStatus;
        this.trackingDetails = trackingDetails;
        this.clientName = clientName;
        this.clientMobile = clientMobile;
        this.clientEmail = clientEmail;
    }

    public String getClientImg() {
        return clientImg == null ? "" : clientImg+ "?" + new Date();
    }

    public void setClientImg(String clientImg) {
        this.clientImg = clientImg;
    }

    public String getTrackingDetails() {
        return trackingDetails;
    }

    public void setTrackingDetails(String trackingDetails) {
        this.trackingDetails = trackingDetails;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getClientServerId() {
        return clientServerId;
    }

    public void setClientServerId(String clientServerId) {
        this.clientServerId = clientServerId;
    }

    public String getClientDesktopId() {
        return clientDesktopId;
    }

    public void setClientDesktopId(String clientDesktopId) {
        this.clientDesktopId = clientDesktopId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderPaymentStatus() {
        return orderPaymentStatus;
    }

    public void setOrderPaymentStatus(String orderPaymentStatus) {
        this.orderPaymentStatus = orderPaymentStatus;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientMobile() {
        return clientMobile;
    }

    public void setClientMobile(String clientMobile) {
        this.clientMobile = clientMobile;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getProductImg() {
        return productImg+ "?" + new Date();
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}
