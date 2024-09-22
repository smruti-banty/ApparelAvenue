package com.ApparelAvenue.backend.service.impl;

import java.util.List;
import java.util.Optional;

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
    public List<Banner> getAllBanners() {
        return bannerRepository.findAll();
    }

    @Override
    public Banner getBannerById(String id) {
        return bannerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Banner not found"));
    }

    @Override
    public Banner updateBanner(String id, Banner newBanner) {
        Optional<Banner> optionalBanner = bannerRepository.findById(id);

        return optionalBanner.map(existingBanner -> {
            if (newBanner.getBannerTitle() != null) {
                existingBanner.setBannerTitle(newBanner.getBannerTitle());
            }
            if (newBanner.getBannerImage() != null) {
                existingBanner.setBannerImage(newBanner.getBannerImage());
            }
            if (newBanner.getSection() != null) {
                existingBanner.setSection(newBanner.getSection());
            }
            return bannerRepository.save(existingBanner);
        }).orElseThrow(() -> new RuntimeException("Banner not found with id: " + id));
    }

    @Override
    public Banner deleteBannerById(String id) {
       if (!bannerRepository.existsById(id)) {
        throw new IllegalArgumentException(id + "does not exist");
       }
       Banner banner = bannerRepository.findById(id).get();
       bannerRepository.delete(banner);
       return banner;
    }
}