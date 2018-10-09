package com.h2rd.refactoring.userservice;

import com.h2rd.refactoring.bean.User;
import com.h2rd.refactoring.dao.UserDaoImpl;
import com.h2rd.refactoring.exceptions.UserAlreadyExistException;
import com.h2rd.refactoring.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Vector;


/**
 * Created by adil on 05/10/18.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDaoImpl dao;


    /**
     * {@inheritDoc}
     */
    public void saveUser(User user) {
        if(dao.findUser(user.getEmail()) != null) {
            throw new UserAlreadyExistException(user.getName());
        }
        dao.saveUser(user);
    }

    /**
     * {@inheritDoc}
     */
    public Vector<User> getUsers() {
        return dao.getUsers();
    }

    /**
     * {@inheritDoc}
     */
    public void deleteUser(User userToDelete) {
        dao.deleteUser(userToDelete);
    }

    /**
     * {@inheritDoc}
     */
    public void updateUser(User userToUpdate) {
        dao.updateUser(userToUpdate);
    }

    /**
     * {@inheritDoc}
     */
    public User findUser(String email) {
        User user = dao.findUser(email);
        if(user == null) {
            throw new UserNotFoundException(email);
        }
        return user;
    }
}
