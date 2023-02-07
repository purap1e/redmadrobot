package com.example.bootcamp.services;

import com.example.bootcamp.entities.AppUser;
import com.example.bootcamp.entities.Role;
import com.example.bootcamp.exceptions.EmailExistsException;

import java.util.List;

public interface UserService {
    AppUser save(AppUser user);
    AppUser get(String email);
    void addRoleToUser(String email, String roleName);
    AppUser registerUser(AppUser user);
    List<AppUser> getUsers();
}
