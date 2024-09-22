package com.ApparelAvenue.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ApparelAvenue.backend.model.Banner;

public interface BannerRepository extends JpaRepository<Banner, String> {
    
}
