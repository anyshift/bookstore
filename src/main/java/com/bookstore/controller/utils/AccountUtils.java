package com.bookstore.controller.utils;

import com.bookstore.controller.dao.Impl.AccountDAOImpl;
import com.bookstore.model.Account;

public class AccountUtils {
    private static final AccountDAOImpl accountDAO = new AccountDAOImpl();

    public static Account getAccount(long accountID) {
        return accountDAO.getAccount(accountID);
    }
}
