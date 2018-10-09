package com.h2rd.refactoring.web;

import com.h2rd.refactoring.bean.User;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import com.h2rd.refactoring.helper.UserValidationHelper;
import com.h2rd.refactoring.message.UserMessage;
import com.h2rd.refactoring.userservice.UserService;
import com.h2rd.refactoring.util.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/users")
public class UserResource{


    @Autowired
    private UserService service;



    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response addUser(@QueryParam("name") String name,
                            @QueryParam("email") String email,
                            @QueryParam("role") List<String> roles) {

        if(UserValidationHelper.validateUser(name, email, roles)) {

            User user = new User(name, email, roles);
            service.saveUser(user);

            return Response.status(StatusCode.CREATED_STATUS.getCode()).entity(user).build();
        } else {
            return Response.status(StatusCode.BAD_REQUEST_STATUS.getCode()).entity(UserMessage.MISSING_CRITERIA_MESSAGE).build();
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response updateUser(@QueryParam("name") String name,
                               @QueryParam("email") String email,
                               @QueryParam("role") List<String> roles) {

        if(UserValidationHelper.validateUser(name, email, roles)) {
            User user = new User(name, email, roles);
            service.updateUser(user);
            return Response.ok().entity(user).build();
        }
        return Response.serverError().build();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response deleteUser(@QueryParam("name") String name,
                               @QueryParam("email") String email,
                               @QueryParam("role") List<String> roles) {

        if(UserValidationHelper.validateUser(name, email, roles)) {
            User user = new User(name, email, roles);
            service.deleteUser(user);
            return Response.ok().build();
        } else {
            return Response.status(StatusCode.NOT_FOUND_STATUS.getCode()).entity(UserMessage.USER_NOT_FOUND_MESSAGE).build();
        }
    }

    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response getUsers() {

        List<User> users = service.getUsers();

        if(!users.isEmpty()) {
            GenericEntity<List<User>> usersEntity = new GenericEntity<List<User>>(users) {};
            return Response.status(200).entity(usersEntity).build();
        } else {
            return Response.status(StatusCode.NO_CONTENT_STATUS.getCode()).entity(UserMessage.NO_CONTENT_FOUND_MESSAGE).build();
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Response findUser(@QueryParam("email") String email) {

        if(!StringUtils.isEmpty(email)) {
            User user = service.findUser(email);
            if(user != null) {
                return Response.ok().entity(user.getName()).build();
            } else {
                return Response.status(StatusCode.NO_CONTENT_STATUS.getCode()).entity(UserMessage.NO_USER_FOUND_MESSAGE).build();
            }
        }else {
            return Response.status(StatusCode.BAD_REQUEST_STATUS.getCode()).entity(UserMessage.USER_NAME_ERROR_MESSAGE).build();
        }
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String testUser() {
        return "Succes";
    }
}
