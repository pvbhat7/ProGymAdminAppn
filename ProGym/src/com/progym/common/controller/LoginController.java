package com.progym.common.controller;

import com.progym.api.Identity;
import com.progym.common.constants.ProjectConstants;
import com.progym.common.fcm.FCM;
import com.progym.common.model.CollectionDashboardPVO;
import com.progym.common.model.User;
import com.progym.common.model.isMobileExists;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


@Controller
public class LoginController {
    //kkk
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLogin(HttpSession session,HttpServletRequest request, HttpServletResponse response) throws IOException {
        FCM.initialize();
        ModelAndView mav = new ModelAndView("loginform");

        if(isConnected()){
            if(!userService.getMacActivationStatus())
                mav = new ModelAndView("handleLicenceValidation");
            else{
                userService.register();
                userService.updateModuleDataFromServer();
                mav = new ModelAndView("loginform");
                mav.addObject("user", new User());
                mav.addObject("imageObject", userService.getImageObjectByBrand("progym"));
                return mav;
            }
        }
        else{
            userService.register();
            userService.updateModuleDataFromServer();
            mav = new ModelAndView("loginform");
            mav.addObject("user", new User());
            mav.addObject("imageObject", userService.getImageObjectByBrand("progym"));
            return mav;
        }

        return mav;
    }

    @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
    public ModelAndView loginProcess(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                     @ModelAttribute("login") User user) throws IOException {

        // USER
        User u = userService.validateUser(new User(user.getUsername(), user.getPassword(), null, null));
        if (u != null) {

            System.out.println("user found in logincontroller");

            session.setAttribute("loggedInUser", u);
            userService.triggerEnableDisableProfileBatch();
            ModelAndView mav = new ModelAndView("index");
            CollectionDashboardPVO c = userService.getDashboardCollection();
            initCollDash(mav, c);
            mav.addObject("username", u.getName());

            //run UpdateEnableDisable profile batch
            userService.triggerEnableDisableProfileBatch();

            // run send payment reminder batch
            userService.triggerFeesPaymentReminderBatch();

            // reconcile contacts , move missing contacts from offline->online server
            userService.reconcileContacts();

            userService.syncClientUpdatedData();

            increaseDbConnectionPool();

            return mav;
        } else {
            response.sendRedirect("login");
            return null;
        }
    }

    private void initCollDash(ModelAndView mav, CollectionDashboardPVO c) {
        mav.addObject("imageObject", userService.getImageObjectByBrand("progym"));
        mav.addObject("male", c.getMale());
        mav.addObject("female", c.getFemale());
        mav.addObject("steam", c.getSteam());
        mav.addObject("total", c.getTotal());
        mav.addObject("maletotal", c.getMaletotal());
        mav.addObject("femaletotal", c.getFemaletotal());
        mav.addObject("clienttotal", c.getClienttotal());
        mav.addObject("maleFullPaid", c.getMaleFullPaid());
        mav.addObject("malePartialPaid", c.getMalePartialPaid());
        mav.addObject("maleNotPaid", c.getMaleNotPaid());
        mav.addObject("femaleFullPaid", c.getFemaleFullPaid());
        mav.addObject("femalePartialPaid", c.getFemalePartialPaid());
        mav.addObject("femaleNotPaid", c.getFemaleNotPaid());
        mav.addObject("emailInvoiceFlag", userService.isModuleEnabled(ProjectConstants.EMAIL_INVOICE_FLAG));
        mav.addObject("smsFlag", userService.isModuleEnabled(ProjectConstants.SMS_FLAG));
        mav.addObject("enableMembers", c.getEnableMembers());
        mav.addObject("disableMembers", c.getDisableMembers());
        mav.addObject("birthdayNameList", c.getBirthdayNameList());
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    public void increaseDbConnectionPool() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/prashant", "prashant", "pooja.418");
            Statement stmt = con.createStatement();
            stmt.execute("SET GLOBAL max_connections = 1000");
            con.close();
            System.out.println("Setting db connections to 1000...");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @RequestMapping(value = "/handleLicenceValidation", method = RequestMethod.GET)
    public ModelAndView handleLicenceValidation(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("handleLicenceValidation");
        return mav;
    }

    @RequestMapping(value = "/initiatePayment", method = RequestMethod.GET)
    public ModelAndView initiatePayment(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView("handleLicenceValidation");
        return mav;
    }

    @RequestMapping(value = "/activateProduct", method = RequestMethod.POST)
    public ModelAndView activateProduct(HttpSession session, HttpServletRequest request,
                                        HttpServletResponse response,@RequestParam String secret_key) {

        String mac = Identity.getMacAddress();
        isMobileExists obj = userService.initiateActivateProductOperation(mac,secret_key);
        String res = "";
        if(obj != null){
            if(obj.getResult().equalsIgnoreCase("-1"))
                res = "Invalid activation key entered";
            else if(obj.getResult().equalsIgnoreCase("0"))
                res = "Activation key already in use";
            else if(obj.getResult().equalsIgnoreCase("1"))
                res = "Activation successful";
        }
        else
            res = "Please try again..";
        ModelAndView mav = new ModelAndView("licenseServerResponse");
        mav.addObject("serverResponse",res);
        Boolean positive = false;
        Boolean negative = false;
        if(obj != null){
            if(obj.getResult().equalsIgnoreCase("1")){
                positive = true;
            }
            else
                negative = true;

        }
        else
            negative = true;
        mav.addObject("positive",positive);
        mav.addObject("negative",negative);
        return mav;
    }

    public static boolean isConnected() {
        try {
            final URL url = new URL("http://www.google.com");
            final URLConnection conn = url.openConnection();
            conn.connect();
            conn.getInputStream().close();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }




}
