package com.example.bootcamp.services;

import com.example.bootcamp.entities.Ad;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public interface AdService {
    Ad saveAd(Ad ad, MultipartFile file) throws IOException;
    Ad updateAd(Long id, Ad newAd, MultipartFile file) throws IOException;
    Ad getAd(Long id);
    Stream<Ad> getAds();
    void deleteAd(Long id);
//    List<Ad> findAllByActiveIs(boolean isActive);
    List<Ad> findAdsByPriceLessThanAndPriceGreaterThan(int min, int max);
    List<Ad> findAdsWithSortingByASC(String field);
    Page<Ad> findAdsWithPagination(int offset, int pageSize);
}
