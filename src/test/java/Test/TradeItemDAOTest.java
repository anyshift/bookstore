package Test;

import com.bookstore.controller.dao.Impl.TradeItemDAOImpl;
import com.bookstore.controller.utils.OrderUtils;
import com.bookstore.model.TradeItem;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TradeItemDAOTest {

    TradeItemDAOImpl tradeItemDAO = new TradeItemDAOImpl();

    @Test
    public void testBatchSave() {
        List<TradeItem> list = new ArrayList<>();
        TradeItem tradeItem = new TradeItem(2, 10, 17, OrderUtils.getRandomOrderSerialNumber());
        list.add(tradeItem);
        tradeItemDAO.batchSave(list);
    }

    @Test
    public void testGetTradeItemsByTradeID() {
        Set<TradeItem> set = tradeItemDAO.getTradeItemsByTradeID(17);
        for (TradeItem item : set) {
            System.out.println(item);
        }
    }
}
