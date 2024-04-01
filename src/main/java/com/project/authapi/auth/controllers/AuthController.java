package com.project.authapi.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.authapi.auth.dtos.RegisterDto;
import com.project.authapi.auth.services.AuthService;
import com.project.authapi.users.models.User;

import jakarta.validation.Valid;


@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {
  
  private final AuthService authService;

  @Autowired
  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/register")
  public ResponseEntity<User> register(@Valid @RequestBody RegisterDto registerDto) {
    User newUser = this.authService.register(registerDto.toUser());
    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
  }
}
