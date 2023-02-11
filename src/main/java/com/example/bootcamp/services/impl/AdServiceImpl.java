package com.example.bootcamp.services.impl;

import com.example.bootcamp.entities.Ad;
import com.example.bootcamp.entities.AdFIle;
import com.example.bootcamp.entities.AppUser;
import com.example.bootcamp.repositories.AdRepository;
import com.example.bootcamp.repositories.UserRepository;
import com.example.bootcamp.response.AdDto;
import com.example.bootcamp.services.AdService;
import com.example.bootcamp.util.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final UserRepository userRepository;

    @Override
    public Ad save(String email, Ad ad, List<MultipartFile> files) {
        log.info("Saving new ad {} to user {}", ad.getTitle(), email);
        List<AdFIle> adFiles = new ArrayList<>();
        files.forEach(file -> {
            try {
                adFiles.add(AdFIle.builder()
                        .name(file.getOriginalFilename())
                        .type(file.getContentType())
                        .data(ImageUtils.compressImage(file.getBytes())).build());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        ad.setAdFiles(adFiles);
        AppUser user = userRepository.findByEmail(email);
        ad.setUser(user);
        return adRepository.save(ad);
    }

    @Override
    public AdDto get(Long id) {
        log.info("Fetching ad by id {}", id);
        return adRepository.findById(id).map(ad -> new AdDto(
                ad.getId(),
                ad.getTitle(),
                ad.getDescription(),
                ad.getOldPrice(),
                ad.getPrice(),
                ad.isActive(),
                ad.getWinnerUserId(),
                ad.getUser().getId(),
                ad.getAdFiles().stream().map(file -> ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/api/v1/files/" + file.getId()).toList())).orElseThrow(() -> new RuntimeException("File not found"));
    }

    @Override
    public List<AdDto> findAll(int page, int size, int minPrice, int maxPrice, boolean isActive) {
        log.info("Fetching all ads, that price less than {} and greater than {} and active is {}", minPrice, maxPrice, isActive);
        return adRepository.findAdsByPriceGreaterThanAndPriceLessThanAndActiveIs(minPrice, maxPrice, PageRequest.of(page, size), isActive)
                .stream()
                .map(ad -> new AdDto(
                        ad.getId(),
                        ad.getTitle(),
                        ad.getDescription(),
                        ad.getOldPrice(),
                        ad.getPrice(),
                        ad.isActive(),
                        ad.getWinnerUserId(),
                        ad.getUser().getId(),
                        ad.getAdFiles().stream().map(file -> ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/api/v1/files/" + file.getId()).toList()))
                .toList();

    }

    @Override
    public AdDto updatePrice(Long id, int oldPrice, int newPrice) {
        log.info("Updating price for ad {}", id);
        Ad ad = adRepository.findById(id).orElseThrow(() -> new RuntimeException("Something went wrong"));

        double minPrice = oldPrice * 0.1;
        if (newPrice >= minPrice) {
            ad.setOldPrice(oldPrice);
            ad.setPrice(newPrice);
            adRepository.save(ad);
            return get(id);
        }
        throw new RuntimeException("New price is too low to increase an ad price");
    }

    @Override
    public void winAd(Long winnerUserId, Long adId) {
        log.info("Fetching ad by id {}", adId);
        Ad ad = adRepository.findById(adId).orElseThrow(() -> new RuntimeException("Something went wrong"));
        ad.setWinnerUserId(winnerUserId);
        ad.setActive(false);
        adRepository.save(ad);
    }
}
