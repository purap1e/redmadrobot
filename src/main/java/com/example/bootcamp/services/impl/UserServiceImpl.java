package com.example.bootcamp.services.impl;

import com.example.bootcamp.entities.AppUser;
import com.example.bootcamp.entities.Role;
import com.example.bootcamp.exceptions.EmailExistsException;
import com.example.bootcamp.repositories.RoleRepository;
import com.example.bootcamp.repositories.UserRepository;
import com.example.bootcamp.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = userRepository.findByEmail(email);
        if (user == null) {
            log.info("This email: {} does not exist", email);
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("Email {} found in the database", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
        return new User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public AppUser save(AppUser user) {
        log.info("Saving new user {} to the database", user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public AppUser get(String email) {
        log.info("Fetching user {}", userRepository.findByEmail(email));
        AppUser user = userRepository.findByEmail(email);
        if (user == null) {
            log.info("This email: {} does not exist", email);
            throw new UsernameNotFoundException("User not found in the database");
        } else {
            log.info("Email {} found in the database", email);
        }
        return user;
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        log.info("Adding role {} to user {}", roleName, email);
        AppUser user = userRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);
    }

    private boolean usernameExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public AppUser registerUser(AppUser user) {
        if (usernameExist(user.getEmail())) {
            throw new EmailExistsException(user.getEmail());
        } else {
            return save(user);
        }
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("Fetching all users");
        return userRepository.findAll();
    }
}
