package com.example.bootcamp.repositories;

import com.example.bootcamp.entities.AdFIle;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface FileRepository extends JpaRepository<AdFIle,Long> {
    Optional<AdFIle> findById(UUID id);
}
