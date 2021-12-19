package com.progym.common.model;

import javax.persistence.*;

@Entity
@Table(name = "Module")
public class ModuleObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String mac;

    @Column
    private String email;

    @Column
    private String sms;

    @Column
    private String diet;

    @Column
    private String workout;

    @Column
    private String monthlyData;

    public ModuleObject() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getWorkout() {
        return workout;
    }

    public void setWorkout(String workout) {
        this.workout = workout;
    }

    public String getMonthlyData() {
        return monthlyData;
    }

    public void setMonthlyData(String monthlyData) {
        this.monthlyData = monthlyData;
    }

    public Boolean isEnabled(String key) {
        Boolean result = Boolean.FALSE;

        if (key.equalsIgnoreCase("EMAIL_INVOICE_FLAG")) {
            if (this.getEmail().equalsIgnoreCase("TRUE"))
                result = true;
        }
        else if (key.equalsIgnoreCase("SMS_FLAG")) {
            if (this.getSms().equalsIgnoreCase("TRUE"))
                result = true;
        }
        else if (key.equalsIgnoreCase("DIET_FLAG")) {
            if (this.getDiet().equalsIgnoreCase("TRUE"))
                result = true;
        }
        else if (key.equalsIgnoreCase("WORKOUT_FLAG")) {
            if (this.getWorkout().equalsIgnoreCase("TRUE"))
                result = true;
        }
        else if (key.equalsIgnoreCase("MONTHLY_DATA_FLAG")) {
            if (this.getMonthlyData().equalsIgnoreCase("TRUE"))
                result = true;
        }

        return result;
    }
}
