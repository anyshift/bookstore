package com.bookstore.controller.dao.Impl;

import com.bookstore.controller.dao.UserDAO;
import com.bookstore.model.User;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    @Override
    public User getUser(String userName) {
        String sql = "select userid, username, accountid from userinfo where username = ?";
        return query(sql, userName);
    }
}
