package com.century.dao;

import com.century.vo.User;

import java.util.List;
import java.util.Map;

public interface UserDAO {
    int updatePassword(User user);
    String queryPasswordByUserName(String userName);
    int queryIdByUserName(String userName);
    User queryInfoByUserName(String userName);
    User queryUserByName(String userName);
    int addUser(User user);
    List<User> queryAllUser();
    User queryUserById(int userId);
    int deleteUserById(String ticketId);
    int updateUser(Map<String,Object> map);
}
