package com.progym.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.progym.model.AddMemberObject;
import com.progym.model.Login;
import com.progym.service.UserService;

@Controller
public class MainController {

	
	
	@Autowired
	  UserService userService;
	  
		@RequestMapping(value = "/index", method = RequestMethod.GET)
	  public ModelAndView showIndexPage(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("index");
	    return mav;
	  }
	
	  @RequestMapping(value = "/maleMembers", method = RequestMethod.GET)
	  public ModelAndView showMaleMembers(HttpServletRequest request, HttpServletResponse response) {
	    ModelAndView mav = new ModelAndView("display-members");
	    return mav;
	  }
	  
	  @RequestMapping(value = "/femaleMembers", method = RequestMethod.GET)
	  public ModelAndView showFemaleMembers(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("display-members");
	    return mav;
	  }
	  
	  @RequestMapping(value = "/allMembers", method = RequestMethod.GET)
	  public ModelAndView showAllMembers(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("display-members");
	    return mav;
	  }
	  
	  @RequestMapping(value = "/addMember", method = RequestMethod.GET)
	  public ModelAndView addMemberFormDisplay(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("add-member");
		  mav.addObject("addmemberobject",new AddMemberObject());
		  
		  //genderlist
		  	Map referenceData = new HashMap();
			Map<String,String> gender = new LinkedHashMap<String,String>();
			gender.put("male", "Male");
			gender.put("female", "Female");
			referenceData.put("genderList", gender);
		/*
		 * referenceData.put("categoryList", gender); referenceData.put("durationList",
		 * gender); referenceData.put("packageList", gender);
		 */
			
	    return mav;
	  }
	  
	  @ModelAttribute("countryList")
	   public Map<String, String> getCountryList() {
	      Map<String, String> countryList = new HashMap<String, String>();
	      countryList.put("US", "United States");
	      countryList.put("CH", "China");
	      countryList.put("SG", "Singapore");
	      countryList.put("MY", "Malaysia");
	      return countryList;
	   }
	  
	  @RequestMapping(value = "/addMember", method = RequestMethod.POST)
	  public ModelAndView addMemberInSystem(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("add-member");
	    return mav;
	  }
	  
	  @RequestMapping(value = "/oneMonthPackage", method = RequestMethod.GET)
	  public ModelAndView showOneMonthPackage(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("display-packages");
	    return mav;
	  }
	  
	  @RequestMapping(value = "/threeMonthPackage", method = RequestMethod.GET)
	  public ModelAndView showThreeMonthPackage(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("display-packages");
	    return mav;
	  }
	  
	  @RequestMapping(value = "/sixMonthPackage", method = RequestMethod.GET)
	  public ModelAndView showSixMonthPackage(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("display-packages");
	    return mav;
	  }
	  
	  @RequestMapping(value = "/oneYearPackage", method = RequestMethod.GET)
	  public ModelAndView showOneYearPackage(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("display-packages");
	    return mav;
	  }
	  
	  @RequestMapping(value = "/allPackages", method = RequestMethod.GET)
	  public ModelAndView showAllPackages(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("display-packages");
	    return mav;
	  }
	  
	  @RequestMapping(value = "/paidPayments", method = RequestMethod.GET)
	  public ModelAndView showPaidPayments(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("display-payments");
	    return mav;
	  }
	  
	  @RequestMapping(value = "/pendingPayments", method = RequestMethod.GET)
	  public ModelAndView showPendingPayments(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("display-payments");
	    return mav;
	  }
	  
	  @RequestMapping(value = "/allPayments", method = RequestMethod.GET)
	  public ModelAndView showAllPayments(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("display-payments");
	    return mav;
	  }
	  
	  @RequestMapping(value = "/allReports", method = RequestMethod.GET)
	  public ModelAndView showAllReports(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("display-reports");
	    return mav;
	  }
	  

}
