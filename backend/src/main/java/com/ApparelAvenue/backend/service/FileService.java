package com.ApparelAvenue.backend.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void createImageDir();

    String uploadImage(MultipartFile file);

    byte[] getImage(String fileName);
}