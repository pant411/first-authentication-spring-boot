package com.project.authapi.users.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.authapi.users.models.User;
import com.project.authapi.users.repositories.UserRepository;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public List<User> findAll() {
    return this.userRepository.findAll();
  }
}
