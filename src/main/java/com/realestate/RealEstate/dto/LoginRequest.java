package com.realestate.RealEstate.dto;



import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Email is must required")
    private String email;

    @NotBlank(message = "Password is must required")
    private String password;
}
