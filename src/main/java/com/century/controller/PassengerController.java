package com.century.controller;

import com.century.service.PassengerService;
import com.century.service.UserService;
import com.century.vo.Passenger;
import com.century.vo.Role;
import com.century.vo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/passenger")
public class PassengerController {
    @Resource
    private PassengerService passengerService;
    @Resource
    private UserService userService;

    @RequestMapping("/add.html")
    public ModelAndView passengerAdd(){
        ModelAndView modelAndView = new ModelAndView();
        List<String> roles = passengerService.getRoles();
        modelAndView.addObject("roles", roles);
        modelAndView.setViewName("../../jsp/passengeradd");
        return modelAndView;
    }

    @RequestMapping("/doAdd.html")
    public ModelAndView doPassengerAdd(HttpServletRequest request, String name, String IDNum, String roleType){
        RedirectView redirectView = new RedirectView("/user/personalInfo.html", true, true);
        ModelAndView modelAndView = new ModelAndView(redirectView);
        Cookie[] cookies = request.getCookies();
        String userName = null;
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userName")){
                    userName = cookie.getValue();
                }
            }
        }
        int status = passengerService.addPassenger(userName, name, IDNum, roleType);
        modelAndView.addObject("status", status);
        return modelAndView;
    }

    @RequestMapping("/sys/listAll.html")
    public ModelAndView listAll(String pageIndex){
        int pageSize = 10;
        if(pageIndex == null){
            pageIndex = "1";
        }
        ModelAndView modelAndView = new ModelAndView();
        List<Passenger> passengerList = passengerService.getAllPassenger();
        List<Passenger> passengerListResult = new ArrayList<Passenger>();
        List<String> roleNameList = passengerService.getRoles();
        List<User> userList = userService.getAllUser();
        int pageNum = (int)Math.ceil(passengerList.size() / 1.0 / pageSize);
        int max = passengerList.size() > Integer.parseInt(pageIndex) * pageSize ? Integer.parseInt(pageIndex) * pageSize : passengerList.size();
        for(int i = (Integer.parseInt(pageIndex) - 1) * pageSize; i < max; i++){
            passengerListResult.add(passengerList.get(i));
        }
        modelAndView.addObject("passengerList", passengerListResult);
        modelAndView.addObject("pageIndex", pageIndex);
        modelAndView.addObject("pageNum", pageNum);
        modelAndView.addObject("roleNameList", roleNameList);
        modelAndView.addObject("userList", userList);
        modelAndView.setViewName("../../jsp/admin/passengerlist");
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/sys/update")
    public boolean update(@RequestBody Map<String, Object> mapParam){
        int status = passengerService.updatePassenger(mapParam);
        if(status > 0){
            return true;
        }
        return false;
    }

    @ResponseBody
    @RequestMapping("/sys/delete")
    public boolean delete(@RequestBody Map<String, Object> mapParam){
        int status = passengerService.removePassenger(mapParam);
        if(status > 0){
            return true;
        }
        return false;
    }
}
