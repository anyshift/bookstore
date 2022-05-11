package com.bookstore.model;

import java.util.Date;

public class Trade {
    private int tradeId; //自增键，交易ID
    private Date tradeTime; //交易时间
    private int userId; //交易记录中的用户ID

    public Trade() {
    }

    public Trade(int userId, Date tradeTime) {
        this.tradeTime = tradeTime;
        this.userId = userId;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "tradeId=" + tradeId +
                ", userId=" + userId +
                ", tradeTime=" + tradeTime +
                '}';
    }
}
