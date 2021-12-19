package com.progym.common.controller;

import com.progym.common.model.ConnectionStatusObject;
import com.progym.common.model.SmsLogs;
import com.progym.common.model.User;
import com.progym.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SmsController {

    @Autowired
    UserService userService;

    @RequestMapping(
            value = {"/getAllSms"},
            method = {RequestMethod.GET},
            produces = {"application/json"}
    )
    @ResponseBody
    public List<SmsLogs> getAllSms(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam String gender) throws Exception {
        return this.userService.getSmsByFilter(gender);
    }

    @ResponseBody
    @RequestMapping(
            value = {"/updateSmsDeliveryStatus"},
            method = {RequestMethod.GET}

    )
    public ConnectionStatusObject updateSmsDeliveryStatus(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam String id) throws Exception {
        this.userService.updateSmsDeliveryStatus(id);
        return new ConnectionStatusObject("CONNECTED");
    }

    @RequestMapping(
            value = {"/updateSmsDeliveryStatusArray"},
            method = {RequestMethod.GET}
    )
    public void updateSmsDeliveryStatusArray(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam String id) throws Exception {
        this.userService.updateSmsDeliveryStatus(id);
    }

    @RequestMapping(value = "/setttingPage", method = RequestMethod.GET)
    public ModelAndView setttingPage(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) session.getAttribute("loggedInUser");
        ModelAndView mav = new ModelAndView("setttingPage");
        return mav;
    }

}
