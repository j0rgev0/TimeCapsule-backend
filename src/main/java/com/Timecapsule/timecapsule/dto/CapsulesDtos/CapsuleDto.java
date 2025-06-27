package com.Timecapsule.timecapsule.dto.CapsulesDtos;


import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
