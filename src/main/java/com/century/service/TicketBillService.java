package com.century.service;

import com.century.dao.PassengerDAO;
import com.century.dao.TicketBillDAO;
import com.century.dao.UserDAO;
import com.century.vo.Passenger;
import com.century.vo.TicketBill;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TicketBillService {
    @Resource
    private TicketBillDAO ticketBillDAO;
    @Resource
    private PassengerDAO passengerDAO;
    @Resource
    private UserDAO userDAO;

    public int book(String ticketId, String userName, String passengerName){
        if(ticketId != null && userName != null && passengerName != null){
            Passenger passenger = passengerDAO.queryPassengerByName(passengerName);
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String dateSimp = simpleDateFormat.format(date);
            TicketBill ticketBill = new TicketBill();
            ticketBill.setTicketId(Integer.parseInt(ticketId));
            ticketBill.setPassengerId(passenger.getId());
            ticketBill.setDate(dateSimp);
            return ticketBillDAO.addTicketBill(ticketBill);
        }
        return -1;
    }

    public int refund(String ticketBillId){
        if(ticketBillId != null) {
            return ticketBillDAO.deleteTicketBill(ticketBillId);
        }
        return -1;
    }

    public List<TicketBill> getBillsList(String userName){
        List<TicketBill> ticketBills = new ArrayList<TicketBill>();
        if(!userName.equals("")){
            int userId = userDAO.queryIdByUserName(userName);
            ticketBills = ticketBillDAO.queryBillByUserId(userId);
        }
        return ticketBills;
    }

    public List<TicketBill> getAllBillsList(){
        return ticketBillDAO.queryAllBills();
    }

    public int updateTicketBill(Map<String, Object> map){
        String passengerName = (String)map.get("passengerName");
        Passenger passenger = passengerDAO.queryPassengerByName(passengerName);
        map.put("passengerId", passenger.getId());
        return ticketBillDAO.updateTicketBill(map);
    }

    public int removeTicketBill(Map<String, Object> map){
        String hotelBillId = (String)map.get("hotelBillId");
        return ticketBillDAO.deleteTicketBill(hotelBillId);
    }
}
