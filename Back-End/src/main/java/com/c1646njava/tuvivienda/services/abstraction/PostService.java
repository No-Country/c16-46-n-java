package com.c1646njava.tuvivienda.services.abstraction;

import com.c1646njava.tuvivienda.exceptions.PostExceptions.entityCreationException;
import com.c1646njava.tuvivienda.exceptions.PostExceptions.postNotFoundException;
import com.c1646njava.tuvivienda.models.post.DTO.FilterDTO;
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
    List<Post> searchByLocation(String address) throws postNotFoundException;

    /**
     * Searches posts by type.
     *
     * @param type The type of post to search for.
     * @return A list of posts matching the specified type.
     * @throws postNotFoundException If no posts are found for the given type.
     */
    List<Post> searchByType(String type) throws postNotFoundException;

    /**
     * Searches posts by number of bedrooms.
     *
     * @param bedrooms1 The minimum number of bedrooms to search for.
     * @return A list of posts that have at least the specified number of bedrooms.
     * @throws postNotFoundException If no posts are found with the given number of bedrooms.
     */
    List<Post> searchByBedrooms(Integer bedrooms1) throws postNotFoundException;

    /**
     * Searches posts by price range.
     *
     * @param priceLow The minimum price of the range.
     * @param priceHigh The maximum price of the range.
     * @return A list of posts that have a price within the specified range.
     * @throws postNotFoundException If no posts are found within the given price range.
     */
    List<Post> searchByPrice(Long priceLow, Long priceHigh) throws postNotFoundException;


    Page<Post> searchByFilter(List<FilterDTO> filterDtoList, Pageable pageable);


    Post crearPost(Post post) throws entityCreationException ;

    Post findById(Long id) throws postNotFoundException;


    String deleteById(Long id) throws postNotFoundException;

    Post putById(Long id, Post post) throws postNotFoundException;

    Post patchById(Long id,  Post fields) throws postNotFoundException, IllegalAccessException;

    Page<Post> getAll(Pageable pageable);

}
