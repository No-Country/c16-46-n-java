package com.c1646njava.tuvivienda.controllers;

import com.c1646njava.tuvivienda.models.image.Image;
import com.c1646njava.tuvivienda.models.image.ImageUser;
import com.c1646njava.tuvivienda.models.image.dto.Mensaje;
import com.c1646njava.tuvivienda.models.user.User;
import com.c1646njava.tuvivienda.models.user.dto.RequestUser;
import com.c1646njava.tuvivienda.repositories.ImageRepository;
import com.c1646njava.tuvivienda.repositories.ImageUserRepository;
import com.c1646njava.tuvivienda.repositories.UserRepository;
import com.c1646njava.tuvivienda.services.implementation.CloudinaryServiceImp;
import com.c1646njava.tuvivienda.services.implementation.ImageServiceImp;
import com.c1646njava.tuvivienda.services.implementation.ImageUserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cloudinary")
@CrossOrigin
@RequiredArgsConstructor
public class MainControler {

    private final CloudinaryServiceImp cloudinaryServiceImp;
    private final ImageUserServiceImp imageUserServiceImp;
    private final ImageUserRepository imageUserRepository;
    private final ImageRepository imageRepository;
    private final UserRepository userRepository;

    @GetMapping("/list")
    public ResponseEntity<List<Image>> list(){
        List<Image> images = imageRepository.findAll();
        return new ResponseEntity<>(images, HttpStatus.OK);
    }
@PostMapping("/upload_image")
    public ResponseEntity<?> uploadImage(@RequestParam MultipartFile multipartFile) throws IOException{
    BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
    if(bi == null){
        return new ResponseEntity<>(new Mensaje("No valid image"), HttpStatus.BAD_REQUEST);
    }
        Map result = cloudinaryServiceImp.upload(multipartFile);
    Image image =
            new Image((String)result.get("original_filename"),
            (String) result.get("url"),
            (String) result.get("public_id"));
    imageRepository.save(image);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @PostMapping("/upload_user_avatar")
    public ResponseEntity<?> uploadAvatar(@RequestParam MultipartFile multipartFile, @RequestParam Long userId) throws IOException {
        BufferedImage bi = ImageIO.read(multipartFile.getInputStream());
        if (bi == null) {
            return new ResponseEntity<>(new Mensaje("No valid image"), HttpStatus.BAD_REQUEST);
        }

        Map result = cloudinaryServiceImp.upload(multipartFile);
        Image image =
                new Image((String) result.get("original_filename"),
                        (String) result.get("url"),
                        (String) result.get("public_id"));

        // Update user avatar association
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(new Mensaje("User not found"), HttpStatus.NOT_FOUND);
        }

        ImageUser imageUser = new ImageUser(user,image);

        imageRepository.save(image);
        imageUserRepository.save(imageUser);
        user.setAvatar(imageUser);
        userRepository.save(user);

        return new ResponseEntity<>(new Mensaje("The avatar has been update"), HttpStatus.OK);
    }


@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws IOException{
        if(!imageUserServiceImp.exists(id)){
            return new ResponseEntity<>(new Mensaje("The image does not exist"), HttpStatus.NOT_FOUND);
        }

        Image image = imageRepository.findById(id).orElse(null);
        Map result = cloudinaryServiceImp.delete(image.getImageId());
        imageRepository.deleteById(id);
        return new ResponseEntity<>(new Mensaje("The image has been deleted"), HttpStatus.OK);
    }
}
