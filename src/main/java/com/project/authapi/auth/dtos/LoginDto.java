package com.project.authapi.auth.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {
  @NotEmpty(message = "Email address is required")
  @Email(message = "Invalid email address")
  private String email;

  @NotEmpty(message = "Password is required")
  @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
  private String password;
}
