package com.bookstore.controller.utils;

import com.bookstore.controller.dao.Impl.OrderDAOImpl;
import com.bookstore.model.Order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class OrderUtils {

    private static final OrderDAOImpl orderDAO = new OrderDAOImpl();

    public static List<Order> getOrder(int userId) {
        return orderDAO.getOrder(userId);
    }

    public static Order getOrderByOrderSerialNumber(String serialNumber) {
        return orderDAO.getOrderByOrderSerialNumber(serialNumber);
    }

    public static void setDeliveryState(String deliveryState, String serialNumber) {
        orderDAO.setDeliveryState(deliveryState, serialNumber);
    }

    /**
     * 生成随机订单编号
     * @return 订单编号
     */
    public static synchronized String getRandomOrderSerialNumber() {
        Random random = new Random();
        // 这是5位随机数
        int r = random.nextInt(50000) + 10000;
        // 返回10位时间
        DateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        String timeStr = sdf.format(new Date());
        // 10位时间+5位随机数
        return timeStr + r;
    }
}
