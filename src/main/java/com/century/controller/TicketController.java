package com.century.controller;

import com.century.service.PassengerService;
import com.century.service.TicketService;
import com.century.vo.Passenger;
import com.century.vo.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/ticket")
public class TicketController {
    @Resource
    private TicketService ticketService;
    @Resource
    private PassengerService passengerService;


    @RequestMapping("/list.html")
    public ModelAndView listTicket(String startCity, String arriveCity, String theDate, @RequestParam(required = false) String pageIndex, HttpServletResponse response){
        int pageSize = 10;
        if(pageIndex == null){
            pageIndex = "1";
            String sadStr = startCity + "&" +
                    arriveCity + "&" +
                    theDate;
            try {
                String sadStrEncode = URLEncoder.encode(sadStr, "utf-8");
                Cookie cookie = new Cookie("SAD", sadStrEncode);
                cookie.setMaxAge(60*60*24*7);
                cookie.setPath("/");
                response.addCookie(cookie);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        List<Ticket> ticketList = ticketService.getTicketsByMap_startCity_arriveCity_date(startCity, arriveCity,theDate);
        if(ticketList.size() == 0) {
            ticketList = ticketService.saveTickets(ticketList, startCity, arriveCity, theDate);
        }
        int pageNum = (int)Math.ceil(ticketList.size()/ 1.0 / pageSize);
        List<Ticket> ticketListResult = new ArrayList<Ticket>();
        int max = ticketList.size() > Integer.parseInt(pageIndex) * pageSize ? Integer.parseInt(pageIndex) * pageSize : ticketList.size();
        for(int i = (Integer.parseInt(pageIndex) - 1) * pageSize; i < max; i++){
            ticketListResult.add(ticketList.get(i));
        }

        modelAndView.addObject("ticketList", ticketListResult);
        modelAndView.addObject("pageNum", pageNum);
        modelAndView.addObject("startCity", startCity);
        modelAndView.addObject("arriveCity", arriveCity);
        modelAndView.addObject("theDate", theDate);
        modelAndView.addObject("pageIndex", pageIndex);
        modelAndView.setViewName("../../jsp/queryticket");
        return modelAndView;
    }

    @RequestMapping("/toBookTicket.html")
    public ModelAndView toBookTicket(HttpServletRequest request,String airCode, String startTime, String arriveTime, String theDateToBuy){
        Cookie[] cookies = request.getCookies();
        String userName = "";
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userName")){
                    userName = cookie.getValue();
                }
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        Ticket ticket = ticketService.getTicketToBuy(airCode, startTime, arriveTime, theDateToBuy);
        List<Passenger> passengerList = passengerService.findPassenger(userName);
        modelAndView.addObject("passengerList", passengerList);
        modelAndView.addObject("ticket", ticket);
        modelAndView.setViewName("../../jsp/bookticket");
        return modelAndView;
    }

}
