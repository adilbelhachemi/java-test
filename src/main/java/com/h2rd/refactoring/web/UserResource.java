package com.h2rd.refactoring.web;

import com.h2rd.refactoring.bean.User;
import com.h2rd.refactoring.dao.UserDaoImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import com.h2rd.refactoring.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Controller
@Path("/users")
public class UserResource{

    @Autowired
    private UserService service;

    @POST
    @Path("/add/")
    public Response addUser(@QueryParam("name") String name,
                            @QueryParam("email") String email,
                            @QueryParam("role") List<String> roles) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRoles(roles);

        service.saveUser(user);
        return Response.status(201).entity(user).build();
    }

    @POST
    @Path("update/")
    public Response updateUser(@QueryParam("name") String name,
                               @QueryParam("email") String email,
                               @QueryParam("role") List<String> roles) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRoles(roles);

        service.updateUser(user);
        return Response.ok().entity(user).build();
    }

    @DELETE
    @Path("delete/")
    public Response deleteUser(@QueryParam("name") String name,
                               @QueryParam("email") String email,
                               @QueryParam("role") List<String> roles) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRoles(roles);

        service.deleteUser(user);
        return Response.ok().build();
    }

    @GET
    @Path("find/")
    public Response getUsers() {
    	List<User> users = service.getUsers();
        GenericEntity<List<User>> usersEntity = new GenericEntity<List<User>>(users) {};
        return Response.status(200).entity(usersEntity).build();
    }

    @GET
    @Path("search/")
    public Response findUser(@QueryParam("name") String name) {
        User user = service.findUser(name);
        return Response.ok().entity(user).build();
    }
}
