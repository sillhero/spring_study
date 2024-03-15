package com.atguigu.spring6.iocXML;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("Bean.xml");
        // 根据ID获取Bean
        User user = (User) context.getBean("user");
        user.run();
        // 根据类型获取Bean
        User user2 = context.getBean(User.class);
        user2.run();
        // 根据ID和类型
        User user3 = context.getBean("user", User.class);
        user3.run();
    }
}
