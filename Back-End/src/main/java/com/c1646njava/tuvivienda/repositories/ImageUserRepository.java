package com.c1646njava.tuvivienda.repositories;


import com.c1646njava.tuvivienda.models.image.ImageUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageUserRepository extends JpaRepository<ImageUser,Long> {
}
