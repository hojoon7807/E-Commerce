package com.example.project.application.user.usecase;

import com.example.project.domain.user.model.Address;
import com.example.project.domain.user.model.User;
import java.util.List;

public record MyPageInfo(
    String username,
    String email,
    String phoneNum,
    List<MyPageAddress> addresses
) {

  private record MyPageAddress(
      Long addressId,
      String zipcode,
      String addressMain,
      String addressSub,
      boolean isDefault
  ) {

    private MyPageAddress(Address address) {
      this(address.getAddressId(), address.getZipcode(), address.getAddressMain(), address.getAddressSub(),
          address.isDefault());
    }
  }

  public static MyPageInfo from(User user, List<Address> address) {
    List<MyPageAddress> addresses = address.stream().map(MyPageAddress::new).toList();

    return new MyPageInfo(user.getUsername(), user.getEmail(), user.getPhoneNum(), addresses);
  }
}
