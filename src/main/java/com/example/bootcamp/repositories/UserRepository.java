package com.example.bootcamp.repositories;

import com.example.bootcamp.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByEmail(String email);
    Optional<AppUser> findById(Long id);
}
