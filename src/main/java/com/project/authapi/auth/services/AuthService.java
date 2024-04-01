package com.project.authapi.auth.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.project.authapi.users.models.User;
import com.project.authapi.users.repositories.UserRepository;

@Service
public class AuthService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public User register(User user) {
    Optional<User> existUser = this.userRepository.findByEmail(user.getEmail());
    if (existUser.isPresent()) {
       throw new ResponseStatusException(HttpStatus.CONFLICT, "User exist");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }
}
