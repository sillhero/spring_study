package com.atguigu.spring6.iocXML.auto.dao;

public class UserDaoImpl implements UserDao{

    @Override
    public void addUserDao() {
        System.out.println("UserDao被执行了");
    }
}
