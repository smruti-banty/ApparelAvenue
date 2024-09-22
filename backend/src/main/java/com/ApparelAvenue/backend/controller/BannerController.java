package com.ApparelAvenue.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApparelAvenue.backend.dto.BannerRequestDto;
import com.ApparelAvenue.backend.mapper.BannerMapper;
import com.ApparelAvenue.backend.model.Banner;
import com.ApparelAvenue.backend.service.BannerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/banners")
@RequiredArgsConstructor
public class BannerController {
    private final BannerService bannerService;

    @PostMapping
    public ResponseEntity<Banner> createBanner(@RequestBody BannerRequestDto dto) {
        Banner banner = BannerMapper.convertToBanner(dto);
        Banner createdBanner = bannerService.creatBanner(banner);
        return new ResponseEntity<>(createdBanner, HttpStatus.CREATED);
    }

    @GetMapping("/{bannerId}")
    public ResponseEntity<?> getBannerById(@PathVariable String bannerId) {
        try {
            Banner banner = bannerService.getBannerById(bannerId);
            return ResponseEntity.ok(banner);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Banner not found with id " + bannerId);
        }
    }

    @GetMapping
    public ResponseEntity<List<Banner>> getAllBanners() {
        List<Banner> banners = bannerService.getAllBanners();
        return new ResponseEntity<>(banners, HttpStatus.OK);
    }
}
