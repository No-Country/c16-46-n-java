package com.c1646njava.tuvivienda.services.implementation;

import com.c1646njava.tuvivienda.models.user.exceptions.PostExceptions.postNotFoundException;
import com.c1646njava.tuvivienda.models.administrator.Administrator;
import com.c1646njava.tuvivienda.models.post.Post;
import com.c1646njava.tuvivienda.models.user.User;
import com.c1646njava.tuvivienda.models.user.dto.RequestUser;
import com.c1646njava.tuvivienda.repositories.AdministratorRepository;
import com.c1646njava.tuvivienda.repositories.PostRepository;
import com.c1646njava.tuvivienda.repositories.UserRepository;
import com.c1646njava.tuvivienda.services.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final AdministratorRepository administratorRepository;


    @Override
    public User registerUser(RequestUser requestUser) {
        validateUserRequest(requestUser);
        User user = new User();

        user.setName(requestUser.name());
        user.setEmail(requestUser.email());
        user.setCountry(requestUser.country());
        user.setPassword(requestUser.password());

        return userRepository.save(user);
    }


    @Override
    public User loginUser(String email, String password) throws AuthenticationException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("Invalid email"));

        // 2. Verify the password using a secure hashing algorithm
        if (!password.equals(user.getPassword())) {
            throw new AuthenticationException("Invalid password");
        }

        // 3. Return the authenticated user object
        return user;
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void updateUser(Long userId, String name, String email, String country) {
        userRepository.updateUserDetails(userId, name, email, country);
    }

    @Override
    public void updatePassword(Long userId, String password, String password2) throws AuthenticationException {
        if(password.equals(password2)){
<<<<<<< HEAD
        userRepository.updateUserPassword(userId, password);
        }else throw new AuthenticationException("Passwords do not match");
    }


=======
            userRepository.updateUserPassword(userId, password);
        }else throw new AuthenticationException("Passwords do not match");
    }
>>>>>>> fc42d70b1b6f57f06bd9d324355a5faddae1d8bc
    @Override
    public Administrator upgradeToAdmin(Long userId, String phoneNumber) throws AuthenticationException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AuthenticationException("Invalid user ID"));

        // Check if user is already an administrator

        if (administratorRepository.findByUser_id(user.getId()) != null) {
            throw new AuthenticationException("User is already an administrator");
        }

        // Update user to Administrator
        Administrator admin = new Administrator(phoneNumber, user);
        // Save the new administrator
        return administratorRepository.save(admin);
    }


    @Override
    public void toggleFavorite(Long userId, Long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user ID"));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post ID"));

        // Toggle the post's presence in favorites
        if (user.getFav().contains(post)) {
            removePostFromFavorites(user, post);
        } else {
            addToFavorites(user, post);
        }
    }

    @Override
    public void addToFavorites(User user, Post post) {
        user.getFav().add(post);
        post.getFav().add(user);
        userRepository.save(user);
        postRepository.save(post);
    }

    @Override
    public void removePostFromFavorites(User user, Post post) {
        user.getFav().remove(post);
        post.getFav().remove(user);
        userRepository.save(user);
        postRepository.save(post);
    }

    @Override
    public void validateUserRequest(RequestUser requestUser) {
        String name = requestUser.name();
        String password = requestUser.password();
        String password2 = requestUser.password2();
        String email = requestUser.email();
        String country = requestUser.country();

        if(name.isBlank()){
            throw new IllegalArgumentException("The user must have a name");
        }
        if(password.isBlank() || password.length() <= 4){
            throw new IllegalArgumentException("The password must be more than 4 characters");
        }
        if (!password2.equals(password)){
            throw new IllegalArgumentException("The passwords entered must be the same");
        }
        if(email.isBlank()){
            throw new IllegalArgumentException("The user must have a email");
        }
        if(userRepository.findByEmail(email).isPresent()){
            throw new IllegalArgumentException("The email is in use");
        }
        if(country.isBlank()){
            throw new IllegalArgumentException("The user must have a country");
        }
    }

    //Verify if a user is admin of a post

    public Boolean isAdmin(Long postId, Long userId) throws postNotFoundException {
        Post post = postRepository.findById(postId).orElseThrow(() -> new postNotFoundException("there is not post with the id"));
        return Objects.equals(post.getAdministrator().getUser().getId(), userId);

    }


    public User getCurrentUser(Long aLong) throws postNotFoundException {
        return  userRepository.findById(aLong).orElseThrow(() -> new postNotFoundException("there is not a user with the specified id"));

    }
}
