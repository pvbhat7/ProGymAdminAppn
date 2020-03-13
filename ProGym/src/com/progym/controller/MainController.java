package com.progym.controller;

import java.io.IOException;
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
import com.progym.model.CollectionDashboardPVO;
import com.progym.model.CollectionPVO;
import com.progym.model.FilterCollectionObject;
import com.progym.model.User;
import com.progym.model.PackageDetails;
import com.progym.model.PaymentTransaction;
import com.progym.service.UserService;

@Controller
public class MainController {

	
	
	@Autowired
	  UserService userService;
	  
		@RequestMapping(value = "/index", method = RequestMethod.GET)
	  public ModelAndView showIndexPage(HttpServletRequest request, HttpServletResponse response) {
			User u = (User)request.getSession().getAttribute("loggedInUser");
			if(u != null)
				System.out.println("user found in session "+u.toString());
	    ModelAndView mav = new ModelAndView("index");
	    CollectionDashboardPVO c = userService.getDashboardCollection();
	    System.out.println(c.toString());
	    mav.addObject("male",c.getMale());
	    mav.addObject("female",c.getFemale());
	    mav.addObject("steam",c.getSteam());
	    mav.addObject("total",c.getTotal());
	    mav.addObject("maletotal",c.getMaletotal());
	    mav.addObject("femaletotal",c.getFemaletotal());
	    mav.addObject("clienttotal",c.getClienttotal());
	    if(u != null)
	    mav.addObject("username",u.getName());
	    return mav;
	  }
	
	  @RequestMapping(value = "/maleMembers", method = RequestMethod.GET)
	  public ModelAndView showMaleMembers(HttpServletRequest request, HttpServletResponse response) {		  
	    ModelAndView mav = new ModelAndView("display-members");
	    mav.clear();
	    mav.addObject("membersList",userService.getMembersBy("male"));
	    mav.setViewName("display-members");
	    return mav;
	  }
	  
	  @RequestMapping(value = "/femaleMembers", method = RequestMethod.GET)
	  public ModelAndView showFemaleMembers(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("display-members");
		  mav.clear();
		  mav.addObject("membersList",userService.getMembersBy("female"));
		  mav.setViewName("display-members");
	    return mav;
	  }
	  
	  @RequestMapping(value = "/allMembers", method = RequestMethod.GET)
	  public ModelAndView showAllMembers(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("display-members");
		  mav.clear();
		  mav.addObject("membersList",userService.getMembersBy("all"));
		  mav.setViewName("display-members");
	    return mav;
	  }
	  
	  @RequestMapping(value = "/addMember", method = RequestMethod.GET)
	  public ModelAndView addMemberFormDisplay(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("add-member");
		  mav.addObject("addmemberobject",new AddMemberObject());
	    return mav;
	  }
	  
