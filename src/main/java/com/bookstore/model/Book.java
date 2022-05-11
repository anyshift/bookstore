package com.bookstore.model;

public class Book {
    private Integer id;
    private String author;
    private String title;
    private float price;
    private String publishingDate;
    private int salesAmount;
    private int storeNumber;
    private String remark;

    public Book() {
    }

    public Book(String author, String title, float price, String publishingDate, int salesAmount, int storeNumber, String remark) {
        this.author = author;
        this.title = title;
        this.price = price;
        this.publishingDate = publishingDate;
        this.salesAmount = salesAmount;
        this.storeNumber = storeNumber;
        this.remark = remark;
    }

    public Integer getBookId() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(int storeNumber) {
        this.storeNumber = storeNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", publishingDate=" + publishingDate +
                ", salesAmount=" + salesAmount +
                ", storeNumber=" + storeNumber +
                ", remark='" + remark + '\'' +
                '}';
    }
}
