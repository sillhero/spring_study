package com.example.atguiguspring.Service;

import com.example.atguiguspring.Dao.UserDao;
import com.example.atguiguspring.anno.Bean;
import com.example.atguiguspring.anno.Di;
import org.springframework.stereotype.Service;

@Bean
public class UserServiceImpl implements UserService{

    @Di
    private UserDao userDao;
}
