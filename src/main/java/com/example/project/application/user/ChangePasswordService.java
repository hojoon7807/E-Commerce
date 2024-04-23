package com.example.project.application.user;

import com.example.project.domain.user.exception.PasswordMismatchException;
import com.example.project.domain.user.exception.UserNotFoundException;
import com.example.project.application.user.usecase.ChangePasswordCommand;
import com.example.project.application.user.usecase.ChangePasswordUseCase;
import com.example.project.application.user.usecase.OneWayEncryptor;
import com.example.project.domain.user.model.User;
import com.example.project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChangePasswordService implements ChangePasswordUseCase {

  private final UserRepository userRepository;
  private final OneWayEncryptor oneWayEncryptor;

  @Override
  @Transactional
  public void accept(ChangePasswordCommand command) {
    User user = findUser(command.userId());

    checkPassword(command.originalPassword(), user.getPassword());
    user.changePassword(oneWayEncryptor.encode(command.newPassword()));
  }

  private void checkPassword(String rawPassword, String encodedPassword) {
    if (!oneWayEncryptor.match(rawPassword, encodedPassword)) {
      throw new PasswordMismatchException();
    }
  }

  private User findUser(Long userId) {
    return userRepository.findById(userId)
                         .orElseThrow(UserNotFoundException::new);
  }

}
