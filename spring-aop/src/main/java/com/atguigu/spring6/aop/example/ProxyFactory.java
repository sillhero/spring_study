package com.atguigu.spring6.aop.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyFactory {
    // 目标对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 返回代理对象
    public Object getProxy() {
        /**
         * newProxyInstance();
         * 有三个参数
         * 第一个参数：类加载器 ClassLoader
         * 第二个参数：接口  Class[] interfaces: 目录对象实现的所有接口的class类型数组
         * 第三个参数：InvocationHandler 设置代理对象实现目标对象方法的过程
         *
         *
         */

        // 第一个参数：类加载器 ClassLoader
        ClassLoader classLoader = target.getClass().getClassLoader();
        // 第二个参数：接口  Class[] interfaces: 目录对象实现的所有接口的class类型数组
        Class<?>[] interfaces = target.getClass().getInterfaces();
        // 第三个参数：InvocationHandler 设置代理对象实现目标对象方法的过程
        InvocationHandler invocationHandler = new InvocationHandler() {
            /**
             * 执行目标对象方法
             * @param proxy 代理对象
             * @param method 目标对象方法
             * @param args 目标对象方法参数
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy,
                                 Method method,
                                 Object[] args) throws Throwable {
                System.out.println("动态代理 日志" + method.getName() + " 参数：" + Arrays.toString(args));
                // 执行目标对象方法
                Object result = method.invoke(target, args);
                System.out.println("动态代理 日志" + method.getName() + " 返回值：" + result);
                return result;
            }
        };
        return Proxy.newProxyInstance(classLoader, interfaces,invocationHandler);
    }
}
