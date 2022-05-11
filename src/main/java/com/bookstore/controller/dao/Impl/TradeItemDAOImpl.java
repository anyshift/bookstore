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
     *
     *      ======================================================
     *      |   itemid      bookid      quantity        tradeid  |
     *      |     22          1            10             12     |
     *      |     23          2            10             12     |  <----tradeitem 表部分内容
     *      |     24          3            10             12     |
     *      |     25          1             1             13     |
     *      ======================================================
     */
    @Override
    public void batchSave(Collection<TradeItem> items) {
        String sql = "insert into tradeitem(bookid, quantity, tradeid) values(?, ?, ?)";

        //方法一
        for (TradeItem item : items) {
            long itemId = insert(sql, item.getBookId(), item.getQuantity(), item.getTradeId());

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
     *
     *      ======================================================
     *      |   itemid      bookid      quantity        tradeid  |
     *      |     22          1            10             12     |
     *      |     23          2            10             12     |  <----tradeitem 表部分内容
     *      |     24          3            10             12     |
     *      |     25          1             1             13     |
     *      ======================================================
     */
    @Override
    public Set<TradeItem> getTradeItemsByTradeID(int tradeID) {
        Set<TradeItem> set = null;
        String sql = "select itemid, bookid, quantity, tradeid from tradeitem where tradeid = ?";
        set = new LinkedHashSet<>(queryForList(sql, tradeID));
        return set;
    }
}
