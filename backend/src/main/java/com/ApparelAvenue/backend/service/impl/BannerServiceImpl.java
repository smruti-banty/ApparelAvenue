package com.ApparelAvenue.backend.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ApparelAvenue.backend.model.Banner;
import com.ApparelAvenue.backend.repository.BannerRepository;
import com.ApparelAvenue.backend.service.BannerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BannerServiceImpl implements BannerService {
    @Value("${upload.file.banner}")
    private String uploadDir;
    private final BannerRepository bannerRepository;

    @Override
    public Banner creatBanner(Banner banner, MultipartFile bannerImage) {
        if (bannerImage == null || bannerImage.isEmpty()) {
            throw new IllegalArgumentException("Banner image cannot be empty");
        }
        String bannerPath = uploadBannerImage(bannerImage);
        banner.setBannerImage(bannerPath);
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
    public Banner updateBanner(String id, Banner newBanner, MultipartFile bannerImage) {
        Banner existingBanner = bannerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Banner not found with id: " + id));
        if (newBanner.getBannerTitle() != null) {
            existingBanner.setBannerTitle(newBanner.getBannerTitle());
        }
        if (bannerImage != null && !bannerImage.isEmpty()) {
            String newImagePath = uploadBannerImage(bannerImage);
            existingBanner.setBannerImage(newImagePath);
        }
        if (newBanner.getSection() != null) {
            existingBanner.setSection(newBanner.getSection());
        }
        return bannerRepository.save(existingBanner);
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

    @Override
    public String uploadBannerImage(MultipartFile bannerImage) {
        if (bannerImage.isEmpty()) {
            throw new IllegalArgumentException("Cannot upload empty file");
        }
        String fileName = UUID.randomUUID().toString() + "_" + bannerImage.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        try {
            Files.createDirectories(filePath.getParent());
            bannerImage.transferTo(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image: " + e.getMessage(), e);
        }
        return fileName;
    }
}