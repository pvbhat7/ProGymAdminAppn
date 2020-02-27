package com.progym.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.progym.model.AddClientPackageForm;
import com.progym.model.AddMemberObject;
import com.progym.model.AddPackageObject;
import com.progym.model.CPackage;
import com.progym.model.Client;
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
	    mav.addObject("membersList",userService.getMembersBy("male"));	    
	    return mav;
	  }
	  
	  @RequestMapping(value = "/femaleMembers", method = RequestMethod.GET)
	  public ModelAndView showFemaleMembers(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("display-members");
		  mav.addObject("membersList",userService.getMembersBy("female"));	    
	    return mav;
	  }
	  
	  @RequestMapping(value = "/allMembers", method = RequestMethod.GET)
	  public ModelAndView showAllMembers(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("display-members");
		  mav.addObject("membersList",userService.getMembersBy("all"));	    
	    return mav;
	  }
	  
	  @RequestMapping(value = "/addMember", method = RequestMethod.GET)
	  public ModelAndView addMemberFormDisplay(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("add-member");
		  mav.addObject("addmemberobject",new AddMemberObject());
	    return mav;
	  }
	  
	  @RequestMapping(value = "/addMember", method = RequestMethod.POST)
	  public ModelAndView addMemberFromForm(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("addmemberobject") AddMemberObject addMemberObject) {
		  userService.addMemberToDatabase(addMemberObject);
	    return new ModelAndView("welcome", "firstname", "prashant");
	  }
	  
	  @ModelAttribute("gendersList")
	   public Map<String, String> getGendersList() {
		  Map<String,String> gendersList = new LinkedHashMap<String,String>();
			gendersList.put("male", "Male");
			gendersList.put("female", "Female");
			return gendersList;		  
	  }
	  @ModelAttribute("packagesList")
	   public Map<String, String> getPackagesList(String gender) {
	      Map<String, String> packagesList = new HashMap<String, String>();
	      for(CPackage c : userService.getPackagesByFilter("male")) {
	    	  packagesList.put(String.valueOf(c.getId()),c.getPackageName());  
	      }	      
	      return packagesList;
	   }
	  
	  
	  @RequestMapping(value = "/malePackage", method = RequestMethod.GET)
	  public ModelAndView showMalePackage(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("display-packages");
		  mav.addObject("pkgList",userService.getPackagesByFilter("male"));	    
	    return mav;
	  }
	  
	  @RequestMapping(value = "/femalePackage", method = RequestMethod.GET)
	  public ModelAndView showFemalePackage(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("display-packages");
		  mav.addObject("pkgList",userService.getPackagesByFilter("female"));	 
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
	  
	  @RequestMapping(value = "/clientProfile", method = RequestMethod.GET)
	  @ResponseBody
	  public ModelAndView clientProfile(@RequestParam String cliendId,@RequestParam String gender) {
		  ModelAndView mav = new ModelAndView("client-profile");
		  mav.addObject("clientAddPackageObject", new AddClientPackageForm());
		  Client c= userService.getClientById(Integer.parseInt(cliendId));
		  mav.addObject("clientObject", c); 
		  getPackagesList(gender);
	    return mav;
	  }
	  
	  @RequestMapping(value = "/addPackageForClient", method = RequestMethod.POST)
	  public ModelAndView addPackageFromForm(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("clientAddPackageObject") AddClientPackageForm addClientPackageForm) {
		  System.out.println("***"+addClientPackageForm.toString());
		  userService.addPackageForClientToDatabase(addClientPackageForm);
	    return new ModelAndView("welcome", "firstname", "prashant");
	  }
	  
	  @RequestMapping(value = "/addPackage", method = RequestMethod.GET)
	  public ModelAndView addPackageFormDisplay(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("add-package");
		  mav.addObject("addPackageObject",new AddPackageObject());
	    return mav;
	  }
	  
	  @RequestMapping(value = "/addPackage", method = RequestMethod.POST)
	  public ModelAndView addPackageFromForm(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("addPackageObject") AddPackageObject addPackageObject) {
		  userService.addPackageToDatabase(addPackageObject);
	    return new ModelAndView("welcome", "firstname", "prashant");
	  }
	  
	  

}
