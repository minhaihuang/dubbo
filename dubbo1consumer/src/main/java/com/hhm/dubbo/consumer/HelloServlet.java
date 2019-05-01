package com.hhm.dubbo.consumer;

import com.hhm.dubbo.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class HelloServlet extends javax.servlet.http.HttpServlet {
    private UserService userService;

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String name = request.getParameter("name");
        String welcome = userService.sayHi(name);
        response.getWriter().write(welcome);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
