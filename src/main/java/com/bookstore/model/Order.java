package com.bookstore.model;

/**
 * Order类是集合了 Trade 和 TradeItem 两个数据表生成的一个Model类
 */
public class Order {
    private int tradeId;
    private String tradeTime;
    private String bookName;
    private int quantity;
    private String deliveryState;
    private String orderSerialNumber;


    public String getOrderSerialNumber() {
        return orderSerialNumber;
    }

    public void setOrderSerialNumber(String orderSerialNumber) {
        this.orderSerialNumber = orderSerialNumber;
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    @Override
    public String toString() {
        return "Order{" +
                "tradeId=" + tradeId +
                ", tradeTime='" + tradeTime + '\'' +
                ", bookName='" + bookName + '\'' +
                ", quantity=" + quantity +
                ", orderSerialNumber=" + orderSerialNumber +
                ", deliveryState='" + deliveryState + '\'' +
                '}';
    }
}
