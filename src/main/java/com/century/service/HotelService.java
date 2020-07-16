package com.century.service;

import com.century.dao.HotelDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HotelService {
    @Resource
    private HotelDAO hotelDAO;


}
