package com.bookstore.controller.dao.Impl;

import com.bookstore.controller.dao.AccountDAO;
import com.bookstore.model.Account;

public class AccountDAOImpl extends BaseDAO<Account> implements AccountDAO {

    public AccountDAOImpl() {
        super();
    }

    @Override
    public Account getAccount(long id) {
        String sql = "select accountid, balance from account where accountid = ?";
        return query(sql, id);
    }

    @Override
    public void setBalance(long accountId, double money) {
        String sql = "update account set balance = " + money + " where accountid = ?";
        update(sql, accountId);
    }
}
