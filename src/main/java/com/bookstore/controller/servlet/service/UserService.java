package com.bookstore.controller.servlet.service;

import com.bookstore.controller.dao.Impl.AccountDAOImpl;
import com.bookstore.controller.utils.AccountUtils;
import com.bookstore.controller.utils.OrderUtils;
import com.bookstore.controller.utils.UserUtils;
import com.bookstore.model.Account;
import com.bookstore.model.Order;
import com.bookstore.model.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String userName = request.getParameter("userName");
        String orderSerialNumber = request.getParameter("orderSerialNumber");

        if (userFromSession != null) {
            if (Integer.parseInt(userFromSession.getIsAdmin()) == 1) {
                User user = UserUtils.getUserByUserName(userName);
                Account account = null;
                if (user != null) {
                    account = AccountUtils.getAccount(user.getAccountId());
                }
                Order order = OrderUtils.getOrderByOrderSerialNumber(orderSerialNumber);
                if (user != null) request.setAttribute("managedUser", user);
                if (account != null) request.setAttribute("account", account);
                if (order != null) request.setAttribute("managedOrder", order);
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

    protected void updateBalanceWithAJAX(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userNameFromURL = request.getParameter("userName");
        String balanceFromURL = request.getParameter("balance");
        User admin = (User) request.getSession().getAttribute("user");
        User user = UserUtils.getUserByUserName(userNameFromURL);
        Account account = null;
        if ("1".equals(admin.getIsAdmin())) { //管理员才能更新
            if (user != null) {
                account = AccountUtils.getAccount(user.getAccountId());
                AccountUtils.setBalance(account.getAccountid(), Double.parseDouble(balanceFromURL));

                Map<String, Object> result = new HashMap<String, Object>();
                result.put("balance", balanceFromURL);

                Gson gson = new Gson();
                String jsonStr = gson.toJson(result);
                response.setContentType("text/javascript");
                response.getWriter().print(jsonStr);
            }
        }
    }

    protected void updateDeleveryStateWithAJAX(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String serialNumberFromURL = request.getParameter("serialNumber");
        String deliveryStateFromURL = request.getParameter("deliveryState");
        User admin = (User) request.getSession().getAttribute("user");
        if ("1".equals(admin.getIsAdmin())) { //管理员才能更新
            OrderUtils.setDeliveryState(deliveryStateFromURL, serialNumberFromURL);

            Map<String, Object> result = new HashMap<String, Object>();
            result.put("deliveryState", deliveryStateFromURL);

            Gson gson = new Gson();
            String jsonStr = gson.toJson(result);
            response.setContentType("text/javascript");
            response.getWriter().print(jsonStr);
        }
    }
}
