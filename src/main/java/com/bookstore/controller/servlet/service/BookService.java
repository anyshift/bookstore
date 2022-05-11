package com.bookstore.controller.servlet.service;

import com.bookstore.controller.dao.Impl.BookDAOImpl;
import com.bookstore.controller.utils.ShoppingCartUtils;
import com.bookstore.controller.webpage.Page;
import com.bookstore.controller.webpage.PriceLimit;
import com.bookstore.model.Book;
import com.bookstore.model.ShoppingCart;
import com.bookstore.model.ShoppingCartItem;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 主要功能是执行对应URL中method参数值对应的方法，例如http://localhost:8080/idnex?method=getBooks则执行本类中的getBooks()方法
 */
public class BookService extends HttpServlet {

    BookDAOImpl bookDAO = new BookDAOImpl();

    //获取所有书籍，然后添加到ServletContext的Attribute中，JSP页面就可以获取这些书籍Attribute
    protected void getBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String minPriceStr = request.getParameter("minPrice");
        String maxPriceStr = request.getParameter("maxPrice");
        String pageNumStr = request.getParameter("pageNum");

        int minPrice = 0;
        int currentPageNum = 1;
        int maxPrice = Integer.MAX_VALUE;

        try {
            minPrice = Integer.parseInt(minPriceStr);
        } catch (NumberFormatException e) {
        }

        try {
            maxPrice = Integer.parseInt(maxPriceStr);
        } catch (NumberFormatException e) {
        }

        try {
            currentPageNum = Integer.parseInt(pageNumStr);
        } catch (NumberFormatException e) {
        }

        PriceLimit pl = new PriceLimit(minPrice, maxPrice, currentPageNum);
        Page<Book> page = bookDAO.getPage(pl);

