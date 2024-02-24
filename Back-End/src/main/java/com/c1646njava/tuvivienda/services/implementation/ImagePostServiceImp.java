package com.c1646njava.tuvivienda.services.implementation;

import com.c1646njava.tuvivienda.models.image.ImagePost;
import com.c1646njava.tuvivienda.models.image.ImageUser;
import com.c1646njava.tuvivienda.models.post.Post;
import com.c1646njava.tuvivienda.models.user.User;
import com.c1646njava.tuvivienda.repositories.ImagePostRepository;
import com.c1646njava.tuvivienda.repositories.PostRepository;
import com.c1646njava.tuvivienda.services.abstraction.ImagePostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ImagePostServiceImp implements ImagePostService {

    private final PostRepository postRepository;
    private final ImagePostRepository imagePostRepository;

    @Override
    public List<ImagePost> findImagesByPost(Long id) {
            Optional<Post> post = postRepository.findById(id);
            return imagePostRepository.findById(id).get().getPost().getImage();
        }
}
