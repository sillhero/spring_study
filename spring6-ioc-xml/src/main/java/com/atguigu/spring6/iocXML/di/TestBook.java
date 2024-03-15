package com.atguigu.spring6.iocXML.di;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBook {
    @Test
    public void TestSetter() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di.xml");
        Book book = context.getBean("bookCon", Book.class);
        System.out.println(book);
    }
}
