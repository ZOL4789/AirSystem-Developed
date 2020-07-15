package com.century.vo;


import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class City {
    private int id;
    private String enCityName;
    private String cnCityName;
    private String abbreviation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnCityName() {
        return enCityName;
    }

    public void setEnCityName(String enCityName) {
        this.enCityName = enCityName;
    }

    public String getCnCityName() {
        return cnCityName;
    }

    public void setCnCityName(String cnCityName) {
        this.cnCityName = cnCityName;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }
}
