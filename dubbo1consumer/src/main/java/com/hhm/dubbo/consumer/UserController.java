package com.hhm.dubbo.consumer;

import com.hhm.dubbo.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/hello.html")
    public String sayHello(String name){
        String welcome = userService.sayHi(name);
        return welcome;
    }
}
