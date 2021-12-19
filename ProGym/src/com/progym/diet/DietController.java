package com.progym.diet;

import com.google.gson.Gson;
import com.progym.api.ProjectContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class DietController {

    @Autowired
    DietService dietService;

    @RequestMapping(value = "/viewActiveDietPlanByClientId", method = RequestMethod.GET)
    @ResponseBody
    public void viewActiveDietPlanByClientId(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                             @RequestParam String clientId, @RequestParam String gender) throws IOException {
        ProjectContext.VIEW_ACTIVE_DIET_PLAN_BY_CLIENT_ID = "true";
        String uri = "clientProfile?cliendId=" + clientId + "&gender=" + gender + "";
        response.sendRedirect(uri);
    }

    @RequestMapping(value = "/viewOldDietPlanTemplate", method = RequestMethod.GET)
    @ResponseBody
    public void viewOldDietPlanTemplate(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                        @RequestParam String clientId, @RequestParam String gender, @RequestParam String templateId) throws IOException {
        ProjectContext.VIEW_OLD_DIET_PLAN_TEMPLATE = templateId;
        String uri = "clientProfile?cliendId=" + clientId + "&gender=" + gender + "";
        response.sendRedirect(uri);
    }

    @RequestMapping(value = "/viewDietPlanObjectDetails", method = RequestMethod.GET)
    @ResponseBody
    public void viewDietPlanObjectDetails(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                          @RequestParam String clientId, @RequestParam String gender,
                                          @RequestParam String dietPlanOjectId, @RequestParam String templateId) throws IOException {
        ProjectContext.VIEW_DIET_PLAN_OBJECT_DETAILS = new Gson().toJson(dietService.getDietPlanObjectById(dietPlanOjectId));
        String uri = "clientProfile?cliendId=" + clientId + "&gender=" + gender + "";
        response.sendRedirect(uri);
    }

    @RequestMapping(value = "/activateDietPlanTemplate", method = RequestMethod.GET)
    @ResponseBody
    public void activateDietPlanTemplate(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam String clientId, @RequestParam String gender,
                                         @RequestParam String templateId) throws IOException {
        dietService.activateDietPlanTemplateForClient(clientId, templateId);
        String uri = "clientProfile?cliendId=" + clientId + "&gender=" + gender + "";
        response.sendRedirect(uri);
    }


    @RequestMapping(value = "/submitTemplateDietPlanData", method = RequestMethod.POST)
    @ResponseBody
    public void submitTemplateDietPlanData(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                           @RequestParam String name, @RequestParam String clientId, @RequestParam String gender,
                                           @RequestParam String templateDisplayTime_1, @RequestParam String templateDisplayActivity_1,
                                           @RequestParam String templateDisplayTime_2, @RequestParam String templateDisplayActivity_2,
                                           @RequestParam String templateDisplayTime_3, @RequestParam String templateDisplayActivity_3,
                                           @RequestParam String templateDisplayTime_4, @RequestParam String templateDisplayActivity_4,
                                           @RequestParam String templateDisplayTime_5, @RequestParam String templateDisplayActivity_5,
                                           @RequestParam String templateDisplayTime_6, @RequestParam String templateDisplayActivity_6,
                                           @RequestParam String templateDisplayTime_7, @RequestParam String templateDisplayActivity_7,
                                           @RequestParam String templateDisplayTime_8, @RequestParam String templateDisplayActivity_8,
                                           @RequestParam String templateDisplayTime_9, @RequestParam String templateDisplayActivity_9,
                                           @RequestParam String templateDisplayTime_10, @RequestParam String templateDisplayActivity_10,
                                           @RequestParam String templateDisplayTime_11, @RequestParam String templateDisplayActivity_11,
                                           @RequestParam String templateDisplayTime_12, @RequestParam String templateDisplayActivity_12,
                                           @RequestParam String templateDisplayTime_13, @RequestParam String templateDisplayActivity_13,
                                           @RequestParam String templateDisplayTime_14, @RequestParam String templateDisplayActivity_14,
                                           @RequestParam String templateDisplayTime_15, @RequestParam String templateDisplayActivity_15,
                                           @RequestParam String templateDisplayTime_16, @RequestParam String templateDisplayActivity_16,
                                           @RequestParam String templateDisplayTime_17, @RequestParam String templateDisplayActivity_17
    ) throws IOException {
        dietService.addDietPlanTemplateObject(name, clientId,
                templateDisplayTime_1, templateDisplayActivity_1,
                templateDisplayTime_2, templateDisplayActivity_2,
                templateDisplayTime_3, templateDisplayActivity_3,
                templateDisplayTime_4, templateDisplayActivity_4,
                templateDisplayTime_5, templateDisplayActivity_5,
                templateDisplayTime_6, templateDisplayActivity_6,
                templateDisplayTime_7, templateDisplayActivity_7,
                templateDisplayTime_8, templateDisplayActivity_8,
                templateDisplayTime_9, templateDisplayActivity_9,
                templateDisplayTime_10, templateDisplayActivity_10,
                templateDisplayTime_11, templateDisplayActivity_11,
                templateDisplayTime_12, templateDisplayActivity_12,
                templateDisplayTime_13, templateDisplayActivity_13,
                templateDisplayTime_14, templateDisplayActivity_14,
                templateDisplayTime_15, templateDisplayActivity_15,
                templateDisplayTime_16, templateDisplayActivity_16,
                templateDisplayTime_17, templateDisplayActivity_17
        );
        String uri = "clientProfile?cliendId=" + clientId + "&gender=" + gender + "";
        response.sendRedirect(uri);
    }

    @RequestMapping(value = "/updateTemplateDietPlanData", method = RequestMethod.POST)
    @ResponseBody
    public void updateTemplateDietPlanData(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                           @RequestParam String id, @RequestParam String name,
                                           @RequestParam String clientId, @RequestParam String gender,
                                           @RequestParam String templateDisplayTime_1, @RequestParam String templateDisplayActivity_1,
                                           @RequestParam String templateDisplayTime_2, @RequestParam String templateDisplayActivity_2,
                                           @RequestParam String templateDisplayTime_3, @RequestParam String templateDisplayActivity_3,
                                           @RequestParam String templateDisplayTime_4, @RequestParam String templateDisplayActivity_4,
                                           @RequestParam String templateDisplayTime_5, @RequestParam String templateDisplayActivity_5,
                                           @RequestParam String templateDisplayTime_6, @RequestParam String templateDisplayActivity_6,
                                           @RequestParam String templateDisplayTime_7, @RequestParam String templateDisplayActivity_7,
                                           @RequestParam String templateDisplayTime_8, @RequestParam String templateDisplayActivity_8,
                                           @RequestParam String templateDisplayTime_9, @RequestParam String templateDisplayActivity_9,
                                           @RequestParam String templateDisplayTime_10, @RequestParam String templateDisplayActivity_10,
                                           @RequestParam String templateDisplayTime_11, @RequestParam String templateDisplayActivity_11,
                                           @RequestParam String templateDisplayTime_12, @RequestParam String templateDisplayActivity_12,
                                           @RequestParam String templateDisplayTime_13, @RequestParam String templateDisplayActivity_13,
                                           @RequestParam String templateDisplayTime_14, @RequestParam String templateDisplayActivity_14,
                                           @RequestParam String templateDisplayTime_15, @RequestParam String templateDisplayActivity_15,
                                           @RequestParam String templateDisplayTime_16, @RequestParam String templateDisplayActivity_16,
                                           @RequestParam String templateDisplayTime_17, @RequestParam String templateDisplayActivity_17
    ) throws IOException {
        dietService.editDietPlanTemplateObject(id, name, clientId,
                templateDisplayTime_1, templateDisplayActivity_1,
                templateDisplayTime_2, templateDisplayActivity_2,
                templateDisplayTime_3, templateDisplayActivity_3,
                templateDisplayTime_4, templateDisplayActivity_4,
                templateDisplayTime_5, templateDisplayActivity_5,
                templateDisplayTime_6, templateDisplayActivity_6,
                templateDisplayTime_7, templateDisplayActivity_7,
                templateDisplayTime_8, templateDisplayActivity_8,
                templateDisplayTime_9, templateDisplayActivity_9,
                templateDisplayTime_10, templateDisplayActivity_10,
                templateDisplayTime_11, templateDisplayActivity_11,
                templateDisplayTime_12, templateDisplayActivity_12,
                templateDisplayTime_13, templateDisplayActivity_13,
                templateDisplayTime_14, templateDisplayActivity_14,
                templateDisplayTime_15, templateDisplayActivity_15,
                templateDisplayTime_16, templateDisplayActivity_16,
                templateDisplayTime_17, templateDisplayActivity_17
        );
        String uri = "dietTemplates";
        response.sendRedirect(uri);
    }

    @RequestMapping(value = "/dietTemplates", method = RequestMethod.GET)
    public ModelAndView dietTemplates(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("dietTemplates");
        mav.addObject("dietTimeSlotObject", dietService.getDietTimeSlotObjectById(1));
        mav.addObject("allDietTemplatesList", dietService.getDefaultDietPlanTemplatesList());
        return mav;
    }

    @RequestMapping(value = "/submitDietPlanData", method = RequestMethod.POST)
    @ResponseBody
    public void submitDietPlanData(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                   @RequestParam String name, @RequestParam String clientId, @RequestParam String gender,
                                   @RequestParam String time_1, @RequestParam String activity_1,
                                   @RequestParam String time_2, @RequestParam String activity_2,
                                   @RequestParam String time_3, @RequestParam String activity_3,
                                   @RequestParam String time_4, @RequestParam String activity_4,
                                   @RequestParam String time_5, @RequestParam String activity_5,
                                   @RequestParam String time_6, @RequestParam String activity_6,
                                   @RequestParam String time_7, @RequestParam String activity_7,
                                   @RequestParam String time_8, @RequestParam String activity_8,
                                   @RequestParam String time_9, @RequestParam String activity_9,
                                   @RequestParam String time_10, @RequestParam String activity_10,
                                   @RequestParam String time_11, @RequestParam String activity_11,
                                   @RequestParam String time_12, @RequestParam String activity_12,
                                   @RequestParam String time_13, @RequestParam String activity_13,
                                   @RequestParam String time_14, @RequestParam String activity_14,
                                   @RequestParam String time_15, @RequestParam String activity_15,
                                   @RequestParam String time_16, @RequestParam String activity_16,
                                   @RequestParam String time_17, @RequestParam String activity_17) throws IOException {
        dietService.addDietPlanTemplateObject(name, clientId,
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
                time_17, activity_17
        );
        String uri = "clientProfile?cliendId=" + clientId + "&gender=" + gender + "";
        response.sendRedirect(uri);
    }

    @RequestMapping(value = "/submitDietPlanTemplateData", method = RequestMethod.POST)
    @ResponseBody
    public void submitDietPlanTemplateData(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam String name,
                                           @RequestParam String time_1, @RequestParam String activity_1,
                                           @RequestParam String time_2, @RequestParam String activity_2,
                                           @RequestParam String time_3, @RequestParam String activity_3,
                                           @RequestParam String time_4, @RequestParam String activity_4,
                                           @RequestParam String time_5, @RequestParam String activity_5,
                                           @RequestParam String time_6, @RequestParam String activity_6,
                                           @RequestParam String time_7, @RequestParam String activity_7,
                                           @RequestParam String time_8, @RequestParam String activity_8,
                                           @RequestParam String time_9, @RequestParam String activity_9,
                                           @RequestParam String time_10, @RequestParam String activity_10,
                                           @RequestParam String time_11, @RequestParam String activity_11,
                                           @RequestParam String time_12, @RequestParam String activity_12,
                                           @RequestParam String time_13, @RequestParam String activity_13,
                                           @RequestParam String time_14, @RequestParam String activity_14,
                                           @RequestParam String time_15, @RequestParam String activity_15,
                                           @RequestParam String time_16, @RequestParam String activity_16,
                                           @RequestParam String time_17, @RequestParam String activity_17) throws IOException {
        dietService.addDietPlanTemplateObject(name, null,
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
                time_17, activity_17
        );
        String uri = "dietTemplates";
        response.sendRedirect(uri);
    }

    @RequestMapping(value = "/assignDiets", method = RequestMethod.GET)
    public void assignDiets(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // create auto-diet plans for clients
        dietService.createAutoDietPlansForClients();
        response.sendRedirect("index");
    }


    @RequestMapping(value = "/resetActiveDietPlan", method = RequestMethod.GET)
    @ResponseBody
    public void resetActiveDietPlan(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                             @RequestParam String clientId, @RequestParam String gender) throws IOException {
        dietService.resetActivePlan(clientId);
        String uri = "clientProfile?cliendId=" + clientId + "&gender=" + gender + "";
        response.sendRedirect(uri);
    }

}
