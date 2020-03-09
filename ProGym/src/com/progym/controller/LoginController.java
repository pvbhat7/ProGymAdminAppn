package com.progym.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.progym.model.Client;
import com.progym.model.CollectionDashboardPVO;
import com.progym.model.Login;
import com.progym.service.UserService;


@Controller
public class LoginController {
	
  @Autowired
  UserService userService;
  
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("loginform");
    mav.addObject("login", new Login());
    return mav;
  }
  
  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
  @ModelAttribute("login") Login login) {
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
    return mav;
  }
}
