package com.progym.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;


public class DietPlanObject {

    private int id;

    private Client client;

    
    private String adminDataSyncRequired;

    
    private String clientDataSyncRequired;

    
    private String dietDate;

    
    private String discontinue;

    
    private String clientCompletionStatus_timeActivity_1;
    
    private String clientCompletionStatus_timeActivity_2;

    
    private String clientCompletionStatus_timeActivity_3;

    
    private String clientCompletionStatus_timeActivity_4;

    
    private String clientCompletionStatus_timeActivity_5;

    
    private String clientCompletionStatus_timeActivity_6;

    
    private String clientCompletionStatus_timeActivity_7;

    
    private String clientCompletionStatus_timeActivity_8;

    
    private String clientCompletionStatus_timeActivity_9;

    
    private String clientCompletionStatus_timeActivity_10;

    
    private String clientCompletionStatus_timeActivity_11;

    
    private String clientCompletionStatus_timeActivity_12;

    
    private String clientCompletionStatus_timeActivity_13;
    
    private String clientCompletionStatus_timeActivity_14;

    
    private String clientCompletionStatus_timeActivity_15;

    
    private String clientCompletionStatus_timeActivity_16;

    
    private String clientCompletionStatus_timeActivity_17;

    @Transient
    @JsonInclude
    private String cid;

    @Transient
    @JsonIgnore
    private String dietCompletionPercentageColor;

    @JsonIgnore
    @ManyToOne
    private DietPlanTemplate dietPlanTemplate;

    @Transient
    @JsonInclude
    private int dptid;

    @Transient
    @JsonInclude
    private String externalCode;

    public DietPlanObject() {
    }

