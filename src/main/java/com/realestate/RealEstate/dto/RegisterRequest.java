package com.realestate.RealEstate.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    @NotBlank(message = "Password is required")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{8,}$",
            message = "Password must be at least 8 characters and contain at least one number"
    )
    private String password;

    @Pattern(regexp = "BUYER|SELLER|AGENT", message = "Role must be BUYER, SELLER, or AGENT")
    private String role;

}