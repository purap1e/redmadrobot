package com.example.bootcamp.services;

import com.example.bootcamp.entities.AppUser;

import java.util.List;

public interface UserService {
    AppUser save(AppUser user);
    AppUser get(String email);
    void addRoleToUser(String email, String roleName);
    AppUser register(String email, String password);
    List<AppUser> getUsers();
}
