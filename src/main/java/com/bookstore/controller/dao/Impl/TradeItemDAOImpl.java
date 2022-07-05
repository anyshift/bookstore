package com.bookstore.controller.dao.Impl;

import com.bookstore.controller.dao.TradeItemDAO;
import com.bookstore.model.TradeItem;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class TradeItemDAOImpl extends BaseDAO<TradeItem> implements TradeItemDAO {

    /**
     * 批量保存交易项目
     * @param items 交易项
     */
    @Override
    public void batchSave(Collection<TradeItem> items) {
        String sql = "insert into tradeitem(bookid, quantity, tradeid, order_serial_number) values(?, ?, ?, ?)";

        //方法一
        for (TradeItem item : items) {
            long itemId = insert(sql, item.getBookId(), item.getQuantity(), item.getTradeId(), item.getOrderSerialNumber());
        }

        //方法二：
        /*Object[][] parameters = new Object[items.size()][3];

        List<TradeItem> list = new ArrayList<>(items);
        for (int i = 0; i < list.size(); i++) {
            parameters[i][0] = list.get(i).getBookId();
            parameters[i][1] = list.get(i).getQuantity();
            parameters[i][2] = list.get(i).getTradeId();
        }
        super.batch(sql, parameters);*/
    }

    /**
     * 通过交易ID获取交易项目集合
     * @param tradeID 交易ID
     * @return 一次交易可能会买多本书，对应的TradeItem记录就会有好几条。返回交易ID所交易的项目集合，Set无序不可重复
     */
    @Override
    public Set<TradeItem> getTradeItemsByTradeID(int tradeID) {
        String sql = "select itemid, bookid, quantity, tradeid, order_serial_number AS orderSerialNumber " +
                "from tradeitem where tradeid = ?";
        return new LinkedHashSet<>(queryForList(sql, tradeID));
    }
}
