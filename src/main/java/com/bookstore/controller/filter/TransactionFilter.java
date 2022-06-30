package com.bookstore.controller.filter;

import com.bookstore.controller.utils.JDBCUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response); //如果doFilter执行有误，就会被catch至回滚
            JDBCUtils.commitAndClose();
        } catch (Exception e) {
            JDBCUtils.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e); /* 回滚后抛异常给Tomcat，展示自定义错误页面 */
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
