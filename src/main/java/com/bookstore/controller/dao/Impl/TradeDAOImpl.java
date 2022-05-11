package com.bookstore.controller.dao.Impl;

import com.bookstore.controller.dao.TradeDAO;
import com.bookstore.model.Trade;
import org.apache.commons.dbutils.QueryRunner;

import java.util.LinkedHashSet;
import java.util.Set;

public class TradeDAOImpl extends BaseDAO<Trade> implements TradeDAO {

    QueryRunner queryRunner = new QueryRunner();

    /**
     * 插入交易记录
     * @param trade Trade对象
     *
     *          =====================================================
     *          |     tradeid       userid        tradetime         |
     *          |       12             1      2012-11-01 00:00:00   |
     *          |       13             1      2012-11-01 00:00:00   |    <--- trade 表部分内容
     *          |       14             1      2012-11-01 00:00:00   |
     *          |       15             1      2012-12-20 00:00:00   |
     *          =====================================================
     */
    @Override
    public void insertTradeRecord(Trade trade) {
        String sql = "insert into trade(userid, tradetime) values(?, ?)";
        long tradeID = insert(sql, trade.getUserId(), trade.getTradeTime());
        trade.setTradeId((int) tradeID); //将插入后自增键ID添加到Trade对象的tradeID属性中。不这样的话trade对象里tradeID是缺失的
    }

    /**
     * 通过用户ID查找该用户的交易记录集合
     * @param userID 用户ID
     * @return 一个用户ID可以下单多次，就存在一个用户ID对应多条交易记录，返回的为交易集合，Set集合无序不可重复。
     *
     *          =====================================================
     *          |     tradeid       userid        tradetime         |
     *          |       12             1      2012-11-01 00:00:00   |
     *          |       13             1      2012-11-01 00:00:00   |    <--- trade 表部分内容
     *          |       14             1      2012-11-01 00:00:00   |
     *          |       15             1      2012-12-20 00:00:00   |
     *          =====================================================
     */
    @Override
    public Set<Trade> getTradeRecordsByUserID(int userID) {
        Set<Trade> set = null;
        String sql = "select tradeid, userid, tradetime from trade where userid = ?";
        set = new LinkedHashSet<>(queryForList(sql, userID)); //使用 LinkedHashSet(Collection<? extends Trade> collection) 构造器
        return set;
    }
}
