package Test;

import com.bookstore.controller.dao.Impl.AccountDAOImpl;
import com.bookstore.model.Account;
import org.junit.jupiter.api.Test;

public class AccountDAOTest {

    AccountDAOImpl accountDAO = new AccountDAOImpl();
    @Test
    public void testGetAccount() {
        Account account = accountDAO.getAccount(2);
        System.out.println(account);
    }

    @Test void testUpdateBalance() {
        Account account = accountDAO.getAccount(2);
        accountDAO.updateBalance(2, 1000);
        System.out.println("更新之后的余额："+ account.getBalance());
    }
}
