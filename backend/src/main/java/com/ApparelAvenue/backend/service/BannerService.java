package com.ApparelAvenue.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ApparelAvenue.backend.model.Banner;

@Service
public interface BannerService {
    Banner creatBanner(Banner banner, MultipartFile bannerImage);

    Banner updateBanner(String id, Banner newBanner, MultipartFile bannerImage);

    List<Banner> getAllBanners();

    Banner getBannerById(String id);

    Banner deleteBannerById(String id);

    String uploadBannerImage(MultipartFile bannerImage);
}