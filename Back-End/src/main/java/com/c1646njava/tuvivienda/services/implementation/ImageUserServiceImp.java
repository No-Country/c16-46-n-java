package com.c1646njava.tuvivienda.services.implementation;

import com.c1646njava.tuvivienda.models.image.ImageUser;
import com.c1646njava.tuvivienda.models.user.User;
import com.c1646njava.tuvivienda.repositories.ImageUserRepository;
import com.c1646njava.tuvivienda.repositories.UserRepository;
import com.c1646njava.tuvivienda.services.abstraction.ImageUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageUserServiceImp implements ImageUserService {

    private final UserRepository userRepository;
    private final ImageUserRepository imageUserRepository;


    @Override
    public Optional<ImageUser> findAvatarByUser(Long id){
        Optional<User> user = userRepository.findById(id);
        return imageUserRepository.findById(user.get().getAvatar().getId());
    }


    @Override
    public User addAvatarToUser(Long id, ImageUser image) {

        User user = userRepository.findById(id).orElse(null);
        user.setAvatar(image);
        return user;
    }

    @Override
    public Boolean exists(Long id) {
        return imageUserRepository.existsById(id);
    }


}
