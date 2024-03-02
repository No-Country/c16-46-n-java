package com.c1646njava.tuvivienda.services.implementation;

import com.c1646njava.tuvivienda.DTO.Patcher.Patcher;
import com.c1646njava.tuvivienda.exceptions.PostExceptions.entityCreationException;
import com.c1646njava.tuvivienda.exceptions.PostExceptions.postNotFoundException;
import com.c1646njava.tuvivienda.models.post.DTO.FilterDTO;
import com.c1646njava.tuvivienda.models.post.DTO.PostSpecification;
import com.c1646njava.tuvivienda.models.post.DTO.postRequest;
import com.c1646njava.tuvivienda.models.post.Post;
import com.c1646njava.tuvivienda.repositories.AdministratorRepository;
import com.c1646njava.tuvivienda.repositories.PostRepository;
import com.c1646njava.tuvivienda.services.abstraction.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceI implements PostService {


    private PostRepository postrepository;
    private Patcher patcher;
    private AdministratorRepository administratorRepository;

    public PostServiceI(PostRepository postrepository, Patcher patcher) {
        this.postrepository = postrepository;
        this.patcher = patcher;
    }

    @Override
    public List<Post> searchByLocation(String address) throws postNotFoundException {
        List<Post> listaposts = postrepository.searchByLocation(address);
        if(!listaposts.isEmpty()){
            return listaposts;
        }else{
            throw new postNotFoundException("There are no posts with the address: " + address);

        }
    }

    @Override
    public List<Post> searchByType(String type) throws postNotFoundException {
        List<Post> listaposts = postrepository.searchByType(type);
        if(!listaposts.isEmpty()){
            return listaposts;
        }else{
            throw new postNotFoundException("There are no posts of type: " + type);

        }

    }

    @Override
    public List<Post>  searchByBedrooms(Integer bedrooms1) throws postNotFoundException{
        List<Post> listaposts = postrepository.searchByBedrooms(bedrooms1);
        if(!listaposts.isEmpty()){
            return listaposts;
        }else{
            throw new postNotFoundException("There are no publications with " + bedrooms1 + " habitaciones");

        }
    }

    @Override
    public List<Post> searchByPrice(Long priceLow, Long PriceHigh) throws postNotFoundException {
        List<Post> listaposts = postrepository.searchByPrice(priceLow,PriceHigh);
        if(!listaposts.isEmpty()){
            return listaposts;
        }else{
            throw new postNotFoundException("There are no listings within this price range ");

        }
    }



    public Page<Post> searchByFilter(List<FilterDTO> filterDtoList, Pageable pageable){
        return postrepository.findAll(PostSpecification.columnEqual(filterDtoList),pageable);

    }

    public Post crearPost(postRequest post) throws entityCreationException{
        Post postc = new Post();
        BeanUtils.copyProperties(post,postc,"adminId");
        postc.setAdministrator(administratorRepository.findById(post.adminId()).get());
        postc.setFav(null);
        Post postv = postrepository.save(postc);

        if(postv == null){
            throw new entityCreationException("The post was not save correctly");
        }else{
            return postv;

        }

    }

    @Override
    public Post findById(Long id) throws postNotFoundException {
        Optional<Post> posteo = postrepository.findById(id);
        if(posteo.isPresent()){
            return posteo.get();
        }else{
            throw new postNotFoundException("there isn't a post with the id: " + id);
        }
    }

    @Override
    public String deleteById(Long id) throws postNotFoundException {
        Optional<Post> posteo = postrepository.findById(id);
        if(posteo.isPresent()){
            postrepository.deleteById(id);
            return "Post eliminado";
        }else{
            throw new postNotFoundException("there isn't a post with the id: " + id);
        }

    }

    @Override
    public Post putById(Long id, Post post) throws postNotFoundException {
        Optional<Post> posteo = postrepository.findById(id);
        if(posteo.isPresent()){
            Post postinner = posteo.get();
            BeanUtils.copyProperties(post,postinner, "id");
            postrepository.save(post);
            return post;
        }else{
            throw new postNotFoundException("there isn't a post with the id: " + id);
        }
    }

    @Override
    public Post patchById(Long id,  Post fields) throws postNotFoundException, IllegalAccessException {
        Optional<Post> posteoOptional = postrepository.findById(id);

        if(posteoOptional.isPresent()){
            try {
                Post postExistente = posteoOptional.get();
                Patcher.postPatcher(postExistente, fields);
                postrepository.save(postExistente);
                return postExistente;
            }catch(IllegalAccessException e){
                throw new IllegalAccessException("Issue trying to execute reflexivity in Post class");
            }
        }else{
            throw new postNotFoundException("there isn't a post with the id: " + id);

        }
    }

    @Override
    public Page<Post> getAll( Pageable pageable){
        return postrepository.findAll(pageable);
    }











}