package com.progym.common.controller;

import com.progym.common.constants.ProjectConstants;
import com.progym.common.model.*;
import com.progym.common.service.UserService;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.sql.*;

@Controller
public class MainController {


    private static final String IMAGE_DIRECTORY = "/D:/imgdata/";

    @Autowired
    UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView showIndexPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {

        User u = (User) session.getAttribute("loggedInUser");
        ModelAndView mav = new ModelAndView("index");

        if (u == null)
            response.sendRedirect("login");
        else {
            CollectionDashboardPVO c = userService.getDashboardCollection();
            mav.addObject("imageObject", userService.getImageObjectByBrand("progym"));
            mav.addObject("male", c.getMale());
            mav.addObject("female", c.getFemale());
            mav.addObject("steam", c.getSteam());
            mav.addObject("total", c.getTotal());
            mav.addObject("maletotal", c.getMaletotal());
            mav.addObject("femaletotal", c.getFemaletotal());
            mav.addObject("clienttotal", c.getClienttotal());
            mav.addObject("username", u.getName());
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
            if (userService.isModuleEnabled(ProjectConstants.EMAIL_INVOICE_FLAG))
                mav.addObject("emailInvoiceFlag", true);
            else
                mav.addObject("emailInvoiceFlag", false);

            if (userService.isModuleEnabled(ProjectConstants.SMS_FLAG))
                mav.addObject("smsFlag", true);
            else
                mav.addObject("smsFlag", false);
        }
        return mav;

    }

    @RequestMapping(value = "/backupDatabase", method = RequestMethod.GET)
    public ModelAndView backupDatabase(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException, SQLException {
        ModelAndView mav = new ModelAndView("index");

        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("cmd.exe /c start C:\\ab.bat");
            System.exit(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return mav;
    }

    @Bean
    public TaskExecutor getTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(1);
        threadPoolTaskExecutor.setMaxPoolSize(5);
        return threadPoolTaskExecutor;
    }

    @RequestMapping(value = "/steamView", method = RequestMethod.GET)
    public ModelAndView steamView(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("steamView");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://151.106.116.1:3306/u636480992_progymkop", "u636480992_progymkop", "##Ppp7771");
            //here sonoo is database name, root is username and password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from client");
            int count = 0;
            while (rs.next()) {
                count++;
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return mav;
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


    @RequestMapping(
            value = {"/checkNwConnectionStatus"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public ConnectionStatusObject checkNwConnectionStatus(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ConnectionStatusObject("CONNECTED");
    }










    @RequestMapping(value = "/syncClientData", method = RequestMethod.GET)
    public void syncClientData(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        userService.syncClientData();
        response.sendRedirect("index");
    }

    @RequestMapping(value = "/assignWorkouts", method = RequestMethod.GET)
    public void assignWorkouts(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // create auto-diet plans for clients
    //    userService.createAutoWorkoutPlansForClients(client);
        response.sendRedirect("index");
    }

    @RequestMapping(value = "/blood_group_data", method = RequestMethod.GET)
    public ModelAndView blood_group_data(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam String bg) {
        return new ModelAndView("pdfView", "bloodGroupDetails", userService.getBloodGroupDetails(bg));
    }




}
