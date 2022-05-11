package com.bookstore.controller.servlet.service;

import com.bookstore.controller.dao.Impl.UserDAOImpl;
import com.bookstore.model.User;

public class UserService {

    private UserDAOImpl userDAO = new UserDAOImpl();

    public User getUserByUserName(String username) {
        return userDAO.getUser(username);
    }
}
