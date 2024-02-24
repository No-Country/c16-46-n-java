package com.c1646njava.tuvivienda.repositories;

import com.c1646njava.tuvivienda.models.image.ImagePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagePostRepository extends JpaRepository<ImagePost,Long> {
}
