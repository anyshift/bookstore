package Test;

import com.bookstore.controller.dao.Impl.TradeDAOImpl;
import com.bookstore.model.Trade;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Set;

public class TradeDAOTest {
    TradeDAOImpl tradeDAO = new TradeDAOImpl();

    @Test
    public void testInsertTradeRecord() {
        Trade trade = new Trade(1, new Date(System.currentTimeMillis()));
        tradeDAO.insertTradeRecord(trade);
        System.out.println(trade.getTradeId());
    }

    @Test
    public void testGetTradeRecordsByUserID() {
        Set<Trade> set = tradeDAO.getTradeRecordsByUserID(1);
        for (Trade trade : set) {
            System.out.println(trade);
        }
    }
}
