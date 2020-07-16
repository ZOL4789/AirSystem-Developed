package com.century.controller;

import com.century.service.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/hotel")
public class HotelController {
    @Resource
    private HotelService hotelService;

    @RequestMapping("/hoteladd.html")
    public ModelAndView hotelAdd(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("../../jsp/hoteladd");
        return modelAndView;
    }

    @RequestMapping("/toBookHotel.html")
    public ModelAndView toBookHotel(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("../../jsp/bookhotel");
        return modelAndView;
    }
}
