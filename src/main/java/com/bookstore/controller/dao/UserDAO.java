package com.bookstore.controller.dao;

import com.bookstore.model.User;

public interface UserDAO {
    User getUser(String userName); //根据用户名获取用户对象
}
