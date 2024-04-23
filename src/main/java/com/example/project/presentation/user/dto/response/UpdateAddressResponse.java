package com.example.project.presentation.user.dto.response;

import com.example.project.domain.user.model.Address;

public record UpdateAddressResponse(
    Long addressId,
    String zipcode,
    String addressMain,
    String addressSub,
    boolean isDefault
) {

  public static UpdateAddressResponse of(Address address) {
    return new UpdateAddressResponse(
        address.getAddressId(),
        address.getZipcode(),
        address.getAddressMain(),
        address.getAddressSub(),
        address.isDefault()
    );
  }
}
