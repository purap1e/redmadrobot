
package com.example.bootcamp.controllers;

import com.example.bootcamp.entities.Ad;
import com.example.bootcamp.response.AdDto;
import com.example.bootcamp.services.AdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/ads")
@Api(description = "Контроллер для работы с объявлениями")
public class AdController {
    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @PostMapping("/createAd")
    @ApiOperation("Создание объявления, объявление может создать только зарегестрированный пользователь")
    public ResponseEntity<?> create(@RequestParam(name = "email") String email,
                                    @RequestParam(name = "title") String title,
                                    @RequestParam(name = "description") String description,
                                    @RequestParam(name = "price") int price,
                                    @RequestParam(name = "active") boolean active,
                                    @RequestParam(name = "files") List<MultipartFile> files) {
        Ad ad = Ad.builder().title(title).description(description).price(price).active(active).adFiles(Collections.emptyList()).build();
        adService.save(email, ad, files);
        return ResponseEntity.created(URI.create("/ads/" + ad.getId())).build();
    }

    @GetMapping("/{id}")
    @ApiOperation("Выдает 1 объявление по id")
    public AdDto get(@PathVariable Long id) {
        return adService.get(id);
    }

    @GetMapping
    @ApiOperation("Получение списка объявлений")
    public List<AdDto> getAll(@RequestParam int page,
                              @RequestParam int size,
                              @RequestParam int minPrice,
                              @RequestParam int maxPrice,
                              @RequestParam boolean isActive) {
        return adService.findAll(page, size, minPrice, maxPrice, isActive);
    }

    @PostMapping("/increasePrice")
    @ApiOperation("Повышение ставки объявления(только зарегестрированный пользователь)")
    public AdDto updatePrice(@RequestParam String userEmail,
                             @RequestParam Long adId,
                             @RequestParam int oldPrice,
                             @RequestParam int newPrice) {
        return adService.updatePrice(userEmail, adId, oldPrice, newPrice);
    }

    @PostMapping("/winner")
    @ApiOperation("Определение победителя аукциона(только зарегестрированный пользователь)")
    public void winAd(@RequestParam Long winnerUserId,
                      @RequestParam Long adId) {
        adService.winAd(winnerUserId,adId);
    }
}
