package com.example.bootcamp.repositories;

import com.example.bootcamp.entities.AdFIle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface FileRepository extends JpaRepository<AdFIle,Long> {
//    Optional<AdFIle> findById(UUID id);
    Optional<AdFIle> findById(Long id);
}
