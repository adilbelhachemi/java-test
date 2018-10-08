package com.h2rd.refactoring.dao;

import com.h2rd.refactoring.bean.User;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Vector;

@Repository
public class UserDaoImpl implements UserDao {


    /**
     * Vector is a thread-safe collection
     * all it's methods are syncronized
     */
    public static Vector<User> users;


    static {
        users = (users == null) ? new Vector<User>() : users;
        users.add(new User("test", "t@gmail.com", Arrays.asList("User")));
    }

    /**
     * {@inheritDoc}
     */
    public void saveUser(User user) {
        users.add(user);
    }

    /**
     * {@inheritDoc}
     */
    public Vector<User> getUsers() {
        return (users != null) ? users : new Vector<User>();
    }

    /**
     * {@inheritDoc}
     */
    public void deleteUser(User userToDelete) {
        User user = (userToDelete != null ) ? findUser(userToDelete.getName()) : null;
        if (user != null) {
            users.remove(user);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void updateUser(User userToUpdate) {
        User user = (userToUpdate != null ) ? findUser(userToUpdate.getName()) : null;
        if (user != null) {
            user.setEmail(userToUpdate.getEmail());
            user.setRoles(userToUpdate.getRoles());
        }
    }

    /**
     * {@inheritDoc}
     */
    public User findUser(String name) {
        for (User user : users) {
            if (user.getName() == name) {
                return user;
            }
        }
        return null;
    }
}
