package com.atguigu.spring6.iocXML.lifecycle;

public class User {

    // 无参构造
    public User() {
        System.out.println("1 bean创建对象，调用无参构造");
    }

    // 初始化方法
    public void initMethod() {
        System.out.println("4 bean对象初始化，调用指定的初始化方法");
    }

    // 销毁的方法
    public void destroyMethod() {
        System.out.println("5 bean对象销毁，调用指定的销毁方法");
    }
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("2 给bean对象设置属性值 set Name");
        this.name = name;
    }
}
