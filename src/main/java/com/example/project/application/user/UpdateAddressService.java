package com.example.project.application.user;

import com.example.project.domain.user.exception.AddressNotFoundException;
import com.example.project.domain.user.exception.UserMismatchException;
import com.example.project.application.user.usecase.UpdateAddressCommand;
import com.example.project.application.user.usecase.UpdateAddressUseCase;
import com.example.project.domain.user.model.Address;
import com.example.project.domain.user.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateAddressService implements UpdateAddressUseCase {

  private final AddressRepository addressRepository;

  @Override
  @Transactional
  public Address apply(UpdateAddressCommand command) {
    Address address = findAddress(command.addressId());

    if (!address.isSameUser(command.userId())) {
      throw new UserMismatchException();
    }

    // 기존 default address를 false로 변경
    if (!address.isDefault() && command.isDefault()) {
      addressRepository.updateDefaultAddress(command.userId());
    }

    address.updateAddress(
        command.zipcode(),
        command.addressMain(),
        command.addressSub(),
        command.isDefault());

    return address;
  }

  private Address findAddress(Long addressId) {
    return addressRepository.findById(addressId)
                            .orElseThrow(AddressNotFoundException::new);
  }
}
