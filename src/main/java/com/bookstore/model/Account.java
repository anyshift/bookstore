package com.bookstore.model;

public class Account {
    private int accountid;
    private int balance;

    public Account() {
    }

    public Account(int accountid, int balance) {
        this.accountid = accountid;
        this.balance = balance;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountid=" + accountid +
                ", balance=" + balance +
                '}';
    }
}
