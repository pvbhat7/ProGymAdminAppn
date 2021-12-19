package com.progym.common.model;

public class FcmTokenModal {

    private String id;
    private String mobile;
    private String token;
    private String discontinue;

    public FcmTokenModal() {
    }

    public FcmTokenModal(String mobile, String token, String discontinue) {
        this.mobile = mobile;
        this.token = token;
        this.discontinue = discontinue;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDiscontinue() {
        return discontinue;
    }

    public void setDiscontinue(String discontinue) {
        this.discontinue = discontinue;
    }
}
