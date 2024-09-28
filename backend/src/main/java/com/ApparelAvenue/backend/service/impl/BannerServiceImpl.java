package com.ApparelAvenue.backend.service.impl;

import com.ApparelAvenue.backend.model.Banner;
import com.ApparelAvenue.backend.repository.BannerRepository;
import com.ApparelAvenue.backend.service.BannerService;
import com.ApparelAvenue.backend.service.FileService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;
    private final FileService fileService;

    @Override
    public Banner createBanner(Banner banner, MultipartFile file) {
        String filePath = fileService.uploadBannerImage(null, file);
        banner.setBannerImage(filePath);
        return bannerRepository.save(banner);
    }

    @Override
    public Banner updateBanner(String id, Banner newBanner, MultipartFile file) {
        Optional<Banner> existingBannerOptional = bannerRepository.findById(id);
        if (existingBannerOptional.isPresent()) {
            Banner existingBanner = existingBannerOptional.get();
            existingBanner.setBannerTitle(newBanner.getBannerTitle());
            existingBanner.setSection(newBanner.getSection());
            if (file != null && !file.isEmpty()) {
                String filePath = fileService.uploadBannerImage(null, file);
                existingBanner.setBannerImage(filePath);
            }
            return bannerRepository.save(existingBanner);
        }
        return null;
    }

    @Override
    public List<Banner> getAllBanners() {
        return bannerRepository.findAll();
    }

    @Override
    public Banner getBannerById(String id) {
        return bannerRepository.findById(id).orElse(null);
    }

    @Override
    public Banner deleteBannerById(String id) {
        Optional<Banner> existingBannerOptional = bannerRepository.findById(id);
        if (existingBannerOptional.isPresent()) {
            bannerRepository.deleteById(id);
            return existingBannerOptional.get();
        }
        return null;
    }
}