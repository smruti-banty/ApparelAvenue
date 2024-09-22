package com.ApparelAvenue.backend.dto;

import com.ApparelAvenue.backend.constant.Section;

import lombok.Data;
@Data
public class BannerUpdateRequestDto {
    private String bannerTitle;
    private String bannerImage;
    private Section section;
}