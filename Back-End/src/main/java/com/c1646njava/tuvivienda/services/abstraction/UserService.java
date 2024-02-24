package com.c1646njava.tuvivienda.services.abstraction;

import com.c1646njava.tuvivienda.exeptions.MyException;
import com.c1646njava.tuvivienda.models.user.User;
import com.c1646njava.tuvivienda.models.user.dto.RequestUser;
import com.c1646njava.tuvivienda.models.user.dto.ResponseUser;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
public interface UserService {

    /**
     * This method is for user registration.
     *
     * @param requestUser The user information to be registered.
     * @return The registered user.
     * @throws IllegalArgumentException If any of the validation checks fail. See validateUserRequest
     */
    User registerUser(RequestUser requestUser);

    /**
     * Validates the attributes of the RequestUser object.
     *
     * @param requestUser The user information to be validated.
     * @throws IllegalArgumentException If any of the following conditions are met:
     *                                  - The user's name is blank.
     *                                  - The password is blank or has less than 4 characters.
     *                                  - The passwords entered do not match.
     *                                  - The email is blank or already in use.
     *                                  - The country is blank.
     */
    void validateUserRequest(RequestUser requestUser);

    /**
     * This method logs in a user.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The logged-in user.
     * @throws AuthenticationException If the login fails due to invalid credentials.
     */
    User loginUser(String email, String password) throws AuthenticationException;

    /**
     * This method is to exit the session.
     *
     * @return true if the user is successfully logged out, otherwise false.
     */
    Boolean logoutUser();

    /**
     * This method is to check the user session.
     *
     * @return true if the user session is active, otherwise false.
     */
    Boolean checkSession();
}

