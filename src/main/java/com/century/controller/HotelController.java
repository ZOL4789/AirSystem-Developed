package com.century.controller;

import com.century.service.HotelService;
import com.century.service.InitService;
import com.century.service.PassengerService;
import com.century.vo.Hotel;
import com.century.vo.City;
import com.century.vo.Passenger;
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
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/hotel")
public class HotelController {
    @Resource
    private HotelService hotelService;
    @Resource
    private InitService initService;
    @Resource
    private PassengerService passengerService;

    @RequestMapping("/sys/add.html")
    public ModelAndView toHotelAdd(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("../../jsp/admin/hoteladd");
        return modelAndView;
    }

    @RequestMapping("/sys/doAdd.html")
    public ModelAndView hotelAdd(String name, String address, String fullAddress,String mark, String singleRoomNum, String singleRoomPrice, String doubleRoomNum, String doubleRoomPrice){
        RedirectView redirectView = new RedirectView("/hotel/sys/listAll.html", true, true);
        ModelAndView modelAndView = new ModelAndView(redirectView);
        int status = hotelService.addHotel(name, address, fullAddress, mark, singleRoomNum, singleRoomPrice, doubleRoomNum, doubleRoomPrice);
        modelAndView.addObject("status", status);
        return modelAndView;
    }

    @RequestMapping("/sys/listAll.html")
    public ModelAndView listAll(@RequestParam(required = false) String pageIndex){
        int pageSize = 10;
        if(pageIndex == null){
            pageIndex = "1";
        }
        ModelAndView modelAndView = new ModelAndView();
        List<Hotel> hotelList = hotelService.findAllHotel();
        int pageNum = (int)Math.ceil(hotelList.size() / 1.0 / pageSize);
        List<Hotel> hotelListResult = new ArrayList<Hotel>();
        int max = hotelList.size() > Integer.parseInt(pageIndex) * pageSize ? Integer.parseInt(pageIndex) * pageSize : hotelList.size();
        for(int i = (Integer.parseInt(pageIndex) - 1) * pageSize; i < max; i++){
            hotelListResult.add(hotelList.get(i));
        }
        modelAndView.addObject("hotelList", hotelListResult);
        modelAndView.addObject("pageNum", pageNum);
        modelAndView.addObject("pageIndex", pageIndex);
        modelAndView.setViewName("../../jsp/admin/hotellist");
        return modelAndView;
    }

    @RequestMapping("/queryList.html")
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) String city,
                             @RequestParam(required = false) String checkInDate,
                             @RequestParam(required = false) String checkOutDate,
                             @RequestParam(required = false) String roomType,
                             @RequestParam(required = false) String pageIndex){
        int pageSize = 10;
        if(pageIndex == null){
            pageIndex = "1";
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (city == null && cookie.getName().equals("Hotel")) {
                    String hotelStrDecode = cookie.getValue();
                    String hotelStr = null;
                    try {
                        hotelStr = URLDecoder.decode(hotelStrDecode, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    String[] hotel = hotelStr.split("&");
                    city = hotel[0];
                    checkInDate = hotel[1];
                    checkOutDate = hotel[2];
                }
            }
        }
        if(city != null){
            String hotelStr = null;
            try {
                hotelStr = URLEncoder.encode(city,"utf-8") + "&" +
                        checkInDate + "&" +
                        checkOutDate;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            Cookie cookie = new Cookie("Hotel", hotelStr);
            cookie.setPath("/");
            cookie.setMaxAge(60*60*24*7);
            response.addCookie(cookie);
        }
        List<String> rooms = hotelService.getRoomType();
        ModelAndView modelAndView = new ModelAndView();
        List<City> cityList = initService.getAllCities();
        List<Hotel> hotelList = hotelService.findHotel(city, roomType);
        int pageNum = (int)Math.ceil(hotelList.size() / 1.0 / pageSize);
        List<Hotel> hotelListResult = new ArrayList<Hotel>();
        int max = hotelList.size() > Integer.parseInt(pageIndex) * pageSize ? Integer.parseInt(pageIndex) * pageSize : hotelList.size();
        for(int i = (Integer.parseInt(pageIndex) - 1) * pageSize; i < max; i++){
            hotelListResult.add(hotelList.get(i));
        }
        modelAndView.addObject("cityList", cityList);
        modelAndView.addObject("hotelList", hotelListResult);
        modelAndView.addObject("rooms", rooms);
        modelAndView.addObject("city", city);
        modelAndView.addObject("pageIndex", pageIndex);
        modelAndView.addObject("pageNum", pageNum);
        modelAndView.addObject("checkInDate", checkInDate);
        modelAndView.addObject("checkOutDate", checkOutDate);
        modelAndView.setViewName("../../jsp/queryhotel");
        return modelAndView;
    }

    @RequestMapping("/toBook.html")
    public ModelAndView toBookTicket(HttpServletRequest request,HttpServletResponse response,String hotelId, String checkInDate, String checkOutDate){
        Cookie[] cookies = request.getCookies();
        String userName = null;
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userName")){
                    userName = cookie.getValue();
                }
                if(hotelId == null && cookie.getName().equals("ticketId")){
                    hotelId = cookie.getValue();
                }
            }
        }
        Cookie cookie = new Cookie("hotelId", hotelId);
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24*7);
        response.addCookie(cookie);
        ModelAndView modelAndView = new ModelAndView();
        Hotel hotel = hotelService.getHotelToBuy(hotelId);
        List<Passenger> passengerList = passengerService.findPassenger(userName);
        modelAndView.addObject("passengerList", passengerList);
        modelAndView.addObject("hotel", hotel);
        modelAndView.addObject("checkInDate", checkInDate);
        modelAndView.addObject("checkOutDate", checkOutDate);
        modelAndView.setViewName("../../jsp/bookhotel");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/sys/update")
    public boolean update(@RequestBody Map<String, Object> mapParam){
        int status = hotelService.updateHotel(mapParam);
        if(status > 0){
            return true;
        }
        return false;
    }

    @ResponseBody
    @RequestMapping("/sys/delete")
    public boolean delete(@RequestBody Map<String, Object> mapParam){
        int status = hotelService.removeHotel(mapParam);
        if(status > 0){
            return true;
        }
        return false;
    }
}
