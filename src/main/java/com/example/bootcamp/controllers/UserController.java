package com.example.bootcamp.controllers;

import com.example.bootcamp.entities.UserRegisterRequest;
import com.example.bootcamp.repositories.RoleRepository;
import com.example.bootcamp.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@Api(description = "Контроллер для работы с пользователями")
public class UserController {

    private final RoleRepository roleRepository;
    private final UserService userService;
    @PostMapping("/registration")
    @ApiOperation("Регистрация пользователя")
    public ResponseEntity<?> signUpNewUser(@RequestBody UserRegisterRequest request) {
        userService.register(request.getEmail(), request.getPassword());
        return ResponseEntity.ok().build();
    }
}
