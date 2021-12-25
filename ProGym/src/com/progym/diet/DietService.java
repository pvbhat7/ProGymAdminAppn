package com.progym.diet;

import com.progym.common.model.*;

import java.util.List;

public interface DietService  {

    DietTimeSlots getDietTimeSlotObjectById(int i);

    void addDietPlanTemplateObject(String dietPlanname,String clientId,String time_1, String activity_1, String time_2, String activity_2, String time_3, String activity_3, String time_4, String activity_4, String time_5, String activity_5, String time_6, String activity_6, String time_7, String activity_7, String time_8, String activity_8, String time_9, String activity_9, String time_10, String activity_10, String time_11, String activity_11, String time_12, String activity_12, String time_13, String activity_13, String time_14, String activity_14, String time_15, String activity_15, String time_16, String activity_16, String time_17, String activity_17);

    void createAutoDietPlansForClients();

    List<DietPlanTemplate> getClientPreviousDietPlanTemplates(Client client);

    DietPlanTemplate getDietPlanTemplateById(String templateId);

    List<DietPlanObject> getDietPlanObjectDetailsList(String dietPlanTemplateId , String clientId);

    DietPlanObject getDietPlanObjectById(String dietPlanOjectId);

    void activateDietPlanTemplateForClient(String clientId, String templateId);

    List<DietPlanTemplate> getDefaultDietPlanTemplatesList();

    void editDietPlanTemplateObject(String id, String name, String clientId, String templateDisplayTime_1, String templateDisplayActivity_1, String templateDisplayTime_2, String templateDisplayActivity_2, String templateDisplayTime_3, String templateDisplayActivity_3, String templateDisplayTime_4, String templateDisplayActivity_4, String templateDisplayTime_5, String templateDisplayActivity_5, String templateDisplayTime_6, String templateDisplayActivity_6, String templateDisplayTime_7, String templateDisplayActivity_7, String templateDisplayTime_8, String templateDisplayActivity_8, String templateDisplayTime_9, String templateDisplayActivity_9, String templateDisplayTime_10, String templateDisplayActivity_10, String templateDisplayTime_11, String templateDisplayActivity_11, String templateDisplayTime_12, String templateDisplayActivity_12, String templateDisplayTime_13, String templateDisplayActivity_13, String templateDisplayTime_14, String templateDisplayActivity_14, String templateDisplayTime_15, String templateDisplayActivity_15, String templateDisplayTime_16, String templateDisplayActivity_16, String templateDisplayTime_17, String templateDisplayActivity_17);

    DietPlanTemplate getActiveDietPlanTemplate(String id);

    void resetActivePlan(String clientId);

}
