package com.progym.workout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.progym.api.DateApi;
import com.progym.api.ServerApi;
import com.progym.common.constants.ProjectConstants;
import com.progym.common.fcm.PushNotificationRequest;
import com.progym.common.model.*;
import com.progym.common.utils.HibernateUtils;
import com.progym.tavros.ServerCom;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class WorkoutDaoImpl implements WorkoutDao {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public Client getClientById(int clientId) {
        // build whole client object
        Client c = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        c = (Client) session.createCriteria(Client.class).add(Restrictions.eq("id", clientId)).uniqueResult();
        session.close();
        return c;
    }

    private void updateSubWorkoutFromServerToOfflineDb(String externalCode, String clientPerformance) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        WorkoutSubType w_offline = (WorkoutSubType) (session.createCriteria(WorkoutSubType.class).add(Restrictions.eq("id", Integer.parseInt(externalCode)))).uniqueResult();
        w_offline.setClientPerformance(clientPerformance);
        session.saveOrUpdate(w_offline);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<T_workoutSubType> getWorkoutSubTypeList() {
        List<T_workoutSubType> list = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_ALL_T_SUB_WORKOUT_TYPE);
        if (!response.isEmpty()) {
            try {
                Gson gson = new Gson();
                T_workoutSubType array[] = gson.fromJson(response, T_workoutSubType[].class);
                list = Arrays.asList(array);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return list;
    }

    @Override
    public List<T_workoutMainType> getWorkoutMainTypeList() {
        return getActiveWorkoutPlansList();
    }

    @Override
    public List<WorkoutScheduleObject> getWorkoutListByClientId(String cliendId) {

        List<WorkoutScheduleObject> list = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_ALL_WORKOUT_SCHEDULE_OBJECT_BY_CLIENT_ID + cliendId);
        if (!response.isEmpty()) {
            try {
                Gson gson = new Gson();
                WorkoutScheduleObject array[] = gson.fromJson(response, WorkoutScheduleObject[].class);
                list = Arrays.asList(array);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (!list.isEmpty()) {
            for (WorkoutScheduleObject obj : list) {

                obj.setMainWorkoutType(getTMainWorkoutTypeById(obj.getMtid()));

                // get sub workout
                obj.setSubTypesStaticList(getSubWorkoutStaticListByMainType(Integer.parseInt(obj.getMtid())));

            }
        }
        return list;
    }

    private T_workoutMainType getTMainWorkoutTypeById(String mtid) {
        T_workoutMainType obj = null;
        // get main workout
        String resp = ServerCom.sendGetRequestToServer(ServerApi.SINGLE_READ_T_MAIN_WORKOUT_TYPE_API + mtid);
        if (!resp.isEmpty()) {
            Gson gson = new Gson();
            obj = gson.fromJson(resp, T_workoutMainType.class);
        }
        return obj;
    }

    private T_workoutSubType getT_workoutSubTypeById(String id) {
        T_workoutSubType obj = null;
        // get main workout
        String resp = ServerCom.sendGetRequestToServer(ServerApi.SINGLE_READ_T_SUB_WORKOUT_TYPE_API + id);
        if (!resp.isEmpty()) {
            Gson gson = new Gson();
            obj = gson.fromJson(resp, T_workoutSubType.class);
        }
        return obj;
    }

    @Override
    public List<WorkoutSubType> getSubWorkoutListByParentObject(int wsoid) {

        List<WorkoutSubType> list = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_ALL_WORKOUT_SUB_TYPE_BY_WOS_ID + wsoid);
        if (!response.isEmpty()) {
            try {
                Gson gson = new Gson();
                WorkoutSubType array[] = gson.fromJson(response, WorkoutSubType[].class);
                list = Arrays.asList(array);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return list;
    }

    @Override
    public List<T_workoutSubType> getSubWorkoutStaticListByMainType(int mainTypeId) {

        List<T_workoutSubType> list = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_ALL_T_SUB_WORKOUT_TYPE_BY_MAIN_TYPE_ID + mainTypeId);
        if (!response.isEmpty()) {
            try {
                Gson gson = new Gson();
                T_workoutSubType array[] = gson.fromJson(response, T_workoutSubType[].class);
                list = Arrays.asList(array);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return list;

    }

    @Override
    public void submitWorkoutSubTypeData(String workoutObjectId, String workoutSubTypeId, String sets, String maxReps) {
        T_workoutSubType obj = getT_workoutSubTypeById(workoutSubTypeId);
        WorkoutSubType object = new WorkoutSubType();
        object.setWsoid(Integer.parseInt(workoutObjectId));
        object.setTwsid(obj.getName());
        object.setSets(Integer.parseInt(sets));
        object.setMaxReps(maxReps);
        object.setDiscontinue("false");
        object.setClientPerformance("no");
        object.setImage(obj.getGifFilePath());
        ServerCom.sendSingleObjectToServer(ServerApi.CREATE_WORKOUT_SUB_TYPE_API, object);
    }

    @Override
    public void addWorkoutObjectToDatabase(String clientId, String workoutDate, String mtid) {
        WorkoutScheduleObject object = new WorkoutScheduleObject(Integer.parseInt(clientId), DateApi.getDdMmYyyyDate(workoutDate), "false", mtid);
        ServerCom.sendSingleObjectToServer(ServerApi.CREATE_WORKOUT_SCHEDULE_OBJECT_API, object);

    }

    @Override
    public void deleteSubType(String subTypeId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        WorkoutSubType object = (WorkoutSubType) session.createCriteria(WorkoutSubType.class).add(Restrictions.eq("id", Integer.parseInt(subTypeId))).uniqueResult();
        object.setDiscontinue("true");
        session.save(object);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void addMainWorkoutToDatabase(String workoutName) {
        T_workoutMainType object = new T_workoutMainType(workoutName, "false");
        ServerCom.sendSingleObjectToServer(ServerApi.CREATE_T_MAIN_WORKOUT_TYPE_API, object);
    }

    @Override
    public void deleteMainWorkoutType(Integer id) {
        T_workoutMainType obj = getTMainWorkoutTypeById(String.valueOf(id));
        obj.setDiscontinue("true");
        ServerCom.sendSingleObjectToServer(ServerApi.UPDATE_T_MAIN_WORKOUT_TYPE_API, obj);
    }

    @Override
    public void deleteSubWorkoutType(Integer id) {

        T_workoutSubType object = getT_workoutSubTypeById(String.valueOf(id));
        object.setDiscontinue("true");
        ServerCom.sendSingleObjectToServer(ServerApi.UPDATE_T_MAIN_WORKOUT_TYPE_API, object);
    }

    @Override
    public void addSubWorkoutToDatabase(String mainWorkoutId, String subWorkoutName, Integer sets, Integer reps) {
        T_workoutMainType mainType = (T_workoutMainType) getTMainWorkoutTypeById(mainWorkoutId);
        T_workoutSubType object = new T_workoutSubType(subWorkoutName, mainType, "false", sets, reps);
        object.setMtid(Integer.parseInt(mainWorkoutId));
        ServerCom.sendSingleObjectToServer(ServerApi.CREATE_T_SUB_WORKOUT_TYPE_API, object);
    }

    public List<String> getMissedWorkoutScheduleObjectRecords() throws Exception {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<String> jsonStringsList = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_ALL_WORKOUT_SCHEDULE_OBJECT_EXTERNAL_CODES_API);
        if (!response.isEmpty()) {
            String externalCodes = new ObjectMapper().readValue(response, String.class);
            List<Integer> arr = new ArrayList<>();
            for (String s : externalCodes.split(",")) {
                arr.add(Integer.parseInt(s));
            }
            System.out.println("Workout schedule object records found in server :" + arr.size());

            List<WorkoutScheduleObject> list = new ArrayList<WorkoutScheduleObject>(new LinkedHashSet((session.createCriteria(WorkoutScheduleObject.class).add(Restrictions.not(Restrictions.in("id", arr))).list())));
            System.out.println("Workout schedule object missing Records found :" + list.size());
            for (WorkoutScheduleObject c : list) {
                c.setCid(c.getCid());
                c.setMtid(c.getMainWorkoutType().getName());
                jsonStringsList.add(new ObjectMapper().writeValueAsString(c));
            }
        } else {
            /*System.out.println("Sending all Workout schedule object records to server");
            for(Client c : getAllClients()){
                jsonStringsList.add(new ObjectMapper().writeValueAsString(c));
            }*/
        }
        session.close();
        return jsonStringsList;
    }

    public List<String> getMissedWorkoutSubTypeObjectRecords() throws Exception {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<String> jsonStringsList = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_ALL_WORKOUT_SUB_TYPE_EXTERNAL_CODES_API);
        if (!response.isEmpty()) {
            String externalCodes = new ObjectMapper().readValue(response, String.class);
            List<Integer> arr = new ArrayList<>();
            for (String s : externalCodes.split(",")) {
                arr.add(Integer.parseInt(s));
            }
            System.out.println("Workout sub type object Records found in server :" + arr.size());

            List<WorkoutSubType> list = new ArrayList<WorkoutSubType>(new LinkedHashSet((session.createCriteria(WorkoutSubType.class).add(Restrictions.not(Restrictions.in("id", arr))).list())));
            System.out.println("Workout sub type object Missing Records found :" + list.size());
            for (WorkoutSubType c : list) {
                c.setWsoid(c.getWorkoutScheduleObject().getId());
                c.setTwsid(c.getSubType().getName());
                c.setImage(c.getSubType().getGifFilePath());
                jsonStringsList.add(new ObjectMapper().writeValueAsString(c));
            }
        } else {
            /*System.out.println("Sending all Workout sub type object records to server");
            for(Client c : getAllClients()){
                jsonStringsList.add(new ObjectMapper().writeValueAsString(c));
            }*/
        }
        session.close();
        return jsonStringsList;
    }

    @Override
    public void reconcileWorkoutData() {
        // RECONCILE WORKOUT SCHEDULE OBJECT RECORDS WITH SERVER
        try {
            List<String> records = getMissedWorkoutScheduleObjectRecords();
            if (!records.isEmpty())
                ServerCom.sendMultipleObjectToServer(ServerApi.CREATE_WORKOUT_SCHEDULE_OBJECT_API, records);
        } catch (Exception e) {
        }

        // RECONCILE WORKOUT SUB TYPE RECORDS WITH SERVER
        try {
            List<String> records = getMissedWorkoutSubTypeObjectRecords();
            if (!records.isEmpty())
                ServerCom.sendMultipleObjectToServer(ServerApi.CREATE_WORKOUT_SUB_TYPE_API, records);
        } catch (Exception e) {
        }
    }

    @Override
    public void setDefaultWorkoutPlan(String clientId, String workoutPlan) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Client object = getClientById(Integer.parseInt(clientId));
        object.setAwp(workoutPlan);
        session.saveOrUpdate(object);
        ServerCom.sendSingleObjectToServer(ServerApi.UPDATE_CLIENT_API, object);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<T_workoutMainType> getActiveWorkoutPlansList() {
        /*return new ArrayList<T_workoutMainType>(new LinkedHashSet((session.createCriteria(T_workoutMainType.class)
                .add(Restrictions.eq("discontinue", "false"))
                .list())));*/
        List<T_workoutMainType> list = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_ALL_T_MAIN_WORKOUT_TYPE_API);
        if (!response.isEmpty()) {
            try {
                Gson gson = new Gson();
                T_workoutMainType array[] = gson.fromJson(response, T_workoutMainType[].class);
                list = Arrays.asList(array);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return list;
    }

    private List<T_workoutSubType> getSubWorkoutListForMuscles(T_workoutMainType mainWorkoutObject, WorkoutScheduleObject scheduleObject) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<T_workoutSubType> finalList = new ArrayList<>();
        MuscleWorkout obj = (MuscleWorkout) ((session.createCriteria(MuscleWorkout.class)
                .add(Restrictions.eq("day", DateApi.getDayName()))
                .add(Restrictions.eq("mainWorkoutName", mainWorkoutObject.getName())))
                .uniqueResult());

        if (obj != null) {
            T_workoutMainType mainType = (T_workoutMainType) ((session.createCriteria(T_workoutMainType.class)
                    .add(Restrictions.eq("discontinue", "false"))
                    .add(Restrictions.eq("name", obj.getSubWorkoutName())))
                    .uniqueResult());
            scheduleObject.setMainWorkoutType(mainType);
            List<T_workoutSubType> list = new ArrayList<T_workoutSubType>(new LinkedHashSet((session.createCriteria(T_workoutSubType.class)
                    .add(Restrictions.eq("discontinue", "false"))
                    .add(Restrictions.eq("mainType.id", mainType.getId()))
                    .list())));
            for (T_workoutSubType w : list) {
                if (w.getMainType().getName().equalsIgnoreCase(obj.getSubWorkoutName()))
                    finalList.add(w);
            }
        }
        session.close();
        return finalList;
    }


    @Override
    public void updateTSubworkoutType(String subWorkoutId, String serverimagePath) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        T_workoutSubType obj = (T_workoutSubType) session.createCriteria(T_workoutSubType.class).add(Restrictions.eq("id", Integer.parseInt(subWorkoutId))).uniqueResult();
        obj.setGifFilePath(serverimagePath);
        session.saveOrUpdate(obj);
        //ServerCom.sendSingleObjectToServer(UPDATE_T_SUB_WORKOUT_TYPE_API,obj);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void updateTSubWorkoutName(String subWorkoutId, String name, Integer sets, Integer reps) {
        T_workoutSubType obj = getT_workoutSubTypeById(subWorkoutId);
        obj.setName(name);
        obj.setSets(sets);
        obj.setReps(reps);
        ServerCom.sendSingleObjectToServer(ServerApi.UPDATE_T_SUB_WORKOUT_TYPE_API, obj);
    }



}
