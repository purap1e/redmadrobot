package com.example.bootcamp.services.impl;

import com.example.bootcamp.entities.ImageFile;
import com.example.bootcamp.repositories.FileRepository;
import com.example.bootcamp.services.FileService;
import com.example.bootcamp.util.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    @Override
    public ImageFile saveFile(MultipartFile file) throws IOException {
        log.info("Saving new file {} to the database", file.getOriginalFilename());
//        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//
//        ImageFile imageFileDB = new ImageFile(null,fileName,file.getContentType(),file.getBytes());
//
//        return fileRepository.save(imageFileDB);

        return fileRepository.save(ImageFile.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .data(ImageUtils.compressImage(file.getBytes())).build());
    }



    @Override
    public ImageFile getFile(Long id) {
        log.info("Fetching file {}", fileRepository.findById(id));
        var file = fileRepository.findById(id);
        if (file.isPresent()){
            return file.get();
        }
        throw new RuntimeException();
    }

    @Override
    public Stream<ImageFile> getAllFiles() {
        log.info("Fetching all files");
        return fileRepository.findAll().stream();
    }

    @Override
    public void deleteFile(ImageFile imageFile) {
        log.info("File {} has been deleted", imageFile.getName());
        fileRepository.delete(imageFile);
    }

    @Override
    public byte[] downloadImage(String fileName) {
        Optional<ImageFile> dbImageData = fileRepository.findByName(fileName);
        return ImageUtils.decompressImage(dbImageData.get().getData());
    }
}
