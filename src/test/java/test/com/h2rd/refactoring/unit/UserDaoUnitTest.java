package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.bean.User;
import com.h2rd.refactoring.dao.UserDaoImpl;
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

    @Test
    public void saveUserTest() {
        userDao.saveUser(user);
        Assert.assertTrue(userDao.findUser(user.getName()) != null);

        User _user = userDao.findUser(user.getName());
        Assert.assertEquals("Fake Name", _user.getName());
    }

    @Test
    public void getUsers() {
        userDao.saveUser(user);
        Assert.assertNotNull(userDao.getUsers());
    }

    @Test
    public void updateUserTest(){
        userDao.saveUser(user);
        user.setName("Will Smith");

        userDao.updateUser(user);
        User updatedUser = userDao.findUser("Will Smith");

        Assert.assertEquals("Will Smith", updatedUser.getName());
    }

    @Test
    public void deleteUserTest() {
        user = new User();
        user.setName("Fresh prince of bel air");
        userDao.saveUser(user);
        userDao.deleteUser(user);
        Assert.assertNull(userDao.findUser(user.getName()));
    }
}