package com.progym.common.model;

public class Merchandise {

    private String id;
    private String productName;
    private String oldPrice;
    private String newPrice;
    private String productPhoto;
    private String discontinue;

    public Merchandise(String id, String productName, String oldPrice, String newPrice, String productPhoto, String discontinue) {
        this.id = id;
        this.productName = productName;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.productPhoto = productPhoto;
        this.discontinue = discontinue;
    }

    public Merchandise(String id, String productPhoto) {
        this.id = id;
        this.productPhoto = productPhoto;
    }

    public Merchandise() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public String getProductPhoto() {
        return productPhoto;
    }

    public void setProductPhoto(String productPhoto) {
        this.productPhoto = productPhoto;
    }

    public String getDiscontinue() {
        return discontinue;
    }

    public void setDiscontinue(String discontinue) {
        this.discontinue = discontinue;
    }
}
