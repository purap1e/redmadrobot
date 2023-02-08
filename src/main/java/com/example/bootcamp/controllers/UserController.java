package com.example.bootcamp.controllers;

import com.example.bootcamp.entities.UserRegisterRequest;
import com.example.bootcamp.repositories.RoleRepository;
import com.example.bootcamp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final RoleRepository roleRepository;
    private final UserService userService;
    @PostMapping("/registration")
    public ResponseEntity<?> signUpNewUser(@RequestBody UserRegisterRequest request) {
        userService.register(request.getEmail(), request.getPassword());
        return ResponseEntity.ok().build();
    }
}
