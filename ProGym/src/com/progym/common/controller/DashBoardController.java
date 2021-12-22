package com.progym.common.controller;

import com.progym.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class DashBoardController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/sendBdayWish", method = RequestMethod.GET)
    public void sendBdayWish(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam String name) throws IOException {
        userService.sendBdayWish(name);
        response.sendRedirect("index");
    }

    @RequestMapping(value = "/createNewEmail", method = RequestMethod.GET)
    public void createNewEmail(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam String emailSubject, String receiver, String image) throws IOException {
        userService.createNewEmail(emailSubject, receiver, image);
        response.sendRedirect("index");
    }

    @RequestMapping(value = "/createNewSms", method = RequestMethod.GET)
    public void createNewSms(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam String smsContent, String receiver) throws IOException {
        userService.createNewSms(smsContent, receiver);
        response.sendRedirect("index");
    }

}
