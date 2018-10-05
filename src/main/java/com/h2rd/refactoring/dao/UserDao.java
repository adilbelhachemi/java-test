package com.h2rd.refactoring.dao;

import com.h2rd.refactoring.bean.User;
import java.util.Vector;

/**
 * Created by adil on 05/10/18.
 */
public interface UserDao{

    /**
     * Save User
     * @param user
     */
    public void saveUser(User user);

    /**
     * Get User
     * @return
     */
    public Vector<User> getUsers();

    /**
     * Delete User
     * @param userToDelete
     */
    public void deleteUser(User userToDelete);

    /**
     * Update User
     * @param userToUpdate
     */
    public void updateUser(User userToUpdate);

    /**
     * Find User
     * @param name
     * @return user
     */
    public User findUser(String name);
}
