package com.century.service;

import com.century.dao.PassengerDAO;
import com.century.dao.UserDAO;
import com.century.vo.Passenger;
import com.century.vo.Role;
import com.century.vo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
            passenger.setDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
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

    public List<Passenger> findPassenger(int userId){
        return passengerDAO.queryPassengerByUserId(userId);
    }

    public List<Passenger> getAllPassenger(){
        return passengerDAO.queryAllPassenger();
    }

    public int removePassenger(Map<String,Object> map){
        String passengerId = (String)map.get("passengerId");
        return passengerDAO.deletePassengerById(passengerId);
    }

    public int updatePassenger(Map<String, Object> map){
        String userName = (String)map.get("userName");
        String roleName = (String)map.get("roleName");
        if(userName != null) {
            User user = userDAO.queryUserByName(userName);
            map.put("userId", user.getId());
        }
        if(roleName != null){
            Role role = passengerDAO.queryRoleByName(roleName);
            map.put("roleType", role.getId());
        }
        return passengerDAO.updatePassenger(map);
    }
}
