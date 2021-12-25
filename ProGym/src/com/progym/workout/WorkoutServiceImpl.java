package com.progym.workout;

import com.progym.common.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    @Autowired
    WorkoutDao workoutDao;

    @Override
    public List<T_workoutMainType> getWorkoutMainTypeList() {
        return workoutDao.getWorkoutMainTypeList();
    }

    @Override
    public void addWorkoutObjectToDatabase(String clientId, String workoutDate, String mainWorkoutType) {
        workoutDao.addWorkoutObjectToDatabase(clientId,workoutDate,mainWorkoutType);
    }

    @Override
    public List<WorkoutScheduleObject> getWorkoutListByClientId(String cliendId) {
        List<WorkoutScheduleObject> mainWorkoutList = workoutDao.getWorkoutListByClientId(cliendId);
        for(WorkoutScheduleObject o : mainWorkoutList){
            o.setDate(o.getDate());
            o.setWorkoutSubTypeList(workoutDao.getSubWorkoutListByParentObject(o.getId()));
            o.setSubTypesStaticList(workoutDao.getSubWorkoutStaticListByMainType(Integer.parseInt(o.getMtid())));
        }
        return mainWorkoutList;
    }

    @Override
    public void submitWorkoutSubTypeData(String workoutObjectId, String workoutSubType, String sequence, String maxReps) {
        workoutDao.submitWorkoutSubTypeData(workoutObjectId,workoutSubType,sequence,maxReps);
    }

    @Override
    public void deleteSubType(String subTypeId) {
        workoutDao.deleteSubType(subTypeId);
    }

    @Override
    public void addMainWorkoutToDatabase(String workoutName) {
        workoutDao.addMainWorkoutToDatabase(workoutName);
    }

    @Override
    public void addSubWorkoutToDatabase(String mainWorkoutId, String subWorkoutName,Integer sets, Integer reps) {
        workoutDao.addSubWorkoutToDatabase(mainWorkoutId,subWorkoutName,sets,reps);
    }

    @Override
    public void deleteMainWorkoutType(Integer id) {
        workoutDao.deleteMainWorkoutType(id);
    }

    @Override
    public void deleteSubWorkoutType(Integer id) {
        workoutDao.deleteSubWorkoutType(id);
    }

    @Override
    public List<T_workoutSubType> getWorkoutSubTypeList() {
        return workoutDao.getWorkoutSubTypeList();
    }

    @Override
    public void reconcileWorkoutData() {
        workoutDao.reconcileWorkoutData();
    }

    @Override
    public void setDefaultWorkoutPlan(String clientId, String workoutPlan) {
        workoutDao.setDefaultWorkoutPlan(clientId , workoutPlan);
    }

    @Override
    public List<T_workoutMainType> getActiveWorkoutPlansList() {
        return workoutDao.getActiveWorkoutPlansList();
    }

    @Override
    public void updateTSubworkoutType(String subWorkoutId, String serverimagePath) {
        workoutDao.updateTSubworkoutType(subWorkoutId , serverimagePath);
    }

    @Override
    public void updateTSubWorkoutName(String subWorkoutId, String name, Integer sets, Integer reps) {
        workoutDao.updateTSubWorkoutName(subWorkoutId , name , sets , reps);
    }
    
}
