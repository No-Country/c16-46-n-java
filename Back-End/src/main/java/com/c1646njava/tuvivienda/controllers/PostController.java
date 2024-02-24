package com.c1646njava.tuvivienda.controllers;

import com.c1646njava.tuvivienda.controllers.Abstraction.PostControllerA;
import com.c1646njava.tuvivienda.exeptions.PostExceptions.entityCreationException;
import com.c1646njava.tuvivienda.exeptions.PostExceptions.postNotFoundException;
import com.c1646njava.tuvivienda.models.post.Post;
import com.c1646njava.tuvivienda.services.abstraction.PostService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController implements PostControllerA {

    private PostService postservicio;

    public PostController(PostService postservicio) {
        this.postservicio = postservicio;

    }


    @GetMapping("/findById/{id}")//*
    public ResponseEntity<Post> getById(@PathVariable("id") Long id) throws postNotFoundException {
        return ResponseEntity.ok(postservicio.findById(id));
    }


    @Override
    @PutMapping("/actualizarAll/{id}")
    public ResponseEntity<Post> putById(@PathVariable("id") Long id, @Valid @RequestBody Post post) throws postNotFoundException, MethodArgumentNotValidException {
        return ResponseEntity.ok(postservicio.putById(id,post));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id) throws postNotFoundException {
        return ResponseEntity.ok(postservicio.deleteById(id));
    }

    @Override
    @PatchMapping("/actualizarCampos/{id}")
    public ResponseEntity<Post> patchPost(@PathVariable Long id, @Valid @RequestBody Post fields) throws postNotFoundException, MethodArgumentNotValidException, IllegalAccessException {
        return ResponseEntity.ok(postservicio.patchById(id, fields));
    }

    @Override
    @GetMapping("/findByName/{address}")
    public ResponseEntity<List<Post>> searchByLocation(@PathVariable("address") String address) throws postNotFoundException{
           return ResponseEntity.ok(postservicio.searchByLocation(address));
    }
    @Override
    @GetMapping("/findByType/{type}")
    public ResponseEntity<List<Post>> searchByType(@PathVariable("type") String type) throws postNotFoundException{
            return ResponseEntity.ok(postservicio.searchByType(type));
    }
    @Override
    @GetMapping("/findByBedrooms/{bedrooms}")
    public ResponseEntity<List<Post>> searchByBedrooms(@PathVariable("bedrooms") Integer bedrooms) throws postNotFoundException{
            return ResponseEntity.ok(postservicio.searchByBedrooms(bedrooms));
    }
    @Override
    @GetMapping("/findByPrice")
    public ResponseEntity<List<Post>>  searchByPrice(@RequestParam(name = "lowprice") Long lowprice, @RequestParam(name = "highprice") Long highprice)
            throws postNotFoundException
    {
        return ResponseEntity.ok(postservicio.searchByPrice(lowprice, highprice));

    }
    @Override
    @PostMapping("/create") //*
    public ResponseEntity<Post> createPost( @Valid @RequestBody Post post) throws entityCreationException, MethodArgumentNotValidException{
        return ResponseEntity.ok(postservicio.crearPost(post));

    }




}
