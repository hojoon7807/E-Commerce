package com.example.project.application.user;

import com.example.project.application.common.TwoWayEncryptor;
import com.example.project.application.user.usecase.GetMyPageUseCase;
import com.example.project.application.user.usecase.MyPageInfo;
import com.example.project.domain.user.model.Address;
import com.example.project.domain.user.model.User;
import com.example.project.domain.user.repository.AddressRepository;
import com.example.project.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMyPageService implements GetMyPageUseCase {

  private final UserRepository userRepository;
  private final AddressRepository addressRepository;
  private final TwoWayEncryptor twoWayEncryptor;

  @Override
  public MyPageInfo apply(Long userId) {
    User user = findUser(userId);
    List<Address> addresses = findAddresses(user.getUserId());
    return MyPageInfo.from(decryptUser(user), decryptAddresses(addresses));
  }

  private User findUser(Long userId) {
    return userRepository.findById(userId)
                         .orElseThrow(() -> new IllegalArgumentException("User not found"));
  }

  private User decryptUser(User user) {
    return User.builder()
               .userId(user.getUserId())
               .username(twoWayEncryptor.decrypt(user.getUsername()))
               .email(twoWayEncryptor.decrypt(user.getEmail()))
               .phoneNum(twoWayEncryptor.decrypt(user.getPhoneNum()))
               .build();
  }

  private List<Address> findAddresses(Long userId) {
    return addressRepository.findAllByUserId(userId);
  }

  private List<Address> decryptAddresses(List<Address> addresses) {
    return addresses.stream()
                    .map(address -> Address.builder()
                                           .addressId(address.getAddressId())
                                           .zipcode(twoWayEncryptor.decrypt(address.getZipcode()))
                                           .addressMain(
                                               twoWayEncryptor.decrypt(address.getAddressMain()))
                                           .addressSub(
                                               twoWayEncryptor.decrypt(address.getAddressSub()))
                                           .isDefault(address.isDefault())
                                           .build())
                    .toList();
  }
}
