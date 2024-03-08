package com.c1646njava.tuvivienda.controllers;

import com.c1646njava.tuvivienda.models.user.exceptions.PostExceptions.entityCreationException;
import com.c1646njava.tuvivienda.models.user.exceptions.PostExceptions.noTokenException;
import com.c1646njava.tuvivienda.models.user.exceptions.PostExceptions.postNotFoundException;
import com.c1646njava.tuvivienda.models.post.DTO.FilterDTO;
import com.c1646njava.tuvivienda.models.post.DTO.postRequest;
import com.c1646njava.tuvivienda.models.post.DTO.postResponse;
import com.c1646njava.tuvivienda.models.post.Post;
import com.c1646njava.tuvivienda.services.abstraction.PostService;
import com.c1646njava.tuvivienda.services.implementation.PostServiceI;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PostController {

    private PostService postservice;

    public PostController(PostService postservice) {
        this.postservice = postservice;

    }



    @GetMapping("/findByName/{address}")
    public ResponseEntity<List<postResponse>> searchByLocation(@PathVariable("address") String address) throws postNotFoundException{
        return ResponseEntity.ok(postservice.searchByLocation(address));
    }

    @PatchMapping("/advertise/{postId}")
    public ResponseEntity<String> advertisePost(@PathVariable("postId") Long postId) throws postNotFoundException, noTokenException {
        return ResponseEntity.ok(postservice.advertisePost(postId));
    }

    @GetMapping("/findByType/{type}")
    public ResponseEntity<List<postResponse>> searchByType(@PathVariable("type") String type) throws postNotFoundException{
        return ResponseEntity.ok(postservice.searchByType(type));
    }

    @GetMapping("/findByBedrooms/{bedrooms}")
    public ResponseEntity<List<postResponse>> searchByBedrooms(@PathVariable("bedrooms") Integer bedrooms) throws postNotFoundException{
        return ResponseEntity.ok(postservice.searchByBedrooms(bedrooms));
    }

    @GetMapping("/findByPrice")
    public ResponseEntity<List<postResponse>>  searchByPrice(@RequestParam(name = "lowprice") Long lowprice, @RequestParam(name = "highprice") Long highprice)
            throws postNotFoundException
    {
        return ResponseEntity.ok(postservice.searchByPrice(lowprice, highprice));

    }



    @GetMapping("/searchByFilter")
    public ResponseEntity<Page<postResponse>> getByFilter(
            @RequestBody List<FilterDTO> filterDTOList, // List of filters provided in the request body
            @SortDefault(sort = "featured", direction = Sort.Direction.DESC) // Default sorting by ID in descending order
            @PageableDefault(page = 0, size = 10) Pageable pageable // Default pagination: page 0, page size 10
    ) throws postNotFoundException {
        // Call the service method to search for posts using the provided filters and pagination
        Page<postResponse> posts = postservice.searchByFilter(filterDTOList, pageable);
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
    public ResponseEntity<postResponse> getById(@PathVariable("id") Long id) throws postNotFoundException {
        return ResponseEntity.ok(postservice.findById(id));
    }


    @PutMapping("/updateAll/{id}")
    public ResponseEntity<postResponse> putById(@PathVariable("id") Long id, @Valid @RequestBody Post post) throws postNotFoundException, MethodArgumentNotValidException {
        return ResponseEntity.ok(postservice.putById(id,post));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id) throws postNotFoundException {
        return ResponseEntity.ok(postservice.deleteById(id));
    }


    @PatchMapping("/updateFields/{id}")
    public ResponseEntity<postResponse> patchPost(@PathVariable Long id, @Valid @RequestBody Post fields) throws postNotFoundException, MethodArgumentNotValidException, IllegalAccessException {
        return ResponseEntity.ok(postservice.patchById(id, fields));
    }




    @PostMapping("/create") //*
    public ResponseEntity<postResponse> createPost(@Valid @RequestBody postRequest post) throws entityCreationException, MethodArgumentNotValidException{
        return ResponseEntity.ok(postservice.crearPost(post));

    }

    @GetMapping("/getall")
    public ResponseEntity<Page<postResponse>> getAll(@SortDefault(sort = "featured", direction = Sort.Direction.DESC)  @PageableDefault(page = 0, size = 10) Pageable pageable)  {
        return ResponseEntity.ok(postservice.getAll(pageable));
    }
    @GetMapping("/getAllByAdm/{admId}")
    public ResponseEntity<?> getAllByAdm(@PathVariable Long admId){
        return  ResponseEntity.ok(postservice.findAllPostByAdministrator(admId));
    }










}