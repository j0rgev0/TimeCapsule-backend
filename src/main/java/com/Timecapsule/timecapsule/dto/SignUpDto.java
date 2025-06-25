package com.Timecapsule.timecapsule.dto;


import jakarta.validation.constraints.Email;
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
public class SignUpDto {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Name is required")
    @Email(message = "Email is not valid")
    private String email;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

}
