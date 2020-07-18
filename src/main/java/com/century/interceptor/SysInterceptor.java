package com.century.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SysInterceptor  implements HandlerInterceptor {
    private static final String[] IGNORE_URI={"/login","/home","/doLogin","/404.jsp", "/403.jsp", "/register", "doRegister"};
    //在Controller方法调用之前执行,当返回值为false的时候整个请求就结束了
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String servletPath = httpServletRequest.getServletPath();
        for(String s:IGNORE_URI){
            if(servletPath.contains(s)){
                return true;
            }
        }
        if(httpServletRequest.getHeader("X-Requested-With") != null && httpServletRequest.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")){
            System.out.println("ajax请求中!");
            return true;
        }
        Cookie[] cookies = httpServletRequest.getCookies();
        String userName = null;
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("userName")){
                    userName = cookie.getValue();
                }
            }
        }
        if(userName == null){
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/404.jsp");
            return false;
        }else{
            if(!userName.equals("admin") && servletPath.contains("/sys")){
                httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/403.jsp");
                return false;
            }else {
                return true;
            }
        }
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        //在整个请求完成后执行，主要用于清理资源
    }

}

