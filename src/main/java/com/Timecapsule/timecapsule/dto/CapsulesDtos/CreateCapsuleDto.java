package com.Timecapsule.timecapsule.dto.CapsulesDtos;


import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateCapsuleDto {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    private Boolean isShared;

    private Boolean isPrivate;

    private String CapsuleAvatar;

    private LocalDate closeDate;
}
