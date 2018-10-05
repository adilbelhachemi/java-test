package com.h2rd.refactoring.userservice;

import com.h2rd.refactoring.bean.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Vector;


/**
 * Created by adil on 05/10/18.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{


    /**
     * {@inheritDoc}
     * @param user.
     */
    public void saveUser(User user) {

    }

    /**
     * {@inheritDoc}
     */
    public Vector<User> getUsers() {
        return null;
    }

    /**
     * {@inheritDoc}
     * @param userToDelete.
     */
    public void deleteUser(User userToDelete) {

    }

    /**
     * {@inheritDoc}
     * @param userToUpdate.
     */
    public void updateUser(User userToUpdate) {

    }

    /**
     * {@inheritDoc}
     * @param name.
     */
    public User findUser(String name) {
        return null;
    }
}
