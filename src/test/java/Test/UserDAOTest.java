package Test;

import com.bookstore.controller.dao.Impl.UserDAOImpl;
import com.bookstore.model.User;
import org.junit.jupiter.api.Test;

public class UserDAOTest {

    UserDAOImpl userDAO = new UserDAOImpl();

    @Test
    public void testGetUser() {
        User tom = userDAO.getUser("Tom");
        System.out.println(tom);
    }
}
