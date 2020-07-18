package com.century.dao;

import com.century.vo.Ticket;

import java.util.List;
import java.util.Map;

public interface TicketDAO {
    List<Ticket> queryAllTickets();
    Ticket queryTicketById(String ticketId);
    int addTicketBatch(List<Ticket> ticketList);
    List<Ticket> queryTicketByMap_startCity_arriveCity_date(Map<String, Object> map);
    Ticket queryTicketByMap_airCode_startTime_arriveTime_date(Map<String, Object> map);
    int deleteTicketById(String ticketId);
    int updateTicket(Map<String,Object> map);
}
