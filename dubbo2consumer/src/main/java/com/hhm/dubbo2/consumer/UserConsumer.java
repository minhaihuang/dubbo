package com.hhm.dubbo2.consumer;

import com.hhm.dubbo2.api.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/consumer.xml");
        context.start();

        for (int i = 0; i < 100; i++){
            UserService userService = (UserService) context.getBean("userService");
            String welcome = userService.sayHello("hhm");
            System.out.println(welcome);
        }

    }
}
