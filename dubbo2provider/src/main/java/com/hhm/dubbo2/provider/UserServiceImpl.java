package com.hhm.dubbo2.provider;

import com.hhm.dubbo2.api.UserService;

public class UserServiceImpl implements UserService {
    public String sayHello(String name) {
        return "hello,"+name;
    }
}
