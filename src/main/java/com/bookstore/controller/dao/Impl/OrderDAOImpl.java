package com.bookstore.controller.dao.Impl;

import com.bookstore.controller.dao.OrderDAO;
import com.bookstore.model.Order;
import java.util.List;

public class OrderDAOImpl extends BaseDAO<Order> implements OrderDAO {
    @Override
    public List<Order> getOrder(int userId) {
        String sql = "SELECT td.tradeid AS tradeId, tradetime AS tradeTime, book_name AS bookName, " +
                "quantity, order_serial_number AS orderSerialNumber, delivery_state AS deliveryState FROM trade AS td " +
                "JOIN tradeitem AS tdi ON td.tradeid = tdi.tradeid AND userid = ? " +
                "JOIN mybooks ON tdi.bookid = mybooks.id " +
                "ORDER BY tradetime DESC LIMIT 0, 10";
        return queryForList(sql, userId);
    }

    @Override
    public Order getOrderByOrderSerialNumber(String serialNumber) {
        String sql = "SELECT td.tradeid AS tradeId, tradetime AS tradeTime, book_name AS bookName, " +
                "quantity, order_serial_number AS orderSerialNumber, delivery_state AS deliveryState FROM trade AS td " +
                "JOIN tradeitem AS tdi ON td.tradeid = tdi.tradeid AND order_serial_number = ? " +
                "JOIN mybooks ON tdi.bookid = mybooks.id";
        return query(sql, serialNumber);
    }

    @Override
    public void setDeliveryState(String deliveryState, String serialNumber) {
        String sql = "update tradeitem set delivery_state = ? where order_serial_number = ?";
        update(sql, deliveryState, serialNumber);
    }
}
