package com.bookstore.controller.servlet.service;

import com.bookstore.controller.dao.Impl.AccountDAOImpl;
import com.bookstore.model.Account;

public class AccountService {
    AccountDAOImpl accountDAO = new AccountDAOImpl();

    public Account getAccount(int accountID) {
        return accountDAO.getAccount(accountID);
    }
}
