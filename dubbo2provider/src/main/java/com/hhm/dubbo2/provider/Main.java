package com.hhm.dubbo2.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 启动dubbo提供者
 */
public class Main {
    public static void main(String[] args) throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:/provider.xml");
        context.start();
        System.out.println(" Dubbo service server started!");
        System.in.read();
    }
}
