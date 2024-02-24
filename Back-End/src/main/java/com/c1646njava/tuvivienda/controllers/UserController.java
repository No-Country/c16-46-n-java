package com.c1646njava.tuvivienda.controllers;

import com.c1646njava.tuvivienda.models.user.User;
import com.c1646njava.tuvivienda.models.user.dto.RequestUser;
import com.c1646njava.tuvivienda.models.user.dto.ResponseUser;
import com.c1646njava.tuvivienda.services.implementation.UserServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserServiceImp userServiceImp;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody RequestUser requestUser) {
        try {
            User user = userServiceImp.registerUser(requestUser);

            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseUser(user));
        }catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestParam String email, @RequestParam String password) throws AuthenticationException {
         try{
             User user = userServiceImp.loginUser(email,password);
             return ResponseEntity.status(HttpStatus.OK).body(user);
         }catch (AuthenticationException e){
             return ResponseEntity.status((HttpStatus.UNAUTHORIZED)).body(e.getMessage());
         }
    }
}
