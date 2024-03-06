package com.c1646njava.tuvivienda.services.abstraction;

import com.c1646njava.tuvivienda.models.administrator.Administrator;
import com.c1646njava.tuvivienda.models.post.Post;
import com.c1646njava.tuvivienda.models.user.User;
import com.c1646njava.tuvivienda.models.user.dto.RequestUser;
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

    void deleteUser(Long userId);

    void updateUser(Long userId, String name, String email, String country);

    void updatePassword(Long userId, String password, String password2) throws AuthenticationException;

    /**
     * This method allows a user to upgrade their account to an administrator account by providing their phone number.
     *
     * @param userId The ID of the user who wants to upgrade their account.
     * @param phoneNumber The phone number of the user.
     * @return A `User` object representing the user upgraded to administrator.
     * @throws AuthenticationException If the user ID is invalid or the user is already an administrator.
     * @throws IllegalArgumentException If the phone number is not in a valid format.
     */
    Administrator upgradeToAdmin(Long userId, String phoneNumber) throws AuthenticationException, IllegalArgumentException;

    /**
     * Toggles the presence of a post in the user's favorites list.
     *
     * @param userId The ID of the user for whom to toggle the favorite.
     * @param postId The ID of the post to toggle as a favorite.
     * @throws IllegalArgumentException If the user or post IDs are invalid.
     */
     void toggleFavorite(Long userId, Long postId);

    /**
     * Adds a post to the user's favorites list and the user to the post's list of users.
     *
     * @param user The user to add the post to their favorites.
     * @param post The post to add to the user's favorites.
     * @throws IllegalArgumentException If the user or post is null.
     */
     void addToFavorites(User user, Post post);

    /**
     * Removes a post from the user's favorites list and the user from the post's list of users.
     *
     * @param user The user from whose favorites to remove the post.
     * @param post The post to remove from the user's favorites.
     * @throws IllegalArgumentException If the user or post is null.
     */
     void removePostFromFavorites(User user, Post post);
}

