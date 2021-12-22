package com.progym.common.controller;

import com.progym.common.model.AddPackageObject;
import com.progym.common.model.User;
import com.progym.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class AddPackageController {

    @Autowired
    UserService userService;

    @ModelAttribute("packagePeriodsList")
    public Map<String, String> getPackagePeriodsList() {
        Map<String, String> packagePeriodsList = new LinkedHashMap<String, String>();
        packagePeriodsList.put("30", "1 Month");
        packagePeriodsList.put("90", "3 Month");
        packagePeriodsList.put("180", "6 Month");
        packagePeriodsList.put("365", "1 Year");
        return packagePeriodsList;
    }

    @ModelAttribute("gendersList")
    public Map<String, String> getGendersList() {
        Map<String, String> gendersList = new LinkedHashMap<String, String>();
        gendersList.put("male", "Male");
        gendersList.put("female", "Female");
        return gendersList;
    }

    @RequestMapping(value = "/malePackage", method = RequestMethod.GET)
    public ModelAndView showMalePackage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("display-packages");
        mav.addObject("pkgList", userService.getPackagesByFilter("male"));
        return mav;
    }

    @RequestMapping(value = "/femalePackage", method = RequestMethod.GET)
    public ModelAndView showFemalePackage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("display-packages");
        mav.addObject("pkgList", userService.getPackagesByFilter("female"));
        return mav;
    }

    @RequestMapping(value = "/addPackage", method = RequestMethod.GET)
    public ModelAndView addPackageFormDisplay(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("add-package");
        mav.addObject("addPackageObject", new AddPackageObject());
        return mav;
    }

    @RequestMapping(value = "/addPackage", method = RequestMethod.POST)
    public void addPackageFromFormToDb(HttpSession session, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("addPackageObject") AddPackageObject addPackageObject) throws IOException {
        User user = (User) session.getAttribute("loggedInUser");
        userService.addPackageToDatabase(addPackageObject, user);
        response.sendRedirect("allMembers?gender=all&zone=none&enableDisable=enable");
    }




}