    public DietPlanObject(DietPlanTemplate dietPlanTemplate,Client client, String adminDataSyncRequired, String clientDataSyncRequired, String dietDate, String discontinue,
                          String clientCompletionStatus_timeActivity_1, String clientCompletionStatus_timeActivity_2, String clientCompletionStatus_timeActivity_3, String clientCompletionStatus_timeActivity_4, String clientCompletionStatus_timeActivity_5, String clientCompletionStatus_timeActivity_6, String clientCompletionStatus_timeActivity_7, String clientCompletionStatus_timeActivity_8, String clientCompletionStatus_timeActivity_9, String clientCompletionStatus_timeActivity_10, String clientCompletionStatus_timeActivity_11, String clientCompletionStatus_timeActivity_12, String clientCompletionStatus_timeActivity_13, String clientCompletionStatus_timeActivity_14, String clientCompletionStatus_timeActivity_15, String clientCompletionStatus_timeActivity_16, String clientCompletionStatus_timeActivity_17) {
        this.client = client;
        this.adminDataSyncRequired = adminDataSyncRequired;
        this.clientDataSyncRequired = clientDataSyncRequired;
        this.dietDate = dietDate;
        this.discontinue = discontinue;
        this.clientCompletionStatus_timeActivity_1 = clientCompletionStatus_timeActivity_1;
        this.clientCompletionStatus_timeActivity_2 = clientCompletionStatus_timeActivity_2;
        this.clientCompletionStatus_timeActivity_3 = clientCompletionStatus_timeActivity_3;
        this.clientCompletionStatus_timeActivity_4 = clientCompletionStatus_timeActivity_4;
        this.clientCompletionStatus_timeActivity_5 = clientCompletionStatus_timeActivity_5;
        this.clientCompletionStatus_timeActivity_6 = clientCompletionStatus_timeActivity_6;
        this.clientCompletionStatus_timeActivity_7 = clientCompletionStatus_timeActivity_7;
        this.clientCompletionStatus_timeActivity_8 = clientCompletionStatus_timeActivity_8;
        this.clientCompletionStatus_timeActivity_9 = clientCompletionStatus_timeActivity_9;
        this.clientCompletionStatus_timeActivity_10 = clientCompletionStatus_timeActivity_10;
        this.clientCompletionStatus_timeActivity_11 = clientCompletionStatus_timeActivity_11;
        this.clientCompletionStatus_timeActivity_12 = clientCompletionStatus_timeActivity_12;
        this.clientCompletionStatus_timeActivity_13 = clientCompletionStatus_timeActivity_13;
        this.clientCompletionStatus_timeActivity_14 = clientCompletionStatus_timeActivity_14;
        this.clientCompletionStatus_timeActivity_15 = clientCompletionStatus_timeActivity_15;
        this.clientCompletionStatus_timeActivity_16 = clientCompletionStatus_timeActivity_16;
        this.clientCompletionStatus_timeActivity_17 = clientCompletionStatus_timeActivity_17;
        this.dietCompletionPercentageColor = dietCompletionPercentageColor;
        this.dietPlanTemplate = dietPlanTemplate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDiscontinue() {
        return discontinue;
    }

    public void setDiscontinue(String discontinue) {
        this.discontinue = discontinue;
    }

    public String getClientCompletionStatus_timeActivity_1() {
        return clientCompletionStatus_timeActivity_1;
    }

    public void setClientCompletionStatus_timeActivity_1(String clientCompletionStatus_timeActivity_1) {
        this.clientCompletionStatus_timeActivity_1 = clientCompletionStatus_timeActivity_1;
    }


    public String getClientCompletionStatus_timeActivity_2() {
        return clientCompletionStatus_timeActivity_2;
    }

    public void setClientCompletionStatus_timeActivity_2(String clientCompletionStatus_timeActivity_2) {
        this.clientCompletionStatus_timeActivity_2 = clientCompletionStatus_timeActivity_2;
    }

    public String getClientCompletionStatus_timeActivity_3() {
        return clientCompletionStatus_timeActivity_3;
    }

    public void setClientCompletionStatus_timeActivity_3(String clientCompletionStatus_timeActivity_3) {
        this.clientCompletionStatus_timeActivity_3 = clientCompletionStatus_timeActivity_3;
    }


    public String getClientCompletionStatus_timeActivity_4() {
        return clientCompletionStatus_timeActivity_4;
    }

    public void setClientCompletionStatus_timeActivity_4(String clientCompletionStatus_timeActivity_4) {
        this.clientCompletionStatus_timeActivity_4 = clientCompletionStatus_timeActivity_4;
    }


    public String getClientCompletionStatus_timeActivity_5() {
        return clientCompletionStatus_timeActivity_5;
    }

    public void setClientCompletionStatus_timeActivity_5(String clientCompletionStatus_timeActivity_5) {
        this.clientCompletionStatus_timeActivity_5 = clientCompletionStatus_timeActivity_5;
    }

    public String getClientCompletionStatus_timeActivity_6() {
        return clientCompletionStatus_timeActivity_6;
    }

    public void setClientCompletionStatus_timeActivity_6(String clientCompletionStatus_timeActivity_6) {
        this.clientCompletionStatus_timeActivity_6 = clientCompletionStatus_timeActivity_6;
    }


    public String getClientCompletionStatus_timeActivity_7() {
        return clientCompletionStatus_timeActivity_7;
    }

    public void setClientCompletionStatus_timeActivity_7(String clientCompletionStatus_timeActivity_7) {
        this.clientCompletionStatus_timeActivity_7 = clientCompletionStatus_timeActivity_7;
    }


    public String getClientCompletionStatus_timeActivity_8() {
        return clientCompletionStatus_timeActivity_8;
    }

    public void setClientCompletionStatus_timeActivity_8(String clientCompletionStatus_timeActivity_8) {
        this.clientCompletionStatus_timeActivity_8 = clientCompletionStatus_timeActivity_8;
    }


    public String getClientCompletionStatus_timeActivity_9() {
        return clientCompletionStatus_timeActivity_9;
    }

    public void setClientCompletionStatus_timeActivity_9(String clientCompletionStatus_timeActivity_9) {
        this.clientCompletionStatus_timeActivity_9 = clientCompletionStatus_timeActivity_9;
    }


    public String getClientCompletionStatus_timeActivity_10() {
        return clientCompletionStatus_timeActivity_10;
    }

    public void setClientCompletionStatus_timeActivity_10(String clientCompletionStatus_timeActivity_10) {
        this.clientCompletionStatus_timeActivity_10 = clientCompletionStatus_timeActivity_10;
    }


    public String getClientCompletionStatus_timeActivity_11() {
        return clientCompletionStatus_timeActivity_11;
    }

    public void setClientCompletionStatus_timeActivity_11(String clientCompletionStatus_timeActivity_11) {
        this.clientCompletionStatus_timeActivity_11 = clientCompletionStatus_timeActivity_11;
    }


    public String getClientCompletionStatus_timeActivity_12() {
        return clientCompletionStatus_timeActivity_12;
    }

    public void setClientCompletionStatus_timeActivity_12(String clientCompletionStatus_timeActivity_12) {
        this.clientCompletionStatus_timeActivity_12 = clientCompletionStatus_timeActivity_12;
    }


    public String getClientCompletionStatus_timeActivity_13() {
        return clientCompletionStatus_timeActivity_13;
    }

    public void setClientCompletionStatus_timeActivity_13(String clientCompletionStatus_timeActivity_13) {
        this.clientCompletionStatus_timeActivity_13 = clientCompletionStatus_timeActivity_13;
    }


    public String getClientCompletionStatus_timeActivity_14() {
        return clientCompletionStatus_timeActivity_14;
    }

    public void setClientCompletionStatus_timeActivity_14(String clientCompletionStatus_timeActivity_14) {
        this.clientCompletionStatus_timeActivity_14 = clientCompletionStatus_timeActivity_14;
    }


    public String getClientCompletionStatus_timeActivity_15() {
        return clientCompletionStatus_timeActivity_15;
    }

    public void setClientCompletionStatus_timeActivity_15(String clientCompletionStatus_timeActivity_15) {
        this.clientCompletionStatus_timeActivity_15 = clientCompletionStatus_timeActivity_15;
    }


    public String getClientCompletionStatus_timeActivity_16() {
        return clientCompletionStatus_timeActivity_16;
    }

    public void setClientCompletionStatus_timeActivity_16(String clientCompletionStatus_timeActivity_16) {
        this.clientCompletionStatus_timeActivity_16 = clientCompletionStatus_timeActivity_16;
    }


    public String getClientCompletionStatus_timeActivity_17() {
        return clientCompletionStatus_timeActivity_17;
    }

    public void setClientCompletionStatus_timeActivity_17(String clientCompletionStatus_timeActivity_17) {
        this.clientCompletionStatus_timeActivity_17 = clientCompletionStatus_timeActivity_17;
    }

    public String getDietDate() {
        return dietDate;
    }

    public void setDietDate(String dietDate) {
        this.dietDate = dietDate;
    }

    public String getAdminDataSyncRequired() {
        return adminDataSyncRequired;
    }

    public void setAdminDataSyncRequired(String adminDataSyncRequired) {
        this.adminDataSyncRequired = adminDataSyncRequired;
    }

    public String getClientDataSyncRequired() {
        return clientDataSyncRequired;
    }

    public void setClientDataSyncRequired(String clientDataSyncRequired) {
        this.clientDataSyncRequired = clientDataSyncRequired;
    }

    public DietPlanTemplate getDietPlanTemplate() {
        return dietPlanTemplate;
    }

    public void setDietPlanTemplate(DietPlanTemplate dietPlanTemplate) {
        this.dietPlanTemplate = dietPlanTemplate;
    }

    public String getDietCompletionPercentageColor() {
        return dietCompletionPercentageColor;
    }

    public void setDietCompletionPercentageColor(String color) {
        this.dietCompletionPercentageColor = color;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getDptid() {
        return dptid;
    }

    public void setDptid(int dptid) {
        this.dptid = dptid;
    }

    public String getExternalCode() {
        return externalCode;
    }

    public void setExternalCode(String externalCode) {
        this.externalCode = externalCode;
    }
}
