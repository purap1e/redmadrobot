package com.example.bootcamp.controllers;

import com.example.bootcamp.entities.Ad;
import com.example.bootcamp.response.ResponseMessage;
import com.example.bootcamp.services.AdService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RestController
@CrossOrigin("http://localhost:8080")
@RequestMapping("/ad")
public class AdController {
    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadAd(@RequestParam("title") String title,
                                                    @RequestParam("description") String description,
                                                    @RequestParam("price") int price,
                                                    @RequestParam("active") boolean isActive,
                                                    @RequestParam("imageFile") MultipartFile file) throws JsonProcessingException {
        String message = "";
        Ad ad = new Ad(null,title,description,price,isActive, null);
        try {
            adService.saveAd(ad,file);

            message = String.format("Ad %s loading successful",ad.getTitle());
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = String.format("Could not upload the ad %s",ad.getTitle());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("{id}")
    public Ad getAdById(@PathVariable Long id) {
        return adService.getAd(id);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public Page<Ad> getAdsWithSort(@PathVariable int offset,
                                   @PathVariable int pageSize) {
        return adService.findAdsWithPagination(offset,pageSize);
    }

    @GetMapping("/between/{min}/{max}")
    public List<Ad> findAdsByPriceLessThanAndPriceGreaterThan(@PathVariable int min, @PathVariable int max) {
        return adService.findAdsByPriceLessThanAndPriceGreaterThan(min, max);
    }

}
