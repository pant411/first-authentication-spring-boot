package com.project.authapi.users.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.authapi.users.models.UserModel;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer> {
  Optional<UserModel> findByEmail(String email);
}
