package com.bookstore.model;

/**
 * 购物车小类，具体到购物车里的每一个购物项，具体到某一本书。
 */
public class ShoppingCartItem {
    private Book book; //提供一个Books对象，用于获取书籍价格
    private int quantity; //购物车中某商品的数量

    public ShoppingCartItem() {
    }

    public ShoppingCartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //获取购物车中某商品总价
    public float getItemMoney() {
        return book.getPrice() * quantity;
    }

    //购物车某商品数+1
    public void increase() {
        quantity++;
    }
}

