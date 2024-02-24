package com.c1646njava.tuvivienda.services.abstraction;

import java.util.List;

import com.c1646njava.tuvivienda.models.post.Post;

/**
 * This interface defines operations available for user management and real estate listings search.
 */
public interface SystemService {

    /**
     * Registers a new user in the system.
     *
     * @param name     The user's name.
     * @param password The user's password.
     * @param email    The user's email address.
     * @return         The unique ID assigned to the newly registered user.
     * @throws IllegalArgumentException If any of the parameters are invalid or already in use.
     */
    Long registerUser(String name, String password, String email) throws IllegalArgumentException;

    /**
     * Allows a user to log in to the system.
     *
     * @param email    The user's email address.
     * @param password The user's password.
     * @return         True if the login was successful, false otherwise.
     * @throws IllegalArgumentException If the provided credentials are incorrect.
     */
    Boolean loginUser(String email, String password) throws IllegalArgumentException;

    /**
     * Logs out the current user.
     *
     * @return True if the logout was successful, false otherwise.
     */
    Boolean logoutUser();

    /**
     * Checks if there is an active session for the current user.
     *
     * @return True if there is an active session, false otherwise.
     */
    Boolean checkSession();

    /**
     * Searches for real estate listings by location using an address.
     *
     * @param address The location address to search for.
     * @return        A list of real estate listings found at the specified location.
     */
    List<Post> searchByLocation(String address);

    /**
     * Searches for real estate listings by type (e.g., house, apartment, etc.).
     *
     * @param type The type of property to search for.
     * @return     A list of real estate listings of the specified type.
     */
    List<Post> searchByType(String type);

    /**
     * Searches for real estate listings by number of bedrooms.
     *
     * @param bedrooms The desired number of bedrooms.
     * @return         A list of real estate listings with the specified number of bedrooms.
     * @throws IllegalArgumentException If the provided number of bedrooms is invalid.
     */
    List<Post> searchByBedrooms(Integer bedrooms) throws IllegalArgumentException;

    /**
     * Searches for real estate listings within a specified price range.
     *
     * @param priceLow  The minimum price of the range.
     * @param priceHigh The maximum price of the range.
     * @return          A list of real estate listings within the specified price range.
     * @throws IllegalArgumentException If the provided price range is invalid.
     */
    List<Post> searchByPrice(Long priceLow, Long priceHigh) throws IllegalArgumentException;
}

