package com.progym.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.progym.model.Client;
import com.progym.model.CollectionDashboardPVO;
import com.progym.model.User;
import com.progym.service.UserService;


@Controller
public class LoginController {
	
  @Autowired
  UserService userService;
  
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
	userService.register();
    ModelAndView mav = new ModelAndView("loginform");
    mav.addObject("user", new User());
    return mav;
  }
  
  @RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
  public ModelAndView loginProcess(HttpSession session,HttpServletRequest request, HttpServletResponse response,
  @ModelAttribute("login") User user) throws IOException {
	  
	  // USER
	  User u = userService.validateUser(new User(user.getUsername(),user.getPassword(),null, null));
	  if(u != null) {
		    System.out.println("user found in logincontroller");

		  session.setAttribute("loggedInUser", u);
		  
		  ModelAndView mav = new ModelAndView("index");
		    CollectionDashboardPVO c = userService.getDashboardCollection();
		    mav.addObject("male",c.getMale());
		    mav.addObject("female",c.getFemale());
		    mav.addObject("steam",c.getSteam());
		    mav.addObject("total",c.getTotal());
		    mav.addObject("maletotal",c.getMaletotal());
		    mav.addObject("femaletotal",c.getFemaletotal());
		    mav.addObject("clienttotal",c.getClienttotal());
		    mav.addObject("username",u.getName());

	    return mav;
	  }
	  else {
		  response.sendRedirect("login");
		  return null;
	  }	
  }
  @RequestMapping("/logout")
  public String logout(HttpSession session) {
      session.invalidate();
      return "redirect:/login";
  } 
}
