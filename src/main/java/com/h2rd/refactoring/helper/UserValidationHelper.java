package com.h2rd.refactoring.helper;

import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by adil on 08/10/18.
 */
public class UserValidationHelper {


    public static boolean validateUser(String name, String email, List roles) {
        return !StringUtils.isEmpty(name) && !StringUtils.isEmpty(email) && roles != null && !roles.isEmpty();
    }
}
