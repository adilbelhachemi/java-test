package test.com.h2rd.refactoring.integration;

import com.h2rd.refactoring.bean.User;
import com.h2rd.refactoring.exceptions.UserNotFoundException;
import com.h2rd.refactoring.util.StatusCode;
import com.h2rd.refactoring.web.UserResource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.Response;
import java.util.Arrays;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration("/application-config.xml")
public class UserIntegrationTest {


    @Autowired
    UserResource userResource;


    @Test
    public void addUserTest() throws Exception {
        User integration = getUser();
        Response response = userResource.addUser(integration.getName(), integration.getEmail(), integration.getRoles());
        Assert.assertEquals(StatusCode.CREATED_STATUS.getCode(), response.getStatus());
    }

    @Test
    public void updateUserTest() {
        User user = new User("user1", "user1@user.com", Arrays.asList("User"));
        userResource.addUser(user.getName(), user.getEmail(), user.getRoles());
        user.setName("Will Smith");
        Response response = userResource.updateUser(user.getName(), user.getEmail(), user.getRoles());
        Assert.assertEquals(StatusCode.OK_STATUS.getCode(), response.getStatus());
    }

    @Test
    public void getUsersTest() {
        Response response = userResource.getUsers();
        Assert.assertEquals(StatusCode.OK_STATUS.getCode(), response.getStatus());
    }

    @Test
    public void deleteUSerTest() {
        User user = new User("user2", "user2@user.com", Arrays.asList("User"));
        userResource.addUser(user.getName(), user.getEmail(), user.getRoles());
        Response response = userResource.deleteUser(user.getName(), user.getEmail(), user.getRoles());
        Assert.assertEquals(StatusCode.OK_STATUS.getCode(), response.getStatus());
    }

    @Test
    public void findUserTest() {
        User user = new User("user3", "user3@user.com", Arrays.asList("User"));
        userResource.addUser(user.getName(), user.getEmail(), user.getRoles());
        Response response = userResource.findUser(user.getEmail());
        Assert.assertEquals(StatusCode.OK_STATUS.getCode(), response.getStatus());
    }

    @Test(expected = UserNotFoundException.class)
    public void findUserDoesntExistTest() {
        User user = new User("user4", "user4@user.com", Arrays.asList("User"));
        Response response = userResource.findUser(user.getEmail());
    }


    /**
     * User stub
     * @return
     */
    private User getUser() {
        return new User("fake user", "fake@user.com", Arrays.asList("User"));
    }
}
