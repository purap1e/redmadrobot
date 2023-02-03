package com.example.bootcamp.services.impl;

import com.example.bootcamp.entities.Ad;
import com.example.bootcamp.repositories.AdRepository;
import com.example.bootcamp.services.AdService;
import com.example.bootcamp.services.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {
    private final AdRepository adRepository;
    private final FileService fileService;

    @Override
    public Ad saveAd(Ad ad, MultipartFile file) throws IOException {
        log.info("Saving new ad {} to the database",ad.getTitle());
        if (file != null) {
            ad.setImageFile(fileService.saveFile(file));
        }
        return adRepository.save(ad);
    }

    @Override
    public Ad updateAd(Long id, Ad newAd, MultipartFile file) throws IOException {
        log.info("Updating ad {} ",newAd.getTitle());

        Ad ad  = getAd(id);
        ad.setTitle(newAd.getTitle());
        ad.setDescription(newAd.getDescription());
        ad.setPrice(newAd.getPrice());
        return saveAd(ad,file);
    }

    @Override
    public Ad getAd(Long id) {
        log.info("Fetching ad by id {}", id);
        Optional<Ad> ad = adRepository.findById(id);
        if (ad.isPresent()) {
            return ad.get();
        }
        throw new RuntimeException();
    }

    @Override
    public Stream<Ad> getAds() {
        log.info("Fetching all ads");
        return adRepository.findAll().stream();
    }

    @Override
    public void deleteAd(Long id) {}

//    @Override
//    public List<Ad>  findAllByActiveIs(boolean active) {
//        log.info("Fetching all ads, that is {} ",active);
//        return adRepository.findAllByActiveIs(active);
//    }

    @Override
    public List<Ad> findAdsByPriceLessThanAndPriceGreaterThan(int min, int max) {
        log.info("Fetching all ads, that price less than {} and greater than {}", min, max);
        return adRepository.findAdsByPriceLessThanAndPriceGreaterThan(min,max);
    }

    @Override
    public List<Ad> findAdsWithSortingByASC(String field) {
        log.info("Fetching all ads with sorting by {}", field);
        return adRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    @Override
    public Page<Ad> findAdsWithPagination(int offset, int pageSize) {
        return adRepository.findAll(PageRequest.of(offset,pageSize));
    }
}
