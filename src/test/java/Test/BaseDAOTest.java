package Test;

import com.bookstore.controller.dao.Impl.AccountDAOImpl;
import com.bookstore.model.Account;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;

public class BaseDAOTest {
    AccountDAOImpl accountDAO = new AccountDAOImpl();

    @Test
    public void testInsert() {
        String sql = "insert into account values(?, ?)";
        long insert = accountDAO.insert(sql, 2, 10000);
        System.out.println(insert);
    }

    @Test
    public void testUpdate() {
        String sql  = "update account set balance = 30000 where accountid = ?";
        accountDAO.update(sql, 2);
    }

    @Test
    public void testQuery() {
        String sql = "select accountid, balance from account where accountid = ?";
        Account account = accountDAO.query(sql, 2);
        System.out.println(account);
    }

    @Test
    public void testQuertForList() {
        String sql = "select * from account";
        List<Account> list = accountDAO.queryForList(sql);
        list.forEach(new Consumer<Account>() {
            @Override
            public void accept(Account account) {
                System.out.println(account);
            }
        });
    }

    @Test
    public void testGetSingleValue() {
        String sql = "select count(*) from account";
        Object count = accountDAO.getSingleValue(sql);
        System.out.println(count);
    }
}
