package com.century.service;

import com.century.dao.TicketDAO;
import com.century.vo.Ticket;
import com.web.cn.xml.DataSet;
import com.web.cn.xml.DomesticAirlineLocator;
import com.web.cn.xml.DomesticAirlineSoap_PortType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TicketService {
    @Resource
    private TicketDAO ticketDAO;

    public List<Ticket> getTicketsByMap_startCity_arriveCity_date(String startCity, String arriveCity,@RequestParam(required = false) String date){
        Map<String, Object> mapCondition = new HashMap<String, Object>();
        mapCondition.put("startCity", startCity);
        mapCondition.put("arriveCity", arriveCity);
        mapCondition.put("date", date);
        List<Ticket> ticketList = ticketDAO.queryTicketByMap_startCity_arriveCity_date(mapCondition);
        return ticketList;
    }

    public int saveTickets(String startCity, String arriveCity, String date){
        List<Ticket> ticketList = new ArrayList<Ticket>();
        DomesticAirlineLocator domesticAirlineLocator = new DomesticAirlineLocator();
        try {
            DomesticAirlineSoap_PortType domesticAirline = domesticAirlineLocator.getDomesticAirlineSoap12();
            DataSet dataSet = new DataSet(domesticAirline.getDomesticAirlinesTime(startCity, arriveCity, date, "").get_any());
            int num = dataSet.get_any()[1].getChildNodes().item(0).getChildNodes().getLength();
            if (num > 0 && !dataSet.get_any()[1].getChildNodes().item(0).getChildNodes().item(0).getChildNodes().item(0).getChildNodes().item(0).getNodeValue().equals("没有航班")) {
                for (int i = 0; i < num; i++) {
                    Ticket ticket = new Ticket();
                    String airCode = dataSet.get_any()[1].getChildNodes().item(0).getChildNodes().item(i).getChildNodes().item(1).getChildNodes().item(0).getNodeValue();
                    String company = dataSet.get_any()[1].getChildNodes().item(0).getChildNodes().item(i).getChildNodes().item(0).getChildNodes().item(0).getNodeValue();
                    String startDrome = dataSet.get_any()[1].getChildNodes().item(0).getChildNodes().item(i).getChildNodes().item(2).getChildNodes().item(0).getNodeValue();
                    String arriveDrome = dataSet.get_any()[1].getChildNodes().item(0).getChildNodes().item(i).getChildNodes().item(3).getChildNodes().item(0).getNodeValue();
                    String startTime = dataSet.get_any()[1].getChildNodes().item(0).getChildNodes().item(i).getChildNodes().item(4).getChildNodes().item(0).getNodeValue();
                    String arriveTime = dataSet.get_any()[1].getChildNodes().item(0).getChildNodes().item(i).getChildNodes().item(5).getChildNodes().item(0).getNodeValue();
                    String mode = dataSet.get_any()[1].getChildNodes().item(0).getChildNodes().item(i).getChildNodes().item(6).getChildNodes().item(0).getNodeValue();
                    String airStop = dataSet.get_any()[1].getChildNodes().item(0).getChildNodes().item(i).getChildNodes().item(7).getChildNodes().item(0).getNodeValue();
                    String week = dataSet.get_any()[1].getChildNodes().item(0).getChildNodes().item(i).getChildNodes().item(8).getChildNodes().item(0).getNodeValue();
                    ticket.setAirCode(airCode);
                    ticket.setCompany(company);
                    ticket.setStartDrome(startDrome);
                    ticket.setArriveDrome(arriveDrome);
                    ticket.setStartTime(startTime);
                    ticket.setArriveTime(arriveTime);
                    ticket.setMode(mode);
                    ticket.setAirStop(airStop);
                    ticket.setWeek(week);
                    ticket.setDate(date);
                    ticketList.add(ticket);
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (javax.xml.rpc.ServiceException e) {
            e.printStackTrace();
        }
        return ticketDAO.addTicketBatch(ticketList);
    }

    public Ticket getTicketToBuy(String ticketId){
        Ticket ticket = new Ticket();
        if(ticketId != null){
            ticket = ticketDAO.queryTicketById(ticketId);
        }
        return ticket;
    }

    public List<Ticket> getAllTicket(){
        return ticketDAO.queryAllTickets();
    }

    public int removeTicket(Map<String,Object> map){
        String ticketId = (String)map.get("ticketId");
        return ticketDAO.deleteTicketById(ticketId);
    }

    public int updateTicket(Map<String, Object> map){
        return ticketDAO.updateTicket(map);
    }
}
