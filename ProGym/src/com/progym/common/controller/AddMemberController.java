package com.progym.common.controller;

import com.progym.common.model.AddMemberObject;
import com.progym.common.model.ReferenceVO;
import com.progym.common.model.User;
import com.progym.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class AddMemberController {

    public static AddMemberObject obj = new AddMemberObject();
    static Boolean clearData = true;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/fetchUserDetailsFromServer", method = RequestMethod.POST)
    public void fetchUserDetailsFromServer(HttpServletRequest request, HttpServletResponse response, @RequestParam String enteredMobile) throws IOException {
        obj = new AddMemberObject();
        obj.setMobile(enteredMobile);
        userService.fetchUserDetailsFromServer(obj);

        clearData = false;
        response.sendRedirect("addMember");
    }

    @RequestMapping(value = "/addMember", method = RequestMethod.GET)
    public ModelAndView addMemberFormDisplay(HttpServletRequest request, HttpServletResponse response) {
        if (clearData)
            obj = new AddMemberObject();
        ModelAndView mav = new ModelAndView("add-member");
        mav.addObject("addmemberobject", obj);
        mav.addObject("gen", obj.getGender());
        mav.addObject("dobb", obj.getBirthDate());
        clearData = true;
        return mav;
    }


    @RequestMapping(value = "/addMember", method = RequestMethod.POST)
    public void addMemberFromForm(HttpSession session, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("addmemberobject") AddMemberObject addMemberObject) throws IOException {
        User user = (User) session.getAttribute("loggedInUser");
        if (user != null) {
            userService.addMemberToDatabase(addMemberObject, user, "new");
            response.sendRedirect("allMembers?gender=all&zone=none&enableDisable=enable");
        } else
            response.sendRedirect("login");
    }

    @ModelAttribute("bloodGroupsList")
    public Map<String, String> getBloodGroupsList() {
        Map<String, String> bloodGroupsList = new LinkedHashMap<String, String>();
        bloodGroupsList.put("A+", "A+");
        bloodGroupsList.put("A-", "A-");
        bloodGroupsList.put("B+", "B+");
        bloodGroupsList.put("B-", "B-");
        bloodGroupsList.put("O+", "O+");
        bloodGroupsList.put("O-", "O-");
        bloodGroupsList.put("AB+", "AB+");
        bloodGroupsList.put("AB-", "AB-");
        return bloodGroupsList;
    }


    @ModelAttribute("referencesList")
    public Map<String, String> getReferencesList() {
        Map<String, String> referencesList = new HashMap<String, String>();
        referencesList.put("none", "None");
        for (ReferenceVO ref : userService.getReferenceList()) {
            referencesList.put(String.valueOf(ref.getClientId()), ref.getName());
        }
        return referencesList;
    }

    @ModelAttribute("gendersList")
    public Map<String, String> getGendersList() {
        Map<String, String> gendersList = new LinkedHashMap<String, String>();
        gendersList.put("male", "Male");
        gendersList.put("female", "Female");
        return gendersList;
    }


}
