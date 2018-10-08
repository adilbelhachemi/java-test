package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.bean.User;
import com.h2rd.refactoring.dao.UserDaoImpl;
import com.h2rd.refactoring.userservice.UserServiceImpl;
import com.h2rd.refactoring.web.UserResource;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/application-config.xml")
public class UserResourceUnitTest {


    @Autowired
    @InjectMocks
    UserResource userResource;

    @Mock
    UserServiceImpl service;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUsersTest() {

        User user = getUser();
        Vector<User> users = new Vector<User>();
        users.add(user);
        when(service.getUsers()).thenReturn(users);

        Response response = userResource.getUsers();
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void addUserTest() {
        User user = getUser();
        doNothing().when(service).saveUser(user);
        Response response = userResource.addUser("name", "test@gmail.com", Arrays.asList("User"));
        Assert.assertEquals(201, response.getStatus());
    }

    @Test
    public void findUserTest() {
        User user = getUser();
        when(service.findUser("name")).thenReturn(user);
        Response response = userResource.findUser("name");
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void updateUserTest() {
        User user = getUser();
        doNothing().when(service).updateUser(user);
        Response response = userResource.updateUser("name", "test@gmail.com", Arrays.asList("User"));
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void deleteUserTest() {
        User user = getUser();
        doNothing().when(service).deleteUser(user);
        Response response = userResource.deleteUser("name", "test@gmail.com", Arrays.asList("User"));
        Assert.assertEquals(200, response.getStatus());
    }

    /**
     * User stub
     * @return
     */
    private User getUser() {

        User user = new User();
        user.setName("fake user");
        user.setEmail("fake@user.com");

        List<String> roles = Arrays.asList("User");
        user.setRoles(roles);

        return user;
    }
}
