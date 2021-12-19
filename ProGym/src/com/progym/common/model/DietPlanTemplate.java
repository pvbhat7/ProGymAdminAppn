package com.progym.common.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


public class DietPlanTemplate {
   
    private int id;

   
    private String name;


    private Client client;

    private String cid;

   
    private String createDate;

   
    private String discontinue;

   
    private String time_1;

   
    private String activity_1;

   
    private String time_2;

   
    private String activity_2;

   
    private String time_3;

   
    private String activity_3;

   
    private String time_4;

   
    private String activity_4;

   
    private String time_5;

   
    private String activity_5;

   
    private String time_6;

   
    private String activity_6;

   
    private String time_7;

   
    private String activity_7;

   
    private String time_8;

   
    private String activity_8;

   
    private String time_9;

   
    private String activity_9;

   
    private String time_10;

   
    private String activity_10;

   
    private String time_11;

   
    private String activity_11;

   
    private String time_12;

   
    private String activity_12;

   
    private String time_13;

   
    private String activity_13;

   
    private String time_14;

   
    private String activity_14;

   
    private String time_15;

   
    private String activity_15;

   
    private String time_16;

   
    private String activity_16;

   
    private String time_17;

   
    private String activity_17;

    public DietPlanTemplate() {
    }

    public DietPlanTemplate(String dietPlanName , Client client, String createDate, String discontinue, String time_1, String activity_1, String time_2, String activity_2, String time_3, String activity_3, String time_4, String activity_4, String time_5, String activity_5, String time_6, String activity_6, String time_7, String activity_7, String time_8, String activity_8, String time_9, String activity_9, String time_10, String activity_10, String time_11, String activity_11, String time_12, String activity_12, String time_13, String activity_13, String time_14, String activity_14, String time_15, String activity_15, String time_16, String activity_16, String time_17, String activity_17) {
        this.name = dietPlanName;
        this.client = client;
        this.createDate = createDate;
        this.discontinue = discontinue;
        this.time_1 = time_1;
        this.activity_1 = activity_1;
        this.time_2 = time_2;
        this.activity_2 = activity_2;
        this.time_3 = time_3;
        this.activity_3 = activity_3;
        this.time_4 = time_4;
        this.activity_4 = activity_4;
        this.time_5 = time_5;
        this.activity_5 = activity_5;
        this.time_6 = time_6;
        this.activity_6 = activity_6;
        this.time_7 = time_7;
        this.activity_7 = activity_7;
        this.time_8 = time_8;
        this.activity_8 = activity_8;
        this.time_9 = time_9;
        this.activity_9 = activity_9;
        this.time_10 = time_10;
        this.activity_10 = activity_10;
        this.time_11 = time_11;
        this.activity_11 = activity_11;
        this.time_12 = time_12;
        this.activity_12 = activity_12;
        this.time_13 = time_13;
        this.activity_13 = activity_13;
        this.time_14 = time_14;
        this.activity_14 = activity_14;
        this.time_15 = time_15;
        this.activity_15 = activity_15;
        this.time_16 = time_16;
        this.activity_16 = activity_16;
        this.time_17 = time_17;
        this.activity_17 = activity_17;
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDiscontinue() {
        return discontinue;
    }

    public void setDiscontinue(String discontinue) {
        this.discontinue = discontinue;
    }

    public String getTime_1() {
        return time_1;
    }

    public void setTime_1(String time_1) {
        this.time_1 = time_1;
    }

    public String getActivity_1() {
        return activity_1;
    }

    public void setActivity_1(String activity_1) {
        this.activity_1 = activity_1;
    }

    public String getTime_2() {
        return time_2;
    }

    public void setTime_2(String time_2) {
        this.time_2 = time_2;
    }

    public String getActivity_2() {
        return activity_2;
    }

    public void setActivity_2(String activity_2) {
        this.activity_2 = activity_2;
    }

    public String getTime_3() {
        return time_3;
    }

    public void setTime_3(String time_3) {
        this.time_3 = time_3;
    }

    public String getActivity_3() {
        return activity_3;
    }

    public void setActivity_3(String activity_3) {
        this.activity_3 = activity_3;
    }

    public String getTime_4() {
        return time_4;
    }

    public void setTime_4(String time_4) {
        this.time_4 = time_4;
    }

    public String getActivity_4() {
        return activity_4;
    }

    public void setActivity_4(String activity_4) {
        this.activity_4 = activity_4;
    }

    public String getTime_5() {
        return time_5;
    }

    public void setTime_5(String time_5) {
        this.time_5 = time_5;
    }

    public String getActivity_5() {
        return activity_5;
    }

    public void setActivity_5(String activity_5) {
        this.activity_5 = activity_5;
    }

    public String getTime_6() {
        return time_6;
    }

    public void setTime_6(String time_6) {
        this.time_6 = time_6;
    }

    public String getActivity_6() {
        return activity_6;
    }

    public void setActivity_6(String activity_6) {
        this.activity_6 = activity_6;
    }

    public String getTime_7() {
        return time_7;
    }

    public void setTime_7(String time_7) {
        this.time_7 = time_7;
    }

    public String getActivity_7() {
        return activity_7;
    }

    public void setActivity_7(String activity_7) {
        this.activity_7 = activity_7;
    }

    public String getTime_8() {
        return time_8;
    }

    public void setTime_8(String time_8) {
        this.time_8 = time_8;
    }

    public String getActivity_8() {
        return activity_8;
    }

    public void setActivity_8(String activity_8) {
        this.activity_8 = activity_8;
    }

    public String getTime_9() {
        return time_9;
    }

    public void setTime_9(String time_9) {
        this.time_9 = time_9;
    }

    public String getActivity_9() {
        return activity_9;
    }

    public void setActivity_9(String activity_9) {
        this.activity_9 = activity_9;
    }

    public String getTime_10() {
        return time_10;
    }

    public void setTime_10(String time_10) {
        this.time_10 = time_10;
    }

    public String getActivity_10() {
        return activity_10;
    }

    public void setActivity_10(String activity_10) {
        this.activity_10 = activity_10;
    }

    public String getTime_11() {
        return time_11;
    }

    public void setTime_11(String time_11) {
        this.time_11 = time_11;
    }

    public String getActivity_11() {
        return activity_11;
    }

    public void setActivity_11(String activity_11) {
        this.activity_11 = activity_11;
    }

    public String getTime_12() {
        return time_12;
    }

    public void setTime_12(String time_12) {
        this.time_12 = time_12;
    }

    public String getActivity_12() {
        return activity_12;
    }

    public void setActivity_12(String activity_12) {
        this.activity_12 = activity_12;
    }

    public String getTime_13() {
        return time_13;
    }

    public void setTime_13(String time_13) {
        this.time_13 = time_13;
    }

    public String getActivity_13() {
        return activity_13;
    }

    public void setActivity_13(String activity_13) {
        this.activity_13 = activity_13;
    }

    public String getTime_14() {
        return time_14;
    }

    public void setTime_14(String time_14) {
        this.time_14 = time_14;
    }

    public String getActivity_14() {
        return activity_14;
    }

    public void setActivity_14(String activity_14) {
        this.activity_14 = activity_14;
    }

    public String getTime_15() {
        return time_15;
    }

    public void setTime_15(String time_15) {
        this.time_15 = time_15;
    }

    public String getActivity_15() {
        return activity_15;
    }

    public void setActivity_15(String activity_15) {
        this.activity_15 = activity_15;
    }

    public String getTime_16() {
        return time_16;
    }

    public void setTime_16(String time_16) {
        this.time_16 = time_16;
    }

    public String getActivity_16() {
        return activity_16;
    }

    public void setActivity_16(String activity_16) {
        this.activity_16 = activity_16;
    }

    public String getTime_17() {
        return time_17;
    }

    public void setTime_17(String time_17) {
        this.time_17 = time_17;
    }

    public String getActivity_17() {
        return activity_17;
    }

    public void setActivity_17(String activity_17) {
        this.activity_17 = activity_17;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
