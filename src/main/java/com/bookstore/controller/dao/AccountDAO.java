package com.bookstore.controller.dao;

import com.bookstore.model.Account;

public interface AccountDAO {
    Account getAccount(long id); //根据ID返回账号对象

    void setBalance(long accountId, double money); //设置该ID的余额
}
