package com.progym.common.controller;

import com.google.gson.Gson;
import com.progym.api.DateApi;
import com.progym.api.ProjectContext;
import com.progym.api.ServerApi;
import com.progym.common.constants.ProjectConstants;
import com.progym.common.model.*;
import com.progym.common.service.UserService;
import com.progym.diet.DietService;
import com.progym.tavros.ServerCom;
import com.progym.workout.WorkoutService;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ClientProfileController {

    private static final String IMAGE_DIRECTORY = "/D:/imgdata/";


    @Autowired
    UserService userService;

    @Autowired
    WorkoutService workoutService;

    @Autowired
    DietService dietService;

    @ModelAttribute("daysList")
    public Map<String, String> getDaysList() {
        Map<String, String> daysList = new LinkedHashMap<String, String>();
        int c = 1;
        for (int i = 0; i < 31; i++) {
            daysList.put(String.valueOf(c), String.valueOf(c));
            c++;
        }
        return daysList;
    }

    @ModelAttribute("yearsList")
    public Map<String, String> getYearsList() {
        Map<String, String> yearsList = new LinkedHashMap<String, String>();
        yearsList.put("2019", "2019");
        yearsList.put("2020", "2020");
        yearsList.put("2021", "2021");
        return yearsList;
    }

    @ModelAttribute("paymentModes")
    public Map<String, String> getPaymentModes() {
        Map<String, String> paymentModes = new LinkedHashMap<String, String>();
        paymentModes.put("cash", "cash");
        paymentModes.put("online", "online");
        return paymentModes;
    }

    @ModelAttribute("packagesList")
    public Map<String, String> getPackagesList(String gender) {
        Map<String, String> packagesList = new HashMap<String, String>();
        if (gender != null) {
            for (CPackage c : userService.getPackagesByFilter(gender)) {
                packagesList.put(String.valueOf(c.getId()), c.getPackageName());
            }
        }
        return packagesList;
    }

    @ModelAttribute("monthsList")
    public Map<String, String> getMonthsList() {
        Map<String, String> monthsList = new LinkedHashMap<String, String>();
        monthsList.put("January", "January");
        monthsList.put("February", "February");
        monthsList.put("March", "March");
        monthsList.put("April", "April");
        monthsList.put("May", "May");
        monthsList.put("June", "June");
        monthsList.put("July", "July");
        monthsList.put("August", "August");
        monthsList.put("September", "September");
        monthsList.put("October", "October");
        monthsList.put("November", "November");
        monthsList.put("December", "December");
        return monthsList;
    }

    @ModelAttribute("discountPercentageList")
    public Map<String, String> getDiscountPercentageList() {
        Map<String, String> discountPercentageList = new LinkedHashMap<String, String>();
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

    @RequestMapping(value = "/clientProfile", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView clientProfile(HttpSession session, @RequestParam String cliendId, @RequestParam String gender) throws InterruptedException {
        User user = (User) session.getAttribute("loggedInUser");
        ModelAndView mav = new ModelAndView("client-profile");
        Client client = userService.getClientById(Integer.parseInt(cliendId));

        mav.addObject("clientObject", client);
        mav.addObject("refferalName", userService.getReferralName(cliendId));

        mav.addObject("clientAddPackageObject", new AddClientPackageForm());
        mav.addObject("clientPackagesList", userService.getClientPackagesByClient(client));


        mav.addObject("transactionObject", new PaymentTransaction());

        session.setAttribute("selectedClient", client);
        getPackagesList(gender);
        session.setAttribute("currentSelectedClient", client.getId());
        session.setAttribute("currentSelectedClientName", client.getName());
        mav.addObject("editClientProfileFormObject", client);

        if (session.getAttribute("maskMobile") != null && session.getAttribute("validatePasswordFlow") != null) {
            session.setAttribute("maskMobile", true);
            session.setAttribute("validatePasswordFlow", null);
        } else
            session.setAttribute("maskMobile", null);


        if(userService.isModuleEnabled(ProjectConstants.MONTHLY_DATA_FLAG)){
            mav.addObject("femaleAditionalDataList", userService.getFemaleAditionalDataListByClientId(client.getId()));
            mav.addObject("femaleAditionalDataFormObject", new FemaleMemberAdditionalDataVO(client.getId() , gender));
            mav.addObject("isEnabledMonthlyDataModule", userService.isModuleEnabled(ProjectConstants.MONTHLY_DATA_FLAG) ? true : false);
        }

        // DIET MODULE
        if(userService.isModuleEnabled(ProjectConstants.DIET_FLAG)){
            addDietModule(mav , session , client);
            mav.addObject("isEnabledDietModule", userService.isModuleEnabled(ProjectConstants.DIET_FLAG) ? true : false);
        }

        // WORKOUT MODULE
        if(userService.isModuleEnabled(ProjectConstants.WORKOUT_FLAG)){
            addWorkoutModule(mav , session , client);
            mav.addObject("isEnabledWorkoutModule", userService.isModuleEnabled(ProjectConstants.WORKOUT_FLAG) ? true : false);
        }




        return mav;
    }

    private void addWorkoutModule(ModelAndView mav, HttpSession session, Client client) {

        mav.addObject("workoutMainTypeList",workoutService.getWorkoutMainTypeList());
        mav.addObject("workoutSubTypeList",workoutService.getWorkoutSubTypeList());

        ServerCom.sendGetRequestToServer(ServerApi.GET_BY_CID_AND_DATE + "clientExtCode=" + client.getId() + "&date=" + DateApi.getDdMmYyyyDate("") + "&day=" + DateApi.getDayName());

        mav.addObject("activeWorkoutPlansList", workoutService.getActiveWorkoutPlansList().stream().filter(e ->
                (
                        e.getName().equalsIgnoreCase("Single Muscle - 1") ||
                                e.getName().equalsIgnoreCase("Single Muscle - 2") ||
                                e.getName().equalsIgnoreCase("Single Muscle - 3") ||
                                e.getName().equalsIgnoreCase("Double Muscle - 1") ||
                                e.getName().equalsIgnoreCase("Double Muscle - 2") ||
                                e.getName().equalsIgnoreCase("Double Muscle - 3") ||
                                e.getName().equalsIgnoreCase("Mixed Workout") ||
                                e.getName().equalsIgnoreCase("Ladies Level - 1") ||
                                e.getName().equalsIgnoreCase("Ladies Level - 2") ||
                                e.getName().equalsIgnoreCase("Ladies Level - 3")
                )
        ).collect(Collectors.toList()));
        if (client.getAwp() != null && !client.getAwp().equalsIgnoreCase("")) {
            Optional<T_workoutMainType> o = ((Optional<T_workoutMainType>) workoutService.getActiveWorkoutPlansList().stream().filter(e -> e.getId() == Integer.parseInt(client.getAwp())).findFirst());
            mav.addObject("defaultWorkoutPlanName", o.isPresent() ? o.get().getName() : "No Plan Found");
        } else
            mav.addObject("defaultWorkoutPlanName", "No Plan Found");

        mav.addObject("clientWorkoutList", workoutService.getWorkoutListByClientId(String.valueOf(client.getId())));
    }

    private void addDietModule(ModelAndView mav, HttpSession session, Client client) {
        mav.addObject("oldDietPlanTemplatesList", dietService.getClientPreviousDietPlanTemplates(client));
        mav.addObject("dietTimeSlotObject", dietService.getDietTimeSlotObjectById(1));
        mav.addObject("defaultDietPlanTemplatesList", dietService.getDefaultDietPlanTemplatesList());

        String var1 = ProjectConstants.VIEW_ACTIVE_DIET_PLAN_BY_CLIENT_ID;
        String var2 = ProjectConstants.VIEW_OLD_DIET_PLAN_TEMPLATE ;
        String var3 = ProjectConstants.VIEW_DIET_PLAN_OBJECT_DETAILS;

        if (var1 != null || var2 != null) {
            if (var1 != null) {
                mav.addObject("selectedDietObjectTemplate", dietService.getActiveDietPlanTemplate(String.valueOf(client.getAdp())));
                if (client.getAdp() != null)
                    mav.addObject("DietPlanObjectDetailsList", dietService.getDietPlanObjectDetailsList(client.getAdp() , String.valueOf(client.getId())));
                ProjectConstants.VIEW_OLD_DIET_PLAN_TEMPLATE = null;
                ProjectConstants.VIEW_ACTIVE_DIET_PLAN_BY_CLIENT_ID = null;
            } else if (var2 != null) {
                DietPlanTemplate dietPlanTemplate = dietService.getDietPlanTemplateById(var2);
                mav.addObject("selectedDietObjectTemplate", dietPlanTemplate);
                mav.addObject("DietPlanObjectDetailsList", dietService.getDietPlanObjectDetailsList(String.valueOf(dietPlanTemplate.getId()) , String.valueOf(client.getId())));
                ProjectConstants.VIEW_OLD_DIET_PLAN_TEMPLATE = null;
                ProjectConstants.VIEW_ACTIVE_DIET_PLAN_BY_CLIENT_ID = null;
            }
        } else {
            ProjectConstants.VIEW_OLD_DIET_PLAN_TEMPLATE = null;
            ProjectConstants.VIEW_ACTIVE_DIET_PLAN_BY_CLIENT_ID = null;
            mav.addObject("selectedDietObjectTemplate", null);
        }

        if (var3 != null) {
            DietPlanObject dietPlanObject = (DietPlanObject)new Gson().fromJson(ProjectConstants.VIEW_DIET_PLAN_OBJECT_DETAILS,DietPlanObject.class);
            DietPlanTemplate dietPlanTemplate = dietService.getDietPlanTemplateById(String.valueOf(dietPlanObject.getDptid()));
            dietPlanObject.setDietPlanTemplate(dietPlanTemplate);
            mav.addObject("selectedDietPlanObject",dietPlanObject );
            mav.addObject("viewDetailedDietObjectClicked", 1);
            //mav.addObject("selectedDietObjectTemplate", );
            ProjectConstants.VIEW_DIET_PLAN_OBJECT_DETAILS = null;
        } else {
            mav.addObject("selectedDietPlanObject", null);
            mav.addObject("viewDetailedDietObjectClicked", 0);
        }
    }

    @RequestMapping(value = "/addPackageForClient", method = RequestMethod.POST)
    public void addPackageFromForm(HttpSession session, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("clientAddPackageObject") AddClientPackageForm addClientPackageForm) throws IOException {
        User user = (User) session.getAttribute("loggedInUser");
        if (addClientPackageForm.getDiscountPercentage().equalsIgnoreCase("NONE"))
            addClientPackageForm.setDiscountPercentage("0");
        userService.addPackageForClientToDatabase(addClientPackageForm, user);
        String uri = "clientProfile?cliendId=" + addClientPackageForm.getClientId() + "&gender=" + addClientPackageForm.getGender() + "";
        response.sendRedirect(uri);
        //response.sendRedirect("allMembers");
    }


    @RequestMapping(value = "/addTransaction", method = RequestMethod.POST)
    public void addTransactionFromForm(HttpSession session, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("transactionObject") PaymentTransaction paymentTransaction) throws IOException {
        Boolean isAuthorized = Boolean.FALSE;
        User u = (User) session.getAttribute("loggedInUser");
        if (u != null && u.getAuthorizedToApprovePayment().equals("YES"))
            isAuthorized = Boolean.TRUE;
        userService.createTransaction(paymentTransaction, isAuthorized, u);
        String uri = "clientProfile?cliendId=" + paymentTransaction.getClientId() + "&gender=" + paymentTransaction.getClientGender() + "";
        response.sendRedirect(uri);
    }

    @RequestMapping(value = "/approveTransaction", method = RequestMethod.GET)
    @ResponseBody
    public void approveTransaction(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam String txnId, @RequestParam String cID, @RequestParam String gender) throws IOException {
        userService.approveTransaction(txnId);
        String uri = "clientProfile?cliendId=" + cID + "&gender=" + gender + "";
        response.sendRedirect(uri);
    }

    @RequestMapping(value = "/updateClientAssignedPackage", method = RequestMethod.POST)
    @ResponseBody
    public void updateClientAssignedPackage(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                            @RequestParam String u_pkgId, @RequestParam String u_startdate, @RequestParam String u_enddate, @RequestParam String u_fees,
                                            @RequestParam String u_clientid, @RequestParam String u_gender) throws IOException {
        User user = (User) session.getAttribute("loggedInUser");
        u_pkgId = u_pkgId.replaceAll("\\s+", "");
        userService.updateClintAssignedPackage(u_pkgId, u_startdate, u_enddate, u_fees, user);
        String uri = "clientProfile?cliendId=" + u_clientid + "&gender=" + u_gender + "";
        response.sendRedirect(uri);

    }

    @RequestMapping(value = "/submitFemaleAditionalDataForm", method = RequestMethod.POST)
    public void submitFemaleAditionalDataForm(HttpSession session, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("femaleAditionalDataFormObject") FemaleMemberAdditionalDataVO femaleMemberAdditionalDataVO, BindingResult result) throws IOException {
        Boolean isAuthorized = Boolean.FALSE;
        User u = (User) session.getAttribute("loggedInUser");
        if (u != null && u.getAuthorizedToApprovePayment().equals("YES"))
            isAuthorized = Boolean.TRUE;
        userService.submitFemaleAditionalDataForm(femaleMemberAdditionalDataVO, u);
        String uri = "clientProfile?cliendId=" + femaleMemberAdditionalDataVO.getClientId() + "&gender=" + femaleMemberAdditionalDataVO.getGender() + "";
        response.sendRedirect(uri);
    }


    @RequestMapping(value = "/editClientProfile", method = RequestMethod.POST)
    public void editClientProfile(HttpSession session, HttpServletRequest request, HttpServletResponse response, @ModelAttribute("editClientProfileFormObject") Client client, BindingResult result) throws IOException {
        User u = (User) session.getAttribute("loggedInUser");
        userService.updateMemberToDatabase(client, u);
        String uri = "clientProfile?cliendId=" + client.getId() + "&gender=" + client.getGender() + "";
        response.sendRedirect(uri);
    }

    @RequestMapping(value = "/deleteFemaleClientAdditionalDetails", method = RequestMethod.GET)
    public void deleteFemaleClientAdditionalDetails(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                                    @RequestParam String id, @RequestParam String gender, @RequestParam String clientid) throws IOException {
        User u = (User) session.getAttribute("loggedInUser");
        userService.deleteFemaleClientAdditionalDetails(id, u);
        String uri = "clientProfile?cliendId=" + clientid + "&gender=" + gender + "";
        response.sendRedirect(uri);
    }


    @RequestMapping(value = "/sendInvoice", method = RequestMethod.GET)
    @ResponseBody
    public void sendInvoice(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam String txnId, @RequestParam String cID, @RequestParam String email, @RequestParam String gender) throws IOException {
        userService.sendInvoice(txnId, email);
        String uri = "clientProfile?cliendId=" + cID + "&gender=" + gender + "";
        response.sendRedirect(uri);
    }

    @RequestMapping(value = "/redeemReferPoints", method = RequestMethod.GET)
    public void redeemReferPoints(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam String clientid, @RequestParam String gender) throws IOException {
        System.out.println(clientid + " " + gender);
        userService.redeemReferPoints(clientid);
        String uri = "clientProfile?cliendId=" + clientid + "&gender=" + gender + "";
        response.sendRedirect(uri);
    }

    @RequestMapping(value = "/renewPackage", method = RequestMethod.GET)
    public void renewPackage(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam String clientid, @RequestParam String gender) throws IOException {
        User user = (User) session.getAttribute("loggedInUser");
        userService.renewPackage(clientid, user);
        String uri = "clientProfile?cliendId=" + clientid + "&gender=" + gender + "";
        response.sendRedirect(uri);
    }

    @RequestMapping(value = "/saveCanvasImage", method = RequestMethod.POST)
    public void saveCanvasImage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        try {
            Integer cid = (Integer) session.getAttribute("currentSelectedClient");
            String cname = (String) session.getAttribute("currentSelectedClientName");

            StringBuffer buffer = new StringBuffer();
            Reader reader = request.getReader();
            int current;
            while ((current = reader.read()) >= 0) buffer.append((char) current);
            String data = new String(buffer);
            data = data.substring(data.indexOf(",") + 1);
            File oldFile = new File("/D:/imgdata/" + cid + cname + ".jpg");
            oldFile.delete();
            String imagePath = IMAGE_DIRECTORY + cid + cname + ".jpg";
            FileOutputStream output = new FileOutputStream(new File(imagePath));
            output.write(new BASE64Decoder().decodeBuffer(data));
            output.flush();
            output.close();

            // send image to ftp
            transferImageToFtp(imagePath, cid + cname + ".png", "clientProfilePicture");

            // update to db
            String uploadedImagePath = "http://tavrostechinfo.com/PROGYM/profilePictures/" + cid + cname + ".png";
            userService.updatePhotoInfo(cid, uploadedImagePath);
            new File(imagePath).delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/validatePassword", method = RequestMethod.POST)
    public void validatePassword(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam String clientId, @RequestParam String gender, @RequestParam String password) throws IOException {
        User user = (User) session.getAttribute("loggedInUser");
        if (user.getPassword().equalsIgnoreCase(password)) {
            session.setAttribute("maskMobile", true);
            session.setAttribute("validatePasswordFlow", true);
        }

        String uri = "clientProfile?cliendId=" + clientId + "&gender=" + gender + "";
        response.sendRedirect(uri);
    }

    @RequestMapping(value = "/deleteClientProfile", method = RequestMethod.POST)
    @ResponseBody
    public void deleteClientProfile(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam String clientId, @RequestParam String gender, @RequestParam String password) throws IOException {
        User user = (User) session.getAttribute("loggedInUser");
        if (user.getPassword().equalsIgnoreCase(password)) {
            userService.deleteClientProfile(clientId, user);
            response.sendRedirect("allMembers?gender=all&zone=none&enableDisable=enable");
        } else {
            String uri = "clientProfile?cliendId=" + clientId + "&gender=" + gender + "";
            response.sendRedirect(uri);
        }
    }

    @RequestMapping(value = "/deleteClientAssignedPackage", method = RequestMethod.POST)
    @ResponseBody
    public void deleteClientAssignedPackage(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                            @RequestParam String pkg_id, @RequestParam String clientId, @RequestParam String gender, @RequestParam String password) throws IOException {
        User user = (User) session.getAttribute("loggedInUser");
        pkg_id = pkg_id.replaceAll("\\s+", "");
        if (user.getPassword().equalsIgnoreCase(password))
            userService.deleteClintAssignedPackage(pkg_id, user);
        String uri = "clientProfile?cliendId=" + clientId + "&gender=" + gender + "";
        response.sendRedirect(uri);
    }



    public void transferImageToFtp(String filePath, String remoteFileName, String uploadType) {
        String server = "151.106.116.44";
        int port = 21;
        String user = "u636480992";
        String pass = "##Ppp7771";

        FTPClient ftpClient = new FTPClient();
        try {

            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            ftpClient.changeWorkingDirectory("PROGYM");
            if (uploadType.equalsIgnoreCase("dashboardPicture")) {
                ftpClient.changeWorkingDirectory("brand");
            } else if (uploadType.equalsIgnoreCase("clientProfilePicture")) {
                ftpClient.changeWorkingDirectory("profilePictures");
            } else if (uploadType.equalsIgnoreCase("subWorkoutGif")) {
                ftpClient.changeWorkingDirectory("subWorkoutGifs");
            } else if (uploadType.equalsIgnoreCase("supplements")) {
                ftpClient.changeWorkingDirectory("supplements");
            } else if (uploadType.equalsIgnoreCase("merchandise")) {
                ftpClient.changeWorkingDirectory("merchandise");
            }

            // APPROACH #1: uploads first file using an InputStream
            File firstLocalFile = new File(filePath);

            InputStream inputStream = new FileInputStream(firstLocalFile);

            System.out.println("Started uploading file");

            boolean done = ftpClient.storeFile(remoteFileName, inputStream);
            inputStream.close();
            if (done) {
                System.out.println("The first file is uploaded successfully.");
            }

        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }



}
