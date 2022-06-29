package com.bookstore.controller.webpage;

import java.util.List;

/**
 * 封装翻页功能的类，主要用于完成初始化当前页码、获取上下页、设置页面项目展示数量、计算总页数、获取页面展示项等有关网页控制的功能。
 */
public class Page<T> {
    private int currentPageNum; //当前页码
    private List<T> bookList; //每页展示的书籍信息构成一个List
    private int itemSizePerPage = 5; //每页展示5本书籍
    private long totalItemNumber; //书籍总数

    //构造函数，初始化当前第几页，只需要初始化当前页码即可
    public Page(int pageNum) {
        this.currentPageNum = pageNum;
    }

    //获取当前页码
    public int getCurrentPageNum() {
        if (currentPageNum < 1) currentPageNum = 1;
        if (currentPageNum > getTotalPageNumber()) currentPageNum = getTotalPageNumber();
        return currentPageNum;
    }

    //获取每页书籍数
    public int getItemSizePerPage() {
        return itemSizePerPage;
    }

    public void setBookList(List<T> list) {
        this.bookList = list;
    }

    //获取每页展示的书籍构成的List
    public List<T> getBookList() {
        return bookList;
    }

    //设置书籍总数
    public void setTotalItemNumber(long number) {
        this.totalItemNumber = number;
    }

    //获取书籍总页数
    public int getTotalPageNumber() {
        int totalPageNumber = (int) (totalItemNumber / itemSizePerPage); //总书籍数➗每页展示数 = 总页数
        if (totalItemNumber % itemSizePerPage != 0) { //如果除不尽
            totalPageNumber++; //剩下的书再加一页展示
        }
        return totalPageNumber;
    }

    //是否有下一页
    public boolean isHasNextPage() {
        if (getCurrentPageNum() < getTotalPageNumber()) return true;
        return false;
    }

    //是否有上一页
    public boolean isHasPrevPage() {
        if (getCurrentPageNum() == 1 || getCurrentPageNum() == 0) return false;
        return true;
    }

    //获取下一页
    public int getNextPage() {
        if (isHasNextPage()) return currentPageNum + 1;
        else return getCurrentPageNum();
    }

    //获取上一页
    public int getPrevPage() {
        if (isHasPrevPage()) return currentPageNum - 1;
        else return getCurrentPageNum();
    }
}
