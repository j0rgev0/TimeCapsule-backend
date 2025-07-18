package com.Timecapsule.timecapsule.dto.UserCapsulesDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserCapsuleDto {

    private UUID userId;
    private UUID capsuleId;
}
