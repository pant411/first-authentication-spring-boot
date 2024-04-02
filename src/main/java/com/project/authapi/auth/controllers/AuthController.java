package com.project.authapi.auth.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.authapi.auth.dtos.LoginDto;
import com.project.authapi.auth.dtos.RegisterDto;
import com.project.authapi.auth.services.AuthService;
import com.project.authapi.handlers.responses.models.ResponseHandler;
import com.project.authapi.users.models.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {
  @Autowired
  private AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<Object> register(@Valid @RequestBody RegisterDto registerDto) {
    User newUser = this.authService.register(registerDto.toUser());
    return ResponseHandler.generateResponse("Register sucessfully", HttpStatus.CREATED, newUser);
  }

  @PostMapping("/login")
  public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginDto loginDto) {
    String token = this.authService.login(loginDto);
    HashMap<String, String> responseData = new HashMap<String, String>();
    responseData.put("accessToken", token);
    return ResponseHandler.generateResponse("Login sucessfully", HttpStatus.CREATED, responseData);
  }
}
