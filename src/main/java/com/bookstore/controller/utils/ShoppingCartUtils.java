package com.bookstore.controller.utils;

import com.bookstore.controller.dao.Impl.AccountDAOImpl;
import com.bookstore.controller.dao.Impl.BookDAOImpl;
import com.bookstore.controller.dao.Impl.TradeDAOImpl;
import com.bookstore.controller.dao.Impl.TradeItemDAOImpl;
import com.bookstore.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 封装了购物车添加、获取购物项以及支付验证，使用Session存放购物车对象，方便取。
 */
public class ShoppingCartUtils {

    public static boolean addToShoppingCart(int bookID, ShoppingCart sc) {
        BookDAOImpl bookDAO = new BookDAOImpl();
        Book book = bookDAO.getBook(bookID);
        if (book != null) {
            sc.add(book);
            return true;
        }
        return false;
    }

    public static void updateShoppingCartItemQuantity(ShoppingCart sc, int bookID, int quantity) {
        sc.updateItemQuantity(bookID, quantity);
    }

    public static ShoppingCart getShoppingCart(HttpServletRequest request) {
        HttpSession session = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        if (shoppingCart == null) { //如果session里没有
            shoppingCart = new ShoppingCart();
            session.setAttribute("shoppingCart", shoppingCart);
        }
        return shoppingCart;
    }

    public static void clearShoppingCart(ShoppingCart shoppingCart) {
        shoppingCart.clear();
    }

    //支付页面填写用户名和账号ID的表单域验证。用于判断填写的是不是为空
    public static StringBuffer validateInputForm(String userName, String password) {
        StringBuffer errorInfo = new StringBuffer("");
        boolean userNameIsNullOrEmpty = userName == null || "".equals(userName.trim());
        boolean passwordIsNullOrEmpty = password == null || "".equals(password.trim());
        if (userNameIsNullOrEmpty && passwordIsNullOrEmpty) {
            errorInfo.append("请填写用户名和密码<br>");
            return errorInfo;
        }
        if (userNameIsNullOrEmpty) {
            errorInfo.append("请填写用户名<br>");
        }
        if(passwordIsNullOrEmpty){
            errorInfo.append("请填写密码<br>");
        }
        return errorInfo;
    }

    //支付页面填写的用户名和账号ID信息验证
    public static StringBuffer validatePayFormUserInfo(String formUserName, String passwordFromURL) {
        User user = UserUtils.getUserByUserName(formUserName);
        StringBuffer errorInfo = new StringBuffer("");
        if (user != null) {
            String realPassword = user.getPassword();
            if(!realPassword.trim().equals(UserUtils.encodePassword(passwordFromURL.trim()))) {
                errorInfo.append("用户名与密码不匹配");
            }
        }
        return errorInfo;
    }

    public static StringBuffer validateBalanceByAccountID(HttpServletRequest request, long accountID) {
        StringBuffer errorInfo = new StringBuffer("");
        ShoppingCart shoppingCart = ShoppingCartUtils.getShoppingCart(request);
        float totalMoney = shoppingCart.getTotalMoney();
        Account account = AccountUtils.getAccount(accountID);
        int balance = account.getBalance();
        if (totalMoney > balance) {
            errorInfo.append("余额不足");
        }
        return errorInfo;
    }

    public static StringBuffer validateStock(HttpServletRequest request) {
        StringBuffer errorInfo = new StringBuffer("");
        ShoppingCart shoppingCart = ShoppingCartUtils.getShoppingCart(request);
        Collection<ShoppingCartItem> items = shoppingCart.getItems();
        BookDAOImpl bookDAO = new BookDAOImpl();
        for (ShoppingCartItem item : items) {
            int quantity = item.getQuantity();
            Integer bookId = item.getBook().getId();
            int storeNumber = bookDAO.getBook(bookId).getStock();
            if (quantity > storeNumber) {
                errorInfo.append("书籍《" + item.getBook().getBookName() + "》 库存不足");
            }
        }
        return errorInfo;
    }

    //用户点击支付后要做的事
    public static void pay(HttpServletRequest request, String userName, long accountID) {
        ShoppingCart shoppingCart = ShoppingCartUtils.getShoppingCart(request);
        Collection<ShoppingCartItem> items = shoppingCart.getItems();

        //1、库存减、销量增
        BookDAOImpl bookDAO = new BookDAOImpl();
        bookDAO.batchUpdateStockNumberAndSalesAmount(items);

        //2、余额变
        AccountDAOImpl accountDAO = new AccountDAOImpl();
        accountDAO.setBalance(accountID, shoppingCart.getTotalMoney());

        //3、交易记录增（交易记录ID、哪个用户、下单时间）
        TradeDAOImpl tradeDAO = new TradeDAOImpl();
        User user = UserUtils.getUserByUserName(userName);
        Trade trade = new Trade(user.getUserId(), new Date(System.currentTimeMillis()));
        tradeDAO.insertTradeRecord(trade);

        //4、交易项记录增（交易项ID、哪本书、数量、对应交易记录ID）
        TradeItemDAOImpl tradeItemDAOImpl = new TradeItemDAOImpl();
        List<TradeItem> tradeItems = new ArrayList<>();
        String orderSerialNumber;
        for (ShoppingCartItem item : items) {
            Integer bookId = item.getBook().getId();
            int quantity = item.getQuantity();
            Integer tradeId = trade.getTradeId();
            orderSerialNumber = OrderUtils.getRandomOrderSerialNumber();
            TradeItem tradeItem = new TradeItem(bookId, quantity, tradeId, orderSerialNumber);
            tradeItems.add(tradeItem);
        }
        tradeItemDAOImpl.batchSave(tradeItems);

        /* 验证ThreadLocal的事务处理结果 */
        //int i = 1 / 0;

        //5、清空购物车
        shoppingCart.clear();

    }
}
