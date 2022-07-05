package com.bookstore.controller.dao.Impl;

import com.bookstore.controller.dao.BookDAO;
import com.bookstore.controller.webpage.Page;
import com.bookstore.controller.webpage.PriceLimit;
import com.bookstore.model.Book;
import com.bookstore.model.ShoppingCartItem;

import java.util.Collection;
import java.util.List;

public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {

    /**
     * 根据bookID返回数据库中对应的Book记录
     * @param bookID 书籍ID
     * @return Book对象
     */
    @Override
    public Book getBook(int bookID) {
        String sql = "select id, author, book_name as bookName, price, publish_date as publishingDate, " +
                "sales_amount as salesAmount, stock, info from mybooks where id = ?";
        return query(sql, bookID);
    }

    public List<Book> searchBookByKeyword(String keyword) {
        String sql = "SELECT id, author, book_name as bookName, price, publish_date as publishingDate, " +
                "sales_amount as salesAmount, stock, info  from mybooks where book_name LIKE CONCAT('%', ?, '%')";
        return queryForList(sql, keyword);
    }

    /**
     * 获取对应bookID的库存量
     * @param bookID 书籍ID
     * @return 库存量
     */
    @Override
    public int getStock(int bookID) {
        String sql = "select stock from mybooks where id = ?";
        return getSingleValue(sql, bookID);
    }

    /**
     * 获取数据库中书籍总数量
     * @param pl 价格区间
     * @return 书籍总数
     */
    @Override
    public long getTotalBooksNumber(PriceLimit pl) {
        String sql = "select count(id) from mybooks where price >= ? and price <= ?";
        return getSingleValue(sql, pl.getMinPrice(), pl.getMaxPrice());
    }

    public long getTotalBooksNumberByKeyword(PriceLimit pl, String keyword) {
        String sql = "select count(id) from mybooks where price >= ? and price <= ? " +
                "AND book_name LIKE CONCAT('%', ?, '%')";
        return getSingleValue(sql, pl.getMinPrice(), pl.getMaxPrice(), keyword);
    }

    /**
     * 获取价格区间内的Page对象。获取的Page对象可以获取当前页面、上下页、判断是否有上下页、获取总页数、获取每页展示数
     * @param pl 价格区间
     * @return Page对象。Page对象主要封装了翻页功能，对页面进行了控制。
     */
    @Override
    public Page<Book> getPage(PriceLimit pl) {
//      创建一个Page对象。得到的Page对象还需对Page对象里的totalItemNumber、bookList赋值，这样才得到一个属性值都有值的Page对象
        Page<Book> page = new Page<>(pl.getCurrentPageNumber());
//      给Page对象设置总书籍数
        page.setTotalItemNumber(getTotalBooksNumber(pl));
//      设置pl里的当前页数
        pl.setCurrentPageNumber(page.getCurrentPageNum());
//      让Page对象设置书籍集合
        page.setBookList(getBookList(pl, page.getItemSizePerPage()));

        return page;
    }

    public Page<Book> getPageByKeyword(PriceLimit pl, String keyword) {
//      创建一个Page对象。得到的Page对象还需对Page对象里的totalItemNumber、bookList赋值，这样才得到一个属性值都有值的Page对象
        Page<Book> page = new Page<>(pl.getCurrentPageNumber());
//      给Page对象设置总书籍数
        page.setTotalItemNumber(getTotalBooksNumberByKeyword(pl, keyword));
//      设置pl里的当前页数
        pl.setCurrentPageNumber(page.getCurrentPageNum());
//      让Page对象设置书籍集合
        page.setBookList(getBookListByKeyword(pl, page.getItemSizePerPage(), keyword));

        return page;
    }

    /**
     * 获取在价格区间内的书籍集合，分页展示在网页上
     * @param pl 价格区间
     * @param itemSizePerPage 每页书籍展示数
     * @return Book对象集
     */
    @Override
    public List<Book> getBookList(PriceLimit pl, int itemSizePerPage) {
        String sql = "SELECT id, author, book_name AS bookName, price, publish_date As publishingDate, " +
                "sales_amount AS salesAmount, stock, info FROM mybooks WHERE price <= ? AND price >= ? LIMIT ?, ?";

        int begin;
        if (pl.getCurrentPageNumber() == 0) {
            begin = 0;
        } else begin = (pl.getCurrentPageNumber() - 1) * itemSizePerPage;

        return queryForList(sql, pl.getMaxPrice(), pl.getMinPrice(),
                        begin, itemSizePerPage);
    }

    public List<Book> getBookListByKeyword(PriceLimit pl, int itemSizePerPage, String keyword) {
        String sql = "SELECT id, author, book_name as bookName, price, publish_date as publishingDate, " +
                "sales_amount as salesAmount, stock, info  FROM mybooks WHERE price <= ? " +
                "AND price >= ? AND book_name LIKE CONCAT('%', ?, '%') LIMIT ?, ?";

        int begin;
        if (pl.getCurrentPageNumber() == 0) {
            begin = 0;
        } else begin = (pl.getCurrentPageNumber() - 1) * itemSizePerPage;

        return queryForList(sql, pl.getMaxPrice(), pl.getMinPrice(), keyword, begin, itemSizePerPage);
    }

    /*
    public List<Book> getFilterBookList(PriceLimit pl, int itemSizePerPage, String filterType, String filterMode) {
        String sql = "select id, author, book_name, price, publish_date, sales_amount, " +
                "stock, info from mybooks where price <= ? AND " +
                "price >= ? order by ? ? limit ?, ?";
        return queryForList(sql, pl.getMaxPrice(), pl.getMinPrice(), filterType, filterMode,
                        (pl.getCurrentPageNumber() - 1) * itemSizePerPage, itemSizePerPage);
    }
    */

    /**
     * 购物车里一次下单多本书，这批书构成items，需要批量更新items的库存量和销售量
     * @param items 购物车里的书
     */
    @Override
    public void batchUpdateStockNumberAndSalesAmount(Collection<ShoppingCartItem> items) {
        String sql = "update mybooks set sales_amount = sales_amount + ?, " +
                "stock = stock - ? where id = ?";

        //方式一
        for (ShoppingCartItem item : items) {
            update(sql, item.getQuantity(), item.getQuantity(), item.getBook().getId());
        }

        //方式二
        /*List<ShoppingCartItem> list = new ArrayList<>(items);
        Object[][] params = new Object[items.size()][3];
        for (int i = 0; i < items.size(); i++) {
            params[i][0] = list.get(i).getQuantity();
            params[i][1] = list.get(i).getQuantity();
            params[i][2] = list.get(i).getBook().getBookId();
        }
        super.batch(sql, params);*/
    }
}
