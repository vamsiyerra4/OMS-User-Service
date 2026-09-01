package com.orderms.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {

    @NotBlank(message = "Name is required")
    @Size(min = 3,message = "Too short! Name must be between 3 to 50 characters")
    private String name;

    @Email(message = "Invalid format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Password required")
    @Size(min = 8,max = 20,message = "Password must be between 8 to 20 characters")
    private String password;


}
