package com.c1646njava.tuvivienda.repositories;

import com.c1646njava.tuvivienda.models.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.address = LOWER(:address1)")
    Optional<List<Post>> searchByLocation(String address1);

    @Query("SELECT p FROM Post p WHERE p.type = LOWER(:type1)")
    Optional<List<Post>>  searchByType(String type1);
    @Query("SELECT p FROM Post p WHERE p.bedrooms = :bedrooms1")
    Optional<List<Post>>  searchByBedrooms(Integer bedrooms1);

    @Query("SELECT p FROM Post p WHERE p.price >= :priceLow AND p.price <= :PriceHigh")
    Optional<List<Post>>  searchByPrice(Long priceLow, Long PriceHigh);
}