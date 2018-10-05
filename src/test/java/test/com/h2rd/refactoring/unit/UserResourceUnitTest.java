package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.bean.User;
import com.h2rd.refactoring.dao.UserDaoImpl;
import com.h2rd.refactoring.web.UserResource;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.Response;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(locations={"classpath:WEB-INF/app-context.xml"})
public class UserResourceUnitTest {

    UserResource userResource;

    @Autowired
    UserDaoImpl userDao;

    @Test
    public void getUsersTest() {

        userResource = new UserResource();

        User user = new User();
        user.setName("fake user");
        user.setEmail("fake@user.com");
        userDao.saveUser(user);

        Response response = userResource.getUsers();
        Assert.assertEquals(200, response.getStatus());
    }
}
