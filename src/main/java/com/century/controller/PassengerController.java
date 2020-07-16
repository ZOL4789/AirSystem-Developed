package com.century.controller;

import com.century.service.PassengerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/passenger")
public class PassengerController {
    @Resource
    private PassengerService passengerService;

    @RequestMapping("/passengeradd.html")
    public ModelAndView passengerAdd(){
        ModelAndView modelAndView = new ModelAndView();
        List<String> roles = passengerService.getRoles();
        modelAndView.addObject("roles", roles);
        modelAndView.setViewName("../../jsp/passengeradd");
        return modelAndView;
    }

    @RequestMapping("/doPassengerAdd.html")
    public ModelAndView doPassengerAdd(HttpServletRequest request, String name, String IDNum, String roleType){
        System.out.println("name = " + name + IDNum+ roleType);
        RedirectView redirectView = new RedirectView("/user/personalInfo.html", true, true);
        ModelAndView modelAndView = new ModelAndView(redirectView);
        Cookie[] cookies = request.getCookies();
        String userName = null;
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userName")){
                    userName = cookie.getValue();
                }
            }
        }
        int status = passengerService.addPassenger(userName, name, IDNum, roleType);
        modelAndView.addObject("status", status);
        return modelAndView;
    }
}
