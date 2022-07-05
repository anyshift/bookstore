package com.bookstore.controller.dao;

import com.bookstore.model.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> getOrder(int userId);
}
