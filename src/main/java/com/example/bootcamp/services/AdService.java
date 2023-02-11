package com.example.bootcamp.services;

import com.example.bootcamp.entities.Ad;
import com.example.bootcamp.response.AdDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdService {
    Ad save(String email, Ad ad, List<MultipartFile> files);
    AdDto get(Long id);
    List<AdDto> findAll(int page, int size, int minPrice, int maxPrice, boolean isActive);

    AdDto updatePrice(String email, Long id, int oldPrice, int newPrice);
    void winAd(Long winnerUserId, Long adId);
}
