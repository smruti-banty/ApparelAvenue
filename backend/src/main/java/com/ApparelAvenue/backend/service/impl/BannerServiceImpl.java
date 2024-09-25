package com.ApparelAvenue.backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ApparelAvenue.backend.model.Banner;
import com.ApparelAvenue.backend.repository.BannerRepository;
import com.ApparelAvenue.backend.service.BannerService;
import com.ApparelAvenue.backend.service.FileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;
    private final FileService fileService;

    @Override
    public Banner creatBanner(Banner banner, MultipartFile file) {
        String fileName = fileService.uploadImage(file);
        banner.setBannerImage(fileName);
        return bannerRepository.save(banner);
    }

    @Override
    public Banner updateBanner(String id, Banner newBanner, MultipartFile file) {
        Banner existingBanner = bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banner not found"));
        if (file != null && !file.isEmpty()) {
            String newFileName = fileService.uploadImage(file);
            newBanner.setBannerImage(newFileName);
        } else {
            newBanner.setBannerImage(existingBanner.getBannerImage());
        }
        newBanner.setBannerId(existingBanner.getBannerId());
        return bannerRepository.save(newBanner);
    }

    @Override
    public List<Banner> getAllBanners() {
        return bannerRepository.findAll();
    }

    @Override
    public Banner getBannerById(String id) {
        return bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banner not found"));
    }

    @Override
    public Banner deleteBannerById(String id) {
        Banner banner = getBannerById(id);
        bannerRepository.deleteById(id);
        return banner;
    }
}