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
        String sql = "select id, author, title, price, publishingdate, salesamount, " +
                "storenumber, remark from mybooks where id = ?";
        return query(sql, bookID);
    }

    /**
     * 获取对应bookID的库存量
     * @param bookID 书籍ID
     * @return 库存量
     */
    @Override
    public int getStoreNumber(int bookID) {
        String sql = "select storenumber from mybooks where id = ?";
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
        long count = getSingleValue(sql, pl.getMinPrice(), pl.getMaxPrice());
        return count;
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

    /**
     * 获取在价格区间内的书籍集合，分页展示在网页上
     * @param pl 价格区间
     * @param itemSizePerPage 每页书籍展示数
     * @return Book对象集
     */
    @Override
    public List<Book> getBookList(PriceLimit pl, int itemSizePerPage) {
        String sql = "select id, Author, Title, Price, Publishingdate, Salesamount, " +
                "Storenumber, Remark from mybooks where price <= ? AND price >= ? limit ?, ?";
        return queryForList(sql, pl.getMaxPrice(), pl.getMinPrice(), (pl.getCurrentPageNumber() - 1) * itemSizePerPage, itemSizePerPage);
    }

    /*
    public List<Book> getFilterBookList(PriceLimit pl, int itemSizePerPage, String filterType, String filterMode) {
        String sql = "select id, Author, Title, Price, Publishingdate, Salesamount, " +
                "Storenumber, Remark from mybooks where price <= ? AND " +
                "price >= ? order by ? ? limit ?, ?";
        return queryForList(sql, pl.getMaxPrice(), pl.getMinPrice(), filterType, filterMode, (pl.getCurrentPageNumber() - 1) * itemSizePerPage, itemSizePerPage);
    }
    */

    /**
     * 购物车里一次下单多本书，这批书构成items，需要批量更新items的库存量和销售量
     * @param items 购物车里的书
     */
    @Override
    public void batchUpdateStockNumberAndSalesAmount(Collection<ShoppingCartItem> items) {
        String sql = "update mybooks set Salesamount = Salesamount + ?, " +
                "Storenumber = Storenumber - ? where id = ?";

        //方式一
        for (ShoppingCartItem item : items) {
            update(sql, item.getQuantity(), item.getQuantity(), item.getBook().getBookId());
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
