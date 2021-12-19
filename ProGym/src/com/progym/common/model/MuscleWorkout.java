package com.progym.common.model;

import javax.persistence.*;

@Entity
@Table(name = "MuscleWorkout")
public class MuscleWorkout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String mainWorkoutName;

    @Column
    private String day;

    @Column
    private String subWorkoutName;

    public MuscleWorkout(int id, String mainWorkoutName, String day, String subWorkoutName) {
        this.id = id;
        this.mainWorkoutName = mainWorkoutName;
        this.day = day;
        this.subWorkoutName = subWorkoutName;
    }

    public MuscleWorkout() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainWorkoutName() {
        return mainWorkoutName;
    }

    public void setMainWorkoutName(String mainWorkoutName) {
        this.mainWorkoutName = mainWorkoutName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getSubWorkoutName() {
        return subWorkoutName;
    }

    public void setSubWorkoutName(String subWorkoutName) {
        this.subWorkoutName = subWorkoutName;
    }
}
