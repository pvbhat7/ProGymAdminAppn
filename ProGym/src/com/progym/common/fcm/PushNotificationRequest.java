package com.progym.common.fcm;

import java.util.List;

public class PushNotificationRequest {

    private String title;
    private String message;
    private String image;
    private String topic;
    private String token;
    private List<String> tokensList;
    private String clientName;
    private String targetClass;
    private String notificationType;
    private String mobile;

    public PushNotificationRequest(String title, String message, String image , String clientName , String mobile,String notificationType,String targetClass) {
        this.title = title;
        this.message = message;
        this.mobile = mobile;
        this.clientName = clientName;
        this.notificationType = notificationType;
        this.targetClass = targetClass;
        this.image = image;
    }

    public PushNotificationRequest() {
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getTokensList() {
        return tokensList;
    }

    public void setTokensList(List<String> tokensList) {
        this.tokensList = tokensList;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}