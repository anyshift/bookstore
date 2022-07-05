package com.bookstore.controller.servlet;

import com.bookstore.controller.servlet.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class UserServlet extends HttpServlet {

    UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Type", "text/html; charset=UTF-8");

        String methodFromURL = request.getParameter("method");
        try {

            /* 获取userService对象中与methodFromURL同名的方法 */
            Method method = userService.getClass().getDeclaredMethod(methodFromURL, HttpServletRequest.class, HttpServletResponse.class);

            method.setAccessible(true);

            /* 反射，方法调用对象。可以简单理解为对象调用方法：userService调用method */
            method.invoke(userService, request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e); /* 抛出异常给 TransactionFilter 处理 */
        }
    }
}
