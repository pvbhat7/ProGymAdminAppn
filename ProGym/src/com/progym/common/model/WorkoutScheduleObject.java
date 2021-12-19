package com.progym.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;

public class WorkoutScheduleObject {


    @JsonInclude
    private int id;

    @JsonInclude
    private int cid;

    @JsonInclude
    private String mtid;

    @JsonInclude
    private String date;

    @JsonInclude
    private String discontinue;

    @JsonIgnore
    List<WorkoutSubType> workoutSubTypeList;

    @JsonIgnore
    List<T_workoutSubType> subTypesStaticList;

    @JsonIgnore
    T_workoutMainType mainWorkoutType;

    public WorkoutScheduleObject() {
    }

    public WorkoutScheduleObject(int cid, String date, String discontinue, String mtid) {
        this.cid = cid;
        this.date = date;
        this.discontinue = discontinue;
        this.mtid = mtid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDiscontinue() {
        return discontinue;
    }

    public void setDiscontinue(String discontinue) {
        this.discontinue = discontinue;
    }

    public List<WorkoutSubType> getWorkoutSubTypeList() {
        return workoutSubTypeList;
    }

    public void setWorkoutSubTypeList(List<WorkoutSubType> workoutSubTypeList) {
        this.workoutSubTypeList = workoutSubTypeList;
    }

    public T_workoutMainType getMainWorkoutType() {
        return mainWorkoutType;
    }

    public void setMainWorkoutType(T_workoutMainType mainWorkoutType) {
        this.mainWorkoutType = mainWorkoutType;
    }

    public List<T_workoutSubType> getSubTypesStaticList() {
        return subTypesStaticList;
    }

    public void setSubTypesStaticList(List<T_workoutSubType> subTypesStaticList) {
        this.subTypesStaticList = subTypesStaticList;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getMtid() {
        return mtid;
    }

    public void setMtid(String mtid) {
        this.mtid = mtid;
    }
}
