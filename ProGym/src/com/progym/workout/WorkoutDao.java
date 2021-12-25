package com.progym.workout;

import com.progym.common.model.*;

import java.util.List;

public interface WorkoutDao {

    List<T_workoutMainType> getWorkoutMainTypeList();

    void addWorkoutObjectToDatabase(String clientId, String workoutDate, String mainWorkoutType);

    List<WorkoutScheduleObject> getWorkoutListByClientId(String cliendId);

    void submitWorkoutSubTypeData(String workoutObjectId, String workoutSubType, String sequence, String maxReps);

    List<WorkoutSubType> getSubWorkoutListByParentObject(int id);

    void deleteSubType(String subTypeId);

    List<T_workoutSubType> getSubWorkoutStaticListByMainType(int id);

    void addMainWorkoutToDatabase(String workoutName);

    void addSubWorkoutToDatabase(String mainWorkoutId, String subWorkoutName,Integer sets , Integer reps);

    void deleteMainWorkoutType(Integer id);

    void deleteSubWorkoutType(Integer id);

    List<T_workoutSubType> getWorkoutSubTypeList();

    void reconcileWorkoutData();

    void setDefaultWorkoutPlan(String clientId, String workoutPlan);

    List<T_workoutMainType> getActiveWorkoutPlansList();

    void updateTSubworkoutType(String subWorkoutId, String serverimagePath);

    void updateTSubWorkoutName(String subWorkoutId, String name , Integer sets , Integer reps);

}