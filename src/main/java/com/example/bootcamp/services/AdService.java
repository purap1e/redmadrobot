package com.example.bootcamp.services;

import com.example.bootcamp.entities.Ad;
import com.example.bootcamp.response.AdDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdService {
    Ad save(Ad ad, List<MultipartFile> files);
    AdDto get(Long id);
    List<AdDto> findAll(int page, int size, int minPrice, int maxPrice);
}
