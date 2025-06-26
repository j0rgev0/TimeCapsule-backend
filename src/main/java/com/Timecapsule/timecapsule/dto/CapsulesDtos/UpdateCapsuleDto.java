
package com.Timecapsule.timecapsule.dto.CapsulesDtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateCapsuleDto {

    private String title;

    private String description;

    private Boolean isShared;

    private Boolean isPrivate;

    private String CapsuleAvatar;

    private LocalDate closeDate;
}