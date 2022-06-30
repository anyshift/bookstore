package com.bookstore.model;

public class User {
    private Integer userId; //用户ID
    private String username; //用户名
    private String password; //密码
    private String accountId; //账户ID

    public User() {
    }

    public User(Integer userId, String username, String password, String accountId) {
        this.userId = userId;
        this.username = username;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
                ", password=" + password +
                ", accountId=" + accountId +
                '}';
    }
}
