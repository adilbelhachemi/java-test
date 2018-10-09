package com.h2rd.refactoring.dao;

import com.h2rd.refactoring.bean.User;
import com.h2rd.refactoring.exceptions.UserNotFoundException;
import org.springframework.stereotype.Repository;

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
        return users;
    }

    /**
     * {@inheritDoc}
     */
    public void deleteUser(User userToDelete) {
        User user = (userToDelete != null) ? findUser(userToDelete.getEmail()) : null;
        if (user != null) {
            users.remove(user);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void updateUser(User userToUpdate) {
        User user = (userToUpdate != null) ? findUser(userToUpdate.getEmail()) : null;
        if (user != null) {
            user.setName(userToUpdate.getName());
            user.setEmail(userToUpdate.getEmail());
            user.setRoles(userToUpdate.getRoles());
        }
    }

    /**
     * {@inheritDoc}
     */
    public User findUser(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}
