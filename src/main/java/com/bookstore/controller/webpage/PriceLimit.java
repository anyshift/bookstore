package com.bookstore.controller.webpage;

/**
 * 封装限制价格查询条件的类，如查询某个价格区间的书籍等
 */
public class PriceLimit {
    private float minPrice = 0;
    private float maxPrice = Integer.MAX_VALUE;
    private int currentPageNumber;

    public PriceLimit(float minPrice, float maxPrice, int currentPageNumber) {
        super();
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.currentPageNumber = currentPageNumber;
    }

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    @Override
    public String toString() {
        return "priceLimit{" +
                "minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", currentPageNumber=" + currentPageNumber +
                '}';
    }
}
