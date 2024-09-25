package com.ApparelAvenue.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.ApparelAvenue.backend.service.BannerService;
import com.ApparelAvenue.backend.service.FileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BannerImageApi {
    private final BannerService bannerService;
    private final FileService fileService;

    @GetMapping(path = "/banners/image/{bannerId}", produces = {"image/jpg", "image/jpeg", "image/png"})
    public byte[] getBannerImage(@PathVariable String bannerId) {
        var banner = bannerService.getBannerById(bannerId);
        return fileService.getImage(banner.getBannerImage());
    }
}