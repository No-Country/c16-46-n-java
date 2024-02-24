package com.c1646njava.tuvivienda.services.abstraction;

import com.c1646njava.tuvivienda.exeptions.PostExceptions.entityCreationException;
import com.c1646njava.tuvivienda.exeptions.PostExceptions.postNotFoundException;
import com.c1646njava.tuvivienda.models.post.Post;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public interface PostService {

    List<Post> searchByLocation(String address) throws postNotFoundException;

    List<Post>searchByType(String type) throws postNotFoundException;
    List<Post> searchByBedrooms(Integer bedrooms1) throws postNotFoundException;
    List<Post> searchByPrice(Long priceLow,Long PriceHigh) throws postNotFoundException;
    Post crearPost(Post post) throws entityCreationException ;

    Post findById(Long id) throws postNotFoundException;


    String deleteById(Long id) throws postNotFoundException;

    Post putById(Long id, Post post) throws postNotFoundException;

    Post patchById(Long id,  Post fields) throws postNotFoundException, IllegalAccessException;



}
