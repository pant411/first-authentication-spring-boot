package com.project.authapi.auth.dtos;

import com.project.authapi.users.models.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern.Flag;
import lombok.Data;
import jakarta.validation.constraints.Size;

@Data
public class RegisterDto {
  @NotEmpty(message = "The full name is required.")
  @Size(min = 2, max = 100, message = "The length of full name must be between 2 and 100 characters.")
  private String fullname;

  @NotEmpty(message = "The email address is required.")
  @Email(message = "The email address is invalid.", flags = { Flag.CASE_INSENSITIVE })
  private String email;

  @NotEmpty(message = "The password is required.")
  private String password;
  
  public User toUser() {
    return new User()
        .setFullname(fullname)
        .setEmail(email)
        .setPassword(password);
  }
}
