package com.progym.common.controller;

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
import java.io.IOException;

@Controller
public class NotificationController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/notifications", method = RequestMethod.GET)
    public ModelAndView notifications(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) session.getAttribute("loggedInUser");
        ModelAndView mav = new ModelAndView("notifications");
        mav.addObject("notificationsList", userService.getNotifications(user));
        return mav;
    }

    @RequestMapping(value = "/getSmsLogs", method = RequestMethod.GET)
    public ModelAndView getSmsLogs(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        User user = (User) session.getAttribute("loggedInUser");
        ModelAndView mav = new ModelAndView("smsLogs");
        mav.addObject("smsLogsList", userService.getSmsLogs());
        return mav;
    }

    @RequestMapping(value = "/discardNotification", method = RequestMethod.GET)
    @ResponseBody
    public void discardNotification(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam String notiId) throws IOException {
        User user = (User) session.getAttribute("loggedInUser");
        userService.discardNotification(notiId);
        response.sendRedirect("notifications");

    }

    @RequestMapping(value = "/mobilenotifications", method = RequestMethod.GET)
    public ModelAndView mobilenotifications(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("notifications");
        mav.addObject("notificationsList", userService.getMobileNotifications());
        return mav;
    }
}
