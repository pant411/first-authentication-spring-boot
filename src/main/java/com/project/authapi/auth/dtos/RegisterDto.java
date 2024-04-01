package com.project.authapi.auth.dtos;

import com.project.authapi.users.models.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import jakarta.validation.constraints.Size;

@Data
public class RegisterDto {
  @NotEmpty(message = "Full name is required")
  @Size(min = 2, max = 100, message = "Full name must be between 2 and 100 characters")
  private String fullname;

  @NotEmpty(message = "Email address is required")
  @Email(message = "Invalid email address")
  private String email;

  @NotEmpty(message = "Password is required")
  @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
  private String password;
  
  public User toUser() {
    return new User()
        .setFullname(fullname)
        .setEmail(email)
        .setPassword(password);
  }
}
