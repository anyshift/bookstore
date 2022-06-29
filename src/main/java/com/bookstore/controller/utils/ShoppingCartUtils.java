package com.bookstore.controller.utils;

import com.bookstore.controller.dao.Impl.AccountDAOImpl;
import com.bookstore.controller.dao.Impl.BookDAOImpl;
import com.bookstore.controller.dao.Impl.TradeDAOImpl;
import com.bookstore.controller.dao.Impl.TradeItemDAOImpl;
import com.bookstore.controller.servlet.service.AccountService;
import com.bookstore.controller.servlet.service.UserService;
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
    public static StringBuffer validatePayForm(String userName, String accountID) {
        StringBuffer errorInfo = new StringBuffer("");
        if (userName == null | userName.trim().equals("")) {
            errorInfo.append("请填写信用卡姓名<br>");
        }
        if(accountID == null | accountID.trim().equals("")){
            errorInfo.append("请填写信用卡ID<br>");
        }
        return errorInfo;
    }

    //支付页面填写的用户名和账号ID信息验证
    public static StringBuffer validatePayFormUserInfo(String formUserName, String formAccountID) {
        UserService userService = new UserService();
        User user = userService.getUserByUserName(formUserName);
        StringBuffer errorInfo = new StringBuffer("");
        if (user != null) {
            String trueUserID = user.getAccountId();
            if(!trueUserID.trim().equals(formAccountID)) {
                errorInfo.append("用户名与密码不匹配");
            }
        }
        return errorInfo;
    }

    public static StringBuffer validateBalanceByAccountID(HttpServletRequest request, String accountID) {
        StringBuffer errorInfo = new StringBuffer("");
        ShoppingCart shoppingCart = ShoppingCartUtils.getShoppingCart(request);
        float totalMoney = shoppingCart.getTotalMoney();
        AccountService accountService = new AccountService();
        Account account = accountService.getAccount(Integer.parseInt(accountID));
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
            Integer bookId = item.getBook().getBookId();
            int storeNumber = bookDAO.getBook(bookId).getStoreNumber();
            if (quantity > storeNumber) {
                errorInfo.append("书籍《" + item.getBook().getTitle() + "》 库存不足");
            }
        }
        return errorInfo;
    }

    //用户点击支付后要做的事
    public static void pay(HttpServletRequest request, String userName, String accountID) {
        ShoppingCart shoppingCart = ShoppingCartUtils.getShoppingCart(request);
        Collection<ShoppingCartItem> items = shoppingCart.getItems();

        //1、库存减、销量增
        BookDAOImpl bookDAO = new BookDAOImpl();
        bookDAO.batchUpdateStockNumberAndSalesAmount(items);

        //2、余额变
        AccountDAOImpl accountDAO = new AccountDAOImpl();
        accountDAO.updateBalance(Integer.parseInt(accountID), shoppingCart.getTotalMoney());

        //3、交易记录增（交易记录ID、哪个用户、下单时间）
        TradeDAOImpl tradeDAO = new TradeDAOImpl();
        UserService userService = new UserService();
        User user = userService.getUserByUserName(userName);
        Trade trade = new Trade(user.getUserId(), new Date(System.currentTimeMillis()));
        tradeDAO.insertTradeRecord(trade);

        //4、交易项记录增（交易项ID、哪本书、数量、对应交易记录ID）
        TradeItemDAOImpl tradeItemDAOImpl = new TradeItemDAOImpl();
        List<TradeItem> tradeItems = new ArrayList<>();
        for (ShoppingCartItem item : items) {
            Integer bookId = item.getBook().getBookId();
            int quantity = item.getQuantity();
            Integer tradeId = trade.getTradeId();
            TradeItem tradeItem = new TradeItem(bookId, quantity, tradeId);
            tradeItems.add(tradeItem);
        }
        tradeItemDAOImpl.batchSave(tradeItems);

        //5、清空购物车
        shoppingCart.clear();

    }
}
