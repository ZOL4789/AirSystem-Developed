package com.century.service;

import com.century.dao.HotelDAO;
import com.century.dao.PassengerDAO;
import com.century.dao.UserDAO;
import com.century.vo.Hotel;
import com.century.vo.HotelBill;
import com.century.vo.Passenger;
import com.century.vo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HotelService {
    @Resource
    private HotelDAO hotelDAO;
    @Resource
    private UserDAO userDAO;
    @Resource
    private PassengerDAO passengerDAO;

    public List<Hotel> findAllHotel(){
        return hotelDAO.queryAllHotel();
    }

    public List<Hotel> findHotel(String city, String roomType){
        List<Hotel> hotelList = new ArrayList<Hotel>();
        if(city != null){
            Map<String, Object> mapCondition = new HashMap<String, Object>();
            mapCondition.put("address", city);
            hotelList = hotelDAO.queryHotelByAddress(city);
        }
        return hotelList;
    }

    public int addHotel(String name, String address, String fullAddress, String mark, String singleRoomLeft, String singleRoomPrice, String doubleRoomLeft, String doubleRoomPrice){
        if(name != null && address != null && fullAddress != null && mark != null
                && singleRoomLeft != null && singleRoomPrice != null && doubleRoomLeft != null && doubleRoomPrice != null){
            Hotel hotel = new Hotel();
            hotel.setName(name);
            hotel.setAddress(address);
            hotel.setFullAddress(fullAddress);
            hotel.setMark(mark);
            hotel.setSingleRoomLeft(Integer.parseInt(singleRoomLeft));
            hotel.setSingleRoomPrice(Double.parseDouble(singleRoomPrice));
            hotel.setDoubleRoomLeft(Integer.parseInt(doubleRoomLeft));
            hotel.setDoubleRoomPrice(Double.parseDouble(doubleRoomPrice));
            return hotelDAO.addHotel(hotel);
        }
        return -1;
    }

    public List<String> getRoomType(){
        return hotelDAO.queryRoomType();
    }

    public Hotel getHotelToBuy(String hotelId){
        return hotelDAO.queryHotelById(hotelId);
    }

    public int updateHotel(Map<String, Object> map){
        return hotelDAO.updateHotel(map);
    }

    public int removeHotel(Map<String, Object> map){
        String hotelBillId = (String)map.get("hotelId");
        return hotelDAO.deleteHotel(hotelBillId);
    }


}
