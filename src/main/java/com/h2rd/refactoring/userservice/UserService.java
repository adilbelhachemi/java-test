package com.h2rd.refactoring.userservice;

import com.h2rd.refactoring.bean.User;

import java.util.Vector;

/**
 * Created by adil on 05/10/18.
 */
public interface UserService {

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
     * Find user
     * {@inheritDoc}
     * @param name.
     */
    public User findUser(String email);

}
