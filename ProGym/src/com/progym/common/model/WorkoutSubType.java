package com.progym.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

public class WorkoutSubType {

    @JsonInclude
    private int id;

    @JsonInclude
    private int wsoid;

    @JsonInclude
    private String twsid;

    @JsonInclude
    private String maxReps;

    @JsonInclude
    private Integer sets;

    @JsonInclude
    private String discontinue;

    @JsonInclude
    private String clientPerformance;

    @JsonInclude
    private String image;

    @JsonIgnore
    private WorkoutScheduleObject workoutScheduleObject;

    @JsonIgnore
    private T_workoutSubType subType;

    public WorkoutSubType() {
    }

    public WorkoutSubType(T_workoutSubType subType, String maxReps, Integer sets, WorkoutScheduleObject workoutScheduleObject, String discontinue,String clientPerformance,String image) {
        this.subType = subType;
        this.maxReps = maxReps;
        this.sets = sets;
        this.workoutScheduleObject = workoutScheduleObject;
        this.discontinue = discontinue;
        this.clientPerformance = clientPerformance;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WorkoutScheduleObject getWorkoutScheduleObject() {
        return workoutScheduleObject;
    }

    public void setWorkoutScheduleObject(WorkoutScheduleObject workoutScheduleObject) {
        this.workoutScheduleObject = workoutScheduleObject;
    }

    public T_workoutSubType getSubType() {
        return subType;
    }

    public void setSubType(T_workoutSubType subType) {
        this.subType = subType;
    }

    public String getMaxReps() {
        return maxReps;
    }

    public void setMaxReps(String maxReps) {
        this.maxReps = maxReps;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public String getDiscontinue() {
        return discontinue;
    }

    public void setDiscontinue(String discontinue) {
        this.discontinue = discontinue;
    }

    public int getWsoid() {
        return wsoid;
    }

    public void setWsoid(int wsoid) {
        this.wsoid = wsoid;
    }

    public String getTwsid() {
        return twsid;
    }

    public void setTwsid(String twsid) {
        this.twsid = twsid;
    }

    public String getClientPerformance() {
        return clientPerformance;
    }

    public void setClientPerformance(String clientPerformance) {
        this.clientPerformance = clientPerformance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
