package com.hhm.dubbo1.provider2;

import com.hhm.dubbo.api.UserService;

/**
 * 实现类
 */
public class UserServiceImpl implements UserService {
    public String sayHi(String name) {
        return  "hi,"+name+"!";
    }
}
