package com.century.service;

import com.century.dao.BillDAO;
import com.century.dao.TicketDAO;
import com.century.dao.UserDAO;
import com.century.vo.Bill;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BillService {
    @Resource
    private BillDAO billDAO;
    @Resource
    private UserDAO userDAO;
    @Resource
    private TicketDAO ticketDAO;

    public int book(String airCode, String startTime, String arriveTime, String theDate, String userName){
        if(!airCode.equals("") && !userName.equals("") && !startTime.equals("") && !arriveTime.equals("") && !theDate.equals("")){
            Map<String, Object> mapCondition = new HashMap<String, Object>();
            mapCondition.put("airCode", airCode);
            mapCondition.put("startTime", startTime);
            mapCondition.put("arriveTime", arriveTime);
            mapCondition.put("date", theDate);
            int ticketId = ticketDAO.queryTicketByMap_airCode_startTime_arriveTime_date(mapCondition).getId();
            System.out.println("ticketId = " + ticketId);
            int userId = userDAO.queryIdByUserName(userName);
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String dateSimp = simpleDateFormat.format(date);
            Bill bill = new Bill();
            bill.setTicketId(ticketId);
            bill.setUserId(userId);
            bill.setDate(dateSimp);
            return billDAO.addBill(bill);
        }
        return -1;
    }

    public int refund(String airCode, String billDate, String ticketDate, String userName){
        if(airCode != null && userName != null && billDate != null && ticketDate != null) {
            Map<String, Object> mapCondition = new HashMap<String, Object>();
            mapCondition.put("airCode", airCode);
            mapCondition.put("date", ticketDate);
            int userId = userDAO.queryIdByUserName(userName);
            int ticketId = ticketDAO.queryTicketByMap_airCode_startTime_arriveTime_date(mapCondition).getId();
            mapCondition.put("date", billDate);
            mapCondition.put("userId", userId);
            mapCondition.put("ticketId", ticketId);
            return billDAO.deleteBill(mapCondition);
        }
        return -1;
    }

    public List<Bill> getBillsList(String userName){
        List<Bill> bills = new ArrayList<Bill>();
        if(!userName.equals("")){
            int userId = userDAO.queryIdByUserName(userName);
            bills = billDAO.queryBillByUserId(userId);
        }
        return bills;
    }
}
