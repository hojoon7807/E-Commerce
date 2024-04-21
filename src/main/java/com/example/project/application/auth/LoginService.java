package com.example.project.application.auth;

import com.example.project.application.auth.exception.EmailVerificationNotCompletedException;
import com.example.project.application.auth.exception.LoginFailException;
import com.example.project.application.auth.usecase.LoginCommand;
import com.example.project.application.auth.usecase.LoginUseCase;
import com.example.project.application.common.TwoWayEncryptor;
import com.example.project.application.user.usecase.OneWayEncryptor;
import com.example.project.domain.user.model.User;
import com.example.project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

  private final UserRepository userRepository;
  private final OneWayEncryptor oneWayEncryptor;
  private final TwoWayEncryptor twoWayEncryptor;

  @Override
  public User apply(LoginCommand command) {
    User user = findUser(twoWayEncryptor.encrypt(command.email()));
    checkPassword(command.password(), user.getPassword());

    // 이메일 인증이 완료되지 않은 경우 로그인 불가
    if (!user.isApproved()) {
      throw new EmailVerificationNotCompletedException();
    }

    return user;
  }

  private User findUser(String email) {
    return userRepository.findByEmail(email)
                         .orElseThrow(LoginFailException::new);
  }

  private void checkPassword(String rawPassword, String encodedPassword) {
    if (!oneWayEncryptor.match(rawPassword, encodedPassword)) {
      throw new LoginFailException();
    }
  }
}
