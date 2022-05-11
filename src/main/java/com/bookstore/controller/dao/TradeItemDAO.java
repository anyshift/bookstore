package com.bookstore.controller.dao;

import com.bookstore.model.TradeItem;

import java.util.Collection;
import java.util.Set;

/**
 * TradeItemDAO操作数据表'TradeItem'，TradeItem表用于记录交易时的书籍ID、书籍数量、以及当次交易ID
 */
public interface TradeItemDAO {
    void batchSave(Collection<TradeItem> items); //批量保存交易项目
    Set<TradeItem> getTradeItemsByTradeID(int tradeID); //通过交易ID获取交易项目集合
}
