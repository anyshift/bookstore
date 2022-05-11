package com.bookstore.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 购物车大类，包含所有的购物项。
 */
public class ShoppingCart {

    //books 根据<bookID, ShoppingCartItem>完成映射，可以根据bookID获取购物车里对应的商品项。BookID是key，购物项是value
    private Map<Integer, ShoppingCartItem> books = new HashMap<>();

    //添加书籍到购物车
    public void add(Book book) {
        ShoppingCartItem sci = books.get(book.getBookId());
        if (sci == null) {
            sci = new ShoppingCartItem(book, 1); //因为sci==null，所以要新建一个购物项(new)，不然put的就是一个null
            books.put(book.getBookId(), sci);
        } else sci.increase();
    }

    //购物车里是否有书
    public boolean hasBook(int bookID) {
        return books.containsKey(bookID);
    }

    //获取所有<bookID, ShoppingCartItem>键值对
    public Map<Integer, ShoppingCartItem> getBooks() {
        return books;
    }

    //获取购物车里所有的待购项组成的集合
    public Collection<ShoppingCartItem> getItems() {
        return books.values();
    }

    //获取购物车商品总数量
    public int getBookNumber() {
        int total = 0;
        for (ShoppingCartItem sci : books.values()) {
            total += sci.getQuantity();
        }
        return total;
    }

    //获取购物车商品总金额
    public float getTotalMoney() {
        int total = 0;
        for (ShoppingCartItem sci : books.values()) {
            total += sci.getItemMoney();
        }
        return total;
    }

    //购物车是否为空
    public boolean isEmpty() {
        return books.isEmpty();
    }

    //清空购物车
    public void clear() {
        books.clear();
    }

    //从购物车中移除购买项
    public void removeItem(int bookID) {
        books.remove(bookID);
    }

    //更新购物车中书籍的数量
    public void updateItemQuantity(int bookID, int quantity) {
        ShoppingCartItem sci = books.get(bookID);
        if (sci != null) {
            sci.setQuantity(quantity);
        }
    }
}
