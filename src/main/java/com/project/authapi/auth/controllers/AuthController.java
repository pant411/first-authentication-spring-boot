package com.project.authapi.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.authapi.auth.dtos.RegisterDto;
import com.project.authapi.auth.services.AuthService;
import com.project.authapi.users.models.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/api/auth")
public class AuthController {
  @Autowired
  private AuthService authService;

  @PostMapping(path="/register")
  public ResponseEntity<User> register(@Valid @RequestBody RegisterDto registerDto) {
    System.out.println(registerDto);
    User createdUser = authService.register(registerDto.toUser());
    return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
  }
}
