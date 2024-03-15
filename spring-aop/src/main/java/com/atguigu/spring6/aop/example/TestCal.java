package com.atguigu.spring6.aop.example;

public class TestCal {
    public static void main(String[] args) {
       ProxyFactory proxyFactory =  new ProxyFactory(new CalculatorImpl());
        Calculator proxy = (Calculator) proxyFactory.getProxy();
        proxy.add(1,2);
        proxy.mul(1,2);
    }
}
