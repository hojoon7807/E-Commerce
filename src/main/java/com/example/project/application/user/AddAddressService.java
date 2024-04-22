package com.example.project.application.user;

import com.example.project.application.common.TwoWayEncryptor;
import com.example.project.application.user.exception.UserNotFoundException;
import com.example.project.application.user.usecase.AddAddressCommand;
import com.example.project.application.user.usecase.AddAddressUseCase;
import com.example.project.domain.user.model.Address;
import com.example.project.domain.user.model.User;
import com.example.project.domain.user.repository.AddressRepository;
import com.example.project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddAddressService implements AddAddressUseCase {

  private final AddressRepository addressRepository;
  private final UserRepository userRepository;
  private final TwoWayEncryptor twoWayEncryptor;

  @Override
  public void accept(AddAddressCommand command) {
    User user = findUser(command.userId());
    Address address = Address.builder()
                             .zipcode(twoWayEncryptor.encrypt(command.zipcode()))
                             .addressMain(twoWayEncryptor.encrypt(command.addressMain()))
                             .addressSub(twoWayEncryptor.encrypt(command.addressSub()))
                             .isDefault(false)
                             .user(user)
                             .build();

    addressRepository.save(address);
  }

  private User findUser(Long userId) {
    return userRepository.findById(userId)
                         .orElseThrow(UserNotFoundException::new);
  }
}
