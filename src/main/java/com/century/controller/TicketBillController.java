package com.century.controller;

import com.century.dao.UserDAO;
import com.century.service.PassengerService;
import com.century.service.TicketBillService;
import com.century.service.UserService;
import com.century.vo.Passenger;
import com.century.vo.TicketBill;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sun.java2d.pipe.AAShapePipe;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ticket/bill")
public class TicketBillController {
    @Resource
    private TicketBillService ticketBillService;
    @Resource
    private PassengerService passengerService;
    @Resource
    private UserService userService;

    //保存用户订单到数据库中
    @RequestMapping("/book.html")
    public ModelAndView buy(HttpServletRequest request, String ticketId, String passengerName){
        ModelAndView modelAndView = new ModelAndView();
        Cookie[] cookies = request.getCookies();
        String userName = null;
        if(cookies != null) {
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userName")){
                    userName = cookie.getValue();       //获取用户名
                }
            }
        }
        int status = ticketBillService.book(ticketId, userName, passengerName);
        List<TicketBill> ticketBills = ticketBillService.getBillsList(userName);
        modelAndView.addObject("ticketBillList", ticketBills);
        modelAndView.addObject("status", status);
        modelAndView.setViewName("../../jsp/ticketbill");
        return modelAndView;
    }


    @RequestMapping("/refund.html")
    public ModelAndView refund(HttpServletRequest request, String ticketBillId){
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
        int status = ticketBillService.refund(ticketBillId);
        List<TicketBill> ticketBills = ticketBillService.getBillsList(userName);
        modelAndView.addObject("ticketBillList", ticketBills);
        modelAndView.addObject("status", status);
        modelAndView.setViewName("../../jsp/ticketbill");
        return modelAndView;
    }


    @RequestMapping("/list.html")
    public ModelAndView list(HttpServletRequest request){
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
        List<TicketBill> ticketBillList = ticketBillService.getBillsList(userName);
        modelAndView.addObject("ticketBillList", ticketBillList);
        modelAndView.setViewName("../../jsp/ticketbill");
        return modelAndView;
    }

    @RequestMapping("/sys/listAll.html")
    public ModelAndView listAll(@RequestParam(required = false) String pageIndex){
        int pageSize = 10;
        if(pageIndex == null){
            pageIndex = "1";
        }
        ModelAndView modelAndView = new ModelAndView();
        List<TicketBill> ticketBillList = ticketBillService.getAllBillsList();
        List<List<Passenger>> passengerAllList = new ArrayList<List<Passenger>>();
        List<Integer> userIdList = new ArrayList<Integer>();
        for(int i = 0; i < ticketBillList.size(); i++){
            if(!userIdList.contains(ticketBillList.get(i).getPassenger().getUser().getId())){
                userIdList.add(ticketBillList.get(i).getPassenger().getUser().getId());
            }
        }
        for(int i = 0; i < userIdList.size(); i++){
            passengerAllList.add(passengerService.findPassenger(userIdList.get(i)));
        }
        int pageNum = (int)Math.ceil(ticketBillList.size() / 1.0 / pageSize);
        int max = ticketBillList.size() > Integer.parseInt(pageIndex) * pageSize ? Integer.parseInt(pageIndex) * pageSize : ticketBillList.size();
        List<TicketBill> ticketBillListResult = new ArrayList<TicketBill>();
        for(int i = (Integer.parseInt(pageIndex) - 1) * pageSize; i < max; i++){
            ticketBillList.get(i).getTicket().setDate(ticketBillList.get(i).getTicket().getDate().split(" ")[0]);
            ticketBillListResult.add(ticketBillList.get(i));
        }
        modelAndView.addObject("ticketBillList", ticketBillListResult);
        modelAndView.addObject("passengerAllList", passengerAllList);
        modelAndView.addObject("pageNum", pageNum);
        modelAndView.addObject("pageIndex", pageIndex);
        modelAndView.setViewName("../../jsp/admin/ticketbilllist");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/sys/update")
    public boolean update(@RequestBody Map<String, Object> mapParam){
        int status = ticketBillService.updateTicketBill(mapParam);
        if(status > 0){
            return true;
        }
        return false;
    }

    @ResponseBody
    @RequestMapping("/sys/delete")
    public boolean delete(@RequestBody Map<String, Object> mapParam){
        int status = ticketBillService.removeTicketBill(mapParam);
        if(status > 0){
            return true;
        }
        return false;
    }
}
