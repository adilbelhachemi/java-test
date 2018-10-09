package com.h2rd.refactoring.exceptions;

/**
 * Created by adil on 09/10/18.
 */
public class UserAlreadyExistException extends RuntimeException{


    public UserAlreadyExistException(String name) {
        super("The user : "+ name + " does already exist");
    }
}
