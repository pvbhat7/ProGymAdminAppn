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

@Controller
public class PaymentsController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/paidPayments", method = RequestMethod.GET)
    public ModelAndView showPaidPayments(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam String gender) {
        ModelAndView mav = new ModelAndView("display-payments");
        mav.addObject("paymentDataPVOList", userService.getPaymentData("fully-paid", gender));
        mav.addObject("type", "paid");
        return mav;
    }

    @RequestMapping(value = "/pendingPayments", method = RequestMethod.GET)
    public ModelAndView showPendingPayments(HttpSession session, HttpServletRequest request, HttpServletResponse response, @RequestParam String gender) {
        ModelAndView mav = new ModelAndView("display-payments");
        mav.addObject("paymentDataPVOList", userService.getPaymentData("partial-paid", gender));
        mav.addObject("type", "notpaid");
        return mav;
    }


}
