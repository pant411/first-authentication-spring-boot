package com.project.authapi.users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.authapi.handlers.responses.models.ResponseHandler;
import com.project.authapi.users.models.User;
import com.project.authapi.users.services.UserService;

@RestController
@RequestMapping(path = "/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/me")
  public ResponseEntity<Object> authenticatedUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    User currentUser = (User) authentication.getPrincipal();
    return ResponseHandler.generateResponse("Ok", HttpStatus.OK, currentUser);
  }

  @GetMapping
  public ResponseEntity<Object> findAll() {
    List<User> users = this.userService.findAll();
    return users.size() > 0 ? ResponseHandler.generateResponse("Found Users", HttpStatus.OK, users)
        : ResponseHandler.generateResponse("Not found users", HttpStatus.NOT_FOUND, users);
  }
}
