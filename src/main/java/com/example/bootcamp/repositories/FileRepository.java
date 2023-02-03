package com.example.bootcamp.repositories;

import com.example.bootcamp.entities.ImageFile;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface FileRepository extends JpaRepository<ImageFile,Long> {
}
