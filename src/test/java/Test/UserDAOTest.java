package Test;

import com.bookstore.controller.dao.Impl.UserDAOImpl;
import com.bookstore.model.User;
import org.junit.jupiter.api.Test;

public class UserDAOTest {

    UserDAOImpl userDAO = new UserDAOImpl();

    @Test
    public void testGetUser() {
        User tom = userDAO.getUserByUserName("qwe");
        System.out.println(tom.getUsername());
        System.out.println(tom.getUserId());

        User user = userDAO.getUserByUserId(2);
        System.out.println(user.getIsAdmin());
    }

    @Test
    public void testAddUser() {
        User user = new User("Test","1234567890");
        long l = userDAO.addUser(user);
        System.out.println(l);
    }
}
