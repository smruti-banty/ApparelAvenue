package com.ApparelAvenue.backend.mapper;

import org.springframework.beans.BeanUtils;

import com.ApparelAvenue.backend.dto.BannerRequestDto;
import com.ApparelAvenue.backend.model.Banner;

public final class BannerMapper {
    private BannerMapper() {
    }

    public static Banner convertToBanner(BannerRequestDto dto) {
        Banner banner = new Banner();
        BeanUtils.copyProperties(dto, banner);
        return banner;
    }    
}
