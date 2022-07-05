package Test;

import com.bookstore.controller.dao.Impl.OrderDAOImpl;
import com.bookstore.model.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

public class OrderDAOTest {
    OrderDAOImpl orderDAO = new OrderDAOImpl();

    @Test
    public void testGetOrder() {
        List<Order> order = orderDAO.getOrder(1);
        order.forEach(System.out::println);
    }
}
