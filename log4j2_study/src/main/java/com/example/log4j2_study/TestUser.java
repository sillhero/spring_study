package com.example.log4j2_study;


//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;

public class TestUser {

//    private Logger logger = LoggerFactory.getLogger(TestUser.class);
    @Test
    public void testUserObject() {
        // 加载spring配置文件,对象创建
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        // 获取创建的对象
        User user = (User) context.getBean("user");
        System.out.println(user);
//        logger.info("User has been created");

        user.add();
    }
//    @Test
//    public void testUserObject1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
//        // 获取类Class对象
//        Class<?> clazz = Class.forName("com.example.spring_study02.User");
//        // 调用方法创建对象
//        User user = (User) clazz.getDeclaredConstructor().newInstance();
//        System.out.println(user);
//    }
}
