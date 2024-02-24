package com.c1646njava.tuvivienda.services.implementation;

import com.c1646njava.tuvivienda.DTO.Patcher.Patcher;
import com.c1646njava.tuvivienda.exeptions.PostExceptions.entityCreationException;
import com.c1646njava.tuvivienda.exeptions.PostExceptions.postNotFoundException;
import com.c1646njava.tuvivienda.models.post.Post;
import com.c1646njava.tuvivienda.repositories.PostRepository;
import com.c1646njava.tuvivienda.services.abstraction.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PostServiceI implements PostService {


    private  PostRepository postrepositorio;
    private Patcher patcher;

    public PostServiceI(PostRepository postrepositorio, Patcher patcher) {
        this.postrepositorio = postrepositorio;
        this.patcher = patcher;
    }

    @Override
    public List<Post> searchByLocation(String address) throws postNotFoundException {
        Optional<List<Post>> listaposts = postrepositorio.searchByLocation(address);
        if(listaposts.isPresent()){
            return listaposts.get();
        }else{
            throw new postNotFoundException("No hay publicaciones con la dirección: " + address);

        }
    }

    @Override
    public List<Post> searchByType(String type) throws postNotFoundException {
        Optional<List<Post>> listaposts = postrepositorio.searchByType(type);
        if(listaposts.isPresent()){
            return listaposts.get();
        }else{
            throw new postNotFoundException("No hay publicaciones de tipo: " + type);

        }

    }

    @Override
    public List<Post>  searchByBedrooms(Integer bedrooms1) throws postNotFoundException{
        Optional<List<Post>> listaposts = postrepositorio.searchByBedrooms(bedrooms1);
        if(listaposts.isPresent()){
            return listaposts.get();
        }else{
            throw new postNotFoundException("No hay publicaciones con " + bedrooms1 + " habitaciones");

        }
    }

    @Override
    public List<Post> searchByPrice(Long priceLow, Long PriceHigh) throws postNotFoundException {
        Optional<List<Post>> listaposts = postrepositorio.searchByPrice(priceLow,PriceHigh);
        if(listaposts.isPresent()){
            return listaposts.get();
        }else{
            throw new postNotFoundException("No hay publicaciones dentro de ese rango de precios ");

        }
    }

    public Post crearPost(Post post) throws entityCreationException{
        postrepositorio.save(post);
        Optional<Post> posteo = postrepositorio.findById(post.getId());

        if(posteo.isPresent()){
            return posteo.get();
        }else{
            throw new entityCreationException("La entidad no fue persistida correctamente");
        }
    }

    @Override
    public Post findById(Long id) throws postNotFoundException {
        Optional<Post> posteo = postrepositorio.findById(id);
        if(posteo.isPresent()){
            return posteo.get();
        }else{
            throw new postNotFoundException("No existe un elemento con id: " + id);
        }
    }

    @Override
    public String deleteById(Long id) throws postNotFoundException {
        Optional<Post> posteo = postrepositorio.findById(id);
        if(posteo.isPresent()){
            postrepositorio.deleteById(id);
            return "Post eliminado";
        }else{
            throw new postNotFoundException("No existe un elemento con id: " + id);
        }

    }

    @Override
    public Post putById(Long id, Post post) throws postNotFoundException {
        Optional<Post> posteo = postrepositorio.findById(id);
        if(posteo.isPresent()){
            Post postinner = posteo.get();
            BeanUtils.copyProperties(postinner,post);
            postrepositorio.save(postinner);
            return postinner;
        }else{
            throw new postNotFoundException("No existe un elemento con id: " + id);
        }
    }

    @Override
    public Post patchById(Long id,  Post fields) throws postNotFoundException, IllegalAccessException {
        Optional<Post> posteoOptional = postrepositorio.findById(id);

        if(posteoOptional.isPresent()){
            try {
                Post postExistente = posteoOptional.get();
                Patcher.postPatcher(postExistente, fields);
                postrepositorio.save(postExistente);
                return postExistente;
            }catch(IllegalAccessException e){
                throw new IllegalAccessException("Problema al ejecutar la reflexividad");
            }
        }else{
            throw new postNotFoundException("No existe un post con la id: " + id);

        }


    }
}
