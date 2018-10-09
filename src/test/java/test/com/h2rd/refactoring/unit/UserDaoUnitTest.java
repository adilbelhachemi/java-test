package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.bean.User;
import com.h2rd.refactoring.dao.UserDaoImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration("/application-config.xml")
public class UserDaoUnitTest {

    @Autowired
    UserDaoImpl userDao;

    User user;

    @Before
    public void setup() {
        user = new User();
        user.setName("Fake Name");
        user.setEmail("fake@email.com");
        user.setRoles(Arrays.asList("admin", "master"));
    }

    @After

    @Test
    public void saveUserTest() {
        userDao.saveUser(user);
        Assert.assertTrue(userDao.findUser(user.getEmail()) != null);

        User _user = userDao.findUser(user.getEmail());
        Assert.assertEquals("Fake Name", _user.getName());
        userDao.deleteUser(user);
    }

    @Test
    public void getUsers() {
        userDao.saveUser(user);
        Assert.assertNotNull(userDao.getUsers());
    }

    @Test
    public void updateUserTest(){

        User newUser = new User("test2", "test2@user.com", Arrays.asList("Admin"));
        userDao.saveUser(newUser);
        newUser.setName("Will Smith");

        userDao.updateUser(newUser);
        User updatedUser = userDao.findUser(newUser.getEmail());

        Assert.assertTrue("Will Smith".equals(updatedUser.getName()));
    }

    @Test
    public void deleteUserTest() {
        User newUser = new User("test3", "test3@user.com", Arrays.asList("Admin"));
        userDao.saveUser(newUser);
        userDao.deleteUser(newUser);
        Assert.assertNull(userDao.findUser(newUser.getEmail()));
    }
}