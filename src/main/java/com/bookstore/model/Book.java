package com.bookstore.model;

public class Book {
    private Integer id;
    private String author;
    private String bookName;
    private float price;
    private String publishingDate;
    private int salesAmount;
    private int stock;
    private String info;

    public Book() {
    }

    public Book(String author, String bookName, float price, String publishingDate, int salesAmount, int stock, String info) {
        this.author = author;
        this.bookName = bookName;
        this.price = price;
        this.publishingDate = publishingDate;
        this.salesAmount = salesAmount;
        this.stock = stock;
        this.info = info;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(String  publishingDate) {
        this.publishingDate = publishingDate;
    }

    public int getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(int salesAmount) {
        this.salesAmount = salesAmount;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                ", publishingDate=" + publishingDate +
                ", salesAmount=" + salesAmount +
                ", stock=" + stock +
                ", info='" + info + '\'' +
                '}';
    }
}
