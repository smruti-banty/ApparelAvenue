package com.ApparelAvenue.backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ApparelAvenue.backend.model.Banner;
import com.ApparelAvenue.backend.repository.BannerRepository;
import com.ApparelAvenue.backend.service.BannerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;

    @Override
    public Banner creatBanner(Banner banner) {
        return bannerRepository.save(banner);
    }

    @Override
    public Banner updateBannerImage(String id, Banner newImage) {
        Banner existingBanner = bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banner not found"));
        existingBanner.setBannerImage(newImage.getBannerImage()); 
        return bannerRepository.save(existingBanner);
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
    public void deleteBanner(String id) {
        bannerRepository.deleteById(id);
    }
}