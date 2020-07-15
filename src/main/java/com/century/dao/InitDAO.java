package com.century.dao;

import com.century.vo.City;

import java.util.List;

public interface InitDAO {


    int addCity(City city);

    int addCityBatch(List<City> cityList);

    List<City> queryAllCities();
}
