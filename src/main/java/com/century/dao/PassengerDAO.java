package com.century.dao;

import com.century.vo.Passenger;

import java.util.List;

public interface PassengerDAO {
    int addPassenger(Passenger passenger);
    List<String> queryRoleName();
    List<Passenger> queryPassengerByUserId(int userId);
    Passenger queryPassengerByName(String name);
}
