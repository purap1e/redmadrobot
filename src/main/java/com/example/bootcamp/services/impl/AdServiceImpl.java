package com.example.bootcamp.services.impl;

import com.example.bootcamp.entities.Ad;
import com.example.bootcamp.entities.AdFIle;
import com.example.bootcamp.repositories.AdRepository;
import com.example.bootcamp.response.AdDto;
import com.example.bootcamp.services.AdService;
import com.example.bootcamp.services.FileService;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final FileService fileService;

    @Override
    public Ad save(Ad ad, List<MultipartFile> files) {
        log.info("Saving new ad {} to the database",ad.getTitle());
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
        return adRepository.save(ad);
    }

    @Override
    public Ad get(Long id) {
        log.info("Fetching ad by id {}", id);
        Optional<Ad> ad = adRepository.findById(id);
        if (ad.isPresent()) {
            return ad.get();
        }
        throw new RuntimeException();
    }

    @Override
    public List<AdDto> findAll(int page, int size, int minPrice, int maxPrice) {
        log.info("Fetching all ads, that price less than {} and greater than {}", minPrice, maxPrice);

        return adRepository.findAdsByPriceGreaterThanAndPriceLessThan(minPrice,maxPrice, PageRequest.of(page, size))
                .stream()
                .map(ad -> new AdDto(
                        ad.getId(),
                        ad.getTitle(),
                        ad.getDescription(),
                        ad.getPrice(),
                        ad.getAdFiles().stream().map(file -> ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString() + "/files/" + file.getId()).toList()))
                .toList();

    }

}
