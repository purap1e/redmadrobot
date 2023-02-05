package com.example.bootcamp.services;

import com.example.bootcamp.entities.AdFIle;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FileService {
    AdFIle save(MultipartFile file);
    AdFIle getById(String id);
}

