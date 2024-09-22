package com.ApparelAvenue.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ApparelAvenue.backend.model.Banner;

@Service
public interface BannerService {
    Banner creatBanner(Banner banner);

    Banner updateBannerImage(String id, Banner newImage);

    List<Banner> getAllBanners();

    Banner getBannerById(String id);

    void deleteBanner(String id);
}
