package com.Timecapsule.timecapsule.dto.CapsulesDtos;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CapsuleDto {
    
    private UUID id;
    private UUID ownerId;
    private String title;
    private String description;
    private Boolean isShared;
    private Boolean isPrivate;
    private String CapsuleAvatar;
    private LocalDate createAt;
    private LocalDate closeDate;
}
