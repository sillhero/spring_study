package com.atguigu.spring6.iocXML.auto.controller;

import com.atguigu.spring6.iocXML.auto.service.UserService;
import com.atguigu.spring6.iocXML.auto.service.UserServiceImpl;

public class UserController {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void addUser() {
        System.out.println("controller方法执行了......");
//        UserService userService = new UserServiceImpl();
//        userService.addUserService();
        userService.addUserService();
    }
}