	  @RequestMapping(value = "/addMember", method = RequestMethod.POST)
	  public void addMemberFromForm(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("addmemberobject") AddMemberObject addMemberObject) throws IOException {
		  userService.addMemberToDatabase(addMemberObject);
		  response.sendRedirect("allMembers");	    
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
	      if(gender!=null) {
	      for(CPackage c : userService.getPackagesByFilter(gender)) {
	    	  packagesList.put(String.valueOf(c.getId()),c.getPackageName());  
	      }	      
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
	  public ModelAndView showPaidPayments(HttpServletRequest request, HttpServletResponse response,@RequestParam String gender) {		  
		  ModelAndView mav = new ModelAndView("display-payments");
		  mav.addObject("paymentDataPVOList", userService.getPaymentData("fully-paid",gender) );
		  mav.addObject("type", "paid");
	    return mav;
	  }
	  
	  @RequestMapping(value = "/pendingPayments", method = RequestMethod.GET)
	  public ModelAndView showPendingPayments(HttpServletRequest request, HttpServletResponse response,@RequestParam String gender) {
		  ModelAndView mav = new ModelAndView("display-payments");
		  mav.addObject("paymentDataPVOList", userService.getPaymentData("partial-paid",gender) );
		  mav.addObject("type", "notpaid");
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
		  Client client = userService.getClientById(Integer.parseInt(cliendId));
		  mav.addObject("clientObject", client);
		  mav.addObject("clientPackagesList", userService.getClientPackagesByClient(client));
		  mav.addObject("transactionObject", new PaymentTransaction());
		  getPackagesList(gender);
	    return mav;
	  }
	  
	  @RequestMapping(value = "/addPackageForClient", method = RequestMethod.POST)
	  public void addPackageFromForm(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("clientAddPackageObject") AddClientPackageForm addClientPackageForm) throws IOException {
		  userService.addPackageForClientToDatabase(addClientPackageForm);
		  response.sendRedirect("allMembers");
	  }
	  
	  @RequestMapping(value = "/addPackage", method = RequestMethod.GET)
	  public ModelAndView addPackageFormDisplay(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("add-package");
		  mav.addObject("addPackageObject",new AddPackageObject());
	    return mav;
	  }
	  
	  @RequestMapping(value = "/addPackage", method = RequestMethod.POST)
	  public void addPackageFromFormToDb(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("addPackageObject") AddPackageObject addPackageObject) throws IOException {
		  userService.addPackageToDatabase(addPackageObject);
		  response.sendRedirect("allMembers");
	  }
	  
	  @RequestMapping(value = "/addTransaction", method = RequestMethod.POST)
	  public void addTransactionFromForm(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("transactionObject") PaymentTransaction paymentTransaction) throws IOException {
		  System.out.println(paymentTransaction.toString());
		  userService.createTransaction(paymentTransaction);
		  response.sendRedirect("allMembers");
	  }
	  
	 
	  @RequestMapping(value = "/viewCollectionByGMY", method = RequestMethod.GET)
	  public ModelAndView viewCollectionByGMY(HttpServletRequest request, HttpServletResponse response,
			  @RequestParam String gender,@RequestParam String month,@RequestParam String year) throws IOException {
		  ModelAndView mav = new ModelAndView("display-reports");
		  List<CollectionPVO> list = userService.getCollectionBy(new FilterCollectionObject("GMY", gender, month, year, null));
		  mav.addObject("collectionDataPVOList",list );
		  Double total =0.00;
		  for(CollectionPVO p : list)
		  {
			  total = total + p.getFeesPaid(); 
		  }
		  mav.addObject("filtername", "1d");
		  mav.addObject("totalCollection", total);
		  return mav;		  
	  }
	  
	  @RequestMapping(value = "/viewCollectionByGD", method = RequestMethod.GET)
	  public ModelAndView viewCollectionByGD(HttpServletRequest request, HttpServletResponse response,
			  @RequestParam String gender,@RequestParam String date) throws IOException {
		  ModelAndView mav = new ModelAndView("display-reports");
		  List<CollectionPVO> list = userService.getCollectionBy(new FilterCollectionObject("GD", gender, null, null, date));
		  mav.addObject("collectionDataPVOList",list );
		  Double total =0.00;
		  for(CollectionPVO p : list)
		  {
			  total = total + p.getFeesPaid(); 
		  }
		  mav.addObject("filtername", "2d");
		  mav.addObject("totalCollection", total);
		  return mav;		  
	  }
	  
	  @RequestMapping(value = "/viewCollectionByG", method = RequestMethod.GET)
	  public ModelAndView viewCollectionByG(HttpServletRequest request, HttpServletResponse response,
			  @RequestParam String gender) throws IOException {
		  ModelAndView mav = new ModelAndView("display-reports");
		  List<CollectionPVO> list = userService.getCollectionBy(new FilterCollectionObject("G", gender, null, null, null));
		  mav.addObject("collectionDataPVOList",list );
		  Double total =0.00;
		  for(CollectionPVO p : list)
		  {
			  total = total + p.getFeesPaid(); 
		  }
		  mav.addObject("filtername", "3d");
		  mav.addObject("totalCollection", total);
		  return mav;		  
	  }
	  
	  
	  @RequestMapping(value = "/approveTransaction", method = RequestMethod.GET)
	  @ResponseBody
	  public void approveTransaction(HttpServletRequest request, HttpServletResponse response,@RequestParam String txnId,@RequestParam String cID,@RequestParam String gender) throws IOException {
		  userService.approveTransaction(txnId);
		  String uri = "clientProfile?cliendId="+cID+"&gender="+gender+"";
		  System.out.println(uri);
		  response.sendRedirect(uri);
		  }
	  
	  
	  
	  
	  

}
