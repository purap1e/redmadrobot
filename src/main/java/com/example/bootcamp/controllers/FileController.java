package com.example.bootcamp.controllers;

import com.example.bootcamp.entities.AdFIle;
import com.example.bootcamp.services.FileService;
import com.example.bootcamp.util.ImageUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable Long id) {
        AdFIle adFIle = fileService.getById(id);
        byte[] imageData = ImageUtils.decompressImage(adFIle.getData());
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(adFIle.getType()))
                .body(imageData);
    }
}
