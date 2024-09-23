package com.ApparelAvenue.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ApparelAvenue.backend.constant.Section;
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
    public ResponseEntity<Banner> createBanner(
            @RequestParam("bannerTitle") String bannerTitle,
            @RequestParam("bannerImage") MultipartFile bannerImage,
            @RequestParam("section") String section) {
        BannerRequestDto dto = new BannerRequestDto();
        dto.setBannerTitle(bannerTitle);
        dto.setSection(Section.valueOf(section.toUpperCase()));
        dto.setBannerImage(bannerImage);
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
            @RequestParam("bannerTitle") String bannerTitle,
            @RequestParam("section") String section,
            @RequestParam(value = "bannerImage", required = false) MultipartFile bannerImage) {
        BannerUpdateRequestDto dto = new BannerUpdateRequestDto();
        dto.setBannerTitle(bannerTitle);
        dto.setSection(Section.valueOf(section.toUpperCase()));
        Banner newBanner = BannerMapper.convertBannerUpdateRequestDtoToBanner(dto);
        try {
            Banner updatedBanner = bannerService.updateBanner(id, newBanner, bannerImage);
            return ResponseEntity.ok(updatedBanner);
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