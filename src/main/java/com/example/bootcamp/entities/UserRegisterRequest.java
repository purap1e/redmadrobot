package com.example.bootcamp.entities;

import lombok.Data;

@Data
public class UserRegisterRequest {
    private String email;
    private String password;
}
