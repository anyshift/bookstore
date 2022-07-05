package com.bookstore.controller.servlet.service;

import com.bookstore.controller.dao.Impl.AccountDAOImpl;
import com.bookstore.controller.utils.OrderUtils;
import com.bookstore.controller.utils.UserUtils;
import com.bookstore.model.Account;
import com.bookstore.model.Order;
import com.bookstore.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserService {

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userNameFromURL = request.getParameter("userName");
        String passwordFromURL = request.getParameter("password");
        String cartAction = request.getParameter("cartAction");

        boolean userNameIsNullOrEmpty = (userNameFromURL == null);
        boolean passwordIsNullOrEmpty = (passwordFromURL == null);

        /* 避免一打开就提示输入用户名和密码 */
        if (userNameIsNullOrEmpty  && passwordIsNullOrEmpty) {
            request.getRequestDispatcher("/page/login.jsp").forward(request, response);
            return;
        }

        StringBuffer errorInfo = UserUtils.validateInputForm(userNameFromURL, passwordFromURL);

        if (!"".equals(errorInfo.toString())) {
            request.setAttribute("errorInfo", errorInfo); //将错误信息添加到Attribute中
            request.getRequestDispatcher("/page/login.jsp").forward(request, response);
        } else {
            User user = UserUtils.getUserByUserName(userNameFromURL);
            assert passwordFromURL != null;
            errorInfo = UserUtils.validateInputPassword(user, passwordFromURL.trim());
            if ("".equals(errorInfo.toString())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                if ("toPay".equals(cartAction)) {
                    request.getRequestDispatcher("/page/deal.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("/page/login_success.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorInfo", errorInfo); //将错误信息添加到Attribute中
                request.getRequestDispatcher("/page/login.jsp").forward(request, response);
            }
        }
    }

    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userNameFromURL = request.getParameter("userName");
        String passwordFromURL = request.getParameter("password");
        String password_again_FromURL = request.getParameter("password_again");

        boolean userNameIsNullOrEmpty = (userNameFromURL == null);
        boolean passwordIsNullOrEmpty = (passwordFromURL == null);

        /* 避免一打开就提示输入用户名和密码 */
        if (userNameIsNullOrEmpty  && passwordIsNullOrEmpty) {
            request.getRequestDispatcher("/page/register.jsp").forward(request, response);
            return;
        }

        StringBuffer errorInfo = UserUtils.validateInputForm(userNameFromURL, passwordFromURL, password_again_FromURL);
        if ("".equals(errorInfo.toString())) {
            errorInfo = UserUtils.validateUsernameUnique(userNameFromURL);
            if ("".equals(errorInfo.toString())) {
                errorInfo = UserUtils.validateInputPasswordSecure(passwordFromURL);
            }
        }

        if (!"".equals(errorInfo.toString())) {
            request.setAttribute("errorInfo", errorInfo); //将错误信息添加到Attribute中
            request.getRequestDispatcher("/page/register.jsp").forward(request, response);
        } else {
            assert passwordFromURL != null;
            User user = new User(userNameFromURL, UserUtils.encodePassword(passwordFromURL));
            long userId = UserUtils.addUser(user);
            if (userId >= 0) {
                user.setUserId((int) userId);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                request.getRequestDispatcher("/page/register_success.jsp").forward(request, response);
            }
        }
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("/page/logout.jsp").forward(request, response);
    }

    protected void mySpace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userFromSession = (User) request.getSession().getAttribute("user");
        if (userFromSession != null) {
            if (Integer.parseInt(userFromSession.getIsAdmin()) == 1) {
                request.getRequestDispatcher("/page/space_admin.jsp").forward(request, response);
            } else {
                AccountDAOImpl accountDAO = new AccountDAOImpl();
                Account account = accountDAO.getAccount(userFromSession.getUserId());
                request.setAttribute("account", account);
                request.getRequestDispatcher("/page/space_user.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("index?method=getBooks");
        }
    }

    protected void myOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User userFromSession = (User) request.getSession().getAttribute("user");
        if (userFromSession != null) {
            List<Order> orders = OrderUtils.getOrder(userFromSession.getUserId());
            request.setAttribute("orders", orders);
            request.getRequestDispatcher("/page/space_order.jsp").forward(request, response);
        } else {
            response.sendRedirect("index?method=getBooks");
        }
    }
}
