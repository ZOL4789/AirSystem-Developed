package com.century.cut;

import org.springframework.stereotype.Component;

@Component
public class Cut {

    public void  before(){
        System.out.println("我是前置通知！");
    }
}
