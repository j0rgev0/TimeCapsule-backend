package com.Timecapsule.timecapsule.dto.ImagesDto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ImagesDto {

    private UUID id;
    private String url;
    private String publicId;
    private UUID capsuleId;
}
