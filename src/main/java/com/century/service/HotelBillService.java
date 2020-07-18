package com.century.service;

import com.century.dao.HotelBillDAO;
import com.century.dao.PassengerDAO;
import com.century.dao.UserDAO;
import com.century.vo.HotelBill;
import com.century.vo.Passenger;
import com.century.vo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class HotelBillService {
    @Resource
    private HotelBillDAO hotelBillDAO;
    @Resource
    private PassengerDAO passengerDAO;
    @Resource
    private UserDAO userDAO;

    public int addHotelBill(String hotelId, String checkInDate, String checkOutDate, String passengerName){
        if(hotelId != null && checkInDate != null && checkOutDate != null && passengerName != null){
            HotelBill hotelBill = new HotelBill();
            Passenger passenger = passengerDAO.queryPassengerByName(passengerName);
            hotelBill.setHotelId(Integer.parseInt(hotelId));
            hotelBill.setCheckInDate(checkInDate);
            hotelBill.setCheckOutDate(checkOutDate);
            hotelBill.setDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
            hotelBill.setPassengerId(passenger.getId());
            return hotelBillDAO.addHotelBill(hotelBill);
        }
        return -1;
    }

    public List<HotelBill> getHotelBills(String userName){
        List<HotelBill> hotelBillList = new ArrayList<HotelBill>();
        if(userName != null){
            User user = userDAO.queryUserByName(userName);
            hotelBillList = hotelBillDAO.queryHotelBillByUserId(user.getId());
        }
        return hotelBillList;
    }

    public int refund(String hotelBillId){
        if(hotelBillId != null) {
            return hotelBillDAO.deleteHotelBill(hotelBillId);
        }
        return -1;
    }

    public List<HotelBill> getAllHotelBills(){
        return hotelBillDAO.queryAllHotelBills();
    }

    public int updateHotelBill(Map<String, Object> map){
        return hotelBillDAO.updateHotelBill(map);
    }

    public int removeHotelBill(Map<String, Object> map){
        String hotelBillId = (String)map.get("hotelBillId");
        return hotelBillDAO.deleteHotelBill(hotelBillId);
    }
}
