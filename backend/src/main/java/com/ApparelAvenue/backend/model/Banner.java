package com.ApparelAvenue.backend.model;

import com.ApparelAvenue.backend.constant.Section;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Banner {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String bannerId;
    private String bannerTitle;
    private String bannerImage;
    @Enumerated(value = EnumType.STRING)
    private Section section;
}