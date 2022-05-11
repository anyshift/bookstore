package com.bookstore.model;

public class User {
    private Integer userId; //用户ID
    private String username; //用户名
    private String accountId; //账户ID

    public User() {
    }

    public User(Integer userId, String username, String accountId) {
        this.userId = userId;
        this.username = username;
        this.accountId = accountId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", accountId=" + accountId +
                '}';
    }
}
