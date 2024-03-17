package com.example.spring6validator;

import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;

public class TestPerson {
    public static void main(String[] args) {
        // 创建person对象
        Person person = new Person();

        // 创建person对应的databinder
        DataBinder binder = new DataBinder(person);

        // 设置校验器
        binder.setValidator(new PersonValidator());

        // 调用方法执行校验
        binder.validate();

        // 输出校验结果
        BindingResult bindingResult = binder.getBindingResult();
        System.out.println(bindingResult.getAllErrors());
    }
}
