package com.example.project.application.user;

import com.example.project.application.user.exception.UserNotFoundException;
import com.example.project.application.user.usecase.UpdateUserCommand;
import com.example.project.application.user.usecase.UpdateUserUseCase;
import com.example.project.domain.user.model.User;
import com.example.project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateUserService implements UpdateUserUseCase {

  private final UserRepository userRepository;


  @Override
  @Transactional
  public void accept(UpdateUserCommand command) {
    User user = findUser(command.userId());

    user.updateUser(command.phoneNum());
  }

  private User findUser(Long userId) {
    return userRepository.findById(userId)
                         .orElseThrow(UserNotFoundException::new);
  }
}
