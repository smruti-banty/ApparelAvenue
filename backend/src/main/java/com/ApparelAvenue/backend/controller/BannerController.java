package com.ApparelAvenue.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ApparelAvenue.backend.dto.BannerRequestDto;
import com.ApparelAvenue.backend.dto.BannerUpdateRequestDto;
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

    @GetMapping("/{id}")
    public ResponseEntity<?> getBannerById(@PathVariable String id) {
        try {
            Banner banner = bannerService.getBannerById(id);
            return ResponseEntity.ok(banner);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Banner not found with id " + id);
        }
    }

    @GetMapping
    public ResponseEntity<List<Banner>> getAllBanners() {
        List<Banner> banners = bannerService.getAllBanners();
        return new ResponseEntity<>(banners, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Banner> updateBanner(@PathVariable String id,
            @RequestBody BannerUpdateRequestDto dto) {
        try {
            Banner newBanner = BannerMapper.convertBannerUpdateRequestDtoToBanner(dto);
            Banner updateBanner = bannerService.updateBanner(id, newBanner);
            return ResponseEntity.ok(updateBanner);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBanner(@PathVariable String id) {
        try {
            bannerService.deleteBannerById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}