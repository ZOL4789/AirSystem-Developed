package com.century.dao;

import com.century.vo.HotelBill;

import java.util.List;
import java.util.Map;

public interface HotelBillDAO {
    int addHotelBill(HotelBill hotelBill);
    List<HotelBill> queryHotelBillByUserId(int userId);
    int deleteHotelBill(String hotelBillId);
    List<HotelBill> queryAllHotelBills();
    int updateHotelBill(Map<String, Object> map);
}
