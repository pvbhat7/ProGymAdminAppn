package com.progym.common.controller;

import com.progym.common.constants.ProjectConstants;
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
public class ModuleHandlerController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/updateModuleState", method = RequestMethod.GET)
    @ResponseBody
    public void updateModuleState(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam String key, @RequestParam String value) throws IOException {
        userService.updateModuleState(key , value);
        response.sendRedirect("index");
    }

}
