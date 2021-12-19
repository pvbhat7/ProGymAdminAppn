package com.progym.diet;

import com.progym.common.model.Client;
import com.progym.common.model.DietPlanObject;
import com.progym.common.model.DietPlanTemplate;
import com.progym.common.model.DietTimeSlots;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietServiceImpl implements DietService {

    @Autowired
    DietDao dietDao;

    @Override
    public DietTimeSlots getDietTimeSlotObjectById(int i) {
        return dietDao.getDietTimeSlotObjectById(i);
    }

    @Override
    public void addDietPlanTemplateObject(String dietPlanname,String clientId,String time_1, String activity_1, String time_2, String activity_2, String time_3, String activity_3, String time_4, String activity_4, String time_5, String activity_5, String time_6, String activity_6, String time_7, String activity_7, String time_8, String activity_8, String time_9, String activity_9, String time_10, String activity_10, String time_11, String activity_11, String time_12, String activity_12, String time_13, String activity_13, String time_14, String activity_14, String time_15, String activity_15, String time_16, String activity_16, String time_17, String activity_17) {
        if(clientId != null){
            dietDao.addDietPlanTemplateObject(dietPlanname,clientId,
                    time_1,activity_1,
                    time_2,activity_2,
                    time_3,activity_3,
                    time_4,activity_4,
                    time_5,activity_5,
                    time_6,activity_6,
                    time_7,activity_7,
                    time_8,activity_8,
                    time_9,activity_9,
                    time_10,activity_10,
                    time_11,activity_11,
                    time_12,activity_12,
                    time_13,activity_13,
                    time_14,activity_14,
                    time_15,activity_15,
                    time_16,activity_16,
                    time_17,activity_17);
        }
        else{
            dietDao.saveDietTemplateWithoutClient(dietPlanname,
                    time_1,activity_1,
                    time_2,activity_2,
                    time_3,activity_3,
                    time_4,activity_4,
                    time_5,activity_5,
                    time_6,activity_6,
                    time_7,activity_7,
                    time_8,activity_8,
                    time_9,activity_9,
                    time_10,activity_10,
                    time_11,activity_11,
                    time_12,activity_12,
                    time_13,activity_13,
                    time_14,activity_14,
                    time_15,activity_15,
                    time_16,activity_16,
                    time_17,activity_17);
        }

    }

    @Override
    public void createAutoDietPlansForClients() {
        dietDao.createAutoDietPlansForClients();
    }

    @Override
    public List<DietPlanTemplate> getClientPreviousDietPlanTemplates(Client client) {
        return dietDao.getClientPreviousDietPlanTemplates(client);
    }

    @Override
    public DietPlanTemplate getDietPlanTemplateById(String templateId) {
        return dietDao.getDietPlanTemplateById(templateId);
    }

    @Override
    public List<DietPlanObject> getDietPlanObjectDetailsList(String dietPlanTemplateId , String clientId) {
        return dietDao.getDietPlanObjectDetailsList(dietPlanTemplateId , clientId);
    }

    @Override
    public DietPlanObject getDietPlanObjectById(String dietPlanOjectId) {
        return dietDao.getDietPlanObjectById(dietPlanOjectId);
    }

    @Override
    public void activateDietPlanTemplateForClient(String clientId, String templateId) {
        dietDao.activateDietPlanTemplateForClient(clientId,templateId);
    }

    @Override
    public List<DietPlanTemplate> getDefaultDietPlanTemplatesList() {
        return dietDao.getDefaultDietPlanTemplatesList();
    }

    @Override
    public void editDietPlanTemplateObject(String id, String name, String clientId, String templateDisplayTime_1, String templateDisplayActivity_1, String templateDisplayTime_2, String templateDisplayActivity_2, String templateDisplayTime_3, String templateDisplayActivity_3, String templateDisplayTime_4, String templateDisplayActivity_4, String templateDisplayTime_5, String templateDisplayActivity_5, String templateDisplayTime_6, String templateDisplayActivity_6, String templateDisplayTime_7, String templateDisplayActivity_7, String templateDisplayTime_8, String templateDisplayActivity_8, String templateDisplayTime_9, String templateDisplayActivity_9, String templateDisplayTime_10, String templateDisplayActivity_10, String templateDisplayTime_11, String templateDisplayActivity_11, String templateDisplayTime_12, String templateDisplayActivity_12, String templateDisplayTime_13, String templateDisplayActivity_13, String templateDisplayTime_14, String templateDisplayActivity_14, String templateDisplayTime_15, String templateDisplayActivity_15, String templateDisplayTime_16, String templateDisplayActivity_16, String templateDisplayTime_17, String templateDisplayActivity_17) {
        dietDao.editDietPlanTemplateObject(id,name,clientId,
                templateDisplayTime_1,templateDisplayActivity_1,
                templateDisplayTime_2,templateDisplayActivity_2,
                templateDisplayTime_3,templateDisplayActivity_3,
                templateDisplayTime_4,templateDisplayActivity_4,
                templateDisplayTime_5,templateDisplayActivity_5,
                templateDisplayTime_6,templateDisplayActivity_6,
                templateDisplayTime_7,templateDisplayActivity_7,
                templateDisplayTime_8,templateDisplayActivity_8,
                templateDisplayTime_9,templateDisplayActivity_9,
                templateDisplayTime_10,templateDisplayActivity_10,
                templateDisplayTime_11,templateDisplayActivity_11,
                templateDisplayTime_12,templateDisplayActivity_12,
                templateDisplayTime_13,templateDisplayActivity_13,
                templateDisplayTime_14,templateDisplayActivity_14,
                templateDisplayTime_15,templateDisplayActivity_15,
                templateDisplayTime_16,templateDisplayActivity_16,
                templateDisplayTime_17,templateDisplayActivity_17
        );
    }

    @Override
    public DietPlanTemplate getActiveDietPlanTemplate(String id) {
        return dietDao.getDietPlanTemplateById(id);
    }

    @Override
    public void resetActivePlan(String clientId) {
        dietDao.resetActivePlan(clientId);
    }
}
