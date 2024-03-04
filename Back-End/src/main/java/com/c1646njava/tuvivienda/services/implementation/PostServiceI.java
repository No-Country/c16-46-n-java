package com.c1646njava.tuvivienda.services.implementation;

import com.c1646njava.tuvivienda.DTO.Patcher.Patcher;
import com.c1646njava.tuvivienda.exceptions.PostExceptions.entityCreationException;
import com.c1646njava.tuvivienda.exceptions.PostExceptions.postNotFoundException;
import com.c1646njava.tuvivienda.models.post.DTO.*;
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
import java.util.stream.Collectors;

@Service
public class PostServiceI implements PostService {


    private PostRepository postrepository;
    private Patcher patcher;
    private AdministratorRepository administratorRepository;


    public PostServiceI(PostRepository postrepository, Patcher patcher, AdministratorRepository administratorRepository) {
        this.postrepository = postrepository;
        this.patcher = patcher;
        this.administratorRepository = administratorRepository;
    }

    @Override
    public List<postResponse> searchByLocation(String address) throws postNotFoundException {
        List<Post> listaposts = postrepository.searchByLocation(address);
        if(!listaposts.isEmpty()){
            return listaposts.stream()
                    .map(this::convertToPostResponse)
                    .collect(Collectors.toList());
        }else{
            throw new postNotFoundException("There are no posts with the address: " + address);
        }

    }

    @Override
    public List<postResponse> searchByType(String type) throws postNotFoundException {
        List<Post> listaposts = postrepository.searchByType(type);
        if(!listaposts.isEmpty()){
            return listaposts.stream()
                    .map(this::convertToPostResponse)
                    .collect(Collectors.toList());
        }else{
            throw new postNotFoundException("There are no posts of type: " + type);

        }

    }

    @Override
    public List<postResponse> searchByBedrooms(Integer bedrooms1) throws postNotFoundException{
        List<Post> listaposts = postrepository.searchByBedrooms(bedrooms1);
        if(!listaposts.isEmpty()){
            return listaposts.stream()
                    .map(this::convertToPostResponse)
                    .collect(Collectors.toList());
        }else{
            throw new postNotFoundException("There are no publications with " + bedrooms1 + " bedrooms");

        }
    }

    @Override
    public List<postResponse> searchByPrice(Long priceLow, Long PriceHigh) throws postNotFoundException {
        List<Post> listaposts = postrepository.searchByPrice(priceLow,PriceHigh);
        if(!listaposts.isEmpty()){
            return listaposts.stream()
                    .map(this::convertToPostResponse)
                    .collect(Collectors.toList());
        }else{
            throw new postNotFoundException("There are no listings within this price range ");

        }
    }

    @Override
    public Page<postResponse> searchByFilter(List<FilterDTO> filterDtoList, Pageable pageable){
        Page<Post> pagePost = postrepository.findAll(PostSpecification.columnEqual(filterDtoList), pageable);
        return pagePost.map(this::convertToPostResponse);
    }

    @Override
    public Page<postResponse> getAll(Pageable pageable){
        Page<Post> pagePost = postrepository.findAll(pageable);
        return pagePost.map(this::convertToPostResponse);
    }


    private postResponse convertToPostResponse(Post post) {
        postResponse postr = new postResponse();
        BeanUtils.copyProperties(post, postr);
        postr.setAdministrator_id(post.getAdministrator().getId());
        return postr;
    }

    public postResponse crearPost(postRequest post) throws entityCreationException{
        Post postc = new Post();
        BeanUtils.copyProperties(post,postc,"adminId");
        postc.setAdministrator(administratorRepository.findById(post.adminId()).get());
        postc.setFav(null);
        Post postv = postrepository.save(postc);

        if(postv == null){
            throw new entityCreationException("The post was not save correctly");
        }else{
            postResponse postr = new postResponse();
            BeanUtils.copyProperties(postv, postr);
            postr.setAdministrator_id(postv.getAdministrator().getId());
            return postr;
        }

    }

    @Override
    public postResponse findById(Long id) throws postNotFoundException {
        Optional<Post> posteo = postrepository.findById(id);
        if(posteo.isPresent()){
            postResponse postr = new postResponse();
            BeanUtils.copyProperties(posteo.get(), postr);
            postr.setAdministrator_id(posteo.get().getAdministrator().getId());

            return postr;
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
    public postResponse putById(Long id, Post post) throws postNotFoundException {
        Optional<Post> posteo = postrepository.findById(id);
        if(posteo.isPresent()){
            Post postinner = posteo.get();
            BeanUtils.copyProperties(post,postinner, "id");
            postrepository.save(postinner);
            postResponse postr = new postResponse();
            BeanUtils.copyProperties(postinner, postr);
            postr.setAdministrator_id(postinner.getAdministrator().getId());

            return postr;
        }else{
            throw new postNotFoundException("there isn't a post with the id: " + id);
        }
    }

    @Override
    public postResponse patchById(Long id, Post fields) throws postNotFoundException, IllegalAccessException {
        Optional<Post> posteoOptional = postrepository.findById(id);

        if(posteoOptional.isPresent()){
            try {
                Post postExistente = posteoOptional.get();
                Patcher.postPatcher(postExistente, fields);
                postrepository.save(postExistente);
                postResponse postr = new postResponse();
                BeanUtils.copyProperties(postExistente, postr);
                postr.setAdministrator_id(postExistente.getAdministrator().getId());

            }catch(IllegalAccessException e){
                throw new IllegalAccessException("Issue trying to execute reflexivity in Post class");
            }
        }else{
            throw new postNotFoundException("there isn't a post with the id: " + id);

        }
        return null;
    }



    @Override
    public String advertisePost(Long postId) throws postNotFoundException {
        Optional<Post> post = postrepository.findById(postId);
        if(post.isPresent()){
            post.get().setFeatured(1);
            postrepository.save(post.get());
            return "The post was featured";
        }else{
            throw new postNotFoundException("There isn't a post with the indicate id");
        }
    }


}