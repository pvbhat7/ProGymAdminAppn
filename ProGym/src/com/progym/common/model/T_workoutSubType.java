package com.progym.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

public class T_workoutSubType {

    @JsonInclude
    private int id;

    @JsonInclude
    private String name;

    @JsonInclude
    private String discontinue;

    @JsonInclude
    private int mtid;

    @JsonInclude
    private String gifFilePath;

    @JsonInclude
    private Integer sets;

    @JsonInclude
    private Integer reps;

    @JsonIgnore
    @ManyToOne
    private T_workoutMainType mainType;

    @JsonIgnore
    private String gifImageName;

    public T_workoutSubType() {
    }

    public T_workoutSubType(String name,T_workoutMainType mainType,String discontinue,int sets,int reps) {
        this.name = name;
        this.discontinue = discontinue;
        this.mainType = mainType;
        this.sets = sets;
        this.reps  = reps;
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

    public T_workoutMainType getMainType() {
        return mainType;
    }

    public void setMainType(T_workoutMainType mainType) {
        this.mainType = mainType;
    }



    public int getMtid() {
        return mtid;
    }

    public void setMtid(int mtid) {
        this.mtid = mtid;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public String getGifFilePath() {
        return gifFilePath;
    }

    public void setGifFilePath(String gifFilePath) {
        this.gifFilePath = gifFilePath;
    }

    public String getGifImageName() {
        return id+""+name;
    }

    public void setGifImageName(String gifImageName) {
        this.gifImageName = gifImageName;
    }
}
