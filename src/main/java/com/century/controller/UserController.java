package com.century.controller;

import com.century.service.PassengerService;
import com.century.service.UserService;
import com.century.vo.Passenger;
import com.century.vo.User;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.IDN;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private PassengerService passengerService;

    @RequestMapping("/doLogin.html")
    public ModelAndView login(String user_Name, String user_Password, HttpServletResponse response, HttpSession session){
        String loginResult=userService.login(user_Name,user_Password);
        if (loginResult.equals("用户登录成功")){
            Cookie cookie = new Cookie("userName",user_Name);
            //cookie.setHttpOnly(true);
            cookie.setMaxAge(60*60*24*7);
            cookie.setPath("/");    //设置全局访问
            cookie.setSecure(false);
            response.addCookie(cookie);
            session.removeAttribute("result");
            RedirectView redirectView=new RedirectView("/init/home.html",true,true);
            ModelAndView modelAndView=new ModelAndView(redirectView);
            return modelAndView;
        }else if(loginResult.equals("管理员登录成功")){
            Cookie cookie = new Cookie("userName",user_Name);
            //cookie.setHttpOnly(true);
            cookie.setMaxAge(60*60*24*7);
            cookie.setPath("/");    //设置全局访问
            response.addCookie(cookie);
            RedirectView redirectView=new RedirectView("/hotel/hoteladd.html",true,true);
            ModelAndView modelAndView = new ModelAndView(redirectView);
            modelAndView.addObject("msg","欢迎使用AirSystem管理系统！");
            return modelAndView;
        }
        session.setAttribute("result",loginResult);
        RedirectView redirectView2=new RedirectView("/user/login.html",true,true);
        ModelAndView modelAndView=new ModelAndView(redirectView2);
        return modelAndView;
    }

    @RequestMapping("/doRegister.html")
    public ModelAndView register(String userName, String password, String phone,String email,HttpSession session){
        User user = new User();
        user.setName(userName);
        user.setPassword(password);
        user.setPhone(phone);
        user.setEmail(email);
        String rjresult=userService.register(user);
        if (rjresult.equals("用户注册成功")){
            session.setAttribute("result",rjresult);
            RedirectView redirectView=new RedirectView("/user/login.html",true,true);
            ModelAndView modelAndView=new ModelAndView(redirectView);
            return modelAndView;
        }
        session.setAttribute("result2",rjresult);
        RedirectView redirectView2=new RedirectView("/user/register.html",true,true);
        ModelAndView modelAndView=new ModelAndView(redirectView2);
        return modelAndView;
    }


    @RequestMapping("/changePwd")
    public ModelAndView changePwd(String oldPassword, String newPassword, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Cookie[] cookies = request.getCookies();
        String userName = "";
        if(cookies != null) {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userName")){
                    userName = cookie.getValue();
                }
            }
        }
        int status = userService.changePwd(userName, oldPassword, newPassword);
        modelAndView.addObject("status", status);
        modelAndView.setViewName("../../jsp/login");
        return modelAndView;
    }


    @RequestMapping("/personalInfo.html")
    public ModelAndView personalInfo(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Cookie[] cookies = request.getCookies();
        String userName = "";
        if(cookies != null) {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userName")){
                    userName = cookie.getValue();
                }
            }
        }
        User user = userService.getPersonalInfo(userName);
        List<Passenger> passengerList = passengerService.findPassenger(userName);
        modelAndView.addObject("user", user);
        modelAndView.addObject("passengerList", passengerList);
        modelAndView.setViewName("../../jsp/personalInfo");
        return modelAndView;
    }

    @RequestMapping("/logout.html")
    public String logout(HttpServletResponse response){
        Cookie cookie = new Cookie("userName", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "../../jsp/login";
    }

    @ResponseBody
    @RequestMapping("/getUserName")
    public String getUserName(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String userName = "";
        if(cookies != null) {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userName")){
                    userName = cookie.getValue();
                }
            }
        }
        return userName;
    }

    @RequestMapping("/login.html")
    public String forwardLogin(){
        return "../../jsp/login";
    }

    @RequestMapping("/register.html")
    public String forwardRegister(){
        return "../../jsp/register";
    }

    @RequestMapping("/changePwd.html")
    public String forwardChangePwd(){
        return "../../jsp/changePwd";
    }



}
