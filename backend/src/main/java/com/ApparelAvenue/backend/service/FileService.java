package com.ApparelAvenue.backend.service;

import org.springframework.web.multipart.MultipartFile;
public interface FileService {
    void createBannerImageDir(); 

    String uploadBannerImage(String filePath, MultipartFile file); 

    byte[] getBannerImage(String filePath);  
}