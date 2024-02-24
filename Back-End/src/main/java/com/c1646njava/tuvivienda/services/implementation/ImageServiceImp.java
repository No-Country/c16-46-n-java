package com.c1646njava.tuvivienda.services.implementation;


import com.c1646njava.tuvivienda.models.image.Image;
import com.c1646njava.tuvivienda.models.image.ImageUser;
import com.c1646njava.tuvivienda.repositories.ImagePostRepository;
import com.c1646njava.tuvivienda.repositories.ImageRepository;
import com.c1646njava.tuvivienda.repositories.ImageUserRepository;
import com.c1646njava.tuvivienda.services.abstraction.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageServiceImp implements ImageService {

    private final ImageUserRepository imageUserRepository;
    private final ImagePostRepository imagePostRepository;
    private final ImageRepository imageRepository;


    @Override
    public List<Image> list() {
        return null;
    }

    @Override
    public void delete(Long id) {
     imageRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return imageRepository.existsById(id);
    }
}
