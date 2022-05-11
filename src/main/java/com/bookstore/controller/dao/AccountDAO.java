package com.bookstore.controller.dao;

import com.bookstore.model.Account;

public interface AccountDAO {
    Account getAccount(int id); //根据ID返回账号对象
    void updateBalance(int id, float money); //设置该ID的余额
}
