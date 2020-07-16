package com.century.vo;

public class Passenger {
    private int id;
    private String name;
    private String IDNum;
    private Role role;
    private int roleType;
    private int userId;

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

    public String getIDNum() {
        return IDNum;
    }

    public void setIDNum(String IDNum) {
        this.IDNum = IDNum;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public int getRoleType(){
        return roleType;
    }

    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
