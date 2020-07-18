package com.century.controller;

import com.century.service.HotelBillService;
import com.century.vo.HotelBill;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hotel/bill")
public class HotelBillController {
    @Resource
    private HotelBillService hotelBillService;


    @RequestMapping("/book.html")
    public ModelAndView bookHotel(String hotelId, String checkInDate, String checkOutDate, String passengerName){
        System.out.println("hotelId = " + hotelId + checkInDate + checkOutDate + passengerName);
        RedirectView redirectView = new RedirectView("/hotel/bill/list.html", true, true);
        ModelAndView modelAndView = new ModelAndView(redirectView);
        int status = hotelBillService.addHotelBill(hotelId, checkInDate, checkOutDate, passengerName);
        modelAndView.addObject("status", status);
        return modelAndView;
    }

    @RequestMapping("/list.html")
    public ModelAndView hotelList(HttpServletRequest request, @RequestParam(required = false) String pageIndex){
        int pageSize = 10;
        if(pageIndex == null){
            pageIndex = "1";
        }
        String userName = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userName")){
                    userName = cookie.getValue();
                }
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        List<HotelBill> hotelBillList = hotelBillService.getHotelBills(userName);
        int pageNum = (int)Math.ceil(hotelBillList.size() / 1.0 / pageSize);
        List<HotelBill> hotelBillListResult = new ArrayList<HotelBill>();
        int max = hotelBillList.size() > Integer.parseInt(pageIndex) * pageSize ? Integer.parseInt(pageIndex) * pageSize : hotelBillList.size();
        for(int i = (Integer.parseInt(pageIndex) - 1) * pageSize; i < max; i++){
            hotelBillListResult.add(hotelBillList.get(i));
        }
        modelAndView.addObject("hotelBillList", hotelBillListResult);
        modelAndView.addObject("pageIndex", pageIndex);
        modelAndView.addObject("pageNum", pageNum);
        modelAndView.setViewName("../../jsp/hotelbill");
        return modelAndView;
    }

    @RequestMapping("/refund.html")
    public ModelAndView refund(HttpServletRequest request, String hotelBillId){
        ModelAndView modelAndView = new ModelAndView();
        String userName = null;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userName")){
                    userName = cookie.getValue();
                }
            }
        }
        int status = hotelBillService.refund(hotelBillId);
        List<HotelBill> ticketBills = hotelBillService.getHotelBills(userName);
        modelAndView.addObject("hotelBillList", ticketBills);
        modelAndView.addObject("status", status);
        modelAndView.setViewName("../../jsp/hotelbill");
        return modelAndView;
    }

    @RequestMapping("/sys/listAll.html")
    public ModelAndView listAll(String pageIndex){
        int pageSize = 10;
        if(pageIndex == null){
            pageIndex = "1";
        }
        ModelAndView modelAndView = new ModelAndView();
        List<HotelBill> hotelBillList = hotelBillService.getAllHotelBills();
        List<HotelBill> hotelBillListResult = new ArrayList<HotelBill>();
        int pageNum = (int)Math.ceil(hotelBillList.size() / 1.0 / pageSize);
        int max = hotelBillList.size() > Integer.parseInt(pageIndex) * pageSize ? Integer.parseInt(pageIndex) * pageSize : hotelBillList.size();
        for(int i = (Integer.parseInt(pageIndex) - 1) * pageSize; i < max; i++){
            hotelBillListResult.add(hotelBillList.get(i));
        }
        modelAndView.addObject("hotelBillList", hotelBillListResult);
        modelAndView.addObject("pageIndex", pageIndex);
        modelAndView.addObject("pageNum", pageNum);
        modelAndView.setViewName("../../jsp/admin/hotelbilllist");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/sys/update")
    public boolean update(@RequestBody Map<String, Object> mapParam){
        int status = hotelBillService.updateHotelBill(mapParam);
        if(status > 0){
            return true;
        }
        return false;
    }

    @ResponseBody
    @RequestMapping("/sys/delete")
    public boolean delete(@RequestBody Map<String, Object> mapParam){
        int status = hotelBillService.removeHotelBill(mapParam);
        if(status > 0){
            return true;
        }
        return false;
    }
}
