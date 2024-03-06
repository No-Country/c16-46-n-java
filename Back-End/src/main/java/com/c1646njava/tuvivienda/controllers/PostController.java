package com.c1646njava.tuvivienda.controllers;

import com.c1646njava.tuvivienda.controllers.Abstraction.PostControllerA;
import com.c1646njava.tuvivienda.exceptions.PostExceptions.entityCreationException;
import com.c1646njava.tuvivienda.exceptions.PostExceptions.postNotFoundException;
import com.c1646njava.tuvivienda.models.post.DTO.FilterDTO;
import com.c1646njava.tuvivienda.models.post.Post;
import com.c1646njava.tuvivienda.services.abstraction.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin
public class PostController implements PostControllerA {

    private PostService postservice;

    public PostController(PostService postservice) {
        this.postservice = postservice;

    }


    @Override
    @GetMapping("/findByName/{address}")
    public ResponseEntity<List<Post>> searchByLocation(@PathVariable("address") String address) throws postNotFoundException{
        return ResponseEntity.ok(postservice.searchByLocation(address));
    }
    @Override
    @GetMapping("/findByType/{type}")
    public ResponseEntity<List<Post>> searchByType(@PathVariable("type") String type) throws postNotFoundException{
        return ResponseEntity.ok(postservice.searchByType(type));
    }
    @Override
    @GetMapping("/findByBedrooms/{bedrooms}")
    public ResponseEntity<List<Post>> searchByBedrooms(@PathVariable("bedrooms") Integer bedrooms) throws postNotFoundException{
        return ResponseEntity.ok(postservice.searchByBedrooms(bedrooms));
    }
    @Override
    @GetMapping("/findByPrice")
    public ResponseEntity<List<Post>>  searchByPrice(@RequestParam(name = "lowprice") Long lowprice, @RequestParam(name = "highprice") Long highprice)
            throws postNotFoundException
    {
        return ResponseEntity.ok(postservice.searchByPrice(lowprice, highprice));

    }


    @Override
    @GetMapping("/searchByFilter")
    public ResponseEntity<Page<Post>> getByFilter(
            @RequestBody List<FilterDTO> filterDTOList, // List of filters provided in the request body
            @SortDefault(sort = "id", direction = Sort.Direction.DESC) // Default sorting by ID in descending order
            @PageableDefault(page = 0, size = 10) Pageable pageable // Default pagination: page 0, page size 10
    ) throws postNotFoundException {
        // Call the service method to search for posts using the provided filters and pagination
        Page<Post> posts = postservice.searchByFilter(filterDTOList, pageable);
        // Check if posts are found
        if (!posts.isEmpty()) {
            // If posts are found, return a response with the posts and an OK status
            return ResponseEntity.ok(posts);
        } else {
            // If no posts are found, throw a postNotFoundException
            throw new postNotFoundException("There isn't a post with the indicated filters");
        }
    }


    @GetMapping("/findById/{id}")//*
    public ResponseEntity<Post> getById(@PathVariable("id") Long id) throws postNotFoundException {
        return ResponseEntity.ok(postservice.findById(id));
    }

    @Override
    @PutMapping("/updateAll/{id}")
    public ResponseEntity<Post> putById(@PathVariable("id") Long id, @Valid @RequestBody Post post) throws postNotFoundException, MethodArgumentNotValidException {
        return ResponseEntity.ok(postservice.putById(id,post));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id) throws postNotFoundException {
        return ResponseEntity.ok(postservice.deleteById(id));
    }

    @Override
    @PatchMapping("/updateFields/{id}")
    public ResponseEntity<Post> patchPost(@PathVariable Long id, @Valid @RequestBody Post fields) throws postNotFoundException, MethodArgumentNotValidException, IllegalAccessException {
        return ResponseEntity.ok(postservice.patchById(id, fields));
    }



    @Override
    @PostMapping("/create") //*
    public ResponseEntity<Post> createPost( @Valid @RequestBody Post post) throws entityCreationException, MethodArgumentNotValidException{
        return ResponseEntity.ok(postservice.crearPost(post));

    }

    @GetMapping("/getall")
    public ResponseEntity<Page<Post>> getAll(@SortDefault(sort = "id", direction = Sort.Direction.DESC)  @PageableDefault(page = 0, size = 10) Pageable pageable)  {
        return ResponseEntity.ok(postservice.getAll(pageable));
    }

    @GetMapping("/getAllByAdm/{admId}")
    public ResponseEntity<?> getAllByAdm(@PathVariable Long admId){
        return  ResponseEntity.ok(postservice.findAllPostByAdministrator(admId));
    }










}