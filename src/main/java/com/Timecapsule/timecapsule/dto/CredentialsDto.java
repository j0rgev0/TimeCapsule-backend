package com.Timecapsule.timecapsule.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CredentialsDto {

    @NotBlank(message = "Email is required")
    private String email;

    @Size(min = 1, message = "Password is required")
    private char[] password;
}
