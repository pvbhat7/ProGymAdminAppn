package com.progym.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.progym.model.AddClientPackageForm;
import com.progym.model.AddMemberObject;
import com.progym.model.AddPackageObject;
import com.progym.model.CPackage;
import com.progym.model.Client;
import com.progym.model.CollectionDashboardPVO;
import com.progym.model.CollectionPVO;
import com.progym.model.FemaleMemberAdditionalDataVO;
import com.progym.model.FileModel;
import com.progym.model.FilterCollectionObject;
import com.progym.model.User;
import com.progym.model.PackageDetails;
import com.progym.model.PaymentTransaction;
import com.progym.model.ReferenceVO;
import com.progym.service.UserService;
import com.progym.utils.EmailUtil;
import com.smattme.MysqlExportService;
import com.sun.mail.smtp.SMTPTransport;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Controller
public class MainController {

	@Autowired
	  UserService userService;
	
	  
		@RequestMapping(value = "/index", method = RequestMethod.GET)
	  public ModelAndView showIndexPage(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws IOException {
		User u = (User)session.getAttribute("loggedInUser");
	    ModelAndView mav = new ModelAndView("index");

		if(u == null)
			response.sendRedirect("login");
		else {
		
		CollectionDashboardPVO c = userService.getDashboardCollection();
	    mav.addObject("male",c.getMale());
	    mav.addObject("female",c.getFemale());
	    mav.addObject("steam",c.getSteam());
	    mav.addObject("total",c.getTotal());
	    mav.addObject("maletotal",c.getMaletotal());
	    mav.addObject("femaletotal",c.getFemaletotal());
	    mav.addObject("clienttotal",c.getClienttotal());
	    mav.addObject("username",u.getName());	
	    mav.addObject("maleFullPaid",c.getMaleFullPaid());
	    mav.addObject("malePartialPaid",c.getMalePartialPaid());
	    mav.addObject("maleNotPaid",c.getMaleNotPaid());
	    mav.addObject("femaleFullPaid",c.getFemaleFullPaid());
	    mav.addObject("femalePartialPaid",c.getFemalePartialPaid());
	    mav.addObject("femaleNotPaid",c.getFemaleNotPaid());
	    mav.addObject("emailInvoiceFlag", getEmailFlag());
	    }
	    return mav;

	  }
	
	  @RequestMapping(value = "/maleMembers", method = RequestMethod.GET)
	  public ModelAndView showMaleMembers(HttpServletRequest request, HttpServletResponse response) {		  
	    ModelAndView mav = new ModelAndView("display-members");
	    mav.clear();
	    //mav.addObject("membersList",userService.getMembersBy("male"));
	    mav.setViewName("display-members");
	    return mav;
	  }
	  
	  @RequestMapping(value = "/femaleMembers", method = RequestMethod.GET)
	  public ModelAndView showFemaleMembers(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("display-members");
		  mav.clear();
		  //mav.addObject("membersList",userService.getMembersBy("female"));
		  mav.setViewName("display-members");
	    return mav;
	  }
	  
	  @RequestMapping(value = "/allMembers", method = RequestMethod.GET)
	  public ModelAndView showAllMembers(HttpServletRequest request, 
			  HttpServletResponse response,@RequestParam String gender , @RequestParam String zone) {
		  ModelAndView mav = new ModelAndView("display-allMembers");
		  mav.addObject("membersList",userService.getMembersBy(gender,zone));		  
	    return mav;
	  }
	  
	  @RequestMapping(value = "/addMember", method = RequestMethod.GET)
	  public ModelAndView addMemberFormDisplay(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("add-member");
		  mav.addObject("addmemberobject",new AddMemberObject());
	    return mav;
	  }
	  
	  @RequestMapping(value = "/addMember", method = RequestMethod.POST)
	  public void addMemberFromForm(HttpSession session, HttpServletRequest request, HttpServletResponse response,@ModelAttribute("addmemberobject") AddMemberObject addMemberObject) throws IOException {
		  User user = (User)session.getAttribute("loggedInUser");
		  if(user != null){
		  userService.addMemberToDatabase(addMemberObject,user,"new");
		  response.sendRedirect("allMembers?gender=all&zone=none");
		  }
		  else
			  response.sendRedirect("login");
			  
	  }
	  
	  @ModelAttribute("bloodGroupsList")
	   public Map<String, String> getBloodGroupsList() {
		  Map<String,String> bloodGroupsList = new LinkedHashMap<String,String>();
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
	  
	  @ModelAttribute("discountPercentageList")
	   public Map<String, String> getDiscountPercentageList() {
		  Map<String,String> discountPercentageList = new LinkedHashMap<String,String>();
		  discountPercentageList.put("0", "0");
		  discountPercentageList.put("100", "100");
		  discountPercentageList.put("200", "200");
		  discountPercentageList.put("300", "300");
		  discountPercentageList.put("400", "400");
		  discountPercentageList.put("500", "500");
		  discountPercentageList.put("600", "600");
		  discountPercentageList.put("700", "700");
		  discountPercentageList.put("800", "800");
		  discountPercentageList.put("900", "900");
		  discountPercentageList.put("1000", "1000");
		  return discountPercentageList;		  
	  }
	  
	  @ModelAttribute("packagePeriodsList")
	   public Map<String, String> getPackagePeriodsList() {
		  Map<String,String> packagePeriodsList = new LinkedHashMap<String,String>();
		  packagePeriodsList.put("30", "1 Month");
		  packagePeriodsList.put("90", "3 Month");
		  packagePeriodsList.put("180", "6 Month");
		  packagePeriodsList.put("365", "1 Year");
		  return packagePeriodsList;		  
	  }
	  
	  
	  
	  @ModelAttribute("referencesList")
	   public Map<String, String> getReferencesList() {
		  Map<String, String> referencesList = new HashMap<String, String>();
		  referencesList.put("none", "None");
	      for(ReferenceVO ref : userService.getReferenceList()) {
	    	  referencesList.put(String.valueOf(ref.getClientId()),ref.getName());  
	      }	      
	      return referencesList;	  
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
	    return fillCollectionDashboardData(mav);
	  }
	  
	  private ModelAndView fillCollectionDashboardData(ModelAndView mav) {
		  CollectionDashboardPVO c = userService.getDashboardCollection();
		  mav.addObject("male",c.getMale());
		    mav.addObject("female",c.getFemale());
		    mav.addObject("steam",c.getSteam());
		    mav.addObject("total",c.getTotal());		    
		    mav.addObject("currentMale",c.getCurrentMonthMaleCollection());
		    mav.addObject("currentFemale",c.getCurrentMonthFemaleCollection());
		    mav.addObject("currentSteam",c.getCurrentMonthSteamCollection());
		    mav.addObject("currentTotal",c.getCurrentMonthTotalCollection());	    
		    mav.addObject("lastMale",c.getLastMonthMaleCollection());
		    mav.addObject("lastFemale",c.getLastMonthFemaleCollection());
		    mav.addObject("lastSteam",c.getLastMonthSteamCollection());
		    mav.addObject("lastTotal",c.getLastMonthTotalCollection());		    
		    mav.addObject("currentMonthName",c.getCurrentMonth());
		    mav.addObject("lasttMonthName",c.getLastMonth());
		    return mav;
	}

	@RequestMapping(value = "/clientProfile", method = RequestMethod.GET)
	  @ResponseBody
	  public ModelAndView clientProfile(HttpSession session,@RequestParam String cliendId,@RequestParam String gender) throws InterruptedException {
		  ModelAndView mav = new ModelAndView("client-profile");		  
		  mav.addObject("clientAddPackageObject", new AddClientPackageForm());
		  Client client = userService.getClientById(Integer.parseInt(cliendId));
		  mav.addObject("clientObject", client);
		  mav.addObject("clientPackagesList", userService.getClientPackagesByClient(client));
		  mav.addObject("femaleAditionalDataList", userService.getFemaleAditionalDataListByClientId(client.getId()));
		  mav.addObject("transactionObject", new PaymentTransaction());
		  FemaleMemberAdditionalDataVO fm = new FemaleMemberAdditionalDataVO();
		  fm.setClientId(Integer.valueOf(cliendId));
		  fm.setGender(gender);
		  mav.addObject("femaleAditionalDataFormObject", fm);
		  session.setAttribute("selectedClient", client);
		  getPackagesList(gender);
		  mav.addObject("editClientProfileFormObject", client);
		  
	    return mav;
	  }	  
	  
	  @RequestMapping(value = "/addPackageForClient", method = RequestMethod.POST)
	  public void addPackageFromForm(HttpSession session, HttpServletRequest request, HttpServletResponse response,@ModelAttribute("clientAddPackageObject") AddClientPackageForm addClientPackageForm) throws IOException {
		  User user = (User)session.getAttribute("loggedInUser");
		  if(addClientPackageForm.getDiscountPercentage().equalsIgnoreCase("NONE"))
			  addClientPackageForm.setDiscountPercentage("0");
		  userService.addPackageForClientToDatabase(addClientPackageForm , user);
		  String uri = "clientProfile?cliendId="+addClientPackageForm.getClientId()+"&gender="+addClientPackageForm.getGender()+"";
		  response.sendRedirect(uri);
		  //response.sendRedirect("allMembers");
	  }
	  
	  @RequestMapping(value = "/addPackage", method = RequestMethod.GET)
	  public ModelAndView addPackageFormDisplay(HttpServletRequest request, HttpServletResponse response) {
		  ModelAndView mav = new ModelAndView("add-package");
		  mav.addObject("addPackageObject",new AddPackageObject());
	    return mav;
	  }
	  
	  @RequestMapping(value = "/addPackage", method = RequestMethod.POST)
	  public void addPackageFromFormToDb(HttpSession session, HttpServletRequest request, HttpServletResponse response,@ModelAttribute("addPackageObject") AddPackageObject addPackageObject) throws IOException {
		  User user = (User)session.getAttribute("loggedInUser");
		  userService.addPackageToDatabase(addPackageObject , user);
		  response.sendRedirect("allMembers?gender=all&zone=none");
	  }
	  
	  @RequestMapping(value = "/addTransaction", method = RequestMethod.POST)
	  public void addTransactionFromForm(HttpSession session,HttpServletRequest request, HttpServletResponse response,@ModelAttribute("transactionObject") PaymentTransaction paymentTransaction) throws IOException {
		  Boolean isAuthorized = Boolean.FALSE;
		  User u = (User)session.getAttribute("loggedInUser");
		  if(u != null && u.getAuthorizedToApprovePayment().equals("YES"))
			  isAuthorized = Boolean.TRUE;
		  userService.createTransaction(paymentTransaction,isAuthorized , u);
		  response.sendRedirect("allMembers?gender=all&zone=none");
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
		  return fillCollectionDashboardData(mav);		  
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
		  return fillCollectionDashboardData(mav);		  
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
		  return fillCollectionDashboardData(mav);		  
	  }
	  
	  
	  @RequestMapping(value = "/approveTransaction", method = RequestMethod.GET)
	  @ResponseBody
	  public void approveTransaction(HttpServletRequest request, HttpServletResponse response,@RequestParam String txnId,@RequestParam String cID,@RequestParam String gender) throws IOException {
		  userService.approveTransaction(txnId);
		  String uri = "clientProfile?cliendId="+cID+"&gender="+gender+"";
		  response.sendRedirect(uri);
		  }
	  
	  @RequestMapping(value = "/updateClientAssignedPackage", method = RequestMethod.POST)
	  @ResponseBody
	  public void updateClientAssignedPackage(HttpSession session,HttpServletRequest request, HttpServletResponse response,
			  @RequestParam String u_pkgId,@RequestParam String u_startdate,@RequestParam String u_enddate,@RequestParam String u_fees,
			  @RequestParam String u_clientid,@RequestParam String u_gender) throws IOException {
		  User user = (User)session.getAttribute("loggedInUser");
		  userService.updateClintAssignedPackage(u_pkgId,u_startdate,u_enddate,u_fees ,user);
		  String uri = "clientProfile?cliendId="+u_clientid+"&gender="+u_gender+"";
		  response.sendRedirect(uri);
		  
	  }
	  
	  @RequestMapping(value = "/deleteClientAssignedPackage", method = RequestMethod.GET)
	  @ResponseBody
	  public void deleteClientAssignedPackage(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			  @RequestParam String u_pkgId,@RequestParam String u_clientid,@RequestParam String u_gender) throws IOException {
		  User user = (User)session.getAttribute("loggedInUser");
		  userService.deleteClintAssignedPackage(u_pkgId , user);
		  String uri = "clientProfile?cliendId="+u_clientid+"&gender="+u_gender+"";
		  response.sendRedirect(uri);		  
	  }
	  
	  @RequestMapping(value = "/backupDatabase", method = RequestMethod.GET)
	  public ModelAndView backupDatabase(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, SQLException {		  
	    ModelAndView mav = new ModelAndView("index");
	      
	    try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("cmd.exe /c start C:\\ab.bat");
            System.exit(0);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
	    
	    return mav;
	  }
	  
	  @RequestMapping(value = "/notifications", method = RequestMethod.GET)
	  public ModelAndView notifications(HttpSession session, HttpServletRequest request, HttpServletResponse response){
		  User user = (User)session.getAttribute("loggedInUser");
	    ModelAndView mav = new ModelAndView("notifications");
		  mav.addObject("notificationsList", userService.getNotifications(user) );		  
	    return mav;
	  }
	  
	  @RequestMapping(value = "/discardNotification", method = RequestMethod.GET)
	  @ResponseBody
	  public void discardNotification(HttpSession session, HttpServletRequest request, HttpServletResponse response,
			  @RequestParam String notiId) throws IOException {
		  User user = (User)session.getAttribute("loggedInUser");
		  userService.discardNotification(notiId);
		  response.sendRedirect("notifications");
		  
	  }
	  
	  @RequestMapping(value = "/fileUploadPage", method = RequestMethod.GET)
	   public ModelAndView fileUploadPage() {
	      FileModel file = new FileModel();
	      ModelAndView modelAndView = new ModelAndView("uploadPhotoForm", "fileUpload", file);
	      return modelAndView;
	   }

	   @RequestMapping(value="/fileUploadPage", method = RequestMethod.POST)
	   public ModelAndView fileUpload(HttpSession session,@Validated FileModel file, BindingResult result, ModelMap model) throws IOException {
		   ModelAndView mav = new ModelAndView("index");
		   if (result.hasErrors()) {
	         mav.setViewName("fileUploadPage");
	         return mav;
	      } else {            
	         MultipartFile multipartFile = file.getFile();
	         String uploadPath = session.getServletContext().getRealPath("/img/");

	         //Now do something with file...
	         FileCopyUtils.copy(file.getFile().getBytes(), new File(uploadPath+file.getReference()+".jpg"));
	         String fileName = multipartFile.getOriginalFilename();
	         model.addAttribute("fileName", fileName);
	         //sendEmail();
	         //EmailUtil.sendEmail2();
	         mav.addObject("emailInvoiceFlag", getEmailFlag());
	         return mav;
	      }
	   }

	   @RequestMapping(value = "/submitFemaleAditionalDataForm", method = RequestMethod.POST)
		  public void submitFemaleAditionalDataForm(HttpSession session,HttpServletRequest request, HttpServletResponse response,@ModelAttribute("femaleAditionalDataFormObject") FemaleMemberAdditionalDataVO femaleMemberAdditionalDataVO) throws IOException {
			  Boolean isAuthorized = Boolean.FALSE;
			  User u = (User)session.getAttribute("loggedInUser");
			  if(u != null && u.getAuthorizedToApprovePayment().equals("YES"))
				  isAuthorized = Boolean.TRUE;
			  userService.submitFemaleAditionalDataForm(femaleMemberAdditionalDataVO , u);
			  String uri = "clientProfile?cliendId="+femaleMemberAdditionalDataVO.getClientId()+"&gender="+femaleMemberAdditionalDataVO.getGender()+"";
			  response.sendRedirect(uri);
		  }
	   
	   
	   @RequestMapping(value = "/editClientProfile", method = RequestMethod.POST)
		  public void editClientProfile(HttpSession session,HttpServletRequest request, HttpServletResponse response,@ModelAttribute("editClientProfileFormObject") Client client) throws IOException {
		   User u = (User)session.getAttribute("loggedInUser");  
		   userService.updateMemberToDatabase(client , u);
			  String uri = "clientProfile?cliendId="+client.getId()+"&gender="+client.getGender()+"";
			  response.sendRedirect(uri);
		}
	   
	   @RequestMapping(value = "/deleteClientProfile", method = RequestMethod.GET)
		  @ResponseBody
		  public void deleteClientProfile(HttpSession session, HttpServletRequest request, HttpServletResponse response,
				  @RequestParam String clientid) throws IOException {
			  User user = (User)session.getAttribute("loggedInUser");
			  userService.deleteClientProfile(clientid , user);
			  response.sendRedirect("allMembers?gender=all&zone=none");	  
		}
	   
	   @RequestMapping(value = "/deletePackage", method = RequestMethod.GET)
		  @ResponseBody
		  public void deletePackage(HttpSession session, HttpServletRequest request, HttpServletResponse response,
				  @RequestParam String pkgid) throws IOException {
			  User user = (User)session.getAttribute("loggedInUser");
			  userService.deletePackage(pkgid , user);
			  response.sendRedirect("index");	  
		}
	   
	   @RequestMapping(value = "/deleteFemaleClientAdditionalDetails", method = RequestMethod.GET)
		  public void deleteFemaleClientAdditionalDetails(HttpSession session,HttpServletRequest request, HttpServletResponse response,
				  @RequestParam String id,@RequestParam String gender,@RequestParam String clientid) throws IOException {
		   User u = (User)session.getAttribute("loggedInUser");  
		   userService.deleteFemaleClientAdditionalDetails(id , u);
			  String uri = "clientProfile?cliendId="+clientid+"&gender="+gender+"";
			  response.sendRedirect(uri);
		}
	   
	   @RequestMapping(value = "/searchMember", method = RequestMethod.GET)
		  public ModelAndView searchMember(HttpServletRequest request, 
				  HttpServletResponse response,@RequestParam String searchCriteria) {
			  ModelAndView mav = new ModelAndView("display-allMembers");
			  mav.addObject("membersList",userService.getMembersBySearchCriteria(searchCriteria));		  
		    return mav;
		  }
	   
	   @RequestMapping(value = "/mobilenotifications", method = RequestMethod.GET)
		  public ModelAndView mobilenotifications(HttpServletRequest request, HttpServletResponse response){		  
		    ModelAndView mav = new ModelAndView("notifications");
			  mav.addObject("notificationsList", userService.getMobileNotifications() );		  
		    return mav;
		  }
	   @RequestMapping(value = "/sendPendingInvoices", method = RequestMethod.GET)
		  public ModelAndView sendPendingInvoices(HttpServletRequest request, HttpServletResponse response){		  
		    ModelAndView mav = new ModelAndView("index");
		    mav.addObject("emailInvoiceFlag", getEmailFlag());
		    userService.sendPendingInvoices();
		    return mav;
		  }

	   @RequestMapping(value = "/sendInvoice", method = RequestMethod.GET)
		  @ResponseBody
		  public void sendInvoice(HttpServletRequest request, HttpServletResponse response,@RequestParam String txnId,@RequestParam String cID,@RequestParam String email,@RequestParam String gender) throws IOException {
			  userService.sendInvoice(txnId,email);
			  String uri = "clientProfile?cliendId="+cID+"&gender="+gender+"";
			  response.sendRedirect(uri);
			  }
	   
	   @RequestMapping(value = "/toggleInvoiceFlag", method = RequestMethod.GET)
		  @ResponseBody
		  public ModelAndView toggleInvoiceFlag(HttpServletRequest request, HttpServletResponse response,@RequestParam String flag) throws IOException {
		   userService.updateToggleInvoiceFlag(flag);
		   ModelAndView mav = new ModelAndView("index");
			if(flag.equalsIgnoreCase("true"))
			  mav.addObject("emailInvoiceFlag", "ON");
			else if(flag.equalsIgnoreCase("false"))
				  mav.addObject("emailInvoiceFlag", "OFF");
		    return mav;
		}
	   
	   public String getEmailFlag(){
		   return userService.getToggleInvoiceFlag();
	   }
	   
	   	   
	   
}
