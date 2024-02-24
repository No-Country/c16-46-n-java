package com.c1646njava.tuvivienda.controllers.Abstraction;

import com.c1646njava.tuvivienda.exeptions.PostExceptions.entityCreationException;
import com.c1646njava.tuvivienda.exeptions.PostExceptions.postNotFoundException;
import com.c1646njava.tuvivienda.models.post.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

public interface PostControllerA {

    public ResponseEntity<List<Post>> searchByLocation(@PathVariable("address") String address) throws postNotFoundException;
    public ResponseEntity<List<Post>>searchByType(@PathVariable("type") String type) throws postNotFoundException;
    public ResponseEntity<List<Post>>  searchByBedrooms(@PathVariable("bedrooms") Integer bedrooms)throws postNotFoundException;
    public ResponseEntity<List<Post>>  searchByPrice(@RequestParam(name = "lowprice") Long lowprice, @RequestParam(name = "highprice") Long highprice) throws postNotFoundException;

    public ResponseEntity<Post> createPost( @Valid @RequestBody Post post) throws entityCreationException, MethodArgumentNotValidException; //cambiar response x post
    public ResponseEntity<Post> getById(@PathVariable("id") Long id) throws postNotFoundException;

    public ResponseEntity<Post> putById(@PathVariable("id") Long id, @Valid @RequestBody Post post)  throws postNotFoundException, MethodArgumentNotValidException;

    public ResponseEntity<String> deletePost(@PathVariable("id") Long id) throws postNotFoundException;

    public ResponseEntity<Post> patchPost(@PathVariable("id") Long id, @Valid @RequestBody Post fields) throws postNotFoundException, MethodArgumentNotValidException, IllegalAccessException;


}
