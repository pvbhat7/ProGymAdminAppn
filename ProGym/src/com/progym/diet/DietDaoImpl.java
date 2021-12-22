package com.progym.diet;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

public class DietDaoImpl implements DietDao{

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

    @Override
    public DietTimeSlots getDietTimeSlotObjectById(int i) {
        Session session = null;
        session = HibernateUtils.getSessionFactory().openSession();
        DietTimeSlots object = (DietTimeSlots) session.createCriteria(DietTimeSlots.class).add(Restrictions.eq("id", i)).uniqueResult();
        session.close();
        return object;
    }

    @Override
    public void addDietPlanTemplateObject(String dietPlanname, String clientId, String time_1, String activity_1, String time_2, String activity_2, String time_3, String activity_3, String time_4, String activity_4, String time_5, String activity_5, String time_6, String activity_6, String time_7, String activity_7, String time_8, String activity_8, String time_9, String activity_9, String time_10, String activity_10, String time_11, String activity_11, String time_12, String activity_12, String time_13, String activity_13, String time_14, String activity_14, String time_15, String activity_15, String time_16, String activity_16, String time_17, String activity_17) {
        Session session = null;
        session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Client client = getClientById(Integer.parseInt(clientId));
        DietPlanTemplate object = new DietPlanTemplate(dietPlanname, client, DateApi.getDdMmYyyyDate(""), "false",
                time_1, activity_1,
                time_2, activity_2,
                time_3, activity_3,
                time_4, activity_4,
                time_5, activity_5,
                time_6, activity_6,
                time_7, activity_7,
                time_8, activity_8,
                time_9, activity_9,
                time_10, activity_10,
                time_11, activity_11,
                time_12, activity_12,
                time_13, activity_13,
                time_14, activity_14,
                time_15, activity_15,
                time_16, activity_16,
                time_17, activity_17);
        object.setCid(String.valueOf(client.getId()));

        // update to server
        String dietId = ServerCom.sendSingleObjectToServer(ServerApi.CREATE_DIET_TEMPLATE_API, object);
        client.setAdp(dietId);
        ServerCom.sendSingleObjectToServer(ServerApi.UPDATE_CLIENT_API, client);

        session.saveOrUpdate(client);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveDietTemplateWithoutClient(String dietPlanname, String time_1, String activity_1, String time_2, String activity_2, String time_3, String activity_3, String time_4, String activity_4, String time_5, String activity_5, String time_6, String activity_6, String time_7, String activity_7, String time_8, String activity_8, String time_9, String activity_9, String time_10, String activity_10, String time_11, String activity_11, String time_12, String activity_12, String time_13, String activity_13, String time_14, String activity_14, String time_15, String activity_15, String time_16, String activity_16, String time_17, String activity_17) {
        DietPlanTemplate object = new DietPlanTemplate(dietPlanname, null, DateApi.getDdMmYyyyDate(""), "false",
                time_1, activity_1,
                time_2, activity_2,
                time_3, activity_3,
                time_4, activity_4,
                time_5, activity_5,
                time_6, activity_6,
                time_7, activity_7,
                time_8, activity_8,
                time_9, activity_9,
                time_10, activity_10,
                time_11, activity_11,
                time_12, activity_12,
                time_13, activity_13,
                time_14, activity_14,
                time_15, activity_15,
                time_16, activity_16,
                time_17, activity_17);
        object.setCid(null);
        // update to server
        ServerCom.sendSingleObjectToServer(ServerApi.CREATE_DIET_TEMPLATE_API, object);
    }

    @Override
    public List<DietPlanTemplate> getClientPreviousDietPlanTemplates(Client client) {
        List<DietPlanTemplate> list = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_CLIENT_PREVIOUS_DIET_PLAN_TEMPLATES+"cid="+client.getId()+"&adp="+client.getAdp());
        if (!response.isEmpty()) {
            try {
                Gson gson = new Gson();
                DietPlanTemplate array[] = gson.fromJson(response, DietPlanTemplate[].class);
                list = Arrays.asList(array);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return list;
    }

    @Override
    public List<DietPlanObject> getDietPlanObjectDetailsList(String dietPlanTemplateId  , String clientId) {
        DietPlanTemplate dietPlanTemplate = getDietPlanTemplateById(dietPlanTemplateId);
        List<DietPlanObject> list = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_ALL_DIET_OBJECT_BY_TEMPLATE_ID+dietPlanTemplateId+"&cid="+clientId+"&dietDate="+DateApi.getDdMmYyyyDate(""));
        if (!response.isEmpty()) {
            try {
                Gson gson = new Gson();
                DietPlanObject array[] = gson.fromJson(response, DietPlanObject[].class);
                list = Arrays.asList(array);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if(!list.isEmpty()){
            for(DietPlanObject d : list){
                calculateDietCompletePercentage(d);
                d.setDietPlanTemplate(dietPlanTemplate);
            }

        }

        return list;
    }

    private String calculateDietCompletePercentage(DietPlanObject object) {
        String color;
        int count = 0;
        if (object.getClientCompletionStatus_timeActivity_1().equals("yes"))
            count++;
        if (object.getClientCompletionStatus_timeActivity_2().equals("yes"))
            count++;
        if (object.getClientCompletionStatus_timeActivity_3().equals("yes"))
            count++;
        if (object.getClientCompletionStatus_timeActivity_4().equals("yes"))
            count++;
        if (object.getClientCompletionStatus_timeActivity_5().equals("yes"))
            count++;
        if (object.getClientCompletionStatus_timeActivity_6().equals("yes"))
            count++;
        if (object.getClientCompletionStatus_timeActivity_7().equals("yes"))
            count++;
        if (object.getClientCompletionStatus_timeActivity_8().equals("yes"))
            count++;
        if (object.getClientCompletionStatus_timeActivity_9().equals("yes"))
            count++;
        if (object.getClientCompletionStatus_timeActivity_10().equals("yes"))
            count++;
        if (object.getClientCompletionStatus_timeActivity_11().equals("yes"))
            count++;
        if (object.getClientCompletionStatus_timeActivity_12().equals("yes"))
            count++;
        if (object.getClientCompletionStatus_timeActivity_13().equals("yes"))
            count++;
        if (object.getClientCompletionStatus_timeActivity_14().equals("yes"))
            count++;
        if (object.getClientCompletionStatus_timeActivity_15().equals("yes"))
            count++;
        if (object.getClientCompletionStatus_timeActivity_16().equals("yes"))
            count++;
        if (object.getClientCompletionStatus_timeActivity_17().equals("yes"))
            count++;

        if (count <= 5)
            color = "red";
        else if (count > 5 && count <= 10)
            color = "yellow";
        else
            color = "green";

        object.setDietCompletionPercentageColor(color);
        return color;
    }

    @Override
    public List<DietPlanTemplate> getDefaultDietPlanTemplatesList() {
       List<DietPlanTemplate> list = new ArrayList<>();
        String response = ServerCom.sendGetRequestToServer(ServerApi.GET_ALL_DEFAULT_DIET_TEMPLATES);
        if (!response.isEmpty()) {
            try {
                Gson gson = new Gson();
                DietPlanTemplate array[] = gson.fromJson(response, DietPlanTemplate[].class);
                list = Arrays.asList(array);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return list;
    }

    @Override
    public void editDietPlanTemplateObject(String id, String name, String clientId, String templateDisplayTime_1, String templateDisplayActivity_1, String templateDisplayTime_2, String templateDisplayActivity_2, String templateDisplayTime_3, String templateDisplayActivity_3, String templateDisplayTime_4, String templateDisplayActivity_4, String templateDisplayTime_5, String templateDisplayActivity_5, String templateDisplayTime_6, String templateDisplayActivity_6, String templateDisplayTime_7, String templateDisplayActivity_7, String templateDisplayTime_8, String templateDisplayActivity_8, String templateDisplayTime_9, String templateDisplayActivity_9, String templateDisplayTime_10, String templateDisplayActivity_10, String templateDisplayTime_11, String templateDisplayActivity_11, String templateDisplayTime_12, String templateDisplayActivity_12, String templateDisplayTime_13, String templateDisplayActivity_13, String templateDisplayTime_14, String templateDisplayActivity_14, String templateDisplayTime_15, String templateDisplayActivity_15, String templateDisplayTime_16, String templateDisplayActivity_16, String templateDisplayTime_17, String templateDisplayActivity_17) {
        DietPlanTemplate obj  = getDietPlanTemplateById(id);

        obj.setName(name);
        obj.setTime_1(templateDisplayTime_1);
        obj.setTime_2(templateDisplayTime_2);
        obj.setTime_3(templateDisplayTime_3);
        obj.setTime_4(templateDisplayTime_4);
        obj.setTime_5(templateDisplayTime_5);
        obj.setTime_6(templateDisplayTime_6);
        obj.setTime_7(templateDisplayTime_7);
        obj.setTime_8(templateDisplayTime_8);
        obj.setTime_9(templateDisplayTime_9);
        obj.setTime_10(templateDisplayTime_10);
        obj.setTime_11(templateDisplayTime_11);
        obj.setTime_12(templateDisplayTime_12);
        obj.setTime_13(templateDisplayTime_13);
        obj.setTime_14(templateDisplayTime_14);
        obj.setTime_15(templateDisplayTime_15);
        obj.setTime_16(templateDisplayTime_16);
        obj.setTime_17(templateDisplayTime_17);
        obj.setActivity_1(templateDisplayActivity_1);
        obj.setActivity_2(templateDisplayActivity_2);
        obj.setActivity_3(templateDisplayActivity_3);
        obj.setActivity_4(templateDisplayActivity_4);
        obj.setActivity_5(templateDisplayActivity_5);
        obj.setActivity_6(templateDisplayActivity_6);
        obj.setActivity_7(templateDisplayActivity_7);
        obj.setActivity_8(templateDisplayActivity_8);
        obj.setActivity_9(templateDisplayActivity_9);
        obj.setActivity_10(templateDisplayActivity_10);
        obj.setActivity_11(templateDisplayActivity_11);
        obj.setActivity_12(templateDisplayActivity_12);
        obj.setActivity_13(templateDisplayActivity_13);
        obj.setActivity_14(templateDisplayActivity_14);
        obj.setActivity_15(templateDisplayActivity_15);
        obj.setActivity_16(templateDisplayActivity_16);
        obj.setActivity_17(templateDisplayActivity_17);

        ServerCom.sendSingleObjectToServer(ServerApi.UPDATE_DIET_TEMPLATE_API, obj);

    }

    @Override
    public DietPlanObject getDietPlanObjectById(String dietPlanOjectId) {
        DietPlanObject serverObject = null;
        String response = ServerCom.sendGetRequestToServer(ServerApi.SINGLE_READ_DIET_PLAN_API + dietPlanOjectId);
        if (!response.isEmpty()) {
            try {
                serverObject = new ObjectMapper().readValue(response, DietPlanObject.class);

            } catch (Exception e) {}
        }
        return serverObject;
    }

    public DietPlanObject getDietPlanObjectByCidAndDate(String clientid , String date) {
        DietPlanObject serverObject = null;
        String response = ServerCom.sendGetRequestToServer(ServerApi.SINGLE_READ_DIET_PLAN_BY_CID_AND_DATE_API + "cid="+clientid+"&dietDate="+date);
        if (!response.isEmpty()) {
            try {
                serverObject = new ObjectMapper().readValue(response, DietPlanObject.class);

            } catch (Exception e) {}
        }
        return serverObject;
    }



    @Override
    public DietPlanTemplate getDietPlanTemplateById(String dietPlanOjectId) {
        DietPlanTemplate serverObject = null;
        String response = ServerCom.sendGetRequestToServer(ServerApi.SINGLE_READ_DIET_TEMPLATE_API + dietPlanOjectId);
        if (!response.isEmpty()) {
            try {
                serverObject = new Gson().fromJson(response, DietPlanTemplate.class);

            } catch (Exception e) {}
        }
        return serverObject;
    }

    @Override
    public void createAutoDietPlansForClients() {
        Session session = null;
        // check if diet plan present or not for today's date
        session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        List<Client> clientList = new ArrayList<Client>(new LinkedHashSet((session.createCriteria(Client.class).add(Restrictions.eq("discontinue", "false")).add(Restrictions.eq("profileActiveFlag", "enable")).list())));
        for (Client c : clientList) {
            String adp = c.getAdp();
            if (adp != null) {
                DietPlanTemplate activeDietPlanTemplate = getDietPlanTemplateById(adp);
                DietPlanObject object = getDietPlanObjectByCidAndDate(String.valueOf(c.getId()) , DateApi.getDdMmYyyyDate(""));


                //DietPlanObject object = (DietPlanObject) ((session.createCriteria(DietPlanObject.class).add(Restrictions.eq("client.id", c.getId())).add(Restrictions.eq("dietDate", DateApi.getDdMmYyyyDate("")))).uniqueResult());
                if (object == null) {
                    DietPlanObject dietPlan = new DietPlanObject(activeDietPlanTemplate, c, "no", "no", DateApi.getDdMmYyyyDate(""), "false",
                            "no", "no", "no", "no", "no", "no", "no", "no", "no", "no", "no", "no", "no", "no", "no", "no", "no"
                    );
                    dietPlan.setDptid(activeDietPlanTemplate.getId());
                    dietPlan.setCid(String.valueOf(c.getId()));
                    session.save(dietPlan);
                    ServerCom.sendSingleObjectToServer(ServerApi.CREATE_DIET_PLAN_API, dietPlan);

                    PushNotificationRequest noti = new PushNotificationRequest();
                    noti.setTitle("Workout Added");
                    noti.setMessage("Hi " + c.getName() + " , \nToday's Diet is available now !!");
                    noti.setNotificationType(ProjectConstants.SIMPLE);
                    noti.setTargetClass(ProjectConstants.MAIN);
                    noti.setMobile(c.getMobile());
                    noti.setImage("");
                    noti.setClientName(c.getName());

                    List<String> tok = new ArrayList<>();
                    List<FcmTokenModal> tokens = ServerCom.getAllTokensByMobile(noti.getMobile());
                    if (tokens != null && tokens.size() > 0) {
                        for (FcmTokenModal t : tokens) {
                            tok.add(t.getToken());
                        }
                        noti.setTokensList(tok);

                        ServerCom.sendSameMessageToAllTokens(noti);
                    }
                }
            }
        }// end for

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void resetActivePlan(String clientId) {
        Session session = null;
        session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        Client c = getClientById(Integer.parseInt(clientId));
        c.setAdp(null);

        session.saveOrUpdate(c);
        session.getTransaction().commit();
        session.close();
        ServerCom.sendSingleObjectToServer(ServerApi.UPDATE_CLIENT_API, c);
    }

    @Override
    public void activateDietPlanTemplateForClient(String clientId, String templateId) {
        Session session = null;
        Client c = getClientById(Integer.parseInt(clientId));
        c.setAdp(templateId);
        session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(c);
        session.getTransaction().commit();
        session.close();
        ServerCom.sendSingleObjectToServer(ServerApi.UPDATE_CLIENT_API, c);
    }
}
