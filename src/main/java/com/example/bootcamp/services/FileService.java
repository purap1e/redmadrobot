package com.example.bootcamp.services;

import com.example.bootcamp.entities.AdFIle;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    AdFIle save(MultipartFile file);
    AdFIle getById(Long id);
}

