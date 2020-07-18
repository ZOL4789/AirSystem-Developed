package com.century.dao;

import com.century.vo.Hotel;
import com.century.vo.HotelBill;

import java.util.List;
import java.util.Map;

public interface HotelDAO {
    List<Hotel> queryAllHotel();
    int addHotel(Hotel hotel);
    List<String> queryRoomType();
    List<Hotel> queryHotelByAddress(String address);
    Hotel   queryHotelById(String hotelId);
    int deleteHotel(String hotelId);
    int updateHotel(Map<String, Object> map);
}
