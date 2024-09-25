package com.ApparelAvenue.backend.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ApparelAvenue.backend.service.FileService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Value("${upload.file.banner}")
    private String imageDir;

    @Override
    public void createImageDir() {
        createDirectory(imageDir);
    }

    @Override
    public String uploadImage(MultipartFile file) {
        createImageDir();
        return uploadImageFile(file, imageDir);
    }

    @Override
    public byte[] getImage(String fileName) {
        return getImageFile(imageDir, fileName);
    }

    private void createDirectory(String dir) {
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
            log.info("Directory created: {}", dir);
        } else {
            log.info("Directory already exists: {}", dir);
        }
    }

    private String getCustomName(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf("."));
        String customName = UUID.randomUUID().toString();
        return customName + extension;
    }

    private String uploadImageFile(MultipartFile file, String dir) {
        if (!file.isEmpty()) {
            String originalName = file.getOriginalFilename();
            String customName = getCustomName(originalName);
            log.info("Uploading to directory: {}", dir);
            try (FileOutputStream fos = new FileOutputStream(dir + File.separator + customName)) {
                fos.write(file.getBytes());
                log.info("File uploaded: {}", customName);
                return customName;
            } catch (Exception e) {
                log.error("Error uploading image: {}", e.getMessage());
                throw new RuntimeException("Image upload failed", e);
            }
        }
        throw new RuntimeException("No image found to upload");
    }

    private byte[] getImageFile(String dir, String fileName) {
        String location = dir + File.separator + fileName;

        try (FileInputStream fis = new FileInputStream(location)) {
            return fis.readAllBytes();
        } catch (Exception e) {
            log.error("Error retrieving image: {}", e.getMessage());
            throw new RuntimeException("Image not found", e);
        }
    }
}