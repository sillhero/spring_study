package com.atguigu.spring6.iocXML.factoryBean;

import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<User> {


    @Override
    public User getObject() throws Exception {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
