package Test;

import com.bookstore.controller.dao.Impl.BookDAOImpl;
import com.bookstore.controller.webpage.Page;
import com.bookstore.controller.webpage.PriceLimit;
import com.bookstore.model.Book;
import com.bookstore.model.ShoppingCartItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookDAOTest {
    BookDAOImpl bookDAO = new BookDAOImpl();

    @Test
    public void testGetBook() {
        Book book = bookDAO.getBook(1);
        System.out.println(book);
    }

    @Test
    public void testGetStoreNumber() {
        System.out.println(bookDAO.getStock(1));
    }

    @Test
    public void testGetTotalBooksNumber() {
        PriceLimit plp = new PriceLimit(0, Integer.MAX_VALUE, 1);
        long total = bookDAO.getTotalBooksNumber(plp);
        System.out.println(total);
    }

    @Test
    public void testGetPage() {
        PriceLimit plp = new PriceLimit(0, Integer.MAX_VALUE, 1);
        Page<Book> page = bookDAO.getPage(plp);
        System.out.println(page.getCurrentPageNum()); //1
        System.out.println(page.getNextPage()); //2
        System.out.println(page.getPrevPage()); //1
        System.out.println(page.getTotalPageNumber()); //7
        System.out.println(page.getItemSizePerPage()); //5
    }

    @Test
    public void testGetBookList() {
        PriceLimit plp = new PriceLimit(0, Integer.MAX_VALUE, 1);
        List<Book> list = bookDAO.getBookList(plp, 5);
        list.forEach(System.out::println);
    }

    @Test
    public void testBatchUpdateStoreNumberAndSalesAmount() {
        List<ShoppingCartItem> list = new ArrayList<>();
        Book book = bookDAO.getBook(32);
        ShoppingCartItem item = new ShoppingCartItem(book, 1);
        list.add(item);
        bookDAO.batchUpdateStockNumberAndSalesAmount(list);
    }

    @Test
    public void testSearchBookByKeyword_1() {
        List<Book> list = bookDAO.searchBookByKeyword("Java");
        list.forEach(System.out::println);
    }

    @Test
    public void testSearchBookByKeyword_2() {
        PriceLimit priceLimit = new PriceLimit(75, 80, 1);
        Page<Book> page = bookDAO.getPageByKeyword(priceLimit,"Java");
        System.out.println(page.getCurrentPageNum()); //1
        System.out.println(page.getTotalPageNumber()); //3
        System.out.println(page.getItemSizePerPage()); //5


        System.out.println();

        List<Book> bookList = page.getBookList();
        System.out.println(bookList.size());
        System.out.println();

        Iterator<Book> iterator = bookList.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
