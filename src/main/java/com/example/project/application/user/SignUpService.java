package com.example.project.application.user;

import com.example.project.application.user.exception.DuplicatedEmailException;
import com.example.project.application.user.usecase.OneWayEncryptor;
import com.example.project.application.user.usecase.SignUpCommand;
import com.example.project.application.user.usecase.SignUpUseCase;
import com.example.project.domain.user.model.Address;
import com.example.project.domain.user.model.User;
import com.example.project.domain.user.model.UserRole;
import com.example.project.domain.user.model.UserStatus;
import com.example.project.domain.user.repository.AddressRepository;
import com.example.project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignUpService implements SignUpUseCase {

  private final UserRepository userRepository;
  private final AddressRepository addressRepository;
  private final OneWayEncryptor oneWayEncryptor;

  @Override
  @Transactional
  public User apply(SignUpCommand signUpCommand) {
    // 이메일 중복 확인
    checkEmailDuplicate(signUpCommand.email());

    // 패스워드 암호화
    String encodedPassword = oneWayEncryptor.encode(signUpCommand.password());
    // 개인 정보 암호화
    // 유저, 주소 저장
    User user = User.builder()
                    .email(signUpCommand.email())
                    .password(encodedPassword)
                    .username(signUpCommand.username())
                    .phoneNum(signUpCommand.phoneNum())
                    .userStatus(UserStatus.REQUEST_APPROVAL)
                    .userRole(UserRole.ROLE_BUYER)
                    .build();

    User savedUser = userRepository.save(user);

    Address address = Address.builder()
                             .zipcode(signUpCommand.zipcode())
                             .addressMain(signUpCommand.addressMain())
                             .addressSub(signUpCommand.addressSub())
                             .isDefault(true)
                             .user(savedUser)
                             .build();

    addressRepository.save(address);

    // TODO 이메일 검증 메일 전송

    return savedUser;
  }

  private void checkEmailDuplicate(String email) {
    if (userRepository.existsByEmail(email)) {
      throw new DuplicatedEmailException();
    }
  }
}
