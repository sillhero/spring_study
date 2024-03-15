package com.atguigu.spring6.iocXML.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUserDao {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Bean.xml");
        // 根据类型获取接口对应的bean
        UserDao userDao = context.getBean(UserDao.class);
        userDao.run();
    }
}
