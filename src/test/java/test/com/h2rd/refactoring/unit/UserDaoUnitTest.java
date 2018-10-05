package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.bean.User;
import com.h2rd.refactoring.dao.UserDaoImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class UserDaoUnitTest {

    @Autowired
    UserDaoImpl userDao;

    @Test
    public void saveUserTest() {
        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));

        userDao.saveUser(user);
    }

    @Test
    public void deleteUserTest() {
        User user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));

        try {
            userDao.deleteUser(user);
        } catch (NullPointerException e) {
        }
    }
}