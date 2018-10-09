package com.h2rd.refactoring.exceptions;

/**
 * Created by adil on 09/10/18.
 */
public class UserNotFoundException extends RuntimeException{


    public UserNotFoundException(String email) {
        super("No User found with this email: "+ email);
    }
}
