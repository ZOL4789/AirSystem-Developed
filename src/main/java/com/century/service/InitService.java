package com.century.service;

import com.century.dao.InitDAO;
import com.century.dao.TicketDAO;
import com.century.vo.City;
import com.web.cn.xml.DataSet;
import com.web.cn.xml.DomesticAirlineLocator;
import com.web.cn.xml.DomesticAirlineSoap_PortType;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

@Service
public class InitService {
    @Resource
    private InitDAO initDAO;

    //判断数据库中是否已经存在城市，若否，则从网上获取，并且保存到数据库中
    @PostConstruct
    public void init(){
        List<City> cityList = initDAO.queryAllCities();
        if(cityList.size() <= 0) {
            //服务类接口（具体不知道是啥，莽就对了）
            DomesticAirlineLocator domesticAirlineLocator = new DomesticAirlineLocator();
            try {
                DomesticAirlineSoap_PortType domesticAirline = domesticAirlineLocator.getDomesticAirlineSoap12();
                DataSet dataSet = new DataSet(domesticAirline.getDomesticCity().get_any());
                for(int i = 0; i < dataSet.get_any()[1].getChildNodes().item(0).getChildNodes().getLength(); i++){
                    City city = new City();
                    String enCityName=dataSet.get_any()[1].getChildNodes().item(0).getChildNodes().item(i).getChildNodes().item(0).getChildNodes().item(0).getNodeValue();
                    String cnCityName=dataSet.get_any()[1].getChildNodes().item(0).getChildNodes().item(i).getChildNodes().item(1).getChildNodes().item(0).getNodeValue();
                    String abbreviation=dataSet.get_any()[1].getChildNodes().item(0).getChildNodes().item(i).getChildNodes().item(2).getChildNodes().item(0).getNodeValue();
                    city.setEnCityName(enCityName);
                    city.setCnCityName(cnCityName);
                    city.setAbbreviation(abbreviation);
                    cityList.add(city);
                }
                //写入数据库
                initDAO.addCityBatch(cityList);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (javax.xml.rpc.ServiceException e) {
                e.printStackTrace();
            }
        }
    }

    public List<City> getAllCities(){
       return initDAO.queryAllCities();
    }
}
