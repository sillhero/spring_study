package com.atguigu.spring6.iocXML.auto.service;

import com.atguigu.spring6.iocXML.auto.dao.UserDao;
import com.atguigu.spring6.iocXML.auto.dao.UserDaoImpl;

public class UserServiceImpl implements UserService{


    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUserService() {
        System.out.println("UserService被执行了");
//        UserDao userDao = new UserDaoImpl();
//        userDao.addUserDao();
        userDao.addUserDao();
    }
}
