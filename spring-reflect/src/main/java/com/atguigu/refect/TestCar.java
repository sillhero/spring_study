package com.atguigu.refect;

import org.junit.Test;

import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestCar {

    // 获取Class对象多种方式
    @Test
    public void test01() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 类名.class
        Class clazz1 = Car.class;
        // 对象.getClass()
        Class clazz2 = new Car().getClass();
        // Class.forName(全路径)
        Class clazz3 = Class.forName("com.atguigu.refect.Car");
        // 实例化
        Car car = (Car)clazz3.getDeclaredConstructor().newInstance();
        System.out.println(car);
    }

    // 获取构造方法
    @Test
    public void test02() throws Exception {
        Class<Car> clazz = Car.class;
        // 获取所有构造
        Constructor[] constructor = clazz.getConstructors(); // 这个是针对public构造方法的
//        clazz.getDeclaredConstructor() // 这个是可以得到私有的构造方法
        for (Constructor constructor1 : constructor) {
            System.out.println(constructor1.getName() + "参数的个数：" + constructor1.getParameterCount());
        }
        // 指定有参数的构造创建对象
        // 1 构造PUBLIC
        Constructor c1 =  clazz.getConstructor(String.class, int.class, String.class);
        Car car1 = (Car)c1.newInstance("xiaomi", 19, "red");
        System.out.println(car1);

        // 2 构造private
        Constructor c2 = clazz.getDeclaredConstructor(String.class, int.class, String.class);
        c2.setAccessible(true);
        Car car2 = (Car) c2.newInstance("xiaomi", 19, "red");
        System.out.println(car2);
    }


    // 获取类中的属性
    @Test
    public void test03() throws Exception {
        Class clazz = Car.class;
        Car car = (Car) clazz.getDeclaredConstructor().newInstance();
        // 获取所有的public属性
        Field[] fields = clazz.getDeclaredFields();
        // 获取所有属性 包含私有属性
        for (Field field : fields) {
            if (field.getName().equals("name")) {
                // 设置允许访问
                field.setAccessible(true);
                field.set(car,"五菱宏光");
            }
            System.out.println(field.getName());
            System.out.println(car);
        }
    }

    // 获取方法
    @Test
    public void test04() throws Exception {
        Car car = new Car("奔驰", 10, "black");
        Class clazz = car.getClass();
        // public方法
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
            // 执行方法
            if (method.getName().equals("toString")){
                String invoke = (String) method.invoke(car);
                System.out.println(invoke);
            }
        }
        // private方法
        Method[] methodsAll = clazz.getDeclaredMethods();
        for (Method method : methodsAll) {
            // 执行方法run()
            if (method.getName().equals("run")) {
                method.setAccessible(true);
                 method.invoke(car);
            }
        }
        //
    }
}
