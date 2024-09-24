package com.ApparelAvenue.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<Banner> createBanner(@ModelAttribute BannerRequestDto dto) {
        Banner banner = BannerMapper.convertToBanner(dto);
        Banner createdBanner = bannerService.creatBanner(banner, dto.getBannerImage());
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
    public ResponseEntity<?> updateBanner(
            @PathVariable String id,
            @ModelAttribute BannerUpdateRequestDto dto) {
        try {
            Banner newBanner = BannerMapper.convertBannerUpdateRequestDtoToBanner(dto);
            Banner updatedBanner = bannerService.updateBanner(id, newBanner, dto.getBannerImage());
            return ResponseEntity.ok(updatedBanner);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid section value: " + dto.getSection());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
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