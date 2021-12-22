package com.progym.common.controller;

import com.progym.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class AllMembersPageController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/maleMembers", method = RequestMethod.GET)
    public ModelAndView showMaleMembers(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("display-members");
        mav.clear();
        mav.setViewName("display-members");
        return mav;
    }

    @RequestMapping(value = "/femaleMembers", method = RequestMethod.GET)
    public ModelAndView showFemaleMembers(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("display-members");
        mav.clear();
        mav.setViewName("display-members");
        return mav;
    }

    @RequestMapping(value = "/allMembers", method = RequestMethod.GET)
    public ModelAndView showAllMembers(HttpSession session, HttpServletRequest request,
                                       HttpServletResponse response, @RequestParam String gender, @RequestParam String zone, @RequestParam String enableDisable) {
        ModelAndView mav = new ModelAndView("display-allMembers");
        mav.addObject("membersList", userService.getMembersBy(gender, zone, enableDisable));
        return mav;
    }


    @RequestMapping(value = "/searchMember", method = RequestMethod.GET)
    public ModelAndView searchMember(HttpSession session, HttpServletRequest request,
                                     HttpServletResponse response, @RequestParam String searchCriteria) {
        ModelAndView mav = new ModelAndView("display-allMembers");
        mav.addObject("membersList", userService.getMembersBySearchCriteria(searchCriteria));
        return mav;
    }

    @RequestMapping(value = "/enableDisableMember", method = RequestMethod.GET)
    public void enableDisableMember(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam String selectflag, @RequestParam String clientid) throws IOException {
        userService.updateProfileActiveFlag(clientid, selectflag);
        if (selectflag.equals("enable"))
            response.sendRedirect("allMembers?gender=all&zone=none&enableDisable=disable");
        if (selectflag.equals("disable"))
            response.sendRedirect("allMembers?gender=all&zone=none&enableDisable=enable");
    }

    @RequestMapping(value = "/sendReminder", method = RequestMethod.GET)
    public void sendReminder(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam String clientid) throws IOException {
        userService.sendFeesReminder(clientid);
        response.sendRedirect("allMembers?gender=all&zone=none&enableDisable=enable");
    }

    @RequestMapping(value = "/sendReminderToSingleClient", method = RequestMethod.GET)
    public void sendReminderToSingleClient(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                           @RequestParam String clientname, @RequestParam String clientid,
                                           @RequestParam String daysLeft, @RequestParam String packageName,
                                           @RequestParam String packageDuration, @RequestParam String pendingFees,
                                           @RequestParam String feesPaid, @RequestParam String packageTotalFees) throws IOException {
        userService.sendReminderToSingleClient(clientname, clientid, daysLeft, packageName, packageDuration, pendingFees, feesPaid, packageTotalFees);
        response.sendRedirect("allMembers?gender=all&zone=none&enableDisable=enable");
    }

}
