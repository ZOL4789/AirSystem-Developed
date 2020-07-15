package com.century.dao;

import com.century.vo.Bill;

import java.util.List;
import java.util.Map;

public interface BillDAO {
    int addBill(Bill bill);
    List<Bill> queryBillByUserId(int userId);
    int deleteBill(Map<String, Object> map);

}
