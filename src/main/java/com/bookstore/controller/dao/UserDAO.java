package com.bookstore.controller.dao;

import com.bookstore.model.User;

public interface UserDAO {
    User getUserByUserName(String userName);

    User getUserByUserId(int userId);

    long addUser(User user);
}
