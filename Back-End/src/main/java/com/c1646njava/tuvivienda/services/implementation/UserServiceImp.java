package com.c1646njava.tuvivienda.services.implementation;

import com.c1646njava.tuvivienda.models.administrator.Administrator;
import com.c1646njava.tuvivienda.models.post.Post;
import com.c1646njava.tuvivienda.models.user.User;
import com.c1646njava.tuvivienda.models.user.dto.RequestUser;
import com.c1646njava.tuvivienda.repositories.PostRepository;
import com.c1646njava.tuvivienda.repositories.UserRepository;
import com.c1646njava.tuvivienda.services.abstraction.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

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
    public Boolean logoutUser() {
        return null;
    }

    @Override
    public Boolean checkSession() {
        return null;
    }

    @Override
    public Administrator upgradeToAdmin(Long userId, String phoneNumber) throws AuthenticationException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AuthenticationException("Invalid user ID"));

        // Check if user is already an administrator
        if (user instanceof Administrator) {
            throw new AuthenticationException("User is already an administrator");
        }

        // Update user to Administrator
        Administrator admin = new Administrator(user.getName(),user.getCountry(), user.getEmail(), user.getPassword(), phoneNumber, user.getAvatar(), user.getFav());
        admin.setUserId(user.getId()); // Set ID to maintain existing user data
        // Delete the old user entry
        userRepository.delete(user);
        // Save the new administrator
        return userRepository.save(admin);
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

}
