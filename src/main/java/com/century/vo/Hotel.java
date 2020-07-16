package com.century.vo;

public class Hotel {
    /*
    id:酒店id
    name:酒店名字
    address：酒店地址
    fullAddress：酒店详细地址
    mark:酒店评分
    singleRoomLeft：单人房剩余数目
    doubleRoomLeft:双人房剩余数目
     */
    private int id;
    private String name;
    private String address;
    private String fullAddress;
    private String mark;
    private int singleRoomLeft;
    private double singleRoomPrice;
    private int doubleRoomLeft;
    private double doubleRoomPrice;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getSingleRoomLeft() {
        return singleRoomLeft;
    }

    public void setSingleRoomLeft(int singleRoomLeft) {
        this.singleRoomLeft = singleRoomLeft;
    }

    public int getDoubleRoomLeft() {
        return doubleRoomLeft;
    }

    public void setDoubleRoomLeft(int doubleRoomLeft) {
        this.doubleRoomLeft = doubleRoomLeft;
    }

    public double getSingleRoomPrice() {
        return singleRoomPrice;
    }

    public void setSingleRoomPrice(double singleRoomPrice) {
        this.singleRoomPrice = singleRoomPrice;
    }

    public double getDoubleRoomPrice() {
        return doubleRoomPrice;
    }

    public void setDoubleRoomPrice(double doubleRoomPrice) {
        this.doubleRoomPrice = doubleRoomPrice;
    }
}
