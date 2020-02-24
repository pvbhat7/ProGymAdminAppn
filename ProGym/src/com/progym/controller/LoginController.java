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
    if(login.getUsername().equals("a") && login.getPassword().equals("a")) {
    	System.out.println("authenticated");
    mav.addObject("message", "Pranav");    
    }
    else if(login.getUsername().equals("b") && login.getPassword().equals("b")) {
    	System.out.println("authenticated");
    mav.addObject("message", "Prashant");    
    }
    else
    	mav.addObject("message", "wrong password");
    return mav;
  }
}
