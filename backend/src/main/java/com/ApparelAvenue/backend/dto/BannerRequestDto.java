package com.ApparelAvenue.backend.dto;

import com.ApparelAvenue.backend.constant.Section;

import lombok.Data;
@Data
public class BannerRequestDto {
    private String bannerImage;
    private Section section;
}