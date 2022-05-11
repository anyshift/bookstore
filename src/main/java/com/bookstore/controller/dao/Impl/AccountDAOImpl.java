package com.bookstore.controller.dao.Impl;

import com.bookstore.controller.dao.AccountDAO;
import com.bookstore.model.Account;

public class AccountDAOImpl extends BaseDAO<Account> implements AccountDAO {

    public AccountDAOImpl() {
        super();
    }

    @Override
    public Account getAccount(int id) {
        String sql = "select accountid, balance from account where accountid = ?";
        return query(sql, id);
    }

    @Override
    public void updateBalance(int id, float money) {
        String sql = "update account set balance=balance-" + money + " where accountid = ?";
        update(sql, id);
    }
}
