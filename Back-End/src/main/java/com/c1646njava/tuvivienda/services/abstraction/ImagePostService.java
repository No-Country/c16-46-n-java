package com.c1646njava.tuvivienda.services.abstraction;

import com.c1646njava.tuvivienda.models.image.ImagePost;

import java.util.List;
import java.util.Optional;

public interface ImagePostService {

    List<ImagePost> findImagesByPost(Long id);
}
