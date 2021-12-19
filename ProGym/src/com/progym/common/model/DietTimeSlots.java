package com.progym.common.model;

import javax.persistence.*;

@Entity
@Table(name = "DietTimeSlots")
public class DietTimeSlots {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String time_1;

    @Column
    private String time_2;

    @Column
    private String time_3;

    @Column
    private String time_4;

    @Column
    private String time_5;

    @Column
    private String time_6;

    @Column
    private String time_7;

    @Column
    private String time_8;

    @Column
    private String time_9;

    @Column
    private String time_10;

    @Column
    private String time_11;

    @Column
    private String time_12;

    @Column
    private String time_13;

    @Column
    private String time_14;

    @Column
    private String time_15;

    @Column
    private String time_16;

    @Column
    private String time_17;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime_1() {
        return time_1;
    }

    public void setTime_1(String time_1) {
        this.time_1 = time_1;
    }

    public String getTime_2() {
        return time_2;
    }

    public void setTime_2(String time_2) {
        this.time_2 = time_2;
    }

    public String getTime_3() {
        return time_3;
    }

    public void setTime_3(String time_3) {
        this.time_3 = time_3;
    }

    public String getTime_4() {
        return time_4;
    }

    public void setTime_4(String time_4) {
        this.time_4 = time_4;
    }

    public String getTime_5() {
        return time_5;
    }

    public void setTime_5(String time_5) {
        this.time_5 = time_5;
    }

    public String getTime_6() {
        return time_6;
    }

    public void setTime_6(String time_6) {
        this.time_6 = time_6;
    }

    public String getTime_7() {
        return time_7;
    }

    public void setTime_7(String time_7) {
        this.time_7 = time_7;
    }

    public String getTime_8() {
        return time_8;
    }

    public void setTime_8(String time_8) {
        this.time_8 = time_8;
    }

    public String getTime_9() {
        return time_9;
    }

    public void setTime_9(String time_9) {
        this.time_9 = time_9;
    }

    public String getTime_10() {
        return time_10;
    }

    public void setTime_10(String time_10) {
        this.time_10 = time_10;
    }

    public String getTime_11() {
        return time_11;
    }

    public void setTime_11(String time_11) {
        this.time_11 = time_11;
    }

    public String getTime_12() {
        return time_12;
    }

    public void setTime_12(String time_12) {
        this.time_12 = time_12;
    }

    public String getTime_13() {
        return time_13;
    }

    public void setTime_13(String time_13) {
        this.time_13 = time_13;
    }

    public String getTime_14() {
        return time_14;
    }

    public void setTime_14(String time_14) {
        this.time_14 = time_14;
    }

    public String getTime_15() {
        return time_15;
    }

    public void setTime_15(String time_15) {
        this.time_15 = time_15;
    }

    public String getTime_16() {
        return time_16;
    }

    public void setTime_16(String time_16) {
        this.time_16 = time_16;
    }

    public String getTime_17() {
        return time_17;
    }

    public void setTime_17(String time_17) {
        this.time_17 = time_17;
    }
}
