package com.progym.common.model;

public class ClientSyncModal {

    private String id;
    private String externalCode;
    private String email;
    private String birthDate;
    private String weight;
    private String height;
    private String address;
    private String photo;

    public ClientSyncModal(String id, String externalCode, String email, String birthDate, String weight, String height, String address, String photo) {
        this.id = id;
        this.externalCode = externalCode;
        this.email = email;
        this.birthDate = birthDate;
        this.weight = weight;
        this.height = height;
        this.address = address;
        this.photo = photo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExternalCode() {
        return externalCode;
    }

    public void setExternalCode(String externalCode) {
        this.externalCode = externalCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
