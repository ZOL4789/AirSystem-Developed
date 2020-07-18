package com.century.controller;

import com.century.service.PassengerService;
import com.century.service.TicketService;
import com.century.vo.Passenger;
import com.century.vo.Ticket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/ticket")
public class TicketController {
    @Resource
    private TicketService ticketService;
    @Resource
    private PassengerService passengerService;


    @RequestMapping("/queryList.html")
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
            ticketService.saveTickets(startCity, arriveCity, theDate);
            ticketList = ticketService.getTicketsByMap_startCity_arriveCity_date(startCity, arriveCity,theDate);
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

    @RequestMapping("/toBook.html")
    public ModelAndView toBookTicket(HttpServletRequest request,HttpServletResponse response,String ticketId){
        Cookie[] cookies = request.getCookies();
        String userName = null;
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userName")){
                    userName = cookie.getValue();
                }
                if(ticketId == null && cookie.getName().equals("ticketId")){
                    ticketId = cookie.getValue();
                }
            }
        }
        Cookie cookie = new Cookie("ticketId", ticketId);
        cookie.setPath("/");
        cookie.setMaxAge(60*60*24*7);
        response.addCookie(cookie);
        ModelAndView modelAndView = new ModelAndView();
        Ticket ticket = ticketService.getTicketToBuy(ticketId);
        List<Passenger> passengerList = passengerService.findPassenger(userName);
        modelAndView.addObject("passengerList", passengerList);
        modelAndView.addObject("ticket", ticket);
        modelAndView.setViewName("../../jsp/bookticket");
        return modelAndView;
    }


    @RequestMapping("/sys/listAll.html")
    public ModelAndView listAll(@RequestParam(required = false) String pageIndex){
        int pageSize = 10;
        if(pageIndex == null){
            pageIndex = "1";
        }
        ModelAndView modelAndView = new ModelAndView();
        List<Ticket> ticketList = ticketService.getAllTicket();
        List<Ticket> ticketListResult = new ArrayList<Ticket>();
        int pageNum = (int)Math.ceil(ticketList.size()/ 1.0 / pageSize);
        int max = ticketList.size() > Integer.parseInt(pageIndex) * pageSize ? Integer.parseInt(pageIndex) * pageSize : ticketList.size();
        for(int i = (Integer.parseInt(pageIndex) - 1) * pageSize; i < max; i++){
            ticketListResult.add(ticketList.get(i));
        }
        modelAndView.addObject("ticketList", ticketListResult);
        modelAndView.addObject("pageNum", pageNum);
        modelAndView.addObject("pageIndex", pageIndex);
        modelAndView.setViewName("../../jsp/admin/ticketlist");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/sys/delete")
    public boolean delete(@RequestBody Map<String, Object> mapParam){
        int status = ticketService.removeTicket(mapParam);
        if(status > 0){
            return true;
        }
        return false;
    }

    @ResponseBody
    @RequestMapping("/sys/update")
    public boolean update(@RequestBody Map<String, Object> mapParam){
        int status = ticketService.updateTicket(mapParam);
        if(status > 0){
            return true;
        }
        return false;
    }
}
