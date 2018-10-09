package com.h2rd.refactoring.helper;

import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by adil on 08/10/18.
 *
 * This class contains only static functions
 * That the controller needs to verify/validate
 * all parameters that were received from client side
 */
public class UserValidationHelper {

    /**
     * verify all user data
     *
     * @param name
     * @param email
     * @param roles
     * @return
     */
    public static boolean validateUser(String name, String email, List roles) {
        return !StringUtils.isEmpty(name) && !StringUtils.isEmpty(email) && roles != null && !roles.isEmpty();
    }
}
