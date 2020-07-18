package com.century.dao;

import com.century.vo.TicketBill;

import java.util.List;
import java.util.Map;

public interface TicketBillDAO {
    int addTicketBill(TicketBill ticketBill);
    List<TicketBill> queryBillByUserId(int userId);
    int deleteTicketBill(String ticketBillId);
    List<TicketBill> queryAllBills();
    int updateTicketBill(Map<String,Object> map);
}
