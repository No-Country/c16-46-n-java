package com.c1646njava.tuvivienda.services.implementation;

import com.c1646njava.tuvivienda.models.user.User;
import com.c1646njava.tuvivienda.models.user.dto.RequestUser;
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
