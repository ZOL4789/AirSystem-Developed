package com.century.controller;

import com.century.service.InitService;
import com.century.vo.City;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/init")
public class InitController {
    @Resource
    private InitService initService;

    @RequestMapping("/home.html")
    public ModelAndView home(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String startCity = "南昌";
        String arriveCity = "北京";
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        if(cookies != null) {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("SAD")){
                    try {
                        String sadStr = URLDecoder.decode(cookie.getValue(),"utf-8");     //中文转码，赋值
                        String[] sad = sadStr.split("&");
                        startCity = sad[0];
                        arriveCity = sad[1];
                        date = sad[2];
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        List<City> cityList = initService.getAllCities();
        modelAndView.addObject("cityList", cityList);
        modelAndView.addObject("startCity", startCity);
        modelAndView.addObject("arriveCity", arriveCity);
        modelAndView.addObject("date", date);
        modelAndView.setViewName("../../jsp/home");
        return modelAndView;
    }
}
