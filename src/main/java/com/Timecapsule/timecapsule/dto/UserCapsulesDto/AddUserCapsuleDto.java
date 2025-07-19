package com.Timecapsule.timecapsule.dto.UserCapsulesDto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddUserCapsuleDto {

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "CapsuleId is required")
    private UUID capsuleId;

}
