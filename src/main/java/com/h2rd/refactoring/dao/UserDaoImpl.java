package com.h2rd.refactoring.dao;

import com.h2rd.refactoring.bean.User;
import org.springframework.stereotype.Repository;

import java.util.Vector;

@Repository
public class UserDaoImpl implements UserDao {

    public static  Vector<User> users;

    static {
        users = (users == null) ? new Vector<User>() : users;
    }

    /**
     * {@inheritDoc}
     * @param user.
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
     * @param userToDelete.
     */
    public void deleteUser(User userToDelete) {
        User user = findUser(userToDelete.getName());
        if (user != null) {
            users.remove(user);
        }
    }

    /**
     * {@inheritDoc}
     * @param userToUpdate.
     */
    public void updateUser(User userToUpdate) {
        User user = findUser(userToUpdate.getName());
        if (user != null) {
            user.setEmail(userToUpdate.getEmail());
            user.setRoles(userToUpdate.getRoles());
        }
    }

    /**
     * {@inheritDoc}
     * @param name.
     */
    public User findUser(String name) {
        try {
            for (User user : users) {
                if (user.getName() == name) {
                    return user;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

}
