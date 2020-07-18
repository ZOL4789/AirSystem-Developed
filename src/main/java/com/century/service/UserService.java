package com.century.service;

import com.century.dao.UserDAO;
import com.century.vo.Passenger;
import com.century.vo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.IDN;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Resource
    private UserDAO userDAO;

    public int changePwd(String userName, String oldPassword, String newPassword){
        if(userName != null && oldPassword != null && newPassword != null) {
            if(oldPassword.equals(userDAO.queryPasswordByUserName(userName))) {
                User user = new User();
                user.setName(userName);
                user.setPassword(newPassword);
                return userDAO.updatePassword(user);
            }
        }
        return -1;
    }

    public User getPersonalInfo(String userName){
        User user = new User();
        if(userName != null) {
            user = userDAO.queryInfoByUserName(userName);
        }
        return user;
    }

    public String login(String userName, String userPwd) {
        if (userName==null||"".equals(userName))
            return "用户名不能为空";
        if (userPwd==null||"".equals(userPwd))
            return"用户密码不能为空";
        User user=userDAO.queryUserByName(userName);
        if(user.getName().equals("admin")){
            return "管理员登录成功";
        }
        if (null==user)
            return "用户不存在，用户未注册";
        if (!userPwd.equals(user.getPassword()))
            return "用户名与密码不匹配";
        return "用户登录成功";
    }

    public String register(User user){
        String name = user.getName();
        String password = user.getPassword();
        String phone = user.getPhone();
        String email = user.getEmail();
        if (name==null||"".equals(name)){
            return "用户名不能为空";
        }
        if (password==null||"".equals(password)){
            return "请输入密码";
        }
        if (phone==null||"".equals(phone)){
            return "请输入电话号码";
        }
        if (email==null||"".equals(email)){
            return "请输入邮箱";
        }
        Date date =new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
        String date1 = sdf.format(date);
        user.setDate(date1);
        user.setMoney(0.00);
        int a = userDAO.addUser(user);
        if (a>=0)
        {
            return "用户注册成功";
        }
        return "注册失败";
    }

    public List<User> getAllUser(){
        return userDAO.queryAllUser();
    }

    public User getUserById(int userId){
        return userDAO.queryUserById(userId);
    }

    public int removeUser(Map<String,Object> map){
        String userId = (String)map.get("userId");
        return userDAO.deleteUserById(userId);
    }

    public int updateUser(Map<String, Object> map){
        return userDAO.updateUser(map);
    }
}
