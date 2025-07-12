package com.Timecapsule.timecapsule.dto.ImagesDto;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CreateImageDto {

    @NotBlank(message = "Url is required")
    private String url;

    @NotBlank(message = "PublicId is required")
    private String publicId;
}
