package com.century.controller;

import com.century.service.BillService;
import com.century.vo.Bill;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.util.*;

@Controller
@RequestMapping("/bill")
public class BillController {
    @Resource
    private BillService billService;

    //保存用户订单到数据库中
    @RequestMapping("/bookTicket.html")
    public  ModelAndView buy(HttpServletRequest request, String airCode, String startTime, String arriveTime, String theDate, String passengerName){
        ModelAndView modelAndView = new ModelAndView();
        Cookie[] cookies = request.getCookies();
        String userName = "";
        if(cookies != null) {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userName")){
                    userName = cookie.getValue();       //获取用户名
                }
            }
        }
        int status = billService.book(airCode, startTime, arriveTime, theDate, userName, passengerName);
        List<Bill> bills = billService.getBillsList(userName);
        modelAndView.addObject("billList", bills);
        modelAndView.addObject("status", status);
        modelAndView.setViewName("../../jsp/ticketbill");
        return modelAndView;
    }


    @RequestMapping("/refund.html")
    public ModelAndView refund(HttpServletRequest request, String airCode, String billDate, String ticketDate, String passengerName){
        ModelAndView modelAndView = new ModelAndView();
        String userName = "";
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userName")){
                    userName = cookie.getValue();
                }
            }
        }
        int status = billService.refund(airCode, billDate, ticketDate, userName, passengerName);
        List<Bill> bills = billService.getBillsList(userName);
        modelAndView.addObject("billList", bills);
        modelAndView.addObject("status", status);
        modelAndView.setViewName("../../jsp/ticketbill");
        return modelAndView;
    }


    @RequestMapping("/list.html")
    public ModelAndView list(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        String userName = "";
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userName")){
                    userName = cookie.getValue();
                }
            }
        }
        List<Bill> bills = billService.getBillsList(userName);
        modelAndView.addObject("billList", bills);
        modelAndView.setViewName("../../jsp/ticketbill");
        return modelAndView;
    }

}
