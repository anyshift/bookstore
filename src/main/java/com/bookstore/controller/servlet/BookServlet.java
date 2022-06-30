package com.bookstore.controller.servlet;

import com.bookstore.controller.servlet.service.BookService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * index.jsp加载后来被此Servlet捕获。doPost方法获取到URl中到method参数值，根据参数值的内容执行相应的方法，方法体封装在BookService类中。
 */
public class BookServlet extends HttpServlet {

    BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        doPost(request, response);
    }

    /**
     * index.jsp默认启动时的参数是 (http://xxx/bookServlet?method=getBooks) getBooks，会执行BookService类中getBooks方法，获取所有书籍并展示。
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        String methodFromURL = request.getParameter("method");

        try {

            /* 获取BookService对象中与methodFromURL同名的方法 */
            Method method = bookService.getClass().getDeclaredMethod(methodFromURL, HttpServletRequest.class, HttpServletResponse.class);

            method.setAccessible(true);

            /* 反射，方法调用对象。可以简单理解为对象调用方法：bookService调用method */
            method.invoke(bookService, request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e); /* 抛出异常给 TransactionFilter 处理 */
        }
    }
}
