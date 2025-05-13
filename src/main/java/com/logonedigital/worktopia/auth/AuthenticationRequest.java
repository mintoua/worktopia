package com.logonedigital.worktopia.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record AuthenticationRequest(
        @NotEmpty(message = "Email is mandatory")
        @NotBlank(message = "Email is mandatory")
        @Email(message = "Email not well formatted")
        String email,
        @NotEmpty(message = "Password is mandatory")
        @NotBlank(message = "Password is mandatory")
        @Size(min = 8, message = "Password should be 8 characters minimum")
        String password
)
{


}
