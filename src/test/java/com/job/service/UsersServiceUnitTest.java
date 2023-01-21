package com.job.service;

import com.job.repository.UsersRepository;
import com.job.resource.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class UsersServiceUnitTest {
  @Mock
  private UsersRepository usersRepository;
  @InjectMocks
  private UsersServiceImpl usersService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void should_findExistingUserById_when_userIdIsProvided() {
    // given
    long userId = 1L;
    User user = User.builder()
            .userId(userId)
            .build();

    // when
    usersService.getUser(user);

    // then
    verify(usersRepository).findById(userId);
  }

  @Test
  void should_findExistingUserByEmail_when_userIdIsMissing() {
    // given
    String email = "joe@email.com";
    User user = User.builder()
            .email(email)
            .build();

    // when
    usersService.getUser(user);

    // then
    verify(usersRepository).findByEmail(email);
  }

  @Test
  void should_saveNewUser_when_userWithIdDoesntExist() {
    // given
    long userId = 1L;
    User user = User.builder()
            .userId(userId)
            .build();

    // when
    usersService.getUser(user);

    // then
    verify(usersRepository).findById(userId);
    verify(usersRepository).save(user);
  }

  @Test
  void should_saveNewUser_when_missingIdAndNoUserWithEmailFound() {
    // given
    String email = "joe@email.com";
    User user = User.builder()
            .email(email)
            .build();

    // when
    usersService.getUser(user);

    // then
    verify(usersRepository).findByEmail(email);
    verify(usersRepository).save(user);
  }
}
