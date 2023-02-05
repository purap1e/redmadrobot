package com.example.bootcamp.services.impl;

import com.example.bootcamp.entities.AdFIle;
import com.example.bootcamp.repositories.FileRepository;
import com.example.bootcamp.services.FileService;
import com.example.bootcamp.util.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    @Override
    public AdFIle save(MultipartFile file) {
        log.info("Saving new file {} to the database", file.getOriginalFilename());

        try {
            return fileRepository.save(AdFIle.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .data(ImageUtils.compressImage(file.getBytes())).build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AdFIle getById(String id) {
        log.info("Fetching new adFile {} from the database", fileRepository.findById(UUID.fromString(id)));
        return fileRepository.findById(UUID.fromString(id)).orElseThrow(() -> new RuntimeException("File not found"));
    }
}