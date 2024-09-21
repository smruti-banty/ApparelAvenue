package com.ApparelAvenue.backend.dto.helper;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Auditing {
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime updatedDate;
    private String updatedBy;
}
