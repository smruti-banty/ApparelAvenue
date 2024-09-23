package com.ApparelAvenue.backend.dto;

import org.springframework.web.multipart.MultipartFile;

import com.ApparelAvenue.backend.constant.Section;

import lombok.Data;
@Data
public class BannerUpdateRequestDto {
    private String bannerTitle;
    private MultipartFile bannerImage;
    private Section section;
}