package com.bookstore.controller.dao;

import com.bookstore.controller.webpage.Page;
import com.bookstore.controller.webpage.PriceLimit;
import com.bookstore.model.Book;
import com.bookstore.model.ShoppingCartItem;

import java.util.Collection;
import java.util.List;

public interface BookDAO {
    Book getBook(int bookID); //根据bookID返回Book对象
    int getStoreNumber(int bookID); //根据bookID返回这本书的库存数量
    Page<Book> getPage(PriceLimit pl); //获取在价格区间内的Book页面控制对象
    long getTotalBooksNumber(PriceLimit pl); //获取在价格区间内的总书籍数
    List<Book> getBookList(PriceLimit pl, int itemSizePerPage); //获取在价格区间内的书籍集合，分页展示
    void batchUpdateStockNumberAndSalesAmount(Collection<ShoppingCartItem> items); //批量更新库存数和销售量
}
