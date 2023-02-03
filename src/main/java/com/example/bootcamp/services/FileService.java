package com.example.bootcamp.services;

import com.example.bootcamp.entities.ImageFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

public interface FileService {
    ImageFile saveFile(MultipartFile file) throws IOException;
    ImageFile getFile(Long id);
    Stream<ImageFile> getAllFiles();
    void deleteFile(ImageFile imageFile);
    byte[] downloadImage(String fileName);
}
