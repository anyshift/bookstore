package com.bookstore.controller.dao;

import com.bookstore.model.Trade;

import java.util.Set;

/**
 * TradeDAO操作数据表'Trade'，Trade表用于记录交易用户ID和交易时间
 */
public interface TradeDAO {
    void insertTradeRecord(Trade trade); //插入交易记录
    Set<Trade> getTradeRecordsByUserID(int userID); //通过用户ID查找该用户的交易记录集合
}