        request.setAttribute("books", page);
        request.getRequestDispatcher("/WEB-INF/view/books.jsp").forward(request, response);
    }

    //获取单本书籍，用于书籍详情展示
    protected void getBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookIdFromURL = request.getParameter("bookID");

        int bookID = 0;
        Book book = null;
        try {
            bookID = Integer.parseInt(bookIdFromURL);
        } catch (NumberFormatException e) {
        }

        if (bookID > 0) {
            book = bookDAO.getBook(bookID);
            if (book == null) {
                response.sendRedirect("index?method=getBooks");
                return;
            }
            request.setAttribute("book", book);
            request.getRequestDispatcher("/WEB-INF/view/bookInfo.jsp").forward(request, response);
        } else response.sendRedirect("index?method=getBooks");
    }

    //实现购物车的所有功能，如列出购物车所有购物项、添加书籍到购物车、购物项详情、清空购物车、移除购物项、支付
    protected void shoppingCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cartAction = request.getParameter("cartAction");

        //默认调用list。可以起到不显示 cartAction=list 的作用
        if (cartAction == null | "".equals(cartAction)) {
            request.getRequestDispatcher("index?method=shoppingCart&cartAction=list").forward(request, response);
            return;
        }

        //列出购物车所有购物项
        if ("list".equals(cartAction)) {
            if (ShoppingCartUtils.getShoppingCart(request).isEmpty()) { //购物车都没有的话
                response.sendRedirect("index?method=getBooks"); //那肯定要转到书城首页
            } else {
                request.getRequestDispatcher("/WEB-INF/view/shoppingCart.jsp").forward(request, response);
            }
        }

        //添加书籍到购物车
        if ("add".equals(cartAction)) {
            String bookIdFromURl = request.getParameter("bookID");
            int bookID = 0;
            Book book = null;

            try {
                bookID = Integer.parseInt(bookIdFromURl);
            } catch (NumberFormatException ignored) {}

            if (bookID > 0) {
                book = bookDAO.getBook(bookID);
                if (book == null) {
                    response.sendRedirect("index?method=getBooks");
                } else {
                    ShoppingCart shoppingCart = ShoppingCartUtils.getShoppingCart(request);
                    boolean add = ShoppingCartUtils.addToShoppingCart(bookID, shoppingCart);
                    if (add) {
                        getBooks(request, response);
                    } else {
                        request.getRequestDispatcher("/WEB-INF/view/errors/errorAddToShoppingCart.jsp").forward(request, response);
                    }
                }
            } else {
                response.sendRedirect("index?method=getBooks");
            }
        }

        //购物车里的购物项详情
        if ("bookInfo".equals(cartAction)) {
            if (ShoppingCartUtils.getShoppingCart(request).isEmpty()) { //购物车都没有的话
                response.sendRedirect("index?method=getBooks"); //那肯定要转到书城首页
            } else {
                String bookIdFromURL = request.getParameter("bookID");
                int bookID = 0;
                Book book = null;
                ShoppingCart shoppingCart = null;
                Map<Integer, ShoppingCartItem> books = null;
                ShoppingCartItem item = null;
                try {
                    bookID = Integer.parseInt(bookIdFromURL);
                } catch (NumberFormatException e) {
                }

                if (bookID > 0) {
                    shoppingCart = ShoppingCartUtils.getShoppingCart(request);
                    books = shoppingCart.getBooks();
                    item = books.get(bookID);
                    if (item == null) {
                        response.sendRedirect("index?method=shoppingCart&cartAction=list");
                    } else {
                        book = item.getBook();
                        request.setAttribute("book", book);
                        request.getRequestDispatcher("/WEB-INF/view/bookInfoFromCart.jsp").forward(request, response);
                    }
                } else response.sendRedirect("index?method=getBooks");
            }
        }

        //清空购物车
        if ("clear".equals(cartAction)) {
            ShoppingCart shoppingCart = ShoppingCartUtils.getShoppingCart(request); //在session中获取购物车对象
            if (shoppingCart.isEmpty()) { //购物车都没有的话
                response.sendRedirect("index?method=getBooks"); //那肯定要转到书城首页
            } else {
                ShoppingCartUtils.clearShoppingCart(shoppingCart); //清空购物车
                request.getRequestDispatcher("/WEB-INF/view/shoppingCart.jsp").forward(request, response);
            }
        }

        //移除购物项
        if ("remove".equals(cartAction)) {
            ShoppingCart shoppingCart = ShoppingCartUtils.getShoppingCart(request);
            if (shoppingCart.isEmpty()) { //购物车都没有的话
                response.sendRedirect("index?method=getBooks"); //那肯定要转到书城首页
            } else {
                String bookIdFromUrl = request.getParameter("bookID");
                int bookID = 0;
                try {
                    bookID = Integer.parseInt(bookIdFromUrl);
                } catch (NumberFormatException e) {
                }

                Map<Integer, ShoppingCartItem> books = shoppingCart.getBooks();
                if (books.containsKey(bookID)) {
                    books.remove(bookID);
                    if (shoppingCart.isEmpty()) { //如果移除后购物车为空
                        request.getRequestDispatcher("/WEB-INF/view/shoppingCart.jsp").forward(request, response);
                    } else {
                        response.sendRedirect("index?method=shoppingCart");
                    }
                } else {
                    request.getRequestDispatcher("/WEB-INF/view/shoppingCart.jsp").forward(request, response);
                }
            }
        }

        //转发至支付页面
        if ("toPay".equals(cartAction)) {
            if (ShoppingCartUtils.getShoppingCart(request).isEmpty()) { //购物车都没有的话
                response.sendRedirect("index?method=getBooks"); //那肯定要转到书城首页
            } else {
                request.getRequestDispatcher("/WEB-INF/view/deal.jsp").forward(request, response);
            }
        }

        //支付的一些判断以及完成支付。模拟的是信用卡，没有使用用户名密码组合，而是用户名和账号ID组合。
        if ("pay".equals(cartAction)) {
            if (ShoppingCartUtils.getShoppingCart(request).isEmpty()) { //购物车都没有的话
                response.sendRedirect("index?method=getBooks"); //那肯定要转到书城首页
            } else {
                String userNameFromURL = request.getParameter("userName");
                String accountIDFromURL = request.getParameter("accountID");

                StringBuffer errorInfo = ShoppingCartUtils.validatePayForm(userNameFromURL, accountIDFromURL);
                if (errorInfo.toString().equals("")) {

                    errorInfo = ShoppingCartUtils.validatePayFormUserInfo(userNameFromURL, accountIDFromURL);
                    if (errorInfo.toString().equals("")) {

                        errorInfo = ShoppingCartUtils.validateBalanceByAccountID(request, accountIDFromURL);
                        if (errorInfo.toString().equals("")) {
                            errorInfo = ShoppingCartUtils.validateStock(request);
                        }
                    }
                }

                if (!errorInfo.toString().equals("")) { //如果判断环节出现错误
                    request.setAttribute("errorInfo", errorInfo); //将错误信息添加到Attribute中
                    request.getRequestDispatcher("/WEB-INF/view/deal.jsp").forward(request, response); //转至支付页面，此时的支付页面会显示具体的错误详情
                } else { //如果判断环节没有出现错误
                    ShoppingCartUtils.pay(request, userNameFromURL, accountIDFromURL); //执行具体的支付功能
                    request.getRequestDispatcher("/WEB-INF/view/dealSuccess.jsp").forward(request, response);
                }
            }
        }
    }
}
