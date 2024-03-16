package com.c1646njava.tuvivienda.services.abstraction;

import com.c1646njava.tuvivienda.models.user.exceptions.PostExceptions.entityCreationException;
import com.c1646njava.tuvivienda.models.user.exceptions.PostExceptions.noTokenException;
import com.c1646njava.tuvivienda.models.user.exceptions.PostExceptions.postNotFoundException;
import com.c1646njava.tuvivienda.models.post.DTO.FilterDTO;
import com.c1646njava.tuvivienda.models.post.DTO.postRequest;
import com.c1646njava.tuvivienda.models.post.DTO.postResponse;
import com.c1646njava.tuvivienda.models.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    /**
     * Searches posts by location.
     *
     * @param address The address to search posts for.
     * @return A list of posts matching the specified address.
     * @throws postNotFoundException If no posts are found for the given address.
     */
    List<postResponse> searchByLocation(String address) throws postNotFoundException;

    /**
     * Searches posts by type.
     *
     * @param type The type of post to search for.
     * @return A list of posts matching the specified type.
     * @throws postNotFoundException If no posts are found for the given type.
     */
    List<postResponse> searchByType(String type) throws postNotFoundException;

    /**
     * Searches posts by number of bedrooms.
     *
     * @param bedrooms1 The minimum number of bedrooms to search for.
     * @return A list of posts that have at least the specified number of bedrooms.
     * @throws postNotFoundException If no posts are found with the given number of bedrooms.
     */
    List<postResponse> searchByBedrooms(Integer bedrooms1) throws postNotFoundException;

    /**
     * Searches posts by price range.
     *
     * @param priceLow  The minimum price of the range.
     * @param priceHigh The maximum price of the range.
     * @return A list of posts that have a price within the specified range.
     * @throws postNotFoundException If no posts are found within the given price range.
     */
    List<postResponse> searchByPrice(Long priceLow, Long priceHigh) throws postNotFoundException;


    /**
     * Searches posts based on the provided filters with pagination.
     *
     * @param filterDtoList The list of filter DTOs to apply.
     * @param pageable      The pagination information.
     * @return A page of posts that match the specified filters.
     */
    Page<postResponse> searchByFilter(List<FilterDTO> filterDtoList, Pageable pageable);

    /**
     * Creates a new post.
     *
     * @param post The post object to be created, JSON Object.
     * @return The created post.
     * @throws entityCreationException If the entity was not persisted correctly.
     */
    postResponse crearPost(postRequest post) throws entityCreationException ;

    /**
     * Finds a post by its ID.
     *
     * @param id The ID of the post to find.
     * @return The post with the specified ID.
     * @throws postNotFoundException If no post is found with the given ID.
     */
    postResponse findById(Long id) throws postNotFoundException;

    /**
     * Deletes a post by its ID.
     *
     * @param id The ID of the post to delete.
     * @return A message confirming the deletion.
     * @throws postNotFoundException If no post is found with the given ID.
     */
    String deleteById(Long id) throws postNotFoundException;

    /**
     * Updates a post with the specified ID.
     *
     * @param id   The ID of the post to update.
     * @param post The updated post object.
     * @return The updated post.
     * @throws postNotFoundException If no post is found with the given ID.
     */
    postResponse putById(Long id, Post post) throws postNotFoundException;

    /**
     * Partially updates a post with the specified ID using the provided fields.
     *
     * @param id     The ID of the post to update.
     * @param fields The fields to be updated.
     * @return The updated post.
     * @throws postNotFoundException  If no post is found with the given ID.
     * @throws IllegalAccessException If there's an illegal access exception during the update process.
     */
    postResponse patchById(Long id, Post fields) throws postNotFoundException, IllegalAccessException;

    /**
     * Retrieves all posts with pagination.
     *
     * @param pageable The pagination information (size, page number, sort).
     * @return A page of posts.
     */
    Page<postResponse> getAll(Pageable pageable);

    String advertisePost(Long postId) throws postNotFoundException, noTokenException;

    List<Post> getAllFeaturedPost();

    List<Post> findAllPostByAdministrator(Long administratorId);


}
