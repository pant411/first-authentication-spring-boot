package com.project.authapi.users.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.authapi.handlers.responses.models.ResponseHandler;
import com.project.authapi.users.models.User;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

  @GetMapping("/me")
  public ResponseEntity<Object> authenticatedUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    User currentUser = (User) authentication.getPrincipal();
    return ResponseHandler.generateResponse("Ok", HttpStatus.OK, currentUser);
  }
}
