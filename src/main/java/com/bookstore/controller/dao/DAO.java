package com.bookstore.controller.dao;

import java.util.List;

/**
 * 定义基本操作，由BaseDAO实现
 */
public interface DAO<T> {
    long insert(String sql, Object... args);
    void update(String sql, Object... args);
    T query(String sql, Object... args);
    List<T> queryForList(String sql, Object... args);
    <V> V getSingleValue(String sql, Object... args);
    void batch(String sql, Object[]... args);
}
