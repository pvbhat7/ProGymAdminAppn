package com.progym.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;

public class T_workoutMainType {

    @JsonInclude
    private int id;

    @JsonInclude
    private String name;

    @JsonInclude
    private String discontinue;

    public T_workoutMainType() {
    }

    public T_workoutMainType(String name, String discontinue) {
        this.name = name;
        this.discontinue = discontinue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiscontinue() {
        return discontinue;
    }

    public void setDiscontinue(String discontinue) {
        this.discontinue = discontinue;
    }



}
