package com.project.authapi.auth.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.authapi.auth.dtos.LoginDto;
import com.project.authapi.exceptions.caseExceptions.UnauthorizedException;
import com.project.authapi.users.models.User;
import com.project.authapi.users.repositories.UserRepository;
import com.project.authapi.utils.jwt.JwtUtil;

@Service
public class AuthService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtUtil jwtUtil;

  public User register(User user) {
    Optional<User> existUser = this.userRepository.findByEmail(user.getEmail());
    if (existUser.isPresent()) {
      throw new UnauthorizedException("User exist");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public String login(LoginDto loginDto) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
    } catch (BadCredentialsException e) {
      throw new UnauthorizedException("Invalid username or password");
    }

    String token = jwtUtil.generateToken(loginDto.getEmail());
    return token;
  }
}
