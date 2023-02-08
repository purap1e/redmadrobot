
package com.example.bootcamp.controllers;

import com.example.bootcamp.entities.Ad;
import com.example.bootcamp.response.AdDto;
import com.example.bootcamp.services.AdService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ads")
public class AdController {
    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestParam("title") String title,
                                    @RequestParam("description") String description,
                                    @RequestParam("price") int price,
                                    @RequestParam("active") boolean active,
                                    @RequestParam("imageFiles") List<MultipartFile> files) {
        Ad ad = Ad.builder().title(title).description(description).price(price).active(active).adFiles(Collections.emptyList()).build();
        adService.save(ad, files);
        return ResponseEntity.created(URI.create("/ads/" + ad.getId())).build();
    }

    @GetMapping("/{id}")
    public AdDto get(@PathVariable Long id) {
        return adService.get(id);
    }

    @GetMapping
    public List<AdDto> getAll(@RequestParam int page,
                              @RequestParam int size,
                              @RequestParam int minPrice,
                              @RequestParam int maxPrice) {
        return adService.findAll(page, size, minPrice, maxPrice);
    }
}
