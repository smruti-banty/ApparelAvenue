package com.ApparelAvenue.backend.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ApparelAvenue.backend.service.FileService;

import jakarta.annotation.PostConstruct;

@Service
public class FileServiceImpl implements FileService {

    @Value("${upload.file.root}")
    private String rootDirectory;

    @Value("${upload.file.banner}")
    private String bannerDirectory;

    @PostConstruct
    public void init() {
        createBannerImageDir();  
    }

    @Override
    public void createBannerImageDir() {
        String fullPath = rootDirectory + File.separator + bannerDirectory;
        File dir = new File(fullPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Override
    public String uploadBannerImage(String filePath, MultipartFile file) {
        try {
            String fullPath = rootDirectory + File.separator + bannerDirectory + File.separator + file.getOriginalFilename();
            Path path = Paths.get(fullPath);
            Files.write(path, file.getBytes());
            return fullPath;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public byte[] getBannerImage(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}