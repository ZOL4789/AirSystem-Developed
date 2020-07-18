package com.century.dao;

import com.century.vo.Passenger;
import com.century.vo.Role;

import java.util.List;
import java.util.Map;

public interface PassengerDAO {
    int addPassenger(Passenger passenger);
    List<String> queryRoleName();
    Role queryRoleByName(String roleName);
    List<Passenger> queryPassengerByUserId(int userId);
    Passenger queryPassengerByName(String name);
    List<Passenger> queryAllPassenger();
    int deletePassengerById(String passengerId);
    int updatePassenger(Map<String,Object> map);
}
