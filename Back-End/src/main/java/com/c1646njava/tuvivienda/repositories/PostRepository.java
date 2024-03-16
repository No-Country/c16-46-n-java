package com.c1646njava.tuvivienda.repositories;

import com.c1646njava.tuvivienda.models.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {


    @Query("SELECT p FROM Post p WHERE p.address = LOWER(:address1)")
    List<Post> searchByLocation(String address1);

    @Query("SELECT p FROM Post p WHERE p.type = LOWER(:type1)")
    List<Post>  searchByType(String type1);
    @Query("SELECT p FROM Post p WHERE p.bedrooms = :bedrooms1")
    List<Post>  searchByBedrooms(Integer bedrooms1);

    @Query("SELECT p FROM Post p WHERE p.price >= :priceLow AND p.price <= :PriceHigh")
    List<Post>  searchByPrice(Long priceLow, Long PriceHigh);

    List<Post> findByFeaturedEquals(int featured);

    @Query("SELECT p FROM Post p WHERE p.administrator.id = :administratorId")
    List<Post> findAllPostByAdministrator(Long administratorId);
}


