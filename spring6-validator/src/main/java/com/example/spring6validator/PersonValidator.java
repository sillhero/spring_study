package com.example.spring6validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // name不能为空
        ValidationUtils.rejectIfEmpty(errors, "name", "error.empty", "name is null");
        // age不能小于0
        Person p = (Person) target;
        if (p.getAge() < 0) {
            errors.rejectValue("age", "error.value.error", "Age < 0");
        } else if(p.getAge() > 200) {
            errors.rejectValue("age","age.value.error.old", "Age > 200");
        }
    }
}
