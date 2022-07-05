package com.bookstore.controller.dao.Impl;

import com.bookstore.controller.dao.UserDAO;
import com.bookstore.model.User;

public class UserDAOImpl extends BaseDAO<User> implements UserDAO {
    @Override
    public User getUserByUserName(String userName) {
        String sql = "select userid, username, password, accountid, isAdmin from userinfo where username = ?";
        return query(sql, userName);
    }

    @Override
    public User getUserByUserId(int userId) {
        String sql = "select userid, username, password, accountid, isAdmin from userinfo where userid = ?";
        return query(sql, userId);
    }


    @Override
    public long addUser(User user) {
        String sql1 = "insert into account(balance) values(?)";
        String sql2 = "insert into userinfo(username, password, accountId) " +
                "values (?, ?, ?)";

        long accountId = insert(sql1, 0);
        return insert(sql2, user.getUsername(), user.getPassword(), accountId);
    }
}
