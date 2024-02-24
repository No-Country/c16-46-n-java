package com.c1646njava.tuvivienda.services.abstraction;

import com.c1646njava.tuvivienda.models.image.Image;
import com.c1646njava.tuvivienda.models.image.ImagePost;
import com.c1646njava.tuvivienda.models.image.ImageUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public interface ImageService {
    List<Image> list();

    void delete(Long id);
    boolean exists(Long id);
}
