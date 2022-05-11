package com.bookstore.model;

public class TradeItem {
    private Integer itemId; //交易项ID
    private Book book; //
    private int quantity; //交易数量
    private Integer bookId; //书籍ID
    private Integer tradeId; //交易ID

    public TradeItem() {
    }

    public TradeItem(Integer bookId, int quantity, Integer tradeId) {
        this.quantity = quantity;
        this.bookId = bookId;
        this.tradeId = tradeId;
    }

    public Integer getTradeItemId() {
        return itemId;
    }

    public void setTradeItemId(Integer tradeItemId) {
        this.itemId = tradeItemId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    @Override
    public String toString() {
        return "TradeItem{" +
                "itemId=" + itemId +
                ", bookId=" + bookId +
                ", quantity=" + quantity +
                ", tradeId=" + tradeId +
                '}';
    }
}
