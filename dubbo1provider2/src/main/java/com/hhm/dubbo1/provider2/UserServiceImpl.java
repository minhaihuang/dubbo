package com.hhm.dubbo1.provider2;

import com.hhm.dubbo2.api.UserService;

/**
 * 实现类
 */
public class UserServiceImpl implements UserService {

    public String sayHello(String name) {
        return  "hi,"+name+"!";
    }
}
