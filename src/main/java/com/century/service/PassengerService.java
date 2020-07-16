package com.century.service;

import com.century.dao.PassengerDAO;
import com.century.dao.UserDAO;
import com.century.vo.Passenger;
import com.century.vo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PassengerService {
    @Resource
    private PassengerDAO passengerDAO;
    @Resource
    private UserDAO userDAO;

    public int addPassenger(String userName, String name, String IDNum, String roleType){
        if(userName != null && name != null && IDNum != null && roleType != null){
            User user = userDAO.queryUserByName(userName);
            Passenger passenger = new Passenger();
            passenger.setIDNum(IDNum);
            passenger.setName(name);
            passenger.setRoleType(Integer.parseInt(roleType) + 1);
            passenger.setUserId(user.getId());
            return passengerDAO.addPassenger(passenger);
        }
        return -1;
    }

    public List<String> getRoles(){
        return passengerDAO.queryRoleName();
    }

    public List<Passenger> findPassenger(String userName){
        List<Passenger> passengerList = new ArrayList<Passenger>();
        if(userName != null){
            User user = userDAO.queryUserByName(userName);
            passengerList = passengerDAO.queryPassengerByUserId(user.getId());
        }
        return passengerList;
    }
}
