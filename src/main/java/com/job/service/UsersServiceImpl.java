package com.job.service;

import com.job.repository.UsersRepository;
import com.job.resource.User;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@Singleton
@RequiredArgsConstructor
class UsersServiceImpl implements UsersService {
  private final UsersRepository usersRepository;

  @Override
  public User getUser(User user) {
    User foundUser;
    if (Objects.nonNull(user.getUserId())) {
      foundUser = findUserById(user.getUserId())
              .orElseGet(() -> saveUser(user));
    } else {
      foundUser = findUserByEmail(user.getEmail())
              .orElseGet(() -> saveUser(user));
    }
    return foundUser;
  }

  private Optional<User> findUserById(Long id) {
    return usersRepository.findById(id);
  }

  private User saveUser(final User user) {
    return usersRepository.save(user);
  }

  private Optional<User> findUserByEmail(String email) {
    return usersRepository.findByEmail(email);
  }
}
